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

import codeu.model.data.Gif;
import codeu.model.data.Conversation;
import codeu.model.store.basic.GifStore;
import codeu.model.store.basic.UserStore;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet class responsible for gifs. */
public class GifsServlet extends HttpServlet {

    /** Store class that gives access to gifs. */
    private GifStore gifStore;

    /** Set up state for handling related requests. */
    @Override
    public void init() throws ServletException {
        super.init();
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
    * This function fires when a user navigates to the gif page.
    */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Gif> gifs = gifStore.getAllGifs();
        request.setAttribute("gifs", gifs);
        request.getRequestDispatcher("/WEB-INF/view/gifs.jsp").forward(request, response);
    }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
            
            String url = request.getParameter("url");
            String tag = request.getParameter("tag");
            
            Gif gif = new Gif(UUID.randomUUID(), url, tag, Instant.now());

            gifStore.addGif(gif);
            response.sendRedirect("/gifs");
      
      }
  }
