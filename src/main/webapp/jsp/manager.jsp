<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale" var="lang"/>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags" %>

<%@include file="/jsp/nav_manager.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html">
    <title>All Cargo report</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
</head>
<body>
<div>
    <form method="post" action="controller?action=showmanagerpage">
        <input name="command" value="showmanagerpage" type="hidden">
        <table cellspacing="10" cellpadding="10">
            <tr>
                <th>
                    <div class="form-group">
                        <select name="req_branch_id" id="req_branch_id">
                            <option value="0"><fmt:message key="l.show.all" bundle="${lang}"/></option>
                            <option value=1><fmt:message key="l.Kyiv1" bundle="${lang}"/></option>
                            <option value=2><fmt:message key="l.Lviv2" bundle="${lang}"/></option>
                            <option value=3><fmt:message key="l.Dnipro3" bundle="${lang}"/></option>
                            <option value=4><fmt:message key="l.Kharkiv4" bundle="${lang}"/></option>
                        </select>
                    </div>
                </th>
                <th>
                    <div>
                        <p><fmt:message key="l.del.date" bundle="${lang}"/></p>
                    </div>
                </th>
                <th>
                    <div class="form-group">
                        <input type="date" name="req_date" id="req_date" value="${session_date}">
                    </div>
                </th>
                <th>
                    <div class="form-group">
                        <select name="req_order" id="req_order">
                            <option value="ASC"><fmt:message key="l.asc" bundle="${lang}"/></option>
                            <option value="DESC"><fmt:message key="l.desc" bundle="${lang}"/></option>
                        </select>
                    </div>
                </th>
                <th>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary"><fmt:message key="l.search"
                                                                                   bundle="${lang}"/></button>
                    </div>
                </th>
            <tr>
        </table>
    </form>
    <table cellspacing="10" cellpadding="10">
        <form method="post" action="controller?action=showmanagerpage">
            <input name="command" value="showmanagerpage" type="hidden">
            <input name="req_branch_id" value="0" type="hidden">
            <input name="req_date" value="2023" type="hidden">

            <th>
                <div class="form-group">
                    <button type="submit" class="btn btn-secondary"><fmt:message key="l.сlear"
                                                                                 bundle="${lang}"/></button>
                </div>
            </th>


        </form>
    </table>
</div>

<main class="m-3">
    <div class="col-md-auto">
        <table class="table table-striped table-bordered table-sm table-hover">
            <tr class="text-center">
                <th><fmt:message key="l.id" bundle="${lang}"/></th>
                <th><fmt:message key="l.type" bundle="${lang}"/></th>
                <th><fmt:message key="l.sender" bundle="${lang}"/></th>
                <th><fmt:message key="l.receiver.fullname" bundle="${lang}"/></th>
                <th><fmt:message key="l.departureBranchName" bundle="${lang}"/></th>
                <th><fmt:message key="l.destinationBranchName" bundle="${lang}"/></th>
                <th><fmt:message key="l.price" bundle="${lang}"/></th>
                <th><fmt:message key="l.weight" bundle="${lang}"/></th>
                <th><fmt:message key="l.length" bundle="${lang}"/></th>
                <th><fmt:message key="l.height" bundle="${lang}"/></th>
                <th><fmt:message key="l.width" bundle="${lang}"/></th>
                <th><fmt:message key="l.creationDate" bundle="${lang}"/></th>
                <th><fmt:message key="l.deliveryDate" bundle="${lang}"/></th>
                <th><fmt:message key="l.delivery.status" bundle="${lang}"/></th>
                <th><fmt:message key="l.invoiceStatus" bundle="${lang}"/></th>
                <th><fmt:message key="l.edit.cargo" bundle="${lang}"/></th>
                <th><fmt:message key="l.createInvoice" bundle="${lang}"/></th>
            </tr>

            <c:forEach var="cargo" items="${cargoList}">
                <tr>
                    <td>${cargo.id}</td>
                    <td>${cargo.type}</td>
                    <td>${cargo.user.fullname}</td>
                    <td>${cargo.receiverFullname}</td>
                    <td><fmt:message key="l.${cargo.departureBranch.city}" bundle="${lang}"/></td>
                    <td><fmt:message key="l.${cargo.destinationBranch.city}" bundle="${lang}"/></td>
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
                        <form action="controller?action=editcargopage" method="post">
                            <button type="submit" name="status_id" class="btn btn-info btn-block"
                                    value=${cargo.getId()}><fmt:message key="l.edit" bundle="${lang}"/><tf:editicon/></button>
                        </form>
                    </td>
                    <td>
                        <c:if test="${cargo.invoiceStatus == 'PENDING'}">
                            <form action="controller?action=invoice" method="post" target="_blank">
                                <button type="submit" name="invoice_id" class="btn btn-success btn-block" value=${cargo.getId()}>
                                    <fmt:message key="l.createInvoice" bundle="${lang}"/>
                                </button>
                            </form>
                        </c:if>
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
                                                     href="controller?action=showmanagerpage&page=${i}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </ul>
    </nav>
</main>
</body>
</html>