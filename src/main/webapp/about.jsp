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

  <div class="container">

      <h1><span>About the CodeU Chat App</span></h1>
      <p>
        This is an example chat application designed to be a starting point
        for your CodeU project team work. Here's some stuff to think about:
      </p>

      <ul>
        <li><strong>Algorithms and data structures:</strong> We've made the app
            and the code as simple as possible. You will have to extend the
            existing data structures to support your enhancements to the app,
            and also make changes for performance and scalability as your app
            increases in complexity.</li>
        <li><strong>Look and feel:</strong> The focus of CodeU is on the Java
          side of things, but if you're particularly interested you might use
          HTML, CSS, and JavaScript to make the chat app prettier.</li>
        <li><strong>Customization:</strong> Think about a group you care about.
          What needs do they have? How could you help? Think about technical
          requirements, privacy concerns, and accessibility and
          internationalization.</li>
      </ul>

      <h1><span>About Team 11</span></h1>

      <div class="row team">
          <div class="col-sm-4">
              <h3>Leonardo Lopez</h3>
              <p>A UIUC student who likes volleyball and League of Legends.</p>
          </div>
          <div class="col-sm-4">
              <h3>Levi Villarreal</h3>
              <p>A UTAustin student who likes ultimate frisbee and Breath of the Wild (BOTW is best).</p>
          </div>
          <div class="col-sm-4">
              <h3>Jessica Zhu</h3>
              <p>A UMN student who likes Game of Thrones and Fire Emblem.</p>
          </div>
      </div>

	  <h1><span>New Features and Improvements</span></h1>
	  <p>
        This is your code now. Get familiar with it and get comfortable
        working with your team to plan and make changes. Start by updating the
        homepage and this about page to tell your users more about your team.
        This page should also be used to describe the features and improvements
        you've added.
	  </p>
    </div>
  </div>

<%@ include file="WEB-INF/view/footer.jsp" %>

</body>
</html>
