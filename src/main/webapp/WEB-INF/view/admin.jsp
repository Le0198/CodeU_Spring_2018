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
<body id="admin">

<%@ include file="header.jsp" %>  
  
  <div class="container">
    <h1><span>Admin Dashboard</span></h1>

    <%
    
    if(curUserName != null && curUserName.equals("leviv")) { %>
      
      <h2><span>Site Stats</span></h2>
      <br/>
      <div class="row stat-row">
          <div class="col-sm-4">
              <h3>Current number of users</h3>
              <p><%= request.getAttribute("numUsers") %></p>
          </div>
          
          <div class="col-sm-4">
              <h3>Current number of Conversations</h3>
              <p><%= request.getAttribute("numConvos") %></p>
          </div>
          
          <div class="col-sm-4">
              <h3>Current number of Messages</h3>
              <p><%= request.getAttribute("numMessages") %></p>
          </div>
      </div>
      
      <div class="row stat-row">
          <div class="col-sm-4">
              <h3>Newest User</h3>
              <p><%= request.getAttribute("newestUser") %></p>
          </div>
          
          <div class="col-sm-4">
              <h3>Most Active User</h3>
              <p><%= request.getAttribute("mostActive") %></p>
          </div>
          
          <div class="col-sm-4">
              <h3>Most Wordy User</h3>
              <p><%= request.getAttribute("mostWordy") %></p>
          </div>
      </div>
        
      
      <hr><br/>
      
      <h2><span>Import Data</span></h2>
      <br/><br/>
    <% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red"><%= request.getAttribute("error") %></h2>
    <% } %>

    <div class="new-convo">
        <form action="/admin" method="POST" enctype = "multipart/form-data">
            <label for="username">File: </label>
            <input name="myFile" type="file" id="myFile" enctype="multipart/form-data" accept=".txt" class="file-button">
            <br/><br/>
            <button type="submit">Submit</button>
        </form>
    </div>

    <% }  else {%>

       <p>You do not have administrator privileges to access the page</p>
        <p>Log in to an admin page <a href="/login">here</a>.</p>

    <% } %>
            <br/>
            <br/>


  </div>
  <%@ include file="footer.jsp" %>  

</body>
</html>
