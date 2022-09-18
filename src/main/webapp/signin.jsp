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
    <link rel="stylesheet" href="static/css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
            crossorigin="anonymous"></script>
    <title>Sign in | Cruise company</title>
</head>
<body>
<jsp:include page="page_elements/header.jsp"/>
<div class="container d-flex auth-box justify-content-center align-items-center">
        <form action="./cruise/signin" method="post">
            <div class="row">
                <div class="col px-1 mb-2">
                    <label for="email" class="ms-2 position-absolute mtext">
                        <span class="h6 small bg-white text-muted px-1">Email</span>
                    </label>
                    <input type="text" class="form-control mt-2"name="email" id="email">
                </div>
            </div>
            <div class="row">
                <div class="col px-1 mb-2">
                    <label for="password" class="ms-2 position-absolute mtext">
                        <span class="h6 small bg-white text-muted px-1">Password</span>
                    </label>
                    <input type="password" class="form-control mt-2" name="password" id="password">
                </div>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="remember_me" id="flexCheckDefault">
                <label class="form-check-label" for="flexCheckDefault">
                    Remember me
                </label>
            </div>
            <div class="row justify-content-end">
            <span class="col-auto px-1 mb-2">
                <button class="btn btn-jade" type="submit">Sign up</button>
            </span>
            </div>
        </form>
</div>
</body>
</html>
