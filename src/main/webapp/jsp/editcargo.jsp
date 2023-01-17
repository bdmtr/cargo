<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="/jsp/nav_manager.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Cargo</title>
</head>
<body>

<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center">Edit Cargo Status</div>
        <div class="card-body">
            <form method="post" action="controller?action=editcargo">
                <div class="form-group">
                    <label>Receiver Fullname<b style="color: red">*</b> </label>
                    <input type="text" name="receiverFullname" id="receiverFullname" class="form-control"
                           placeholder="${currentCargo.receiverFullname}">
                </div>
                <div class="form-group">
                    <p>Delivery Status - ${currentCargo.deliveryStatus}</p>
                    <select name="deliveryStatus" id="deliveryStatus">
                        <option value="TRANSIT">TRANSIT</option>
                        <option value="DELIVERED">DELIVERED</option>
                        <option value="RECEIVED">RECEIVED</option>
                        <option value="DECLINED">DECLINED</option>
                    </select>
                </div>
                <div class="form-group">
                    <p>Invoice Status - ${currentCargo.invoiceStatus} </p>
                        <select name="invoiceStatus" id="invoiceStatus">
                            <option value="PAYED">PAYED</option>
                            <option value="PENDING">PENDING</option>
                        </select>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Edit Cargo</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>