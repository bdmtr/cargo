<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="/jsp/nav_guest.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>Calculate cargo</title>
</head>
<body>

<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center">Calculate Cargo</div>
        <div class="card-body">
            <form method="post" action="controller?action=calculate">
                <input name="command" value="calculate" type="hidden">

                <div class="form-group">
                    <p>Departure Branch</p>
                    <select name="departureBranchId">
                        <option value=1>Kyiv, branch 1</option>
                        <option value=2>Lviv, branch 2</option>
                        <option value=3>Dnipro, branch 3</option>
                        <option value=4>Kharkiv, branch 4</option>
                    </select>
                </div>

                <div class="form-group">
                    <p>Destination Branch</p>
                    <select name="destinationBranchId">
                        <option value="1">Kyiv, branch 1</option>
                        <option value="2">Lviv, branch 2</option>
                        <option value="3">Dnipro, branch 3</option>
                        <option value="4">Kharkiv, branch 4</option>
                    </select>
                </div>

                <div class="form-group">
                    <label>Weight <b style="color: red">*</b> </label>
                    <input type="text" name="weight" id="weight" class="form-control" required placeholder="Weight">
                </div>
                <div class="form-group">
                    <label>Height <b style="color: red">*</b> </label>
                    <input type="text" name="height" id="height" class="form-control" required placeholder="Height">
                </div>
                <div class="form-group">
                    <label>Length <b style="color: red">*</b> </label>
                    <input type="text" name="length" id="length" class="form-control" required placeholder="Length">
                </div>
                <div class="form-group">
                    <label>Width <b style="color: red">*</b> </label>
                    <input type="text" name="width" id="width" class="form-control" required placeholder="Width">
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Calculate Cargo</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>