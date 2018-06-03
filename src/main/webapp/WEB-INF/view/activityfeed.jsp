<%@ page import="codeu.model.data.Activity" %>
<%@ page import="java.util.List" %><%--
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
  <title>Activity Feed</title>
  <%@ include file="meta.jsp" %>  
</head>
<body>

<%@ include file="header.jsp" %>  

  <div class="container">

      <h1><span>Activity Feed</span></h1>
      <br>
      <%
        List<Activity> activities =
                (List<Activity>) request.getAttribute("activities");
        if(activities == null || activities.isEmpty()){
      %>
      <p>Nothing has happened.</p>
      <%
      }
      else{
      %>
        <%
          for(Activity activity : activities){
        %>
        <p class="convo"> <%= activity.getContent()%></p>
        <%
          }
        %>
      <%
        }
      %>

    </div>
  </div>
  
<%@ include file="footer.jsp" %>  

</body>
</html>
