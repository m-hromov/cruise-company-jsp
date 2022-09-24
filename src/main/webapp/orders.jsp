<%--
  Created by IntelliJ IDEA.
  User: hromov
  Date: 9/18/2022
  Time: 8:01 PM
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
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
            crossorigin="anonymous"></script>
    <title>Orders | Cruise company</title>
</head>
<body>
<jsp:include page="page_elements/header.jsp"/>
<jsp:useBean id="orderList" scope="request" type="java.util.List<com.cruisecompany.db.dto.PassengerOrderDTO>"/>
<div class="container" style="max-width: 90% !important;">
    <div class="d-flex flex-column">
        <c:forEach items="${orderList}" var="order">
            <div class="row border-bottom br-color">
                <div class="col-auto d-flex flex-column bg-table-col1 justify-content-center">
                    <div>
                        #${order.orderId}
                    </div>
                </div>
                <div class="col d-flex flex-column bg-table-col2 justify-content-center ">
                    <div>
                        Full Name:
                        ${order.firstName.concat(" ").concat(order.lastName)}
                    </div>
                    <div>
                        Email:
                        ${order.email}
                    </div>
                    <div>
                        Phone:
                        ${order.phone}
                    </div>
                    <a class="col-auto link-dark" href="${order.documentPath}" target="_blank">View document</a>

                </div>
                <div class="col d-flex flex-column bg-table-col1 justify-content-center ">
                    <div>
                        Paid:
                            ${order.paid}
                    </div>
                    <div>
                        Banned:
                            ${order.banned}
                    </div>
                </div>
                <div class="col-auto d-flex flex-column bg-table-col2 justify-content-center">
                    <a class="link-dark" href="cruise/find_cruise?cruise_id=${order.cruiseId}" target="_blank">View cruise</a>
                    <a class="link-dark" href="cruise/find_cruise">Block</a>
                    <a class="link-dark" href="cruise/find_cruise">Approve</a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
