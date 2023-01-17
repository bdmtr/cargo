<%@ page language="java" contentType="text/html;charset=UTF-8" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<%@include file="/jsp/nav_guest.jsp" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html">
    <title>Price</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
</head>

<body>
<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center">Price Info</div>
        <div class="card-body">
            <ul class="list-group list-group-flush text-center">
                <li class="list-group-item">
                    <b>${departureBranch.city}</b> - ${departureBranch.address}
                    <span class="glyphicon glyphicon-ok pull-right"></span>
                </li>
                <li class="list-group-item">
                    <h3></h3>
                    <span class="glyphicon glyphicon-remove pull-right"></span>
                </li>
                <li class="list-group-item">
                    <b>${destinationBranch.city}</b> -  ${destinationBranch.address}
                    <span class="glyphicon glyphicon-remove pull-right"></span>
                </li>
                <li class="list-group-item">
                    <b>Weight</b> ${weight} kg
                    <span class="glyphicon glyphicon-remove pull-right"></span>
                </li>
                <li class="list-group-item">
                    <b>Length</b> ${length} cm
                    <span class="glyphicon glyphicon-remove pull-right"></span>
                </li>
                <li class="list-group-item">
                    <b>Height</b> ${height} cm
                    <span class="glyphicon glyphicon-remove pull-right"></span>
                </li>
                <li class="list-group-item">
                    <b>Width</b> ${width} cm
                    <span class="glyphicon glyphicon-remove pull-right"></span>
                </li>
                <li class="list-group-item">
                    <h2>${price} ₴</h2>
                    <span class="glyphicon glyphicon-remove text-center"></span>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>