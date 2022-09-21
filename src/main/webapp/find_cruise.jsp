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
    <jsp:useBean id="listCruise" scope="request" type="java.util.List<com.cruisecompany.db.dto.CruiseShowDTO>"/>
    <c:forEach items="${listCruise}" var="cruise">
        <div class="container item-box justify-content-center text-center bd-search">
            <div class="row">
                <div class="col ">
                    <img src="${cruise.photoPath}" height="360" width="480" alt="ship"/>
                </div>
                <div class="col d-flex flex-column ">
                    <div class="text-start fw-bold fs-4">Getaway from ${cruise.start}, Ukraine</div>
                    <div class="text-start fw-bold fs-6 mt-sm-0">${cruise.shipName}</div>
                    <div class="align-items-center ">
                        <div class="d-flex flex-row">
                            <div class="sm-circle bg-jade me-2">
                                <div class="fw-bold fs-5 text-white me-none">${cruise.daysTotal}</div>
                                <div class="text-white">DAY</div>
                            </div>
                            <div class="text-start align-self-center">
                                Start: ${cruise.start} -> End: ${cruise.end}
                            </div>
                        </div>
                    </div>
                    <p class="text-start text-wrap">${cruise.description}</p>
                    <div class="row align-self-end mt-auto">
                        <div class="col fs-4 fw-bold me-2">${cruise.price}$</div>
                        <a class="col btn btn-jade-reversed">Buy</a>
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
