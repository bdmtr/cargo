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
    <title><fmt:message key="l.Edit.User.Profile" bundle="${lang}"/></title>
</head>
<body>

<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center"><fmt:message key="l.Edit.User.Profile" bundle="${lang}"/></div>
        <div class="card-body">
            <form method="post" action="controller?action=changeprofile">
                <div class="form-group">
                    <label><fmt:message key="l.login" bundle="${lang}"/><b style="color: red">*</b> </label>
                    <input type="text" name="username" id="username" class="form-control" placeholder="${currentUser.username}">
                </div>
                <div class="form-group">
                    <label><fmt:message key="l.fullname" bundle="${lang}"/><b style="color: red">*</b> </label>
                    <input type="text" name="fullname" id="fullname" class="form-control" placeholder="${currentUser.fullname}">
                </div>
                <div class="form-group">
                    <label><fmt:message key="l.password" bundle="${lang}"/><b style="color: red"> Type new password if you want to change it!</b> </label>
                    <input type="text" name="password" id="password" class="form-control">
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary"><fmt:message key="l.Edit.User.Profile" bundle="${lang}"/></button>
                </div>

            </form>
        </div>
    </div>
</div>

</body>
</html>