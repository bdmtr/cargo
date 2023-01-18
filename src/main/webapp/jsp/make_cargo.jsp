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
    <title>Create cargo</title>
</head>
<body>

<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center">Create new cargo</div>
        <div class="card-body">
            <form method="post" action="controller?action=makeCargo">
                <input name="command" value="makeCargo" type="hidden">
                <div class="form-group">
                    <label><fmt:message key="l.cargo.description" bundle="${lang}"/><b style="color: red">*</b> </label>
                    <input type="text" name="type" id="type" class="form-control" required
                           placeholder="<fmt:message key="l.write.cargo.description" bundle="${lang}"/>">
                </div>
                <div class="form-group">
                    <label><fmt:message key="l.receiver.fullname" bundle="${lang}"/><b style="color: red">*</b> </label>
                    <input type="text" name="receiverFullname" id="receiverFullname" class="form-control" required
                           placeholder=<fmt:message key="l.write.receiver.fullname" bundle="${lang}"/>">
                </div>

                <div class="form-group">
                    <p><fmt:message key="l.departure.branch" bundle="${lang}"/></p>
                    <select name="departureBranchId">
                        <option value=1><fmt:message key="l.Kyiv1" bundle="${lang}"/></option>
                        <option value=2><fmt:message key="l.Lviv2" bundle="${lang}"/></option>
                        <option value=3><fmt:message key="l.Dnipro3" bundle="${lang}"/></option>
                        <option value=4><fmt:message key="l.Kharkiv4" bundle="${lang}"/></option>
                    </select>
                </div>
                <div class="form-group">
                    <p><fmt:message key="l.destination.branch" bundle="${lang}"/></p>
                    <select name="destinationBranchId">
                        <option value=1><fmt:message key="l.Kyiv1" bundle="${lang}"/></option>
                        <option value=2><fmt:message key="l.Lviv2" bundle="${lang}"/></option>
                        <option value=3><fmt:message key="l.Dnipro3" bundle="${lang}"/></option>
                        <option value=4><fmt:message key="l.Kharkiv4" bundle="${lang}"/></option>
                    </select>
                </div>

                <div class="form-group">
                    <label><fmt:message key="l.weight" bundle="${lang}"/><b style="color: red">*</b> </label>
                    <input type="text" name="weight" id="weight" class="form-control" required placeholder="Weight">
                </div>
                <div class="form-group">
                    <label><fmt:message key="l.height" bundle="${lang}"/><b style="color: red">*</b> </label>
                    <input type="text" name="height" id="height" class="form-control" required placeholder="Height">
                </div>
                <div class="form-group">
                    <label><fmt:message key="l.length" bundle="${lang}"/><b style="color: red">*</b> </label>
                    <input type="text" name="length" id="length" class="form-control" required placeholder="Length">
                </div>
                <div class="form-group">
                    <label><fmt:message key="l.width" bundle="${lang}"/><b style="color: red">*</b> </label>
                    <input type="text" name="width" id="width" class="form-control" required placeholder="Width">
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary"><fmt:message key="l.cargo" bundle="${lang}"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>