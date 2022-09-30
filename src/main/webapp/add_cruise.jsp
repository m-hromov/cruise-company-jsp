
<%--
  Created by IntelliJ IDEA.
  User: hromov
  Date: 9/20/2022
  Time: 1:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<jsp:useBean id="listStation" scope="request" type="java.util.List<com.cruisecompany.entity.Station>"/>
<jsp:useBean id="listShip" scope="request" type="java.util.List<com.cruisecompany.entity.Ship>"/>
<div class="container auth-box">
    <form class="col flex-column" action="cruise/add_cruise" method="post" enctype="multipart/form-data">
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
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="price" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1">Price</span>
                </label>
                <input type="text" class="form-control mt-2" name="price" id="price">
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="date_departure" class="ms-2 position-absolute mtext2">
                    <span class="h6 small bg-white text-muted px-1">Date departure</span>
                </label>
                <input id="date_departure" class="form-control" type="date" name="date_departure"/>
            </div>
            <div class="col px-1 mb-2">
                <label for="date_arrival" class="ms-2 position-absolute mtext2">
                    <span class="h6 small bg-white text-muted px-1">Date arrival</span>
                </label>
                <input id="date_arrival" class="form-control" type="date" name="date_arrival"/>
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="time_departure" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1">Time departure</span>
                </label>
                <input type="time" class="form-control mt-2" id="time_departure" name="time_departure">
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="stations" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1">Stations</span>
                </label>
                <select class="chosen-select form-control mt-2" id="stations"  tabindex="8" multiple
                        name="stations" data-placeholder="Select stations in correct order">
                    <c:forEach items="${listStation}" var="station">
                        <option value="${station.id}">${station.city}, ${station.country}</option>
                    </c:forEach>
                </select>

            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="description" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1">Description</span>
                </label>
                <textarea class="form-control mt-2" name="description" id="description" rows="4" cols="40"
                          maxlength="1024"></textarea>
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
