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
<%@ page import="codeu.model.data.User" %>
<%@ page import="codeu.model.store.basic.UserStore" %>
<%
String profile = (String) request.getAttribute("profile");
User username = UserStore.getInstance().getUser(profile);
%>

<!DOCTYPE html>
<html>
<head>
  <title><%=profile%></title>
  <%@ include file="meta.jsp" %>  
  <style>
    textarea {
      width: 750px;
      height: 100px;
    }
  </style>
</head>
<body id="convo-body">

<%@ include file="header.jsp" %>  
  <div class="convo-con">
  <div class="container">

      <h1><%=profile%>'s Profile Page</h1>
      <h2>About <%=profile%></h2>
      <p class="about-me"><%=username.getAboutMe() %></p>
      <% if (request.getSession().getAttribute("user") != null) { %>
        <% if (request.getSession().getAttribute("user").equals(profile)) { %>
          <h3>Edit your About Me</h3>
            <form action="/users/<%= profile %>" method="POST">
              <textarea type="text" name="aboutme" id="aboutme"></textarea>
              <br/>
              <button type="submit">Submit</button>

            </form>
        <% } %>
      <% } %>
              <br/>

    </div>
  </div>
  
<%@ include file="footer.jsp" %>  

</body>
</html>
