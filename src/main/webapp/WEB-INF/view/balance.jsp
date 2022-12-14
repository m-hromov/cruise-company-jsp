<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: hromov
  Date: 9/22/2022
  Time: 6:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="${pageContext.request.contextPath}/page_elements/common_scripts_and_css.jsp"/>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="localization.lang" var="loc"/>
    <title><fmt:message bundle="${loc}" key="lang.balance"/> | Cruise company</title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/page_elements/header.jsp"/>
<div class="container auth-box">
    <form id="balanceForm" class="col flex-column" action="${pageContext.request.contextPath}/cruise/do_edit_money"
          method="post">
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="money" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.money"/></span>
                </label>
                <input type="text" class="form-control mt-2" name="money" id="money">
                <div class="invalid-feedback">
                    <fmt:message bundle="${loc}" key="lang.wrong_money"/>
                </div>
            </div>
        </div>
        <div class="row justify-content-end">
            <span class="col-auto px-1 mb-2">
                <button class="btn btn-jade" type="submit"><fmt:message bundle="${loc}" key="lang.add"/></button>
            </span>
        </div>
    </form>
    <script src="${pageContext.request.contextPath}/resources/js/validator/validator.js"></script>
    <script>
        window.onload = (function () {
            validateBalance()
        });
    </script>
</div>
</body>
</html>
