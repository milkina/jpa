<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
 <jsp:attribute name="header">
    <meta name="robots" content="noindex">
    <title>Create Category on ExamClouds</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js?v=1"></script>
    <jsp:include page="/edit/tinymceHeader.jsp"/>
 </jsp:attribute>
 <jsp:body>
  <div class="mainArea">
        <form id="addCategoryForm" action="${pageContext.request.contextPath}/servlet/CreateCategoryServlet">
             <br>
            <strong class="adminLabel">Test:</strong>
            <strong class="adminLabel">${TESTS[param.TEST_PATH].name}</strong>
            <input type="hidden" name="TEST_PATH" value="${param.TEST_PATH}">
            <br>
            <c:set var="CATEGORY_ATTRIBUTE" value="${null}" scope="session"  />
            <%@ include file="/edit/categoryParameters.jsp" %>
            <input type="BUTTON" value="Save" id="Save" name="Save" class="submitButton"
            onclick="saveCategory()">
        </form>
        </div>
 </jsp:body>
</t:wrapper>

