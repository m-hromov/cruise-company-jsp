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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
            crossorigin="anonymous"></script>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="localization.lang" var="loc"/>
    <title><fmt:message bundle="${loc}" key="lang.add_station"/> | Cruise company</title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/page_elements/header.jsp"/>

<div class="container auth-box">
    <form class="col flex-column" action="${pageContext.request.contextPath}/cruise/add_station" method="post"
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
