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
        <title>CodeU Chat App</title>
        <%@ include file="WEB-INF/view/meta.jsp" %>  
    </head>
    <body>

        <%@ include file="WEB-INF/view/header.jsp" %>  

        <div class="container-fluid">
            
            <section id="home-hero">
               <div class="row">
                   <div class="col-sm-6 picture-bg">
                       <p>Go to the <a href="/conversations">conversations</a> page to create or join a conversation.</p>
                   </div>
                   <div class="col-sm-6 login-bg">
                       <div class="login-block">
                            <p><a href="/login">Login</a> to get started.</p>
                        </div><!-- login-block -->
                   </div>
               </div><!-- row -->
                                
            </section>
            
        </div><!-- container -->

        <%@ include file="WEB-INF/view/footer.jsp" %>  
    </body>
</html>
