<%--
  Created by IntelliJ IDEA.
  User: hromov
  Date: 9/29/2022
  Time: 7:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="${pageContext.request.contextPath}/page_elements/common_scripts_and_css.jsp"/>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="localization.lang" var="loc"/>
    <title><fmt:message bundle="${loc}" key="lang.add_staff"/> | Cruise company</title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/page_elements/header.jsp"/>

<div class="container auth-box">
    <form class="col flex-column" action="${pageContext.request.contextPath}/cruise/do_add_staff" method="post"
          enctype="multipart/form-data">
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="first_name" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.first_name"/></span>
                </label>
                <input type="text" class="form-control mt-2" name="first_name" id="first_name">
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="last_name" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.last_name"/></span>
                </label>
                <input type="text" class="form-control mt-2" name="last_name" id="last_name">
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="phone" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.phone"/></span>
                </label>
                <input type="text" class="form-control mt-2" name="phone" id="phone">
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="speciality" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.speciality"/></span>
                </label>
                <input type="text" class="form-control mt-2" id="speciality">
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="ship" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.ship"/></span>
                </label>
                <input list="ships" type="text" class="form-control mt-2" name="ship" id="ship">
                <datalist id="ships">
                    <c:forEach items="${listShip}" var="ship">
                        <option value="${ship.id}">${ship.name}</option>
                    </c:forEach>
                </datalist>
            </div>
        </div>
        <div class="row justify-content-end">
            <span class="col-auto px-1 mb-2">
                <button class="btn btn-jade" type="submit"><fmt:message bundle="${loc}" key="lang.add"/></button>
            </span>
        </div>
    </form>
</div>
</body>
</html>

