<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
 <jsp:attribute name="header">
    <meta name="robots" content="noindex">
    <title><spring:message	code="add.category"/></title>
 </jsp:attribute>
 <jsp:body>
  <div class="mainArea">
        <form id="addCategoryForm" action="${pageContext.request.contextPath}/add-category" method="POST">
            <strong class="adminLabel"><spring:message	code="add.category.to"/>:</strong>
            <strong class="adminLabel">${TESTS[param.TEST_PATH].name}</strong>
             <br>
            <input type="hidden" name="OLD_TEST_PATH" value="${param.TEST_PATH}">
            <br>
            <%@ include file="/edit/select-test-category-new.jsp" %>
            <br>
            <input type="submit" value="<spring:message	code="add"/>" id="Add" name="Add">
        </form>
        </div>
 </jsp:body>
</t:wrapper>

