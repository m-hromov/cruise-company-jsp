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
<header class="navbar navbar-expand navbar-dark flex-fill bg-jade sticky-top">

    <div class="collapse navbar-collapse">
        <ul class="navbar-nav flex-row me-auto">
            <li class="nav-item my-auto">
                <a class="navbar-brand ms-3" href="../">
                    <img src="../static/images/logo.png" alt="home" height="35px" width="35px"/>
                </a>
            </li>
            <li class="nav-item">
                <c:if test="${pageContext.request.requestURI.equals('/')}">
                    <a class="nav-link active" href="../">
                        Home
                    </a>
                </c:if>
                <c:if test="${!pageContext.request.requestURI.equals('/')}">
                    <a class="nav-link" href="../">
                        Home

                    </a>
                </c:if>
            </li>
            <li class="nav-item">
                <c:if test="${pageContext.request.requestURI.equals('/find_cruise.jsp')}">
                    <a class="nav-link active" href="cruise/find_cruise">
                        Find cruise

                    </a>
                </c:if>
                <c:if test="${!pageContext.request.requestURI.equals('/find_cruise.jsp')}">
                    <a class="nav-link" href="cruise/find_cruise">
                        Find cruise
                    </a>
                </c:if>
            </li>
            <c:if test="${sessionScope.role=='admin'}">
                <li class="nav-item">
                    <c:if test="${pageContext.request.requestURI.equals('/orders.jsp')}">
                        <a class="nav-link active" href="orders.jsp">
                            Orders
                        </a>
                    </c:if>
                    <c:if test="${!pageContext.request.requestURI.equals('/orders.jsp')}">
                        <a class="nav-link" href="orders.jsp">
                            Orders
                        </a>
                    </c:if>
                </li>
                <li class="nav-item">
                    <c:if test="${pageContext.request.requestURI.equals('/add_cruise.jsp')}">
                        <a class="nav-link active" href="add_cruise.jsp">
                            Add cruise
                        </a>
                    </c:if>
                    <c:if test="${!pageContext.request.requestURI.equals('/add_cruise.jsp')}">
                        <a class="nav-link" href="add_cruise.jsp">
                            Add cruise
                        </a>
                    </c:if>
                </li>
            </c:if>
        </ul>

        <c:if test="${pageContext.request.requestURI.equals('/find_cruise.jsp')}">
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
            <c:if test="${sessionScope.role=='admin'}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                       aria-expanded="false">${sessionScope.role}</a>
                    <ul class="dropdown-menu" style="right: 0;left: auto;">
                        <li><a class="dropdown-item" href="cruise/edit_profile">Edit profile</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="cruise/sign_out">Sign out</a></li>
                    </ul>
                </li>
            </c:if>
            <c:if test="${sessionScope.role!='admin'}">
                <li class="nav-item">
                    <c:if test="${pageContext.request.requestURI.equals('/signin.jsp')}">
                        <a class="nav-link active" href="../signin.jsp">
                            Sign in

                        </a>
                    </c:if>
                    <c:if test="${!pageContext.request.requestURI.equals('/signin.jsp')}">
                        <a class="nav-link" href="../signin.jsp">
                            Sign in

                        </a>
                    </c:if>
                </li>
                <li class="nav-item py-1 col-12 col-lg-auto">
                    <div class="vr d-none d-lg-flex h-100 mx-lg-2 text-white"></div>
                    <hr class="d-lg-none text-white-50">
                </li>
                <li class="nav-item">
                    <c:if test="${pageContext.request.requestURI.equals('/signup.jsp')}">
                        <a class="nav-link active" href="../signup.jsp">
                            Sign up

                        </a>
                    </c:if>
                    <c:if test="${!pageContext.request.requestURI.equals('/signup.jsp')}">
                        <a class="nav-link" href="../signup.jsp">
                            Sign up

                        </a>
                    </c:if>
                </li>
            </c:if>
        </ul>
    </div>


</header>
</body>
</html>
