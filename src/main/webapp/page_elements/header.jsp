<%--
  Created by IntelliJ IDEA.
  User: hromov
  Date: 9/4/2022
  Time: 12:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="utils" uri="WEB-INF/tld/utils.tld" %>
<html>
<body>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="localization.lang" var="loc"/>
<header class="navbar navbar-expand flex-fill bg-jade sticky-top">

    <div id="headerDiv" class="collapse navbar-collapse">
        <ul class="navbar-nav flex-row me-auto">
            <li class="nav-item my-auto">
                <a class="navbar-brand ms-3" href="../">
                    <img src="${pageContext.request.contextPath}/resources/images/logo.png" alt="home" height="35px"
                         width="35px"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/">
                    <fmt:message bundle="${loc}" key="lang.home"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/cruise/find_cruise">
                    <fmt:message bundle="${loc}" key="lang.find_cruise"/>
                </a>
            </li>
            <c:if test="${sessionScope.role=='ADMIN'}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cruise/orders">
                        <fmt:message bundle="${loc}" key="lang.orders"/>
                    </a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                       aria-expanded="false">
                        <fmt:message bundle="${loc}" key="lang.add"/>
                    </a>
                    <ul class="dropdown-menu" style="right: 0;left: auto;">
                        <li>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/cruise/add_cruise">
                                <fmt:message bundle="${loc}" key="lang.cruise"/>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/cruise/add_ship">
                                <fmt:message bundle="${loc}" key="lang.ship"/>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/cruise/add_staff">
                                <fmt:message bundle="${loc}" key="lang.staff"/>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/cruise/add_station">
                                <fmt:message bundle="${loc}" key="lang.station"/>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                       aria-expanded="false">
                        <fmt:message bundle="${loc}" key="lang.edit"/>
                    </a>
                    <ul class="dropdown-menu" style="right: 0;left: auto;">
                        <li>
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/cruise/edit_cruise">
                                <fmt:message bundle="${loc}" key="lang.cruise"/>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/cruise/edit_ship">
                                <fmt:message bundle="${loc}" key="lang.ship"/>
                            </a>
                        </li>
                    </ul>
                </li>
            </c:if>
        </ul>

        <c:if test="${pageContext.request.requestURI.equals('/WEB-INF/view/find_cruise.jsp') && empty param.cruise_id}">
            <form action="${pageContext.request.contextPath}/cruise/search" class="me-auto d-flex my-auto" role="search"
                  method="get">
                <div class="input-group">
                    <input class="form-control" type="search" name="search_str"
                           placeholder="<fmt:message bundle="${loc}" key="lang.search"/>"
                           aria-label="Search">
                    <button class="btn btn-light" type="submit"><fmt:message bundle="${loc}"
                                                                             key="lang.search"/></button>
                    <button class="btn btn-light" type="button" data-bs-toggle="offcanvas"
                            data-bs-target="#offcanvasWithBothOptions"
                            aria-controls="offcanvasWithBothOptions">
                        <fmt:message bundle="${loc}" key="lang.filter"/>
                    </button>
                </div>
            </form>
        </c:if>

        <ul class="navbar-nav me-3">
            <c:if test="${sessionScope.role.equals('USER')}">
                <li class="nav-item dropdown">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cruise/user_orders">
                        <fmt:message bundle="${loc}" key="lang.my_orders"/>
                    </a>
                </li>
            </c:if>
            <li class="nav-item dropdown">
                <c:if test="${sessionScope.lang.equals('en')}">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                       aria-expanded="false">
                        EN
                    </a>
                </c:if>
                <c:if test="${sessionScope.lang.equals('ua')}">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                       aria-expanded="false">
                        UA
                    </a>
                </c:if>
                <ul class="dropdown-menu" style="right: 0;left: auto;">
                    <li>
                        <button class="dropdown-item lang"
                                value="en">English
                        </button>
                    </li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>
                    <li>
                        <button class="dropdown-item lang"
                                value="ua">Українська
                        </button>
                    </li>
                </ul>
            </li>
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
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/cruise/edit_money">
                                    <fmt:message bundle="${loc}" key="lang.balance"/>:
                                    <utils:currency value="${sessionScope.user.money}"
                                                    convertedcurr="${lang eq 'en' ? 'USD' : 'UAH'}"/>
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/cruise/edit_profile">
                                    <fmt:message bundle="${loc}" key="lang.edit_profile"/>
                                </a>
                            </li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                        </c:if>


                        <li>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/cruise/do_sign_out">
                                <fmt:message bundle="${loc}" key="lang.sign_out"/>
                            </a>
                        </li>
                    </ul>
                </li>
            </c:if>
            <c:if test="${sessionScope.role.equals('VISITOR')}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cruise/sign_in">
                        <fmt:message bundle="${loc}" key="lang.sign_in"/>
                    </a>
                </li>
                <li class="nav-item py-1 col-12 col-lg-auto">
                    <div class="vr d-none d-lg-flex h-100 mx-lg-2 text-white"></div>
                    <hr class="d-lg-none text-white-50">
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cruise/sign_up">
                        <fmt:message bundle="${loc}" key="lang.sign_up"/>
                    </a>
                </li>
            </c:if>
        </ul>
    </div>


</header>
<script>
    document.querySelectorAll("button.lang").forEach(e => {
        e.addEventListener(
            'click',
            function (event) {
                let url = new URL(document.location.href);
                url.searchParams.set("lang", e.value);
                document.location.href = url.toString();
            },
            false
        );
    });
</script>
</body>
</html>
