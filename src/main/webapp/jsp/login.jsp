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

    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,700,900|Display+Playfair:200,300,400,700">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/icomoon/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/magnific-popup.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/flaticon/font/flaticon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/aos.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-migrate-3.0.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.stellar.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.countdown.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.magnific-popup.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/aos.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>

</head>
<body>


<div class="site-wrap">
    <div class="site-mobile-menu">
        <div class="site-mobile-menu-header">
            <div class="site-mobile-menu-close mt-3">
                <span class="icon-close2 js-menu-toggle"></span>
            </div>
        </div>
        <div class="site-mobile-menu-body"></div>
    </div>
    <div class="site-blocks-cover overlay"
         style="background-image: url(${pageContext.request.contextPath}/img/ship.jpg);" data-aos="fade"
         data-stellar-background-ratio="0.5">
        <div class="container">
            <div class="row align-items-center justify-content-center text-center">
                <div class="col-md-8" data-aos="fade-up" data-aos-delay="400">
                    <h1 class="text-white font-weight-light mb-5 text-uppercase font-weight-bold"><fmt:message key="l.cargo.delivery" bundle="${lang}"/></h1>
                </div>
            </div>
        </div>
    </div>


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
                    <br>
                    <div class="form-group">
                        <label><fmt:message key="l.password" bundle="${lang}"/></label>
                        <input type="password" name="password" id="password" class="form-control" required
                               placeholder="Password">
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary"><fmt:message key="l.login"
                                                                                   bundle="${lang}"/></button>
                    </div>
                    <br>
                    <div>
                        <a href="controller?action=registerpage"><fmt:message key="l.register" bundle="${lang}"/></a>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <div class="site-section">
        <div class="container">
            <div class="row justify-content-center mb-5">
                <div class="col-md-7 text-center">
                    <h2 class="mb-0 text-primary"><fmt:message key="l.what.we.offer" bundle="${lang}"/></h2>
                </div>
            </div>
            <br>
            <div class="row align-items-stretch">
                <div class="col-md-6 col-lg-4 mb-4 mb-lg-0">
                    <div class="unit-4 d-flex">
                        <div class="unit-4-icon mr-4"><span class="text-primary flaticon-travel"></span></div>
                        <div>
                            <h3><fmt:message key="l.air.freight" bundle="${lang}"/></h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4 mb-4 mb-lg-0">
                    <div class="unit-4 d-flex">
                        <div class="unit-4-icon mr-4"><span
                                class="text-primary flaticon-sea-ship-with-containers"></span></div>
                        <div>
                            <h3><fmt:message key="l.ocean.freight" bundle="${lang}"/></h3>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4 mb-4 mb-lg-0">
                    <div class="unit-4 d-flex">
                        <div class="unit-4-icon mr-4"><span class="text-primary flaticon-frontal-truck"></span></div>
                        <div>
                            <h3><fmt:message key="l.ground.shipping" bundle="${lang}"/></h3>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<footer class="text-center text-lg-start bg-light">
    <!-- Section: Social media -->
    <section class="d-flex justify-content-center justify-content-lg-between p-4 border-bottom">
        <!-- Left -->
        <div class="me-5 d-none d-lg-block">
            <span><fmt:message key="l.get.connected" bundle="${lang}"/></span>
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
                        <i class="fas fa-gem me-3"></i><fmt:message key="l.cargo.delivery" bundle="${lang}"/>
                    </h6>
                    <p>
                        <fmt:message key="l.citate" bundle="${lang}"/>
                    </p>
                </div>
                <!-- Grid column -->


                <!-- Grid column -->
                <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
                    <!-- Links -->
                    <h6 class="text-uppercase fw-bold mb-4"><fmt:message key="l.contacts"
                                                                         bundle="${lang}"/></h6>
                    <p><i class="fas fa-home me-3"></i><fmt:message key="l.kyiv.ulraine" bundle="${lang}"/></p>
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
        <p><span><fmt:message key="l.delivery.today" bundle="${lang}"/> </span><cust:date-formatter
                localDateTime="${LocalDateTime.now()}" pattern="dd-MM-yyyy"/></p>
        <a class="text-reset fw-bold" href="localhost:8080">Cargo Delivery</a>
    </div>
    <!-- Copyright -->
</footer>
<!-- Footer -->


</body>
</html>