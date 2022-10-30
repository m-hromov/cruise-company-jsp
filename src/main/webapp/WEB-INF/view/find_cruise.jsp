<%--
  Created by IntelliJ IDEA.
  User: hromov
  Date: 9/4/2022
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="utils" uri="WEB-INF/tld/utils.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"
            integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
            crossorigin="anonymous"></script>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="localization.lang" var="loc"/>
    <title><fmt:message bundle="${loc}" key="lang.find_cruise"/> | Cruise company</title>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/page_elements/header.jsp"/>
<div class="container">
    <jsp:useBean id="listCruise" scope="request" type="java.util.List<com.cruisecompany.dto.CruiseShowDTO>"/>
    <c:if test="${error eq true}">
        <div class="container alert alert-danger bd-search" role="alert">
            <fmt:message bundle="${loc}" key="lang.error_not_found"/>
        </div>
    </c:if>
    <c:if test="${empty param.cruise_id}">
        <div class="mt-5">
            <div class="row justify-content-center">
                <div class="col-auto">
                    <ul class="pagination ">
                        <c:forEach begin="1" end="${pageAmount}" var="p">
                            <li class="page-item">
                                    <%--<a class="page-link ${(p eq param.page || p eq page) ? 'active':''}"
                                       href="${pageContext.request.contextPath}?page=${p}&dateFrom=${dateFrom}&dateTo=${dateTo}&durationFrom=${durationFrom}&durationTo=${durationTo}">${p}</a>--%>
                                <button class="page-link ${(p eq page || (p eq 1 && empty page)) ? 'active':''}"
                                        value="${p}">${p}</button>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="col-auto">
                    <select class="form-select" id="limit" aria-label="Limit">
                        <option value="1">1</option>
                        <option value="2" ${limit eq 2 ? 'selected':''}>2</option>
                        <option value="5" ${limit eq 5 ? 'selected':''}>5</option>
                        <option value="20" ${limit eq 20 ? 'selected':''}>20</option>
                    </select>
                </div>
            </div>
        </div>
    </c:if>
    <c:forEach items="${listCruise}" var="cruise">
        <div class="container item-box justify-content-center text-center bd-search">
            <div class="row">
                <div class="col ">
                    <img src="${pageContext.request.contextPath}/${cruise.photoPath}" height="360"
                         width="480" alt="ship"/>

                </div>
                <div class="col d-flex flex-column ">
                    <div class="text-start fw-bold fs-4">
                        <fmt:message bundle="${loc}" key="lang.getaway"/> ${cruise.start.city}, ${cruise.start.country}
                    </div>
                    <div class="text-start fw-bold fs-6 mt-sm-0">${cruise.shipName}</div>
                    <div class="align-items-center ">
                        <div class="d-flex flex-row">
                            <div class="sm-circle bg-jade me-2">
                                <div class="fw-bold fs-5 text-white me-none">${cruise.daysTotal}</div>
                                <div class="text-white">
                                    <fmt:message bundle="${loc}" key="lang.day"/>
                                </div>
                            </div>
                            <div class="text-start align-self-center">
                                <fmt:message bundle="${loc}" key="lang.start"/>: ${cruise.start.city} ->
                                <fmt:message bundle="${loc}" key="lang.end"/>: ${cruise.end.city}
                            </div>
                        </div>
                    </div>
                    <p class="text-start text-wrap mb-0">
                        <fmt:message bundle="${loc}" key="lang.time"/>: ${cruise.timeDeparture}
                    </p>
                    <p class="text-start text-wrap mt-0">
                        <fmt:message bundle="${loc}" key="lang.departure"/>: ${cruise.dateDeparture}
                        <fmt:message bundle="${loc}" key="lang.arrival"/>: ${cruise.dateArrival}</p>
                    <p class="text-start text-wrap">${cruise.description}</p>
                    <div class="row align-self-end mt-auto">
                        <div class="col fs-4 fw-bold me-2">
                            <utils:currency value="${cruise.price}" convertedcurr="${lang eq 'en' ? 'USD' : 'UAH'}"/>
                        </div>
                        <c:if test="${sessionScope.role.equals('USER')}">
                            <form class="col" action="${pageContext.request.contextPath}/cruise/buy_cruise"
                                  method="post">
                                <input type="hidden" name="cruise_id" value="${cruise.id}">
                                <button type="submit"
                                        class="btn btn-jade-reversed ${cruise.dateArrival.isBefore(currentDate) ? 'disabled':''}">
                                    <fmt:message bundle="${loc}" key="lang.buy"/>
                                </button>
                            </form>
                        </c:if>
                        <c:if test="${sessionScope.role=='VISITOR'}">
                            <a href="${pageContext.request.contextPath}/cruise/sign_in"
                               class="col btn btn-jade-reversed ${cruise.dateArrival.isBefore(currentDate) ? 'disabled':''}">
                                <fmt:message bundle="${loc}" key="lang.buy"/>
                            </a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>

