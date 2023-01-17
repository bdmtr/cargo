<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>



<!DOCTYPE html>
<html>
<head>
    <title>Login page</title>
    <%@include file="header.jsp"%>
    <%@include file="/jsp/nav_guest.jsp" %>
</head>
<body>
<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center">User Log in</div>
        <div class="card-body">
            <form method="post" action="controller?action=login">
                <input name="command" value="login" type="hidden">
                <div class="form-group">
                    <label>Username</label>
                    <input type="text" name="username" id="username" class="form-control" required placeholder="Enter username">
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" name="password" id="password" class="form-control" required placeholder="Password">
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
                <div>
                    <a href="controller?action=registerpage">Register</a>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>