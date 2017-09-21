<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:wrapper>
 <jsp:attribute name="header">
    <meta name="robots" content="noindex">
    <title>Edit Category</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
    <jsp:include page="/edit/tinymceHeader.jsp"/>
 </jsp:attribute>
 <jsp:body>
       <div class="mainArea">
        <form action="${pageContext.request.contextPath}/servlet/EditCategoryServlet" method="POST" id="editCategoryForm" >
            <span class="adminLabel">Category Id:</span><span name="categoryId">${CATEGORY_ATTRIBUTE.id}</span>
            <a href="${pageContext.request.contextPath}/servlet/UpCategoryServlet?TEST_PATH=${param.TEST_PATH}&CATEGORY_PATH=${param.CATEGORY_PATH}&PAGE=EDIT"
              name="up${param.CATEGORY_PATH}">Up</a>

            <input type="hidden" name="CATEGORY_PATH" value="${param.CATEGORY_PATH}">
            <input type="hidden" name="TEST_PATH" value="${param.TEST_PATH}">
            <BR>
            <span class="adminLabel">Assigned Tests:</span>
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
            <input type="submit" value="Save" name="Save"
             id="save" class="submitButton">

               <br>
        </form>
      </div>
 </jsp:body>
</t:wrapper>