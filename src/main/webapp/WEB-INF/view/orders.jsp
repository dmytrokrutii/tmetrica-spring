<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <jsp:include page="${pageContext.request.contextPath}./include/head.jsp"/>
    <title>Tmetrica - Orders </title>
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
                    <c:choose>
                        <c:when test="${active}">
                            <h2><spring:message
                                    code="orders.active.header.first"/> <b><spring:message
                                    code="orders.active.header.second"/></b></h2>
                        </c:when>
                        <c:otherwise>
                            <h2><spring:message
                                    code="orders.all.header.first"/> <b><spring:message
                                    code="orders.active.header.second"/></b></h2>
                        </c:otherwise>
                    </c:choose>

                </div>
                <div class="col-sm-6">
                </div>
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
            <tr>

                <th><spring:message
                        code="orders.table.first"/></th>
                <th><spring:message
                        code="orders.table.second"/></th>
                <th><spring:message
                        code="orders.table.third"/></th>
                <th><spring:message
                        code="orders.table.fourth"/></th>
                <c:if test="${active}">
                    <th><spring:message
                            code="orders.table.sixth"/></th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td><c:out value="${order.activityOrder.name}"/></td>
                    <td><c:out value="${order.userOrder.name}"/></td>
                    <c:choose>
                        <c:when test="${order.status == 'ACCEPTED'}">
                            <td><span class="badge badge-success"><c:out value="${order.status}"/></span>
                            </td>
                        </c:when>
                        <c:when test="${order.status == 'PENDING'}">
                            <td><span class="badge badge-warning"><c:out value="${order.status}"/></span>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td><span class="badge badge-danger"><c:out value="${order.status}"/></span>
                            </td>
                        </c:otherwise>
                    </c:choose>
                    <td><span class="badge badge-secondary"><c:out value="${order.action}"/></span>
                    </td>
                    <c:if test="${active}">
                        <td>
                            <a href="#acceptOrderModal" class="success passingIDAccept" data-id="${order.id}"
                               data-toggle="modal"><i
                                    class="material-icons"
                                    data-toggle="tooltip"
                                    title="Accept">&#xE876;</i></a>
                            <a href="#rejectOrderModel" class="delete passingIDReject" data-id="${order.id}"
                               data-toggle="modal"><i
                                    class="material-icons"
                                    data-toggle="tooltip"
                                    title="Reject">&#xE14c;</i></a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <jsp:include page="${pageContext.request.contextPath}./include/pager.jsp"/>
    </div>
</div>

<!--  Modal HTML -->
<div id="rejectOrderModel" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="${pageContext.request.contextPath}/orders/reject">
                <div class="modal-header">
                    <h4 class="modal-title"><spring:message
                            code="orders.rej.header"/></h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p><spring:message
                            code="orders.rej.body"/></p>
                    <p class="text-warning"><small><spring:message
                            code="orders.rej.warn"/></small></p>
                </div>
                <div class="modal-footer">
                    <input type="hidden" class="form-control" name="orderId" id="orderIdToReject" value="">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="<spring:message
                code="cancel"/>">
                    <input type="submit" class="btn btn-danger" value="<spring:message
                code="orders.rej.button"/>">
                </div>
            </form>
        </div>
    </div>
</div>


<div id="acceptOrderModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="post" action="${pageContext.request.contextPath}/orders/accept">
                <div class="modal-header">
                    <h4 class="modal-title"><spring:message
                            code="orders.ok.header"/></h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <p><spring:message
                            code="orders.ok.body"/></p>
                    <p class="text-warning"><small><spring:message
                            code="orders.ok.warn"/></small></p>
                </div>
                <div class="modal-footer">
                    <input type="hidden" class="form-control" name="orderId" id="orderIdToAccept" value="">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="<spring:message
                code="cancel"/>">
                    <input type="submit" class="btn btn-success" value="<spring:message
                code="orders.ok.button"/>">
                </div>
            </form>
        </div>
    </div>
</div>


<script>
    $(".passingIDAccept").click(function () {
        var ids = $(this).attr('data-id');
        console.log(ids);
        $("#orderIdToAccept").val(ids);
        $('#acceptOrderModal').modal('show');
    });
    $(".passingIDReject").click(function () {
        var ids = $(this).attr('data-id');
        console.log(ids);
        $("#orderIdToReject").val(ids);
        $('#rejectOrderModel').modal('show');
    });
</script>

<jsp:include page="${pageContext.request.contextPath}./include/footer.jsp"/>
</body>
</html>