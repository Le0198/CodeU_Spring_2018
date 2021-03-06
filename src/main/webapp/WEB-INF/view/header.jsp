  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

   <%
   Object curUserName = request.getSession().getAttribute("user");
   %>


        <div id="dim-screen"></div>


        <section id="nav">
            <button class="c-hamburger c-hamburger--htx c-hamburger--htra">
                <span>toggle menu</span>
            </button>
        </section><!-- nav -->

        <section id="nav-expand">
            <ul class="nav-list">
                <li class="nav-item"><p><a href="/">CodeU Chat App</a></p></li>
                <li class="nav-item">
                    <div class="nav-one scrollLink">
                        <h2 class="hover"><span class="nav-span-active"><i class="fas fa-comments"></i></span><a href="/conversations">Conversations</a></h2>
                    </div>
                </li><!-- nav-item -->

                <% if(curUserName != null){ %>
                    <li class="nav-item">
                        <div class="nav-two scrollLink">
                            <h2 class="hover"><span><i class="fas fa-user-circle"></i></span><a href="/users/<%= curUserName %>"><%=curUserName%>'s Page</a></h2>
                        </div>
                    </li><!-- nav-item -->
                    <li class="nav-item">
                      <div class="nav-two scrollLink">
                        <h2 class="hover"><span><i class="fas fa-sign-out-alt"></i></span><a href="/logout">Logout</a></h2>
                      </div>
                    </li>
                <% } else{ %>
                    <li class="nav-item">
                    <div class="nav-two scrollLink">
                        <h2 class="hover"><span><i class="fas fa-sign-in-alt"></i></span><a href="/login">Login</a></h2>
                    </div>
                    </li><!-- nav-item -->
                <% } %>

                <li class="nav-item">
                    <div class="nav-three scrollLink">
                        <h2 class="hover"><span><i class="fas fa-address-card"></i></span><a href="/about.jsp">About</a></h2>
                    </div>
                </li><!-- nav-item -->
                <li class="nav-item">
                    <div class="nav-four scrollLink">
                        <h2 class="hover"><span class=""><i class="fas fa-newspaper"></i></span><a href="/activity">Activity Feed</a></h2>
                    </div>
                </li><!-- nav-item -->

                <% if(curUserName != null) { %>
                    <li class="nav-item">
                        <div class="nav-five scrollLink">
                            <h2 class="hover"><span class=""><i class="fas fa-unlock"></i></span><a href="/admin">Admin</a></h2>
                        </div>
                    </li><!-- nav-item -->
                <% } %>

            </ul><!-- nav-list -->


        </section><!-- nav-expand -->