</div>
<div class="offcanvas offcanvas-start" data-bs-scroll="true" tabindex="-1" id="offcanvasWithBothOptions"
     aria-labelledby="offcanvasWithBothOptionsLabel">
    <div class="offcanvas-header bg-jade">
        <h5 class="offcanvas-title" id="offcanvasWithBothOptionsLabel" style="color:white">
            <fmt:message bundle="${loc}" key="lang.filter_cruises"/>
        </h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body">
        <form class="col" action="${pageContext.request.contextPath}/cruise/find_cruise" method="get">
            <input type="hidden" name="limit" value="${limit}">
            <div class="row mb-2">
                <div class="col flex-column ">
                    <label for="dateFrom"><fmt:message bundle="${loc}" key="lang.from"/>:</label>
                    <input id="dateFrom" class="form-control" type="date" name="dateFrom" value="${dateFrom}"
                           onchange="setMinDateForDateTo()"/>
                </div>
                <div class="col flex-column ">
                    <label for="dateTo"><fmt:message bundle="${loc}" key="lang.to"/>:</label>
                    <input id="dateTo" class="form-control" type="date" name="dateTo" value="${dateTo}"
                           onchange="setMaxDateForDateFrom()"/>
                </div>
            </div>
            <div class="fw-bold me-none">
                <hr>
            </div>
            <div class="col flex-column mb-2">
                <label for="duration"><fmt:message bundle="${loc}" key="lang.duration"/>:</label>
                <select class="form-select" aria-label="Select duration" name="duration" id="duration">
                    <option ${empty param.duration ? 'selected':''}><fmt:message bundle="${loc}" key="lang.all"/></option>
                    <option value="1" ${param.duration eq '1' ? 'selected':''}>2 - 5 <fmt:message bundle="${loc}" key="lang.days"/></option>
                    <option value="2" ${param.duration eq '2' ? 'selected':''}>6 - 9 <fmt:message bundle="${loc}" key="lang.days"/></option>
                    <option value="3" ${param.duration eq '3' ? 'selected':''}>10+ <fmt:message bundle="${loc}" key="lang.days"/></option>
                </select>
            </div>
            <div class="d-flex flex-row justify-content-end">
                <button class="btn btn-jade-reversed text-wrap" type="submit"><fmt:message bundle="${loc}" key="lang.apply"/></button>
            </div>
        </form>
    </div>
</div>
<script>
    document.querySelectorAll("#limit").forEach(e => {
        e.addEventListener(
            'change',
            function (event) {
                let url = new URL(document.location.href);
                url.searchParams.set("page", "1");
                url.searchParams.set("limit", e.value);
                document.location.href = url.toString();
            },
            false
        );
    });
    document.querySelectorAll("button.page-link").forEach(e => {
        e.addEventListener(
            'click',
            function (event) {
                let url = new URL(document.location.href);
                url.searchParams.set("page", e.value);
                document.location.href = url.toString();
            },
            false
        );
    });
</script>
<script src="${pageContext.request.contextPath}/resources/js/validator/filter_validator.js"></script>
<jsp:include page="${pageContext.request.contextPath}/page_elements/footer.jsp"/>
</body>
</html>
