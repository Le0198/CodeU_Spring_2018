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

import codeu.model.data.User;
import codeu.model.store.basic.UserStore;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;

/** Servlet class responsible for the profile page. */
public class ProfileServlet extends HttpServlet {

  /** Store class that gives access to Users. */
  private UserStore userStore;

  /** Set up state for handling profile requests. */
  @Override
  public void init() throws ServletException {
    super.init();
    setUserStore(UserStore.getInstance());
  }

  /**
   * Sets the UserStore used by this servlet. This function provides a common setup method for use
   * by the test framework or the servlet's init() function.
   */
  void setUserStore(UserStore userStore) {
    this.userStore = userStore;
  }

  /**
   * This function fires when a user navigates to the profile page.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    String requestUrl = request.getRequestURI();
    String username = requestUrl.substring("/users/".length());

    User user = userStore.getUser(username);
    if (user == null) {
      // user not found, no profile page exists for them
      System.out.println("User not found: " + username);
      response.sendRedirect("/about.jsp");
      // there's probably a better link to redirect them to, but placeholder for now
      return;
    }

    request.setAttribute("profile", username);
    request.setAttribute("id", user.getId());
    request.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request, response);
  }

  /**
   * If a user is logged in, this function allows them to edit their About Me section.
   */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

        String username = (String) request.getSession().getAttribute("user");
        if (username == null) {
          // user is not logged in
          response.sendRedirect("/login");
          return;
        }

        User user = userStore.getUser(username);
        if (user == null) {
          // user was not found
          response.sendRedirect("/login");
          return;
        }

        String requestUrl = request.getRequestURI();
        String userUrl = requestUrl.substring("/users/".length());

        if (request.getParameter("url") == null) {
          String aboutMeDescrip = request.getParameter("aboutme");
          // removes HTML from the message content
          String cleanAboutMeDescrip = Jsoup.clean(aboutMeDescrip, Whitelist.none());

          user.setAboutMe(aboutMeDescrip);
        } else {
          String url = request.getParameter("url");
          if (url.length() < 4) {
            request.setAttribute("error", "Not a valid url.");
            response.sendRedirect("/users/" + username);
            return;
          } else {
            String fileType = url.substring(url.length() - 4);
            if ( !fileType.equals(".png") && !fileType.equals(".jpg") && !fileType.equals(".gif") ) {
              request.setAttribute("error", "Not a valid url.");
              response.sendRedirect("/users/" + username);
              return;
            }
          }
          user.setPicture(url);
        }
        userStore.updateUser(user);
        response.sendRedirect("/users/" + username);
  }
}
