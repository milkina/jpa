<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
    <jsp:attribute name="header">
        <meta name="robots" content="noindex">
        <title>Start Test ${TESTS[param.TEST_PATH].name}</title>
        <jsp:include page="/WEB-INF/google-ads-header.jsp" />
        <link href="${pageContext.request.contextPath}/css/multi-select.css" rel="stylesheet" type="text/css">
    </jsp:attribute>
     <jsp:body>
        <%@ taglib uri="/WEB-INF/tld/cache-tagjsp-taglib.tld" prefix="cache"%>
        <cache:cacheTag/>
        <div class="mainArea">
         <main>
          <h1 class="indexH1"><strong><spring:message code="start.test"/> ${TESTS[param.TEST_PATH].name}</strong></h1>
          <div>
          <form ACTION="${pageContext.request.contextPath}/start-test?TEST_PATH=${param.TEST_PATH}"  method="POST">
          <select name="CATEGORY_PATH" id="CATEGORY_PATH" multiple="multiple" class="2col active" required>
          <c:forEach var="category" items="${CATEGORIES}">
          <c:choose>
             <c:when test="${not empty category.subCategories}">
                <optgroup label="${category.name}">
                <c:forEach var="subCategory" items="${category.subCategories}">
                      <option value="${subCategory.pathName}">${subCategory.name}</option>
                </c:forEach>
                </optgroup>
             </c:when>
             <c:otherwise>
                <option value="${category.pathName}">${category.name}</option>
             </c:otherwise>
             </c:choose>
          </c:forEach>
          </select><BR>
           <script src="${pageContext.request.contextPath}/js/jquery-1.12.2.min.js"></script>
          <script src="${pageContext.request.contextPath}/js/jquery.multi-select.js"></script>
          <script>
          $('select[multiple]').multiselect({
              columns: 2,
              placeholder: '<spring:message code="select.categories"/>',
              selectAll : true,
              selectGroup:true,
              search:true
          });
          </script>
          </div>
         <spring:message code="number.questions.label"/> <input type="text" value="20" name="NUMBER_OF_QUESTIONS"><BR><BR>
         <input type="submit" value="<spring:message code="start.test"/>" name="startTest">
         </form>
         </main>
         <%@ include file="/WEB-INF/socialButtons.jsp" %>
        </div>
 </jsp:body>
</t:wrapper>
