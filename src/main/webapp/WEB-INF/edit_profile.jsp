<%--
  Created by IntelliJ IDEA.
  User: hromov
  Date: 9/20/2022
  Time: 1:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <base href="${pageContext.servletContext.contextPath}/">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../css/style.css"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"
            integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
            crossorigin="anonymous"></script>
    <title>Edit Profile | Cruise company</title>
</head>
<body>
<jsp:include page="../page_elements/header.jsp"/>
<div class="container auth-box parent">
    <form class="col flex-column" action="cruise/edit_profile" method="post">
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="fname" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1">First name</span>
                </label>
                <input type="text" class="form-control mt-2" name="fname" id="fname">
            </div>
            <div class="col px-1 mb-2">
                <label for="lname" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1">Last name</span>
                </label>
                <input type="text" class="form-control mt-2" name="lname" id="lname">
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="phone" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1">Phone</span>
                </label>
                <input type="text" class="form-control mt-2" name="phone" id="phone">
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="email" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1">Email</span>
                </label>
                <input type="text" class="form-control mt-2" name="email" id="email">
            </div>
        </div>
        <div class="row justify-content-end">
            <span class="col-auto px-1 mb-2">
                <button class="btn btn-jade" type="submit">Apply</button>
            </span>
        </div>
    </form>
    <div class="box-divider">
        <hr/>
    </div>
    <form class="col flex-column" action="cruise/signup" method="post" enctype="multipart/form-data">
        <div class="row">
            <div class="col px-1 my-2">

            </div>
        </div>
        <div class="row">
            <span class="col-auto px-1 my-auto">
                <label for="doc" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1">Document</span>
                </label>
                <input type="file" class="form-control mt-2" name="doc" id="doc">
            </span>
            <span class="col-auto px-1 ms-auto">
                <button class="btn btn-jade mt-2" type="submit">Upload</button>
            </span>
        </div>
    </form>
    <div class="box-divider">
        <hr/>
    </div>
    <form class="col flex-column" action="cruise/signup" method="post">
        <div class="row pt-4">
            <div class="col px-1 mb-2">
                <label for="old_password" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1">Old password</span>
                </label>
                <input type="password" class="form-control mt-2" name="old_password" id="old_password">
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="new_password" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1">Password</span>
                </label>
                <input type="password" class="form-control mt-2" name="new_password" id="new_password">
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="confirm_new_password" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1">Confirm password</span>
                </label>
                <input type="password" class="form-control mt-2" name="confirm_new_password" id="confirm_new_password">
            </div>
        </div>
        <div class="row justify-content-end">
            <span class="col-auto px-1 mb-2">
                <button class="btn btn-jade" type="submit">Apply</button>
            </span>
        </div>
    </form>
</div>
</body>
</html>
