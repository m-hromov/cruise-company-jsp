<%--
  Created by IntelliJ IDEA.
  User: hromov
  Date: 9/3/2022
  Time: 8:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <title>Sign up | Cruise company</title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/page_elements/header.jsp"/>
<div class="container auth-box">
    <form id="signUpForm" class="col flex-column"
          action="${pageContext.request.contextPath}/cruise/sign_up"
          method="post">
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="first_name" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1">First name</span>
                </label>
                <input type="text" class="form-control mt-2" name="first_name" id="first_name" required>
                <div class="invalid-feedback">
                    Wrong input format
                </div>
            </div>
            <div class="col px-1 mb-2">
                <label for="last_name" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1">Last name</span>
                </label>
                <input type="text" class="form-control mt-2" name="last_name" id="last_name">
                <div class="invalid-feedback">
                    Wrong input format
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="phone" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1">Phone</span>
                </label>
                <input type="text" class="form-control mt-2" name="phone" id="phone">
                <div class="invalid-feedback">
                    Wrong phone format
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="email" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1">Email</span>
                </label>
                <input type="text" class="form-control mt-2" name="email" id="email">
                <div class="invalid-feedback">
                    Wrong email format
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="password" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1">Password</span>
                </label>
                <input type="password" class="form-control mt-2" name="password" id="password">
                <div class="invalid-feedback">
                    Minimum eight characters, at least one letter and one number
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="confirm_password" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1">Confirm password</span>
                </label>
                <input type="password" class="form-control mt-2" name="confirm_password"
                       id="confirm_password">
                <div class="invalid-feedback">
                    Password is not the same
                </div>
            </div>
        </div>
        <div class="row justify-content-end">
            <span class="col-auto px-1 mb-2">
                <button class="btn btn-jade" type="submit">Sign up</button>
            </span>
        </div>
    </form>
    <script src="${pageContext.request.contextPath}/resources/js/validator/validator.js"></script>
    <script>
        window.onload = (function (){validateSignUp()});
    </script>
</div>
</body>
</html>