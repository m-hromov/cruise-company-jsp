
<%--
  Created by IntelliJ IDEA.
  User: hromov
  Date: 9/20/2022
  Time: 1:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html >
<head>
    <jsp:include page="${pageContext.request.contextPath}/page_elements/common_scripts_and_css.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/chosen-js/chosen.min.css"/>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="localization.lang" var="loc"/>
    <title><fmt:message bundle="${loc}" key="lang.add_cruise"/> | Cruise company</title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/page_elements/header.jsp"/>
<jsp:useBean id="listStation" scope="request" type="java.util.List<com.cruisecompany.entity.Station>"/>
<jsp:useBean id="listShip" scope="request" type="java.util.List<com.cruisecompany.entity.Ship>"/>
<div class="container auth-box">
    <form class="col flex-column" action="${pageContext.request.contextPath}/cruise/do_add_cruise" method="post" enctype="multipart/form-data">
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="ship" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.ship"/></span>
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
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.price"/></span>
                </label>
                <input type="text" class="form-control mt-2" name="price" id="price">
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="date_departure" class="ms-2 position-absolute mtext2">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.departure"/></span>
                </label>
                <input id="date_departure" class="form-control" type="date" name="date_departure"/>
            </div>
            <div class="col px-1 mb-2">
                <label for="date_arrival" class="ms-2 position-absolute mtext2">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.arrival"/></span>
                </label>
                <input id="date_arrival" class="form-control" type="date" name="date_arrival"/>
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="time_departure" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.time"/></span>
                </label>
                <input type="time" class="form-control mt-2" id="time_departure" name="time_departure">
            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="stations" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.stations"/></span>
                </label>
                <select class="chosen-select form-control mt-2" id="stations"  tabindex="8" multiple
                        name="stations" data-placeholder="<fmt:message bundle="${loc}" key="lang.stations_placeholder"/>">
                    <c:forEach items="${listStation}" var="station">
                        <option value="${station.id}">${station.city}, ${station.country}</option>
                    </c:forEach>
                </select>

            </div>
        </div>
        <div class="row">
            <div class="col px-1 mb-2">
                <label for="description" class="ms-2 position-absolute mtext">
                    <span class="h6 small bg-white text-muted px-1"><fmt:message bundle="${loc}" key="lang.description"/></span>
                </label>
                <textarea class="form-control mt-2" name="description" id="description" rows="4" cols="40"
                          maxlength="1024"></textarea>
            </div>
        </div>
        <div class="row justify-content-end">
            <span class="col-auto px-1 mb-2">
                <button class="btn btn-jade" type="submit"><fmt:message bundle="${loc}" key="lang.add"/></button>
            </span>
        </div>

    </form>
</div>
<script src="${pageContext.request.contextPath}/resources/js/chosen-js/chosen.jquery.min.js"
        crossorigin="anonymous"></script>
<script type="text/javascript">
    $(function () {
        $(".chosen-select").chosen();
    });
</script>
</body>
</html>
