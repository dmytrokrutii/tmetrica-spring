<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <jsp:include page="${pageContext.request.contextPath}./include/head.jsp"/>
    <title>Time Tracker - Home Page</title>
</head>

<body>
<jsp:include page="${pageContext.request.contextPath}./include/navbar.jsp"/>
<header>
    <jsp:include page="${pageContext.request.contextPath}./include/header.jsp"/>
</header>

<section class="py-5">
    <div class="container">
        <h1 class="display-4"><spring:message
                code="main.mainHeader"/></h1>
        <p class="lead"><spring:message
                code="main.section"/>
    </div>
</section>
<jsp:include page="${pageContext.request.contextPath}./include/footer.jsp"/>
</body>
</html>
