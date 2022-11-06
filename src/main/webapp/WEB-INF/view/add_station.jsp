<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: hromov
  Date: 9/29/2022
  Time: 5:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="${pageContext.request.contextPath}/page_elements/common_scripts_and_css.jsp"/>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="localization.lang" var="loc"/>
    <title><fmt:message bundle="${loc}" key="lang.add_station"/> | Cruise company</title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/page_elements/header.jsp"/>

<div class="container auth-box">
    <form class="col flex-column" action="${pageContext.request.contextPath}/cruise/do_add_station" method="post"
          enctype="multipart/form-data">
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="city" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.city"/></span>
                </label>
                <input type="text" class="form-control mt-2" name="city" id="city">
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="country" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.country"/></span>
                </label>
                <input id="country" class="form-control" type="text" name="country"/>
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
