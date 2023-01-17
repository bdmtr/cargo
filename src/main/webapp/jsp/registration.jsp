<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="/jsp/nav_guest.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>Register page</title>
</head>
<body>

<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center">User Register</div>
        <div class="card-body">
            <form method="post" action="controller?action=register">
                <div class="form-group">
                    <label>Name <b style="color: red">*</b> </label>
                    <input type="text" name="username" id="username" class="form-control" required
                           placeholder="Enter username">
                </div>
                <div class="form-group">
                    <label>Fullname <b style="color: red">*</b></label>
                    <input type="text" name="fullname" id="fullname" class="form-control" required
                           placeholder="Enter name and surname">
                </div>
                <div class="form-group">
                    <label>Email <b style="color: red">*</b></label>
                    <input type="text" name="email" id="email" class="form-control" required placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label>Password <b style="color: red">*</b></label>
                    <input type="password" name="password" id="password" class="form-control" required
                           placeholder="******">
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Register</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>