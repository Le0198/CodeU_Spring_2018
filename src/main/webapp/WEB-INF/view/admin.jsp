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
  <%@ include file="meta.jsp" %>  

</head>
<body>

<%@ include file="header.jsp" %>  

  <div id="container">
    <h1>Admin Dashboard</h1>

    <%
    
    if(curUserName != null && curUserName.equals("leviv")) { %>
     
      <h2>Welcome to the admin page!</h2>
      
      <hr>
      
      <h2>Site Stats</h2>
      
      <h3>Current number of users</h3>
      
      <p><%= request.getAttribute("numUsers") %></p>
      
      <h3>Current number of Conversations</h3>
      
      <p><%= request.getAttribute("numConvos") %></p>
      
      <h3>Current number of Messages</h3>
      
      <p><%= request.getAttribute("numMessages") %></p>
      
      <h3>Newest User</h3>
      
      <p><%= request.getAttribute("newestUser") %></p>
      
      <h3>Most Active User</h3>
      
      <p><%= request.getAttribute("mostActive") %></p>
      
      <h3>Most Wordy User</h3>
      
      <p><%= request.getAttribute("mostWordy") %></p>
      
      <hr>
      
      <h2>Import Data</h2>
      
    <% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red"><%= request.getAttribute("error") %></h2>
    <% } %>

    <form action="/admin" method="POST" enctype = "multipart/form-data">
        <label for="username">File: </label>
        <br/>
        <input name="myFile" type="file" id="myFile" enctype="multipart/form-data" accept=".txt">
        <button type="submit">Submit</button>
    </form>

    <% }  else {%>

       <p>You do not have administrator privileges to access the page</p>
        <p>Log in to an admin page <a href="/login">here</a>.</p>

    <% } %>


  </div>
</body>
</html>
