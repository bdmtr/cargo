<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="/jsp/nav_user.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit User Profile</title>
</head>
<body>

<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center">Edit User Profile</div>
        <div class="card-body">
            <form method="post" action="controller?action=changeprofile">
                <!--возможно можно убрать-->
                <input name="command" value="edituser" type="hidden">
                <div class="form-group">
                    <label>Username <b style="color: red">*</b> </label>
                    <input type="text" name="username" id="username" class="form-control" placeholder="${currentUser.username}">
                </div>
                <div class="form-group">
                    <label>Fullname <b style="color: red">*</b> </label>
                    <input type="text" name="fullname" id="fullname" class="form-control" placeholder="${currentUser.fullname}">
                </div>
                <div class="form-group">
                    <label>Password <b style="color: red">*</b> </label>
                    <input type="text" name="password" id="password" class="form-control" placeholder="${currentUser.password}">
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Edit Profile</button>
                </div>

            </form>
        </div>
    </div>
</div>

</body>
</html>