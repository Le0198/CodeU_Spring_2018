// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package codeu.controller;

import codeu.model.data.Conversation;
import codeu.model.data.Gif;
import codeu.model.data.Message;
import codeu.model.data.User;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.GifStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Imports for sanitizing the message
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.jsoup.nodes.Document.OutputSettings;

// Imports for converting markdown to html
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.ins.InsExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;

// Import for correctly parsing emojis
import com.vdurmont.emoji.EmojiParser;

/** Servlet class responsible for the chat page. */
public class ChatServlet extends HttpServlet {

    /** Store class that gives access to Conversations. */
    private ConversationStore conversationStore;

    /** Store class that gives access to Messages. */
    private MessageStore messageStore;

    /** Store class that gives access to Users. */
    private UserStore userStore;

    /** Store class that gives access to Gifs. */
    private GifStore gifStore;


    /** Set up state for handling chat requests. */
    @Override
    public void init() throws ServletException {
        super.init();
        setConversationStore(ConversationStore.getInstance());
        setMessageStore(MessageStore.getInstance());
        setUserStore(UserStore.getInstance());
        setGifStore(GifStore.getInstance());
    }

    /**
    * Sets the GifStore used by this servlet. This function provides a common setup method for use
    * by the test framework or the servlet's init() function.
    */
    void setGifStore(GifStore gifStore) {
        this.gifStore = gifStore;
    }


    /**
    * Sets the ConversationStore used by this servlet. This function provides a common setup method
    * for use by the test framework or the servlet's init() function.
    */
    void setConversationStore(ConversationStore conversationStore) {
        this.conversationStore = conversationStore;
    }

    /**
    * Sets the MessageStore used by this servlet. This function provides a common setup method for
    * use by the test framework or the servlet's init() function.
    */
    void setMessageStore(MessageStore messageStore) {
        this.messageStore = messageStore;
    }

    /**
    * Sets the UserStore used by this servlet. This function provides a common setup method for use
    * by the test framework or the servlet's init() function.
    */
    void setUserStore(UserStore userStore) {
        this.userStore = userStore;
    }

    /**
    * This function fires when a user navigates to the chat page. It gets the conversation title from
    * the URL, finds the corresponding Conversation, and fetches the messages in that Conversation.
    * It then forwards to chat.jsp for rendering.
    */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestUrl = request.getRequestURI();
        String conversationTitle = requestUrl.substring("/chat/".length());

        Conversation conversation = conversationStore.getConversationWithTitle(conversationTitle);

        if (conversation == null) {
            // couldn't find conversation, redirect to conversation list
            System.out.println("Conversation was null: " + conversationTitle);
            response.sendRedirect("/conversations");
            return;
        }

        List<Conversation> conversations = conversationStore.getAllConversations();

        UUID conversationId = conversation.getId();
        List<Message> messages = messageStore.getMessagesInConversation(conversationId);
        List<Gif> gifs = gifStore.getAllGifs();

        request.setAttribute("gifs", gifs);
        request.setAttribute("conversation", conversation);
        request.setAttribute("conversations", conversations);
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/WEB-INF/view/chat.jsp").forward(request, response);
    }

    /**
    * This function fires when a user submits the form on the chat page. It gets the logged-in
    * username from the session, the conversation title from the URL, and the chat message from the
    * submitted form data. It creates a new Message from that data, adds it to the model, and then
    * redirects back to the chat page.
    */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {

        String username = (String) request.getSession().getAttribute("user");
        if (username == null) {
            // user is not logged in, don't let them add a message
            response.sendRedirect("/login");
            return;
        }

        User user = userStore.getUser(username);
        if (user == null) {
            // user was not found, don't let them add a message
            response.sendRedirect("/login");
            return;
        }

        String requestUrl = request.getRequestURI();
        String conversationTitle = requestUrl.substring("/chat/".length());

        Conversation conversation = conversationStore.getConversationWithTitle(conversationTitle);
        if (conversation == null) {
            // couldn't find conversation, redirect to conversation list
            response.sendRedirect("/conversations");
            return;
        }

        // Clean the original message
        String messageContent = request.getParameter("message");
        messageContent = clean(messageContent);

        // The type of the message
        String type = request.getParameter("type");

        // Build the message
        Message message =
        new Message(
        UUID.randomUUID(),
        conversation.getId(),
        user.getId(),
        type,
        messageContent,
        Instant.now());

        messageStore.addMessage(message);

        // redirect to a GET request
        response.sendRedirect("/chat/" + conversationTitle);
    }

    /**
    * This method removes potentially harmful tags and converts
    * Markdown and emojis to HTML
    * Shout out to Rebecca Ruvalcaba for suggesting Flexmark
    **/
    private String clean(String originalMessage) {

        // Get rid of all HTML tags except the ones we wand
        Whitelist allowedTags = Whitelist.none();
        allowedTags.addTags("ins", "del", "strong", "em", "sub", "sup");

        // allow strikethrough and underline in markdown
        MutableDataSet options = new MutableDataSet();
        options.set(Parser.EXTENSIONS, Arrays.asList(InsExtension.create(),
        StrikethroughExtension.create()));

        // parse the markdown to html
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Node document  = parser.parse(originalMessage);
        String markdownContent = renderer.render(document);
        markdownContent = markdownContent.replaceAll("\n", "");
        OutputSettings settings = new OutputSettings();
        settings.prettyPrint(false);

        // Sanitize using Jsoup
        String finalMessageContent = Jsoup.clean(markdownContent, "", allowedTags, settings);

        // Make sure emojis end up being readable in  HTML
        finalMessageContent = EmojiParser.parseToUnicode(finalMessageContent);
        finalMessageContent = EmojiParser.parseToHtmlDecimal(finalMessageContent);

        return finalMessageContent;
    }
}
