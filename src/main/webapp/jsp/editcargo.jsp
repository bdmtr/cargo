<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale" var="lang"/>

<%@include file="/jsp/nav_manager.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Cargo</title>
</head>
<body>

<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center"><fmt:message key="l.edit.cargo" bundle="${lang}"/></div>
        <div class="card-body">
            <form method="post" action="controller?action=editcargo">
                <div class="form-group">
                    <label><fmt:message key="l.receiver.fullname" bundle="${lang}"/><b style="color: red">*</b> </label>
                    <input type="text" name="receiverFullname" id="receiverFullname" class="form-control"
                           placeholder="${currentCargo.receiverFullname}">
                </div>
                <div class="form-group">
                    <p><fmt:message key="l.delivery.status-" bundle="${lang}"/> ${currentCargo.deliveryStatus}</p>
                    <select name="deliveryStatus" id="deliveryStatus">
                        <option value="TRANSIT"><fmt:message key="l.TRANSIT" bundle="${lang}"/></option>
                        <option value="DELIVERED"><fmt:message key="l.DELIVERED" bundle="${lang}"/></option>
                        <option value="RECEIVED"><fmt:message key="l.RECEIVED" bundle="${lang}"/></option>
                        <option value="DECLINED"><fmt:message key="l.DECLINED" bundle="${lang}"/></option>
                    </select>
                </div>
                <div class="form-group">
                    <p><fmt:message key="l.Invoice.Status-" bundle="${lang}"/> ${currentCargo.invoiceStatus} </p>
                        <select name="invoiceStatus" id="invoiceStatus">
                            <option value="PAYED"><fmt:message key="l.PAYED" bundle="${lang}"/></option>
                            <option value="PENDING"><fmt:message key="l.PENDING" bundle="${lang}"/></option>
                        </select>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">l.edit.cargo</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>