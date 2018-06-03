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
  <title>Login</title>
  <%@ include file="meta.jsp" %>  
</head>
<body>

<%@ include file="header.jsp" %>  

<div class="log-con">
  <div class="container">
    <h1><span>Login</span></h1>

    <% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red"><%= request.getAttribute("error") %></h2>
    <% } %>

    <div class="contact-con">
        <form action="/login" method="POST">
             <div class="row">
                 <div class="col-sm-12">
                <span class="input input--hoshi">
                    <input class="input__field input__field--hoshi" type="text" id="input-4" name="username" />
                    <label class="input__label input__label--hoshi input__label--hoshi-color-1" for="input-4">
                        <span class="input__label-content input__label-content--hoshi">Username</span>
                    </label>
                </span>

                <span class="input input--hoshi">
                    <input class="input__field input__field--hoshi" type="password" id="input-4" name="password" />
                    <label class="input__label input__label--hoshi input__label--hoshi-color-1" for="input-4">
                        <span class="input__label-content input__label-content--hoshi">Password</span>
                    </label>
                </span>

                <div class="button-con">
                    <button type="submit">Login</button>
                </div>
                </div>
            </div>
        </form>
    </div>

   
    <p class="text-center"><br><br>New users can register <a href="/register">here</a>.<br><br></p>
  </div>
  </div>
  
<%@ include file="footer.jsp" %>  

</body>
</html>
