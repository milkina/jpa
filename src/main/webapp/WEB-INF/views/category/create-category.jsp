<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
 <jsp:attribute name="header">
    <meta name="robots" content="noindex">
    <title><spring:message code="create.category"/> | ExamClouds</title>
    <script src="${pageContext.request.contextPath}/js/common.js?v=1"></script>
    <jsp:include page="/edit/tinymceHeader.jsp"/>
 </jsp:attribute>
 <jsp:body>
  <div class="mainArea">
        <form id="addCategoryForm" action="${pageContext.request.contextPath}/create-category" method="post">
             <br>
            <strong class="adminLabel"><spring:message code="course"/>:</strong>
            <strong class="adminLabel">${TESTS[param.TEST_PATH].name}</strong>
            <input type="hidden" name="TEST_PATH" value="${param.TEST_PATH}">
            <br>
            <c:set var="CATEGORY_ATTRIBUTE" value="${null}" scope="session"  />
            <%@ include file="/edit/categoryParameters.jsp" %>
            <input type="submit" value="<spring:message code="save.button"/>" id="Save" name="Save">
        </form>
        </div>
 </jsp:body>
</t:wrapper>

