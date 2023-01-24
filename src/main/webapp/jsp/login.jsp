<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale" var="lang"/>

<%@ taglib uri="/WEB-INF/tlds/date-formatter.tld" prefix="cust" %>


<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="l.enter.username" bundle="${lang}"/></title>
    <%@include file="header.jsp" %>
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
                    <input type="text" name="username" id="username" class="form-control" required
                           placeholder="<fmt:message key="l.enter.username" bundle="${lang}"/>">
                </div>
                <div class="form-group">
                    <label><fmt:message key="l.password" bundle="${lang}"/></label>
                    <input type="password" name="password" id="password" class="form-control" required
                           placeholder="Password">
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
<br>
<br>
<br>
<br>
<br>

<!-- Footer -->
<footer class="text-center text-lg-start bg-light text-muted">
    <!-- Section: Social media -->
    <section class="d-flex justify-content-center justify-content-lg-between p-4 border-bottom">
        <!-- Left -->
        <div class="me-5 d-none d-lg-block">
            <span>Get connected with us on social networks!</span>
        </div>
        <!-- Left -->

        <!-- Right -->
        <div>
            <a href="https://github.com/" class="me-4 text-reset">
                <i class="fab fa-github"></i>
            </a>
        </div>
        <!-- Right -->
    </section>
    <!-- Section: Social media -->

    <!-- Section: Links  -->
    <section class="">
        <div class="container text-center text-md-start mt-5">
            <!-- Grid row -->
            <div class="row mt-3">
                <!-- Grid column -->
                <div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
                    <!-- Content -->
                    <h6 class="text-uppercase fw-bold mb-4">
                        <i class="fas fa-gem me-3"></i>Cargo Delivery
                    </h6>
                    <p>
                        „Недавно я понял, для чего нужна электронная почта. Чтобы общаться с теми, с кем не хочешь разговаривать.“ —  Д.Карлин
                    </p>
                </div>
                <!-- Grid column -->


                <!-- Grid column -->
                <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
                    <!-- Links -->
                    <h6 class="text-uppercase fw-bold mb-4">Contact</h6>
                    <p><i class="fas fa-home me-3"></i> Kyiv, Ukraine</p>
                    <p>
                        <i class="fas fa-envelope me-3"></i>
                        gmail@gmail.com
                    </p>
                    <p><i class="fas fa-phone me-3"></i> + 01 234 567 88</p>
                    <p><i class="fas fa-print me-3"></i> + 01 234 567 89</p>
                </div>
                <!-- Grid column -->
            </div>
            <!-- Grid row -->
        </div>
    </section>
    <!-- Section: Links  -->

    <!-- Copyright -->
    <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
        <p><span>Delivery cargo today </span><cust:date-formatter localDateTime="${LocalDateTime.now()}" pattern="dd-MM-yyyy"/></p>
        <a class="text-reset fw-bold" href="localhost:8080">Cargo Delivery</a>
    </div>
    <!-- Copyright -->
</footer>
<!-- Footer -->


</body>
</html>

