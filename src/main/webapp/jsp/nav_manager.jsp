<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="locale" var="lang"/>
<fmt:requestEncoding value="UTF-8"/>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/docs/4.1/assets/img/favicons/favicon.ico">
    <link rel="canonical" href="https://getbootstrap.com/docs/4.1/examples/pricing/">
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <h5 class="my-0 mr-md-auto font-weight-normal"><fmt:message key="l.cargo.delivery" bundle="${lang}"/></h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="controller?action=showmanagerpage"><fmt:message key="l.show.reports" bundle="${lang}"/></a>
        <a href="controller?action=changeLanguage&lang=en"><fmt:message key="l.en" bundle="${lang}"/></a>
        <a href="controller?action=changeLanguage&lang=ua"><fmt:message key="l.uk" bundle="${lang}"/></a>
    </nav>
    <a class="btn btn-outline-primary" href="controller?action=logout"><fmt:message key="l.logout" bundle="${lang}"/></a>
</div>

</body>
</html>