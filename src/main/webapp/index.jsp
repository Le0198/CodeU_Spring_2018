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
    <body id="home">

        <%@ include file="WEB-INF/view/header.jsp" %>  

        <div class="container-fluid" id="home">
            
            <section id="home-hero" class="container-hero">
               
               <h1 class="home-text">CodeU Chat App</h1>
                <div class="container-details">
                    <p><span>Go to the<a href="/conversations" class="hover"> conversations</a> page to create or join a conversation.</span></p>
                    <p><span><a href="/login" class="hover">Login</a> to get started.</span></p>
                            
               </div><!-- row -->
            </section>
            
        </div><!-- container -->

        <%@ include file="WEB-INF/view/footer.jsp" %>  
    </body>
</html>
