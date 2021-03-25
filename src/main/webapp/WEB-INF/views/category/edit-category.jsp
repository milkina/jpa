<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
<jsp:attribute name="language">lang="<spring:message code='lang'/>"</jsp:attribute>
 <jsp:attribute name="header">
    <meta name="robots" content="noindex">
    <title>Edit Category</title>
    <script src="${pageContext.request.contextPath}/js/common.js"></script>
    <jsp:include page="/edit/tinymceHeader.jsp"/>
 </jsp:attribute>
 <jsp:body>
       <div class="mainArea">
       <h1><spring:message code="edit.category"/></h1>
        <form action="${pageContext.request.contextPath}/edit-category" method="POST" id="editCategoryForm" >
            <span class="adminLabel"><spring:message code="category.id"/>:</span><span id="categoryId">${CATEGORY_ATTRIBUTE.id}</span>
            <input type="hidden" name="CATEGORY_PATH" value="${param.CATEGORY_PATH}">
            <input type="hidden" name="TEST_PATH" value="${param.TEST_PATH}">
            <BR>
            <span class="adminLabel"><spring:message code="courses"/>:</span>
             <c:choose>
                 <c:when test="${DUPLICATE_CATEGORIES[param.CATEGORY_PATH]!=null}">
                     <c:forEach var="test" items="${DUPLICATE_CATEGORIES[param.CATEGORY_PATH].tests}">
                                                                        ${test.name}&nbsp;
                     </c:forEach>
                 </c:when>
                 <c:otherwise>
                     ${param.TEST_PATH}
                 </c:otherwise>
             </c:choose>
             <BR>
            <%@ include file="/edit/categoryParameters.jsp" %>
            <input type="submit" value="<spring:message code="save.button"/>" name="Save"
             id="save">
               <br>
        </form>
      </div>
 </jsp:body>
</t:wrapper>