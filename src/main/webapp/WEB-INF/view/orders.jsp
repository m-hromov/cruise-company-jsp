<%--
  Created by IntelliJ IDEA.
  User: hromov
  Date: 9/18/2022
  Time: 8:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="localization.lang" var="loc"/>
    <title><fmt:message bundle="${loc}" key="lang.orders"/> | Cruise company</title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/page_elements/header.jsp"/>
<jsp:useBean id="orderList" scope="request" type="java.util.List<com.cruisecompany.dto.PassengerOrderDTO>"/>
<div class="container" style="max-width: 90% !important;">
    <div class="d-flex flex-column">
        <c:forEach items="${orderList}" var="order">
            <div class="row border-bottom br-color">
                <div class="col-1 d-flex flex-column bg-table-col1 justify-content-center">
                    <div>
                        #${order.orderId}
                    </div>
                </div>
                <div class="col-5 d-flex flex-column bg-table-col2 justify-content-center ">
                    <div>
                        <fmt:message bundle="${loc}" key="lang.full_name"/>:
                            ${order.firstName.concat(" ").concat(order.lastName)}
                    </div>
                    <div>
                        <fmt:message bundle="${loc}" key="lang.email"/>:
                            ${order.email}
                    </div>
                    <div>
                        <fmt:message bundle="${loc}" key="lang.phone"/>:
                            ${order.phone}
                    </div>
                    <c:if test="${not empty order.documentPath}">
                    <a class="link-dark" href="${pageContext.request.contextPath}/${order.documentPath}"
                       target="_blank"><fmt:message bundle="${loc}" key="lang.view_document"/></a>
                    </c:if>

                </div>
                <div class="col d-flex flex-column bg-table-col1 justify-content-center ">
                    <div>
                        <fmt:message bundle="${loc}" key="lang.paid"/>:
                            ${order.paid}
                    </div>
                    <div>
                        <fmt:message bundle="${loc}" key="lang.blocked"/>:
                            ${order.banned}
                    </div>
                    <div>
                        <fmt:message bundle="${loc}" key="lang.confirmed"/>:
                            ${order.confirmed}
                    </div>
                    <a class="link-dark"
                       href="${pageContext.request.contextPath}/cruise/find_cruise?cruise_id=${order.cruiseId}"
                       target="_blank"><fmt:message bundle="${loc}" key="lang.view_cruise"/></a>
                </div>
                <div class="col-2 d-flex flex-column bg-table-col2 justify-content-center">
                    <form class="mx-0 my-auto row" method="post"
                          action="${pageContext.request.contextPath}/cruise/do_confirm_order">
                        <input type="hidden" name="order_id" value="${order.orderId}">
                        <c:if test="${order.confirmed==false && order.banned==false}">
                            <button class="btn btn-jade" type="submit"><fmt:message bundle="${loc}" key="lang.confirm"/></button>
                        </c:if>
                        <c:if test="${order.banned==true}">
                            <button class="btn btn-jade" type="submit" disabled><fmt:message bundle="${loc}" key="lang.confirm"/></button>
                        </c:if>
                        <c:if test="${order.confirmed==true}">
                            <button class="btn btn-jade" type="submit" disabled><fmt:message bundle="${loc}" key="lang.confirmed"/></button>
                        </c:if>
                    </form>
                    <c:if test="${order.banned==false}">
                        <form class="mx-0 mt-2 mb-auto row" method="post"
                              action="${pageContext.request.contextPath}/cruise/do_block_order?block=true">
                            <input type="hidden" name="order_id" value="${order.orderId}">
                            <c:if test="${order.confirmed==false}">
                                <button class="btn btn-outline-danger" type="submit"><fmt:message bundle="${loc}" key="lang.block"/></button>
                            </c:if>
                            <c:if test="${order.confirmed==true}">
                                <button class="btn btn-outline-danger" type="submit" disabled><fmt:message bundle="${loc}" key="lang.block"/></button>
                            </c:if>
                        </form>
                    </c:if>
                    <c:if test="${order.banned==true}">
                        <form class="mx-0 mt-2 mb-auto row" method="post"
                              action="${pageContext.request.contextPath}/cruise/do_block_order?block=false">
                            <input type="hidden" name="order_id" value="${order.orderId}">
                            <c:if test="${order.confirmed==false}">
                                <button class="btn btn-outline-danger" type="submit"><fmt:message bundle="${loc}" key="lang.unblock"/></button>
                            </c:if>
                            <c:if test="${order.confirmed==true}">
                                <button class="btn btn-outline-danger" type="submit" disabled><fmt:message bundle="${loc}" key="lang.unblock"/></button>
                            </c:if>
                        </form>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
