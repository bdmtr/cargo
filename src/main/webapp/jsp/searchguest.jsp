<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale" var="lang"/>

<%@include file="/jsp/nav_guest.jsp" %>

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
<div>
    <form method="post" action="controller?action=SearchCityGuest">
        <input name="command" value="SearchCityGuest" type="hidden">
        <table cellspacing="10" cellpadding="10">
            <tr>
                <th>
                    <div class="form-group">
                        <select name="req_branch_id" id="req_branch_id">
                            <option value=0><fmt:message key="l.show.all" bundle="${lang}"/></option>
                            <option value="KYIV"><fmt:message key="l.Kyiv1" bundle="${lang}"/></option>
                            <option value="LVIV"><fmt:message key="l.Lviv2" bundle="${lang}"/></option>
                            <option value="DNIPRO"><fmt:message key="l.Dnipro3" bundle="${lang}"/></option>
                            <option value="KHARKIV"><fmt:message key="l.Kharkiv4" bundle="${lang}"/></option>
                        </select>
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
</div>
<main class="m-3">
    <div class="col-md-auto">
        <table class="table table-striped table-bordered table-sm">
            <tr>
                <th><fmt:message key="l.departureBranchName" bundle="${lang}"/></th>
                <th><fmt:message key="l.destinationBranchName" bundle="${lang}"/></th>
                <th><fmt:message key="l.deliveryDate" bundle="${lang}"/></th>
                <th><fmt:message key="l.delivery.status" bundle="${lang}"/></th>
            </tr>

            <c:forEach var="cargo" items="${cargoList}">
                <tr>
                    <td><fmt:message key="l.${cargo.departureBranch.city}" bundle="${lang}"/></td>
                    <td><fmt:message key="l.${cargo.destinationBranch.city}" bundle="${lang}"/></td>
                    <td>${String.format("%1$TD %1$TT", cargo.deliveryDate)}</td>
                    <td><fmt:message key="l.${cargo.deliveryStatus}" bundle="${lang}"/></td>
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
                                                     href="controller?action=SearchCityGuest&page=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </ul>
    </nav>
</main>
</body>
</html>