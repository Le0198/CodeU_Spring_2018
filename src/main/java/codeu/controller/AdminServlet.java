// Copyright 2017 Google Inc.
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
import codeu.model.data.Message;
import codeu.model.data.User;
import codeu.model.store.basic.UserStore;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/** Servlet class responsible for the admin page. */
public class AdminServlet extends HttpServlet {

	/** Store class that gives access to Users. */
	private UserStore userStore;
	private ConversationStore conversationStore;
	private MessageStore messageStore;
	private final String UPLOAD_DIRECTORY = "C:/uploads";

	/**
	 * Set up state for handling login-related requests. This method is only called
	 * when running in a server, not when running in a test.
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		setStores(UserStore.getInstance(), ConversationStore.getInstance(), MessageStore.getInstance());
	}

	/**
	 * Sets the UserStore used by this servlet. This function provides a common
	 * setup method for use by the test framework or the servlet's init() function.
	 */
	void setStores(UserStore userStore, ConversationStore conversationStore, MessageStore messageStore) {
		this.userStore = userStore;
		this.conversationStore = conversationStore;
		this.messageStore = messageStore;
	}

	/**
	 * This function fires when a user requests the /admin URL. It simply forwards
	 * the request to admin.jsp.
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		request.setAttribute("numUsers", userStore.numUsers());
		request.setAttribute("numConvos", conversationStore.getNumConvos());
		request.setAttribute("numMessages", messageStore.numMessages());
		request.setAttribute("newestUser", userStore.newestUser());
		request.setAttribute("mostActive", getUser(messageStore.getMostActive()));
		request.setAttribute("mostWordy", getUser(messageStore.getWordiest()));
		request.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(request, response);

	}

	/**
	 * Get's the highest user (if there is one)
	 * 
	 * @return the username of the highest user
	 */
	private String getUser(UUID u) {

		// Null if there are no users
		if (u == null) {
			return "No users yet";
		}

		User highestUser = userStore.getUser(u);

		// This should never be null
		if (highestUser == null) {
			return "User not found";
		}

		return highestUser.getName();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// Make sure a file was uploaded
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				// Create a list if multiple files uploaded
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

				//Loop through the file(s)
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						File curFile = new File(UPLOAD_DIRECTORY + File.separator + name);
						
						// Add the imported data as a conversation
						addImportedData(item.getString(), name, request, response);

						item.write(curFile);
						
					}
				}
				request.setAttribute("message", "File Uploaded Successfully");
			} catch (Exception ex) {
				request.setAttribute("message", "File Upload Failed due to " + ex);
			}
		} else {
			request.setAttribute("message", "Sorry this Servlet only handles file upload request");
		}

		response.sendRedirect("/admin");
	}

	/**
	 * Method to add the data sent in through a text file to a conversation
	 * 
	 * @param newFile
	 * @param name
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void addImportedData(String newFile, String name, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// If the file is null, do nothing
		if (newFile != null) {
			
			String username = (String) request.getSession().getAttribute("user");
			User user = userStore.getUser(username); // Get the current user object
			
			// conversation title is already taken, just go into that conversation instead  of creating a new one
			if (conversationStore.isTitleTaken(name)) {
				response.sendRedirect("/chat/" + name);
				return;
			}
			
			// Create the new conversation
			Conversation conversation = new Conversation(UUID.randomUUID(), user.getId(), 
					name, Instant.now());

			// Add the conversation
			conversationStore.addConversation(conversation);
		
			Scanner sc = new Scanner(newFile);
			
			//System.out.println("\n\n\n\n\n\n\n"+ sc.hasNextLine() + "\n\n\n\n\n");
			// Loop thorugh each line of the txt file
			while (sc.hasNextLine()) {
				
				Scanner lineScanner = new Scanner(sc.nextLine());
				
				// Get the name, minus the semicolon
				String curName = lineScanner.next();
				curName = curName.substring(0, curName.length() - 1);
				
				//System.out.println("\n\n\n\n\n\n\n"+ curName + "\n\n\n\n\n");

				StringBuilder sb = new StringBuilder();
				
				// Get the rest of the message
				while (lineScanner.hasNext()) {
					sb.append(lineScanner.next());
					sb.append(" ");
				}
				
				// If the user does not exist, create one with a dummy password
				if (userStore.getUser(curName) == null) {
					User newUser = new User(UUID.randomUUID(), curName, BCrypt.hashpw("password", BCrypt.gensalt()),
							Instant.now());
					userStore.addUser(newUser);
				}
				
				// Create the message
				 Message message = new Message(UUID.randomUUID(), conversation.getId(), 
						 userStore.getUser(curName).getId(), sb.toString(), Instant.now());
				 
				 // Add to the store of messages
				 messageStore.addMessage(message);
				 lineScanner.close();
			}
			sc.close();
			
			// redirect to a GET request
		    response.sendRedirect("/chat/" + name);
		}
		

	    

	}
}
