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
<!DOCTYPE html>
<html>
<head>
  <title>Admin page</title>
  <link rel="stylesheet" href="/css/main.css">
</head>
<body>

  <nav>
    <a id="navTitle" href="/">CodeU Chat App</a>
    <a href="/conversations">Conversations</a>

    <% if(request.getSession().getAttribute("user") != null){ %>
    <a>Hello <%= request.getSession().getAttribute("user") %>!</a>
    <% } else{ %>
    <a href="/login">Login</a>
    <% } %>

    <a href="/about.jsp">About</a>

    <a href="/activity">Activity Feed</a>
  </nav>

  <div id="container">
    <h1>Admin Dashboard</h1>

    <% if(request.getSession().getAttribute("user") != null &&
         request.getSession().getAttribute("user").equals("leviv")){ %>
      <p>Welcome to the admin page!</p>
      
    <% }  else {%>
    
       <p>You do not have administrator privileges to access the page</p>
        <p>Log in to an admin page <a href="/login">here</a>.</p>
    
    <% } %>

    
  </div>
</body>
</html>
