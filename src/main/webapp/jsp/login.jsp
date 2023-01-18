<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale" var="lang"/>


<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="l.enter.username" bundle="${lang}"/></title>
    <%@include file="header.jsp"%>
    <%@include file="/jsp/nav_guest.jsp" %>
</head>
<body>
<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center"><fmt:message key="l.user.login" bundle="${lang}"/></div>
        <div class="card-body">
            <form method="post" action="controller?action=login">
                <input name="command" value="login" type="hidden">
                <div class="form-group">
                    <label><fmt:message key="l.login" bundle="${lang}"/></label>
                    <input type="text" name="username" id="username" class="form-control" required placeholder="<fmt:message key="l.enter.username" bundle="${lang}"/>">
                </div>
                <div class="form-group">
                    <label><fmt:message key="l.password" bundle="${lang}"/></label>
                    <input type="password" name="password" id="password" class="form-control" required placeholder="Password">
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary"><fmt:message key="l.login" bundle="${lang}"/></button>
                </div>
                <div>
                    <a href="controller?action=registerpage"><fmt:message key="l.register" bundle="${lang}"/></a>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>