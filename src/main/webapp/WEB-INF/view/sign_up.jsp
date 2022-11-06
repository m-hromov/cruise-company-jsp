<%--
  Created by IntelliJ IDEA.
  User: hromov
  Date: 9/3/2022
  Time: 8:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="${pageContext.request.contextPath}/page_elements/common_scripts_and_css.jsp"/>
    <script src="https://www.google.com/recaptcha/enterprise.js"></script>
    <script>
        function onCheck(token) {
            $('<input>').attr({
                type: 'hidden',
                value: token,
                name: 'g-recaptcha-response'
            }).appendTo('signUpForm')
        }
    </script>

    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="localization.lang" var="loc"/>
    <title><fmt:message bundle="${loc}" key="lang.sign_up"/> | Cruise company</title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/page_elements/header.jsp"/>
<c:if test="${sessionScope.emailExists==true}">
    <div class="container alert alert-danger bd-search" role="alert">
        <fmt:message bundle="${loc}" key="lang.error_email_exists"/>
        <a href="${pageContext.request.contextPath}/cruise/sign_in">
            <fmt:message bundle="${loc}" key="lang.sign_in"/>
        </a>
    </div>
    <c:remove var="emailExists" scope="session"/>
</c:if>
<div id="recaptcha-alert" class="container alert alert-danger bd-search" role="alert" hidden>
    <fmt:message bundle="${loc}" key="lang.recaptcha_failed"/>
</div>
<div class="container auth-box">
    <form id="signUpForm" class="col flex-column"
          action="${pageContext.request.contextPath}/cruise/do_sign_up"
          method="post">
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="first_name" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}"
                                                                                 key="lang.first_name"/></span>
                </label>
                <input type="text" class="form-control mt-2" name="first_name" id="first_name" required>
                <div class="invalid-feedback">
                    <fmt:message bundle="${loc}" key="lang.wrong_name"/>
                </div>
            </div>
            <div class="col px-1 mb-2">
                <label for="last_name" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}"
                                                                                 key="lang.last_name"/></span>
                </label>
                <input type="text" class="form-control mt-2" name="last_name" id="last_name">
                <div class="invalid-feedback">
                    <fmt:message bundle="${loc}" key="lang.wrong_name"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="phone" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}"
                                                                                 key="lang.phone"/></span>
                </label>
                <input type="text" class="form-control mt-2" name="phone" id="phone">
                <div class="invalid-feedback">
                    <fmt:message bundle="${loc}" key="lang.wrong_phone"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="email" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}"
                                                                                 key="lang.email"/></span>
                </label>
                <input type="text" class="form-control mt-2" name="email" id="email">
                <div class="invalid-feedback">
                    <fmt:message bundle="${loc}" key="lang.wrong_email"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="password" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}"
                                                                                 key="lang.password"/></span>
                </label>
                <input type="password" class="form-control mt-2" name="password" id="password">
                <div class="invalid-feedback">
                    <fmt:message bundle="${loc}" key="lang.wrong_password_format"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="confirm_password" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}"
                                                                                 key="lang.confirm_password"/></span>
                </label>
                <input type="password" class="form-control mt-2" name="confirm_password"
                       id="confirm_password">
                <div class="invalid-feedback">
                    <fmt:message bundle="${loc}" key="lang.password_is_not_the_same"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="g-recaptcha px-1 mb-2" data-sitekey="6LdfJc0iAAAAANPYPinSKq5pGbt9EgLx0SpsUqwQ" data-callback="onCheck"></div>
        </div>
        <div class="row justify-content-end">
            <span class="col-auto px-1 mb-2">
                <button class="btn btn-jade" type="submit"><fmt:message bundle="${loc}" key="lang.sign_up"/></button>
            </span>
        </div>
    </form>
    <script src="${pageContext.request.contextPath}/resources/js/validator/validator.js"></script>
    <script>
        window.onload = (function () {
            validateSignUp()
        });
    </script>
</div>
</body>
</html>
