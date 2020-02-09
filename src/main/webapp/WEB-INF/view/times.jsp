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
                            code="times.header.second"/></b></h2>
                </div>
                <div class="col-sm-6">
                    <a href="#addRecordModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i>
                        <span><spring:message
                                code="times.add"/></span></a>
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>

                <th><spring:message
                        code="times.table.first"/></th>
                <th><spring:message
                        code="times.table.second"/></th>
                <th><spring:message
                        code="times.table.third"/></th>
                <th><spring:message
                        code="times.table.fourth"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="log" items="${logs}">
                <tr>
                    <td><c:out value="${log.activityLog.name}"/></td>
                    <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${log.startTime}"/></td>
                    <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${log.endTime}"/></td>
                    <td>
                        <a href="#editRecordModel" class="edit passingIDEdit" data-id="${log.id}" data-toggle="modal"><i
                                class="material-icons"
                                title="Edit">&#xE254;</i></a>
                        <a href="#deleteRecordModal" class="delete passingID" data-id="${log.id}" data-toggle="modal"><i
                                class="material-icons"
                                data-toggle="tooltip"
                                title="Delete">&#xE872;</i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <jsp:include page="${pageContext.request.contextPath}./include/pager.jsp"/>
    </div>
</div>
<!-- Edit Modal HTML -->
<div id="addRecordModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/times/add" method="post">
                <div class="modal-header">
                    <h4 class="modal-title"><spring:message
                            code="times.add.title"/></h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Start Time</label>
                        <input type="datetime-local" id="startDateAdd" min="2020-01-01T00:00" max="2100-01-00T00:00"
                               name="startDate"
                               class="form-control" required/>
                    </div>
                    <div class="form-group">
                        <label>End Date</label>
                        <input type="datetime-local" id="endDateAdd" name="endDate" class="form-control" required>
                    </div>
                    <label>Activity</label>
                    <select class="form-control" name="activityId" exampleFormControlSelect1">
                    <c:forEach var="activity" items="${activities}">
                        <option value="${activity.id}">${activity.name}</option>
                    </c:forEach>
                    </select>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="<spring:message
                        code="cancel"/>">
                    <input type="submit" class="btn btn-success" value="<spring:message
                        code="times.add.button"/>">
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Edit Modal HTML -->
<div id="editRecordModel" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/times/edit" method="post">
                <div class="modal-header">
                    <h4 class="modal-title"><spring:message
                            code="times.edit.title"/></h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Start Time</label>
                        <input type="datetime-local" min="2020-01-01T00:00" max="2100-01-00T00:00" name="startDate"
                               class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>End Date</label>
                        <input type="datetime-local" name="endDate" class="form-control" required>
                    </div>
                    <label>Activity</label>
                    <select class="form-control" name="activityId" exampleFormControlSelect1">
                    <c:forEach var="activity" items="${activities}">
                        <option value="${activity.id}">${activity.name}</option>
                    </c:forEach>
                    </select>
                </div>
                <div class="modal-footer">
                    <input type="hidden" class="form-control" name="logId" id="logId" value="">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="<spring:message
                        code="cancel"/>">
                    <input type="submit" class="btn btn-info" value="<spring:message
                        code="times.edit.button"/>">
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Delete Modal HTML -->
<div id="deleteRecordModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="${pageContext.request.contextPath}/times/delete">
                <div class="modal-header">
                    <h4 class="modal-title"><spring:message
                            code="times.delete.header"/></h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p><spring:message
                            code="times.delete.body"/></p>
                    <p class="text-warning"><small><spring:message
                            code="delete.warn"/></small></p>
                </div>
                <div class="modal-footer">
                    <input type="hidden" class="form-control" name="logId" id="logIdToDelete" value="">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="<spring:message
                        code="cancel"/>">
                    <input type="submit" class="btn btn-danger" value="<spring:message
                        code="delete.button"/>">
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(".passingID").click(function () {
        var ids = $(this).attr('data-id');
        $("#logIdToDelete").val(ids);
        $('#deleteRecordModal').modal('show');
    });
    $(".passingIDEdit").click(function () {
        var ids = $(this).attr('data-id');
        $("#logId").val(ids);
        $('#editRecordModel').modal('show');
    });
</script>
<jsp:include page="${pageContext.request.contextPath}./include/footer.jsp"/>
</body>
</html>