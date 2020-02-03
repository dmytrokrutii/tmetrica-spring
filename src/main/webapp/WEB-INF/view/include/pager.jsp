<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"
           prefix="fn" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="url" value="${requestScope['javax.servlet.forward.servlet_path']}"/>
<c:set var="params" value="${requestScope['javax.servlet.forward.query_string']}"/>
<c:set var="pageUrl" value="${url}${ not empty params?'?'+=params:'' }"/>
<c:set var="lastPage" value="${numberPages}"/>
<c:set var="currentPage" value="${fn:substringBefore(fn:replace(params,'page=',''), '&')}"/>

<div class="clearfix">
    <ul class="pagination">
        <c:choose>
            <c:when test="${currentPage >= 1}">
                <li class="page-item"><a class="page-link" href="${url}?page=${currentPage-1}&size=10">Previous</a>
                </li>
            </c:when>
        </c:choose>
        <c:if test="${lastPage > 0}">
        <c:forEach var="number" begin="0" end="${lastPage-1}">
            <c:choose>
                <c:when test="${number == currentPage}">
                    <li class="page-item selected"><a class="page-link selected" href="${url}?page=${number}&size=10">${number+1}</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="${url}?page=${number}&size=10">${number+1}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${currentPage < lastPage-1}">
                <li class="page-item"><a class="page-link"
                                         href="${url}?page=${currentPage+1}&size=10">Next</a></li>
            </c:when>
        </c:choose>
        </c:if>
    </ul>
</div>