<%@ page language="java" contentType="text/html;charset=UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<%@include file="/jsp/nav_user.jsp" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html">
    <title>All Cargos</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
</head>

<body>
<main class="m-3">
    <div class="row col-md-6">
        <table class="table table-striped table-bordered table-sm">
            <tr>
                <th>id</th>
                <th>type</th>
                <th>receiverFullname</th>
                <th>departureBranchCity</th>
                <th>departureBranchName</th>
                <th>destinationBranchCity</th>
                <th>destinationBranchName</th>
                <th>price</th>
                <th>weight</th>
                <th>length</th>
                <th>height</th>
                <th>width</th>
                <th>creationDate</th>
                <th>deliveryDate</th>
                <th>deliveryStatus</th>
                <th>invoiceStatus</th>
                <th>PAY</th>
            </tr>

            <c:forEach var="cargo" items="${cargoList}">
                <tr>
                    <td>${cargo.id}</td>
                    <td>${cargo.type}</td>
                    <td>${cargo.receiverFullname}</td>
                    <td>${cargo.departureBranch.city}</td>
                    <td>${cargo.departureBranch.address}</td>
                    <td>${cargo.destinationBranch.city}</td>
                    <td>${cargo.destinationBranch.address}</td>
                    <td>${cargo.price}</td>
                    <td>${cargo.weight}</td>
                    <td>${cargo.length}</td>
                    <td>${cargo.height}</td>
                    <td>${cargo.width}</td>
                        <%--<td>${cargo.creationDate}</td>--%>
                    <td>${String.format("%1$TD %1$TT", cargo.creationDate)}</td>
                        <%--<td>${cargo.deliveryDate}</td>--%>
                    <td>${String.format("%1$TD %1$TT", cargo.deliveryDate)}</td>
                    <td>${cargo.deliveryStatus}</td>
                    <td>${cargo.invoiceStatus}</td>
                    <td>PAY</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <nav aria-label="Navigation">
        <%--For displaying Previous link except for the 1st page --%>
        <ul class="pagination">
            <c:if test="${currentPage != 1}">
                <li class="page-item">
                    <a class="page-link" href="controller?action=showcargospage&page=${currentPage - 1}">Previous</a>
                </li>
            </c:if>

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
                            <li class="page-item"><a class="page-link" href="controller?action=showcargospage&page=${i}">${i}</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>


            <%--For displaying Next link --%>
            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item">
                    <a class="page-link" href="controller?action=showcargospage&page=${currentPage + 1}">Next</a>
                </li>
            </c:if>

        </ul>
    </nav>
</main>
</body>
</html>