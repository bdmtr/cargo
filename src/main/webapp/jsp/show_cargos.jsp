<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale" var="lang"/>

<%@include file="/jsp/nav_user.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html">
    <title><fmt:message key="l.guest.cargo" bundle="${lang}"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
</head>

<body>
<main class="m-3">
    <div class="col-md-auto">
        <table class="table table-striped table-bordered table-sm">
            <tr>
                <th><fmt:message key="l.id" bundle="${lang}"/></th>
                <th><fmt:message key="l.type" bundle="${lang}"/></th>
                <th><fmt:message key="l.receiver.fullname" bundle="${lang}"/></th>
                <th><fmt:message key="l.city.dep" bundle="${lang}"/></th>
                <th><fmt:message key="l.address.dep" bundle="${lang}"/></th>
                <th><fmt:message key="l.city.des" bundle="${lang}"/></th>
                <th><fmt:message key="l.address.des" bundle="${lang}"/></th>
                <th><fmt:message key="l.price" bundle="${lang}"/></th>
                <th><fmt:message key="l.weight" bundle="${lang}"/></th>
                <th><fmt:message key="l.length" bundle="${lang}"/></th>
                <th><fmt:message key="l.height" bundle="${lang}"/></th>
                <th><fmt:message key="l.width" bundle="${lang}"/></th>
                <th><fmt:message key="l.creationDate" bundle="${lang}"/></th>
                <th><fmt:message key="l.deliveryDate" bundle="${lang}"/></th>
                <th><fmt:message key="l.delivery.status" bundle="${lang}"/></th>
                <th><fmt:message key="l.invoiceStatus" bundle="${lang}"/></th>
                <th><fmt:message key="l.createInvoice" bundle="${lang}"/></th>
                <th><fmt:message key="l.pay" bundle="${lang}"/></th>
            </tr>

            <c:forEach var="cargo" items="${cargoList}">
                <tr>
                    <td>${cargo.id}</td>
                    <td>${cargo.type}</td>
                    <td>${cargo.receiverFullname}</td>
                    <td><fmt:message key="l.${cargo.departureBranch.city}" bundle="${lang}"/></td>
                    <td><fmt:message key="l.${cargo.departureBranch.address}" bundle="${lang}"/></td>
                    <td><fmt:message key="l.${cargo.destinationBranch.city}" bundle="${lang}"/></td>
                    <td><fmt:message key="l.${cargo.destinationBranch.address}" bundle="${lang}"/></td>
                    <td>${cargo.price}</td>
                    <td>${cargo.weight}</td>
                    <td>${cargo.length}</td>
                    <td>${cargo.height}</td>
                    <td>${cargo.width}</td>
                    <td>${String.format("%1$TD %1$TT", cargo.creationDate)}</td>
                    <td>${String.format("%1$TD %1$TT", cargo.deliveryDate)}</td>
                    <td><fmt:message key="l.${cargo.deliveryStatus}" bundle="${lang}"/></td>
                    <td><fmt:message key="l.${cargo.invoiceStatus}" bundle="${lang}"/></td>

                    <td>
                        <c:if test="${cargo.invoiceStatus == 'PENDING'}">
                            <form action="controller?action=invoice" method="post">
                                <button type="submit" name="invoice_id" value=${cargo.getId()}><fmt:message
                                        key="l.createInvoice"
                                        bundle="${lang}"/></button>
                            </form>
                        </c:if>
                    </td>

                    <td>
                        <div>
                            <c:if
                                test="${cargo.invoiceStatus != 'PAYED' && sessionScope.balance > cargo.price}">
                            <form action="controller?action=paypage" method="post">
                                <button type="submit" name="pay_id" value=${cargo.getId()}><fmt:message key="l.pay" bundle="${lang}"/></button>
                            </form>
                        </c:if>
                        </div>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </div>

    <nav aria-label="Navigation">
        <ul class="pagination">

            <tr>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active"><a class="page-link">
                                    ${i} <span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link"
                                                     href="controller?action=showcargospage&page=${i}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </ul>
    </nav>
</main>
</body>
</html>