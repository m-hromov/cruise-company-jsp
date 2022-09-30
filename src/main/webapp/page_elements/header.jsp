<%--
  Created by IntelliJ IDEA.
  User: hromov
  Date: 9/4/2022
  Time: 12:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<script>
    $(function(){
        var current_page_URL = location.href;
        $( "a" ).each(function() {
            if ($(this).attr("href") !== "#") {
                var target_URL = $(this).prop("href");
                if (target_URL == current_page_URL) {
                    $('nav a').parents('li, ul').removeClass('active');
                    $(this).parent('li').addClass('active');
                    return false;
                }
            }
        });
    });
</script>
<header class="navbar navbar-expand flex-fill bg-jade sticky-top">

    <div id="headerDiv" class="collapse navbar-collapse">
        <ul class="navbar-nav flex-row me-auto">
            <li class="nav-item my-auto">
                <a class="navbar-brand ms-3" href="../">
                    <img src="../static/images/logo.png" alt="home" height="35px" width="35px"/>
                </a>
            </li>
            <li class="nav-item">
                    <a class="nav-link" href="../">
                        Home
                    </a>
            </li>
            <li class="nav-item">
                    <a class="nav-link" href="cruise/find_cruise">
                        Find cruise
                    </a>
            </li>
            <c:if test="${sessionScope.role=='ADMIN'}">
                <li class="nav-item">
                        <a class="nav-link" href="cruise/orders">
                            Orders
                        </a>
                </li>
                <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                           aria-expanded="false">
                                Add
                        </a>
                    <ul class="dropdown-menu" style="right: 0;left: auto;">
                        <li>
                            <a class="dropdown-item" href="cruise/add_cruise">Cruise</a>
                        </li>
                        <li>
                            <a class="dropdown-item" href="cruise/add_ship">Ship</a>
                        </li>
                        <li>
                            <a class="dropdown-item" href="cruise/add_staff">Staff</a>
                        </li>
                        <li>
                            <a class="dropdown-item" href="cruise/add_station">Station</a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                       aria-expanded="false">
                        Edit
                    </a>
                    <ul class="dropdown-menu" style="right: 0;left: auto;">
                        <li>
                            <a class="dropdown-item" href="cruise/edit_cruise">Cruise</a>
                        </li>
                        <li>
                            <a class="dropdown-item" href="cruise/edit_ship">Ship</a>
                        </li>
                    </ul>
                </li>
            </c:if>
        </ul>

        <c:if test="${pageContext.request.requestURI.equals('/WEB-INF/find_cruise.jsp')}">
            <form action="cruise/search" class="me-auto d-flex my-auto" role="search" method="get">
                <div class="input-group">
                    <input class="form-control" type="search" name="search_str" placeholder="Search"
                           aria-label="Search">
                    <button class="btn btn-light" type="submit">Search</button>
                    <button class="btn btn-light" type="button" data-bs-toggle="offcanvas"
                            data-bs-target="#offcanvasWithBothOptions"
                            aria-controls="offcanvasWithBothOptions">
                        Filter
                    </button>
                </div>
            </form>
        </c:if>

        <ul class="navbar-nav me-3">
            <c:if test="${sessionScope.role.equals('USER')}">
                <li class="nav-item dropdown">

                        <a class="nav-link" href="cruise/user_orders">
                            My orders
                        </a>
                </li>
            </c:if>
            <c:if test="${sessionScope.role.equals('ADMIN')||sessionScope.role.equals('USER')}">
                <li class="nav-item dropdown">
                    <c:if test="${sessionScope.role.equals('ADMIN')}">
                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                           aria-expanded="false">ADMIN</a>
                    </c:if>
                    <c:if test="${sessionScope.role.equals('USER')}">
                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                           aria-expanded="false">
                                ${sessionScope.user.firstName.concat(" ").concat(sessionScope.user.lastName)}
                        </a>
                    </c:if>
                    <ul class="dropdown-menu" style="right: 0;left: auto;">
                        <c:if test="${sessionScope.role.equals('USER')}">
                            <li>
                                <a class="dropdown-item"
                                   href="balance.jsp">Balance: ${sessionScope.user.money}</a></li>
                            </li>
                            <li><a class="dropdown-item" href="cruise/edit_profile">Edit profile</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                        </c:if>


                        <li><a class="dropdown-item" href="cruise/sign_out">Sign out</a></li>
                    </ul>
                </li>
            </c:if>
            <c:if test="${sessionScope.role==null}">
                <li class="nav-item">
                        <a class="nav-link" href="../signin.jsp">
                            Sign in
                        </a>
                </li>
                <li class="nav-item py-1 col-12 col-lg-auto">
                    <div class="vr d-none d-lg-flex h-100 mx-lg-2 text-white"></div>
                    <hr class="d-lg-none text-white-50">
                </li>
                <li class="nav-item">
                        <a class="nav-link" href="../signup.jsp">
                            Sign up
                        </a>
                </li>
            </c:if>
        </ul>
    </div>


</header>

</body>
</html>
