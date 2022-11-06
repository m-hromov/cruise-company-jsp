<%--
  Created by IntelliJ IDEA.
  User: hromov
  Date: 9/22/2022
  Time: 6:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="utils" uri="WEB-INF/tld/utils.tld" %>
<html>
<head>
    <jsp:include page="${pageContext.request.contextPath}/page_elements/common_scripts_and_css.jsp"/>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="localization.lang" var="loc"/>
    <title><fmt:message bundle="${loc}" key="lang.my_orders"/> | Cruise company</title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/page_elements/header.jsp"/>
<div class="container">
    <c:if test="${sessionScope.lowMoney==true}">
        <div class="container alert alert-danger bd-search" role="alert">
            <fmt:message bundle="${loc}" key="lang.error_not_enough_money"/>
        </div>
        <c:remove var="lowMoney" scope="session"/>
    </c:if>
    <jsp:useBean id="ticketList" scope="request" type="java.util.List<com.cruisecompany.entity.Ticket>"/>
    <c:forEach items="${ticketList}" var="ticket">
        <div class="container item-box justify-content-center text-center bd-search">
            <div class="row">
                <div class="col ">
                    <div class="container" style="position: relative">
                        <img src="${pageContext.request.contextPath}/${ticket.cruise.ship.photoPath}" height="360"
                            width="480" alt="ship"/>
                        <c:if test="${ticket.cruise.dateArrival.isBefore(currentDate)}">
                            <div class="finished"> <fmt:message bundle="${loc}" key="lang.COMPLETED"/></div>
                        </c:if>
                    </div>
                </div>
                <div class="col d-flex flex-column ">
                    <div class="text-start fw-bold fs-4">
                        <fmt:message bundle="${loc}" key="lang.getaway"/>
                            ${ticket.cruise.stationList.get(0).city}, ${ticket.cruise.stationList.get(0).country}
                    </div>
                    <div class="text-start fw-bold fs-6 mt-sm-0">${ticket.cruise.ship.name}</div>
                    <div class="align-items-center ">
                        <div class="d-flex flex-row">
                            <div class="sm-circle bg-jade me-2">
                                <div class="fw-bold fs-5 text-white me-none">${ticket.cruise.daysTotal}</div>
                                <div class="text-white"><fmt:message bundle="${loc}" key="lang.day"/></div>
                            </div>
                            <div class="text-start align-self-center">
                                <fmt:message bundle="${loc}" key="lang.start"/>: ${ticket.cruise.stationList.get(0).city} ->
                                <fmt:message bundle="${loc}" key="lang.end"/>: ${ticket.cruise.stationList.get(ticket.cruise.stationList.size()-1).city}
                            </div>
                        </div>
                    </div>
                    <p class="text-start text-wrap mb-0">
                        <fmt:message bundle="${loc}" key="lang.time"/>:${ticket.cruise.timeDeparture}
                    </p>
                    <p class="text-start text-wrap mt-0">
                        <fmt:message bundle="${loc}" key="lang.departure"/>: ${ticket.cruise.dateDeparture}
                        <fmt:message bundle="${loc}" key="lang.arrival"/>: ${ticket.cruise.dateArrival}
                    </p>
                    <p class="text-start text-wrap">${ticket.cruise.description}</p>
                    <div class="row align-self-end mt-auto">
                        <div class="col fs-4 fw-bold me-2">
                            <utils:currency value="${ticket.cruise.price}" convertedcurr="${lang eq 'en' ? 'USD' : 'UAH'}"/>
                        </div>
                        <c:if test="${ticket.confirmed==true}">
                            <c:if test="${ticket.paid==true}">
                                <button class="col btn btn-jade-reversed" disabled><fmt:message bundle="${loc}" key="lang.paid"/></button>
                            </c:if>
                            <c:if test="${ticket.paid==false}">
                                <form class="col" action="${pageContext.request.contextPath}/cruise/do_pay" method="post">
                                    <input type="hidden" name="order_id" value="${ticket.id}">
                                    <button class="btn btn-jade-reversed" type="submit"><fmt:message bundle="${loc}" key="lang.pay"/></button>
                                </form>
                            </c:if>
                        </c:if>
                        <c:if test="${ticket.confirmed==false}">
                            <c:if test="${ticket.banned==false}">
                                <button class="col btn" disabled><fmt:message bundle="${loc}" key="lang.pending"/></button>
                            </c:if>
                            <c:if test="${ticket.banned==true}">
                                <button class="col btn btn-danger" disabled><fmt:message bundle="${loc}" key="lang.blocked"/></button>
                            </c:if>
                        </c:if>

                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>
