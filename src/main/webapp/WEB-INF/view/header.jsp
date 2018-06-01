  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
   <nav>
   <%
   Object curUserName = request.getSession().getAttribute("user");
   %>
    <a id="navTitle" href="/">CodeU Chat App</a>
    <a href="/conversations">Conversations</a>
    <% if(curUserName != null){ %>
      <a href="/users/<%= curUserName %>"><%= curUserName %>'s Page</a>
    <% } else{ %>
      <a href="/login">Login</a>
    <% } %>
    <a href="/about.jsp">About</a>
    <a href="/activity">Activity Feed</a>

    <% 
       if(curUserName != null && curUserName.equals("leviv")) { %>
        <a href="/admin">Admin</a>
    <% } %>
    
    
  </nav>