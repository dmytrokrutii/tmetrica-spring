<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="url" value="${requestScope['javax.servlet.forward.servlet_path']}"/>
<c:set var="params" value="${requestScope['javax.servlet.forward.query_string']}"/>
<c:set var="pageUrl" value="${url}${ not empty params?'?'+=params:'' }"/>

    <c:choose>
        <c:when test="${fn:contains(pageUrl, '?')}">
            <c:choose>
                <c:when test="${fn:contains(pageUrl, 'lang')}">
                    <c:set var="params" value="${fn:replace(params, 'lang=ru', '')}"/>
                    <c:set var="params" value="${fn:replace(params, 'lang=en', '')}"/>
                    <a class="nav-link btn btn-outline-primary btn-sm" href="<c:url value = "${url}?${params}lang=en"/>">EN</a>
                </c:when>
                <c:otherwise>
                    <c:set var="params" value="${fn:replace(params, '&lang=ru', '')}"/>
                    <c:set var="params" value="${fn:replace(params, '&lang=en', '')}"/>
                    <a class="nav-link btn btn-outline-primary btn-sm" href="<c:url value = "${url}?${params}&lang=en"/>">EN</a>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <a class="nav-link btn btn-outline-primary btn-sm" href="<c:url value = "${url}?lang=en"/>">EN</a>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${fn:contains(pageUrl, '?')}">
            <c:choose>
                <c:when test="${fn:contains(pageUrl, 'lang')}">
                    <c:set var="params" value="${fn:replace(params, 'lang=ru', '')}"/>
                    <c:set var="params" value="${fn:replace(params, 'lang=en', '')}"/>
                    <a class="nav-link btn btn-outline-primary btn-sm" href="<c:url value = "${url}?${params}lang=ru"/>">RU</a>
                </c:when>
                <c:otherwise>
                    <c:set var="params" value="${fn:replace(params, '&lang=ru', '')}"/>
                    <c:set var="params" value="${fn:replace(params, '&lang=en', '')}"/>
                    <a class="nav-link btn btn-outline-primary btn-sm" href="<c:url value = "${url}?${params}&lang=ru"/>">RU</a>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <a class="nav-link btn btn-outline-primary btn-sm" href="<c:url value = "${url}?lang=ru"/>">RU</a>
        </c:otherwise>
    </c:choose>

