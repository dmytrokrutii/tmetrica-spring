<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
                    <h2>
                        <c:choose>
                            <c:when test="${usersList}">
                                <spring:message
                                        code="times.header.first"/>
                            </c:when>
                            <c:otherwise>
                                <spring:message
                                        code="activities.all.header.first"/>
                            </c:otherwise>
                        </c:choose>
                        <b><spring:message
                                code="activities.my.header.second"/></b></h2>
                </div>
                <div class="col-sm-6">
                    <c:if test="${!usersList}">
                        <a href="#addRecordModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i>
                            <span><spring:message
                                    code="activity.add.new"/></span></a>
                    </c:if>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>

                <th><spring:message
                        code="activities.all.table.first"/></th>
                <th><spring:message
                        code="activities.all.table.second"/></th>
                <c:if test="${usersList}">
                    <th><spring:message
                            code="activities.my.table.third"/></th>
                </c:if>
                <th><spring:message
                        code="activities.all.table.third"/></th>
                <th><spring:message
                        code="activities.all.table.fourth"/></th>
                <c:if test="${!usersList}">
                    <th><spring:message
                            code="activities.all.table.fifth"/></th>
                </c:if>
                <c:if test="${usersList}">
                    <th>S<spring:message
                            code="activities.my.table.sixth"/></th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="activity" items="${activities}">
                <tr>
                    <td><c:out value="${activity.name}"/></td>
                    <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short"
                                        value="${activity.startDate}"/></td>
                    <c:if test="${usersList}">
                        <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short"
                                            value="${activity.endDate}"/></td>
                    </c:if>
                    <c:choose>
                        <c:when test="${activity.status == 'ACTIVE'}">
                            <td><span class="badge badge-success"><c:out value="${activity.status}"/></span>
                            </td>
                        </c:when>
                        <c:when test="${activity.status == 'SUSPENDED'}">
                            <td><span class="badge badge-warning"><c:out value="${activity.status}"/></span>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td><span class="badge badge-secondary"><c:out value="${activity.status}"/></span>
                            </td>
                        </c:otherwise>
                    </c:choose>

                    <td><c:out value="${activity.users.size()}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${usersList}">
                                <a href="${pageContext.request.contextPath}/times/acivity/${activity.id}"
                                   class="success"><i
                                        class="material-icons"
                                        data-toggle="tooltip"
                                        title="show entries">&#xe8ef;</i></a>
                                <a href="#deleteRecordModal" class="delete passingIDDelete" data-id="${activity.id}"
                                   data-toggle="modal"><i
                                        class="material-icons"
                                        data-toggle="tooltip"
                                        title="Delete">&#xE872;</i></a>
                            </c:when>
                            <c:otherwise><a href="#joinToActivModel" class="succes passingID" data-id="${activity.id}"
                                            data-toggle="modal"><i
                                    class="material-icons"
                                    data-toggle="tooltip"
                                    title="join">&#xe147;</i></a></c:otherwise>

                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <jsp:include page="${pageContext.request.contextPath}./include/pager.jsp"/>
    </div>
</div>

<!-- Delete Modal HTML -->
<div id="deleteRecordModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="${pageContext.request.contextPath}/orders/delete">
                <div class="modal-header">
                    <h4 class="modal-title"><spring:message
                            code="activities.remove.header"/></h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p><spring:message
                            code="activities.remove.body"/></p>
                </div>
                <div class="modal-footer">
                    <input type="hidden" class="form-control" name="activityId" id="activityIdToDelete" value="">
                    <input type="button" class="btn btn-default" data-dismiss="modal"
                           value="<spring:message
                code="cancel"/>">
                    <input type="submit" class="btn btn-danger" value="<spring:message
                code="delete.button"/>">
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Add Modal HTML -->
<div id="addRecordModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/orders/new" method="post">
                <div class="modal-header">
                    <h4 class="modal-title"><spring:message
                            code="activities.add.header"/></h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label><spring:message
                                code="activities.all.table.first"/></label>
                        <input type="text" name="activityName" class="form-control" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="<spring:message
                code="cancel"/>">
                    <sec:authorize access="!hasAuthority('ADMIN')">
                        <input type="submit" class="btn btn-success" value="<spring:message
                code="activities.add.user.ok"/>">
                    </sec:authorize>
                    <sec:authorize access="hasAuthority('ADMIN')">
                        <input type="submit" class="btn btn-success" value="<spring:message
                code="activities.add.admin.ok"/>">
                    </sec:authorize>

                </div>
            </form>
        </div>
    </div>
</div>

<!-- Join Modal HTML -->
<div id="joinToActivModel" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="${pageContext.request.contextPath}/orders/join">
                <div class="modal-header">
                    <h4 class="modal-title"><spring:message
                            code="activities.join.title"/></h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p><spring:message
                            code="activities.join.body"/></p>
                </div>
                <div class="modal-footer">
                    <input type="hidden" class="form-control" name="activityId" id="actId" value="">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <sec:authorize access="!hasAuthority('ADMIN')">
                        <input type="submit" class="btn btn-success" value="<spring:message
                code="activities.add.user.ok"/>">
                    </sec:authorize>
                    <sec:authorize access="hasAuthority('ADMIN')">
                        <input type="submit" class="btn btn-success" value="<spring:message
                code="activities.join.title"/>">
                    </sec:authorize>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(".passingIDDelete").click(function () {
        var ids = $(this).attr('data-id');
        $("#activityIdToDelete").val(ids);
        $('#deleteRecordModal').modal('show');
    });
    $(".passingID").click(function () {
        var ids = $(this).attr('data-id');
        $("#actId").val(ids);
        $('#joinToActivModel').modal('show');
    });
</script>

</body>
<jsp:include page="${pageContext.request.contextPath}./include/footer.jsp"/>
</html>