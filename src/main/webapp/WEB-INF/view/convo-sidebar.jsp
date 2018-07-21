<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>

  <% List<Conversation> showConversations = (List<Conversation>) request.getAttribute("conversations"); %>

  <div class="col-sm-3 convo-sidebar">
    <h2><span>Convos <a href="/conversations"><i class="fas fa-plus-circle"></i></a></span></h2>
      <% for(Conversation conversation2 : showConversations){ %>
        <a href="/chat/<%= conversation2.getTitle() %>">
          <div class="convo">
              <%= conversation2.getTitle() %>
          </div>
        </a>
      <% } %>
  </div><!-- col -->
