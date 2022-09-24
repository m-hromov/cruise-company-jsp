<%--
  Created by IntelliJ IDEA.
  User: hromov
  Date: 9/23/2022
  Time: 2:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <base href="${pageContext.servletContext.contextPath}/">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="js/chosen-js/chosen.css">
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"
            integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
            crossorigin="anonymous"></script>

    <script src="js/chosen-js/chosen.jquery.min.js"
            crossorigin="anonymous"></script>
    <script>
        $(document).ready(function () {
            $(".chosen-select").chosen();
        });
    </script>
    <title>Add Cruise | Cruise company</title>
</head>
<body>
<jsp:include page="page_elements/header.jsp"/>

<div class="container auth-box">
    <form class="col flex-column" action="index.jsp" method="post" enctype="multipart/form-data">
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="name" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1">Ship name</span>
                </label>
                <input type="text" class="form-control mt-2" name="name" id="name">
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="capacity" class="ms-2 position-absolute mtext2">
                    <span class="h6 small bg-white text-muted px-1">Capacity</span>
                </label>
                <input id="capacity" class="form-control" type="text" name="capacity"/>
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="photo" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1">Photo</span>
                </label>
                <input type="file" class="form-control mt-2" id="photo" >
            </div>
        </div>

        <div class="row justify-content-end">
            <span class="col-auto px-1 mb-2">
                <button class="btn btn-jade" type="submit">Add</button>
            </span>
        </div>

    </form>
</div>
</body>

</html>

