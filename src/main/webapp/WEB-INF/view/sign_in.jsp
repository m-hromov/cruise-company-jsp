<%--
  Created by IntelliJ IDEA.
  User: hromov
  Date: 9/3/2022
  Time: 8:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <jsp:include page="${pageContext.request.contextPath}/page_elements/common_scripts_and_css.jsp"/>
    <script src="https://www.google.com/recaptcha/enterprise.js" async
            defer></script>
    <script>
        grecaptcha.enterprise.getResponse()
        function onSubmit(token) {
            $('<input>').attr({
                type: 'hidden',
                value: token,
                name: 'g-recaptcha-response'
            }).appendTo('signInForm')
            document.getElementById("signInForm").submit();
        }
    </script>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="localization.lang" var="loc"/>
    <title><fmt:message bundle="${loc}" key="lang.sign_in"/> | Cruise company</title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/page_elements/header.jsp"/>
<c:if test="${sessionScope.error==true}">
    <div class="container alert alert-danger bd-search" role="alert">
        <fmt:message bundle="${loc}" key="lang.error_password"/>
    </div>
    <c:remove var="error" scope="session"/>
</c:if>
<div class="container d-flex auth-box justify-content-center align-items-center">

    <form id="signInForm" action="${pageContext.request.contextPath}/cruise/do_sign_in"
          method="post">
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="email" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}"
                                                                                 key="lang.email"/></span>
                </label>
                <input type="text" class="form-control mt-2" name="email" id="email" required>
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
                <input type="password" class="form-control mt-2" name="password" id="password" required>
            </div>
        </div>
        <div class="row justify-content-end">
            <span class="col-auto px-1 mb-2">
                <button class="btn btn-jade g-recaptcha" data-sitekey="6Lex-toiAAAAAB2huVa7HT-YlYeqavRXZ-dkDrk_"
                        data-callback="onSubmit">
                    <fmt:message bundle="${loc}" key="lang.sign_in"/>
                </button>
            </span>
        </div>
    </form>
    <script src="${pageContext.request.contextPath}/resources/js/validator/validator.js"></script>
    <script>
        window.onload = (function () {
            validateSignIn()
        });
    </script>
</div>
</body>
</html>
