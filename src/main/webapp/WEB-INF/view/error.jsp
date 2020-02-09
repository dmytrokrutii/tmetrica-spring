<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <jsp:include page="${pageContext.request.contextPath}./include/head.jsp"/>
    <link href="${pageContext.request.contextPath}/css/404.css" rel="stylesheet">
    <title>Tmetrica - ${code} ERROR</title>
</head>

<body>
<jsp:include page="${pageContext.request.contextPath}./include/navbar.jsp"/>
<div id="notfound">
    <div class="notfound">
        <c:choose>
            <c:when test="${code == 404}">
                <div class="notfound-404">
                    <h1>: (</h1>
                </div>
                <h2>404 - Page not found</h2>
                <p>The page you are looking for might have been removed had its name changed or is temporarily
                    unavailable.</p>
                <a href="${pageContext.request.contextPath}/">home page</a>

            </c:when>
            <c:when test="${code == 403}">
                <div class="notfound-404">
                    <h1>&#x1f512;</h1>
                </div>
                <h2>403 - Forbidden</h2>
                <p>Sorry, your access is refused due to security reasons of our server and also our sensitive
                    data.<br/>Please
                    go back to the previous page to continue browsing.</p>
                <a href="${pageContext.request.contextPath}/">home page</a>
            </c:when>
            <c:when test="${code == 500}">
                <div class="notfound-404">
                    <h1>&#x1F4BB;</h1>
                </div>
                <h2>500 - Internal Server Error</h2>
                <p>Unexpected Error:(<br/>Please
                    go back to the previous page to continue browsing.</p>
                <a href="${pageContext.request.contextPath}/">home page</a>
            </c:when>
        </c:choose>
    </div>
</div>
<jsp:include page="${pageContext.request.contextPath}./include/footer.jsp"/>
</body>
</html>
