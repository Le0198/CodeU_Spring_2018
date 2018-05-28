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

import codeu.model.data.User;
import codeu.model.store.basic.UserStore;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

/** Servlet class responsible for the admin page. */
public class AdminServlet extends HttpServlet {

  /** Store class that gives access to Users. */
  private UserStore userStore;
  private ConversationStore conversationStore;
  private MessageStore messageStore;


  /**
   * Set up state for handling login-related requests. This method is only called when running in a
   * server, not when running in a test.
   */
  @Override
  public void init() throws ServletException {
    super.init();
    setStores(UserStore.getInstance(), ConversationStore.getInstance(), MessageStore.getInstance());
  }

  /**
   * Sets the UserStore used by this servlet. This function provides a common setup method for use
   * by the test framework or the servlet's init() function.
   */
  void setStores(UserStore userStore, ConversationStore conversationStore, MessageStore messageStore) {
    this.userStore = userStore;
    this.conversationStore = conversationStore;
    this.messageStore = messageStore;
  }
 
  /**
   * This function fires when a user requests the /admin URL. It simply forwards the request to
   * admin.jsp.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    
    
    request.setAttribute("numUsers", userStore.numUsers());
    request.setAttribute("numConvos", conversationStore.getNumConvos());
    request.setAttribute("numMessages", messageStore.numMessages());
    request.setAttribute("newestUser", userStore.newestUser());
    request.setAttribute("mostActive", getUser());
    request.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(request, response);

  }
  
  /**
   * Get's the highest user (if there is one)
   * @return the username of the highest user
   */
  private String getUser() {
	  UUID u = messageStore.getMostActive();
	  
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
  public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    // TODO ¯\_(ツ)_/¯ not sure if needs to send data anywhere
  }
}
