<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale" var="lang"/>

<%@include file="/jsp/nav_user.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>Pay for Cargo</title>
</head>
<body>

<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center"><fmt:message key="l.pay" bundle="${lang}"/></div>
        <div class="card-body">
            <form method="post" action="controller?action=pay">
                <div class="form-group">
                    <p><b><fmt:message key="l.current.cargo.id" bundle="${lang}"/> </b> ${currentCargo.id}</p>
                </div>
                <div class="form-group">
                    <p><b><fmt:message key="l.receiver.fullname" bundle="${lang}"/> </b>${currentCargo.receiverFullname}
                    </p>
                </div>
                <div class="form-group">
                    <p><b><fmt:message key="l.price" bundle="${lang}"/>: </b>${currentCargo.price}</p>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-primary"><fmt:message key="l.pay" bundle="${lang}"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>

