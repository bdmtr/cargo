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
        <div>
            <label><fmt:message key="l.destination.branch" bundle="${lang}"/></label>
            <input type="text" name="search_branch" id="search_branch" class="form-control">
        </div>
        <div>
            <div>
                <button type="submit" class="btn btn-primary"><fmt:message key="l.search" bundle="${lang}"/></button>
            </div>
        </div>
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
                    <td>${cargo.departureBranch.city}</td>
                    <td>${cargo.destinationBranch.city}</td>
                    <td>${String.format("%1$TD %1$TT", cargo.deliveryDate)}</td>
                    <td>${cargo.deliveryStatus}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <nav aria-label="Navigation">
        <%--For displaying Previous link except for the 1st page --%>
        <ul class="pagination">
            <%--            <c:if test="${currentPage != 1}">
                            <li class="page-item">
                                <a class="page-link"
                                   href="controller?action=SearchCityGuestt&page=${currentPage - 1}">Previous</a>
                            </li>
                        </c:if>--%>

            <%--For displaying Page numbers. The when condition does not display
                        a link for the current page--%>
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


            <%--For displaying Next link --%>
            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item">
                    <a class="page-link"
                       href="controller?action=SearchCityGuest&page=${currentPage + 1}"><fmt:message key="l.next"
                                                                                                     bundle="${lang}"/></a>
                </li>
            </c:if>

        </ul>
    </nav>
</main>
</body>
</html>