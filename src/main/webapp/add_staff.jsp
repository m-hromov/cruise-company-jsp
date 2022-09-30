<%--
  Created by IntelliJ IDEA.
  User: hromov
  Date: 9/29/2022
  Time: 7:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
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
  <title>Add Staff | Cruise company</title>
</head>
<body>
<jsp:include page="page_elements/header.jsp"/>

<div class="container auth-box">
  <form class="col flex-column" action="cruise/add_staff" method="post" enctype="multipart/form-data">
    <div class="row">
      <div class="col px-1 mb-2">
        <label for="first_name" class="ms-2 position-absolute mtext">
          <span class="h6 small bg-white text-muted px-1">First name</span>
        </label>
        <input type="text" class="form-control mt-2" name="first_name" id="first_name">
      </div>
    </div>
    <div class="row">
      <div class="col px-1 mb-2">
        <label for="last_name" class="ms-2 position-absolute mtext">
          <span class="h6 small bg-white text-muted px-1">Last name</span>
        </label>
        <input type="text" class="form-control mt-2" name="last_name" id="last_name">
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
        <label for="speciality" class="ms-2 position-absolute mtext">
          <span class="h6 small bg-white text-muted px-1">Speciality</span>
        </label>
        <input type="text" class="form-control mt-2" id="speciality" >
      </div>
    </div>
    <div class="row">
      <div class="col px-1 mb-2">
        <label for="ship" class="ms-2 position-absolute mtext">
          <span class="h6 small bg-white text-muted px-1">Ship</span>
        </label>
        <input list="ships" type="text" class="form-control mt-2" name="ship" id="ship">
        <datalist id="ships">
          <c:forEach items="${listShip}" var="ship">
            <option value="${ship.id}">${ship.name}</option>
          </c:forEach>
        </datalist>
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

