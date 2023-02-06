<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale" var="lang"/>

<%@include file="/jsp/nav_guest.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="l.register" bundle="${lang}"/></title>
</head>
<body>

<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center"><fmt:message key="l.user.register" bundle="${lang}"/></div>
        <div class="card-body">
            <form method="post" action="controller?action=register">
                <div class="form-group">
                    <label><fmt:message key="l.login" bundle="${lang}"/><b style="color: red">*</b> </label>
                    <input type="text" name="username" id="username" class="form-control" required
                           placeholder="<fmt:message key="l.enter.username" bundle="${lang}"/>">
                </div>
                <div class="form-group">
                    <label><fmt:message key="l.fullname" bundle="${lang}"/><b style="color: red">*</b></label>
                    <input type="text" name="fullname" id="fullname" class="form-control" required
                           placeholder="<fmt:message key="l.enter.fullname" bundle="${lang}"/>">
                </div>
                <div class="form-group">
                    <label>Email<b style="color: red">*</b></label>
                    <input type="text" name="email" id="email" class="form-control" required placeholder="<fmt:message key="l.enter.email" bundle="${lang}"/>">
                </div>
                <div class="form-group">
                    <label><fmt:message key="l.password" bundle="${lang}"/><b style="color: red">*</b></label>
                    <input type="password" name="password" id="password" class="form-control" required
                           placeholder="********"  aria-describedby="passwordHelpBlock">
                    <div id="passwordHelpBlock" class="form-text">
                        <fmt:message key="l.password.description" bundle="${lang}"/>
                    </div>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary"><fmt:message key="l.register" bundle="${lang}"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>