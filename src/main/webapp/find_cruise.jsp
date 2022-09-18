<%--
  Created by IntelliJ IDEA.
  User: hromov
  Date: 9/4/2022
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <base href="${pageContext.servletContext.contextPath}/">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="static/css/style.css"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
            crossorigin="anonymous"></script>
    <title>Cruises | Cruise company</title>
</head>
<body>
<jsp:include page="page_elements/header.jsp"/>
<div class="container">

    <c:forEach items="${listCruise}" var="cruise">
        <div class="container flex-column item-box rounded-0 justify-content-center text-center bd-search">
            <div class="row">
                <div class="col">
                    <img src="${cruise.ship.photoPath}" height="360" width="480" alt="ship"/>
                </div>
                <div class="col">
                    <div class="text-start fw-bold fs-4">Getaway from Odesa, Ukraine</div>
                    <div class="text-start fw-bold fs-6 mt-sm-0">${cruise.ship.name}</div>
                    <div class="row align-items-center ms-auto">
                        <div class="col col-auto sm-circle bg-jade ">
                            <div class="fw-bold fs-5 text-white me-none">${cruise.daysTotal}</div>
                            <div class="text-white">DAY</div>
                        </div>
                        <div class="col text-start">
                            Start: Odesa -> End: Lviv
                        </div>
                    </div>
                    <p class="text-start text-wrap">${cruise.description}</p>
                    <div class="d-flex flex-row justify-content-end">
                        <div class="p-2 fs-4 fw-bold ">
                            ${cruise.price}$
                        </div>
                        <div class="p-2">
                            <button class="btn btn-jade-reversed">Buy</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<div class="offcanvas offcanvas-start" data-bs-scroll="true" tabindex="-1" id="offcanvasWithBothOptions"
     aria-labelledby="offcanvasWithBothOptionsLabel">
    <div class="offcanvas-header bg-jade">
        <h5 class="offcanvas-title" id="offcanvasWithBothOptionsLabel" style="color:white">Filter cruises</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
        <form class="col" action="cruise/find_cruise" method="get">
            <div class="row mb-2">
                <div class="col flex-column ">
                    <label for="dateFrom">From:</label>
                    <input id="dateFrom" class="form-control" type="date" name="dateFrom"/>
                </div>
                <div class="col flex-column ">
                    <label for="dateTo">To:</label>
                    <input id="dateTo" class="form-control" type="date" name="dateTo"/>
                </div>
            </div>
            <div class="fw-bold me-none">
                <hr>
            </div>
            <div class="col flex-column mb-2">
                <label for="duration">Duration:</label>
                <select class="form-select" aria-label="Select duration" name="duration" id="duration">
                    <option value="0" selected>All</option>
                    <option value="1">2 - 5 Days</option>
                    <option value="2">6 - 9 Days</option>
                    <option value="3">10+ Days</option>
                </select>
            </div>
            <div class="d-flex flex-row justify-content-end">
                <button class="btn btn-jade-reversed text-wrap" type="submit">Apply</button>
            </div>
        </form>
    </div>
</div>

<jsp:include page="page_elements/footer.jsp"/>
</body>
</html>
