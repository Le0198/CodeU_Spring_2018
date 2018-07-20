<%--
Copyright 2017 Google Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
--%>
<%@ page import="java.util.List" %>
<%@ page import="codeu.model.data.Conversation" %>
<%@ page import="codeu.model.data.Message" %>
<%@ page import="codeu.model.data.Gif" %>
<%@ page import="codeu.model.data.User" %>
<%@ page import="codeu.model.store.basic.UserStore" %>
<%
Conversation conversation = (Conversation) request.getAttribute("conversation");
List<Message> messages = (List<Message>) request.getAttribute("messages");
%>

<!DOCTYPE html>
<html>
    <head>
        <title><%= conversation.getTitle() %></title>
        <%@ include file="meta.jsp" %>
        <script src='https://cloud.tinymce.com/stable/tinymce.min.js'></script>
        <script>
        tinymce.init({
            selector: '#mytextarea'
        });
        </script>
        <script>
        // scroll the chat div to the bottom
        function scrollChat() {
            var chatDiv = document.getElementById('chat');
            chatDiv.scrollTop = chatDiv.scrollHeight;
        };
        </script>
    </head>
    <body onload="scrollChat()" id="convo-body">

        <%@ include file="header.jsp" %>

        <div class="row">
            <%@ include file="convo-sidebar.jsp" %>

            <div class="convo-con col-sm-9">

                <div class="chat-title">
                    <h1><%= conversation.getTitle() %>
                    <a href="">&#8635;</a></h1>
                </div>

                <div id="chat">
                    <ul>
                        <% for (Message message : messages) {
                            User userAuthor = UserStore.getInstance().getUser(message.getAuthorId());
                            String currentUser = (String) request.getSession().getAttribute("user");
                            String author = userAuthor.getName();
                            String ownerClass = "";

                            if (author.equals(currentUser)) {
                                ownerClass = "my-message";
                            }
                            %>
                            <li class="<%= ownerClass %>">
                                <% if (!author.equals(currentUser)) { %>
                                    <div class="author-content">
                                        <img src="<%= userAuthor.getPicture()%>" alt="User profile picture" class="profile">
                                        <strong class="author"><%= author %></strong>
                                    </div>
                                <% } %>
                                <div class="message-content">
                                    <% if (message.getType().equals("image")) { %>
                                         <img src="<%= message.getContent() %>" alt="Reaction gif" class="chat-image">
                                    <% } else { %>
                                        <%= message.getContent() %>
                                    <% } %>
                                </div>
                                <% if (author.equals(currentUser)) { %>
                                    <div class="author-content">
                                        <strong class="author"><%= author %></strong>
                                        <img src="<%= userAuthor.getPicture()%>" alt="User profile picture" class="profile">
                                    </div>
                                <% } %>
                            </li>
                        <% } %>
                    </ul>
                </div>

                    <div id="chat-actions">
                        <% if (request.getSession().getAttribute("user") != null) { %>

                            <div class="gif-section">
                                <div class="container-fluid">

                                    <% List<Gif> gifs = (List<Gif>) request.getAttribute("gifs");
                                    if(gifs == null || gifs.isEmpty()){
                                        %>
                                        <p>Add some <a href="/gifs">gifs</a> to get started.</p>

                                        <% } else { %>

                                        <%
                                        int numOfCols = 3;
                                        int rowCount = 0;
                                        int bootstrapColWidth = 12 / numOfCols;
                                        int fieldCount = -1;

                                        for(Gif gif : gifs){
                                            fieldCount++;
                                        }

                                        %>
                                        <div class="row">
                                            <% for(Gif gif2 : gifs){ %>
                                                <div class="col-sm-<%= bootstrapColWidth %> organization">
                                                    <div class="captioned-gif">
                                                        <h5><%= gif2.getTag() %></h5>
                                                        <img src="<%= gif2.getURL() %>" alt="<%= gif2.getTag() %>" width="100%">
                                                    </div>
                                                </div>

                                                <% rowCount++;
                                                if(rowCount % numOfCols == 0) {  %>
                                                    </div><div class="row">
                                                <% } %>
                                            <% } %>
                                        </div>
                                <% }%>
                            </div>
                        </div>

                        <form action="/chat/<%= conversation.getTitle() %>" method="POST" id="chatForm">
                            <textarea type="text" name="message" id="textarea" placeholder="Type your message"></textarea>
                            <br/>
                            <input type="text" name="type" id="type">
                                <div class="button-con">
                                    <button type="submit" class="text-center" id="send">Send</button>
                                    <button class="text-center gif" id="gif"  type="button">Gif</button>
                                </div>
                                <br/>
                        </form>
                    <% } else { %>
                        <p class="text-center"><br><br><a href="/login" class="hover">Login</a> to send a message.<br><br></p>
                    <% } %>
                </div><!-- chat-actions -->
            </div><!-- col -->
        </div><!-- row -->

        <%@ include file="footer-meta.jsp" %>

    </body>
</html>
