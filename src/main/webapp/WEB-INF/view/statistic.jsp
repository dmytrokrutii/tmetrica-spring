<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <jsp:include page="${pageContext.request.contextPath}./include/head.jsp"/>


</head>
<body>
<jsp:include page="${pageContext.request.contextPath}./include/navbar.jsp"/>
<br>
<div class="container-fluid">

    <p id="demo"></p>

    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2><spring:message
                            code="stat.header.first"/> <b><spring:message
                            code="stat.header.second"/></b></h2>

                </div>
                <div class="col-sm-6">
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th><spring:message
                        code="stat.table.first"/></th>
                <th><spring:message
                        code="stat.table.second"/></th>
                <th><spring:message
                        code="stat.table.third"/></th>
                <th><spring:message
                        code="stat.table.fourth"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="statistic" items="${stats}">
                <tr>
                    <td><c:out value="${statistic.activityName}"/></td>
                    <c:choose>
                        <c:when test="${statistic.activityStatus == 'ACTIVE'}">
                            <td><span class="badge badge-success"><c:out value="${statistic.activityStatus}"/></span>
                            </td>
                        </c:when>
                        <c:when test="${statistic.activityStatus == 'SUSPENDED'}">
                            <td><span class="badge badge-warning"><c:out value="${statistic.activityStatus}"/></span>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td><span class="badge badge-secondary"><c:out value="${statistic.activityStatus}"/></span>
                            </td>
                        </c:otherwise>
                    </c:choose>
                    <td><span class="badge badge-info"><b><c:out
                            value="${statistic.formatDuration()}"/></b></span>
                    </td>
                    <td><a href="${pageContext.request.contextPath}/times/acivity/${statistic.activityId}"
                           class="succes"><i
                            class="material-icons"
                            data-toggle="tooltip"
                            title="join">&#xe8ef;</i></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <jsp:include page="${pageContext.request.contextPath}./include/pager.jsp"/>
    </div>
</div>


<jsp:include page="${pageContext.request.contextPath}./include/footer.jsp"/>
</body>
</html>