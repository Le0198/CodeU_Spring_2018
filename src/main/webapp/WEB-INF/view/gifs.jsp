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
<%@ page import="java.util.List" %>
<%@ page import="codeu.model.data.Gif" %>

<!DOCTYPE html>
<html>
<head>
  <title>Gifs</title>
  <%@ include file="meta.jsp" %>
</head>
<body>

<%@ include file="header.jsp" %>

    <div class="container">

        <h1><span>Available Gifs</span></h1>

        <% if(curUserName != null && curUserName.equals("leviv")) { %>

        <% List<Gif> gifs = (List<Gif>) request.getAttribute("gifs");

        if(gifs == null || gifs.isEmpty()){
        %>
          <p>Add some gifs to get started.</p>

        <% } else { %>

             <%
                int numOfCols = 3;
                int rowCount = 0;
                int bootstrapColWidth = 12 / numOfCols;
                int fieldCount = -1;

                for(Gif gif : gifs){
                    fieldCount++;
                }

                %>
                <div class="row">
                    <% for(Gif gif2 : gifs){ %>
                        <div class="col-sm-<%= bootstrapColWidth %> organization">
                            <div class="convo">
                               <h3><%= gif2.getTag() %></h3>
                                <img src="<%= gif2.getURL() %>" alt="" width="100%">
                            </a>
                            </div>
                        </div>

                        <% rowCount++;
                        if(rowCount % numOfCols == 0) {  %>
                            </div><div class="row">
                        <% } %>
                    <% } %>
                </div>

        <% }%>
        <br/><br/>

        <h1><span>Add Gifs</span></h1>

        <div class="contact-con">
            <form action="/gifs" method="POST" class="gif-form">
                 <div class="row">
                     <div class="col-sm-12">
                        <span class="input input--hoshi">
                            <input class="input__field input__field--hoshi" type="text" id="input-4" name="url" />
                            <label class="input__label input__label--hoshi input__label--hoshi-color-1" for="input-4">
                                <span class="input__label-content input__label-content--hoshi">Gif Link</span>
                            </label>
                        </span>

                        <span class="input input--hoshi">
                            <input class="input__field input__field--hoshi" type="text" id="input-4" name="tag" />
                            <label class="input__label input__label--hoshi input__label--hoshi-color-1" for="input-4">
                                <span class="input__label-content input__label-content--hoshi">Gif Tag</span>
                            </label>
                        </span>

                        <div class="button-con">
                            <button type="submit">Upload Gif</button>
                        </div>
                    </div>
                </div>
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
