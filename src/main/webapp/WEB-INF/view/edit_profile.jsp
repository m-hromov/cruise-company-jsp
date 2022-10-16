<%--
  Created by IntelliJ IDEA.
  User: hromov
  Date: 9/20/2022
  Time: 1:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"
            integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
            crossorigin="anonymous"></script>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="localization.lang" var="loc"/>
    <title><fmt:message bundle="${loc}" key="lang.edit_profile"/> | Cruise company</title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/page_elements/header.jsp"/>
<jsp:useBean id="user" scope="session" type="com.cruisecompany.entity.Passenger"/>
<div class="container auth-box parent">
    <form id="profileForm" class="col flex-column" action="${pageContext.request.contextPath}/cruise/edit_profile"
          method="post">
        <input type="hidden" name="part" value="info">
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="first_name" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.first_name"/></span>
                </label>
                <input type="text" class="form-control mt-2" name="first_name" id="first_name"
                       value="${user.firstName}">
                <div class="invalid-feedback">
                    <fmt:message bundle="${loc}" key="lang.wrong_name"/>
                </div>
            </div>
            <div class="col px-1 mb-2">
                <label for="last_name" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.last_name"/></span>
                </label>
                <input type="text" class="form-control mt-2" name="last_name" id="last_name" value="${user.lastName}">
                <div class="invalid-feedback">
                    <fmt:message bundle="${loc}" key="lang.wrong_name"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="phone" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.phone"/></span>
                </label>
                <input type="text" class="form-control mt-2" name="phone" id="phone" value="${user.phone}">
                <div class="invalid-feedback">
                    <fmt:message bundle="${loc}" key="lang.wrong_phone"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="email" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.email"/></span>
                </label>
                <c:if test="${empty wrongEmail}">
                    <input type="text" class="form-control mt-2" name="email" id="email" value="${user.email}">
                    <div class="invalid-feedback">
                        <fmt:message bundle="${loc}" key="lang.wrong_email"/>
                    </div>
                </c:if>
                <c:if test="${wrongEmail==true}">
                    <input type="text" class="form-control mt-2 is-invalid" name="email" id="email"
                           value="${user.email}">
                    <div class="invalid-feedback">
                        <fmt:message bundle="${loc}" key="lang.error_email_exists"/>
                    </div>
                    <c:remove var="wrongEmail" scope="session"/>
                </c:if>
            </div>
        </div>
        <div class="row justify-content-end">
            <span class="col-auto px-1 mb-2">
                <button class="btn btn-jade" type="submit"><fmt:message bundle="${loc}" key="lang.apply"/></button>
            </span>
        </div>
    </form>
    <div class="box-divider">
        <hr/>
    </div>
    <form class="col flex-column" action="${pageContext.request.contextPath}/cruise/edit_profile"
          method="post" enctype="multipart/form-data">
        <input type="hidden" name="part" value="document">
        <div class="row">
            <span class="col-auto px-1 my-auto">
                <label for="document" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.document"/></span>
                </label>
                <div class="input-group">
                    <input type="file" class="form-control mt-2" name="document" id="document" required>
                    <c:if test="${not empty user.documentPath}">
                        <a href="${user.documentPath}" class="btn btn-light mt-2"><fmt:message bundle="${loc}" key="lang.view"/></a>
                    </c:if>
                </div>
            </span>
            <span class="col-auto px-1 ms-auto">
                <button class="btn btn-jade mt-2" type="submit"><fmt:message bundle="${loc}" key="lang.upload"/></button>
            </span>
        </div>
    </form>
    <div class="box-divider">
        <hr/>
    </div>
    <form id="profilePasswordForm" class="col flex-column"
          action="${pageContext.request.contextPath}/cruise/edit_profile"
          method="post">
        <input type="hidden" name="part" value="password">
        <div class="row pt-4">
            <div class="col px-1 mb-2">
                <label for="old_password" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.old_password"/></span>
                </label>
                <c:if test="${empty wrongPassword}">
                    <input type="password" class="form-control mt-2" name="old_password" id="old_password">
                </c:if>
                <c:if test="${wrongPassword==true}">
                    <input type="password" class="form-control mt-2 is-invalid" name="old_password" id="old_password">
                    <c:remove var="wrongPassword" scope="session"/>
                </c:if>
                <div class="invalid-feedback">
                    <fmt:message bundle="${loc}" key="lang.wrong_password"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="new_password" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.new_password"/></span>
                </label>
                <input type="password" class="form-control mt-2" name="new_password" id="new_password">
                <div class="invalid-feedback">
                    <fmt:message bundle="${loc}" key="lang.wrong_password_format"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="confirm_new_password" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.confirm_new_password"/></span>
                </label>
                <input type="password" class="form-control mt-2" name="confirm_new_password" id="confirm_new_password">
                <div class="invalid-feedback">
                    <fmt:message bundle="${loc}" key="lang.password_is_not_the_same"/>
                </div>
            </div>
        </div>
        <div class="row justify-content-end">
            <span class="col-auto px-1 mb-2">
                <button class="btn btn-jade" type="submit"><fmt:message bundle="${loc}" key="lang.apply"/></button>
            </span>
        </div>
    </form>
    <script src="${pageContext.request.contextPath}/resources/js/validator/validator.js"></script>
    <script>
        window.onload = (function () {
            validateProfile();
            validateProfilePassword();
        });
    </script>
</div>
</body>
</html>
