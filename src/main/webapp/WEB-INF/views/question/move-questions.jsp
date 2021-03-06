<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
    <jsp:attribute name="language">lang="<spring:message code='lang'/>"</jsp:attribute>
    <jsp:attribute name="header">
        <title><spring:message code="move.questions"/></title>
        <script src="${pageContext.request.contextPath}/js/administration.js"></script>
        <script src="${pageContext.request.contextPath}/js/common.js"></script>
        <meta name="robots" content="noindex">
    </jsp:attribute>
    <jsp:body>
      <div class="mainArea">
           <c:if test="${person.sysadmin}">
              <h1 class="header1"><spring:message code="move.questions"/></h1>
              <form action="${pageContext.request.contextPath}/move-batch" method="POST" id="addQuestionForm">
                 <input type="hidden" name="OLD_TEST_PATH" value="${param.OLD_TEST_PATH}">
                 <input type="hidden" name="OLD_CATEGORY_PATH" value="${param.OLD_CATEGORY_PATH}">
                 <span class="adminLabel"><spring:message code="from.uppercase"/></span><br>
                 <span class="adminLabel"><spring:message code="course"/>:</span> ${TESTS[param.OLD_TEST_PATH].name}&nbsp;
                 <span class="adminLabel"><spring:message code="category.label"/>:</span>&nbsp;${CATEGORY_ATTRIBUTE.name}<BR><BR>
                 <span class="adminLabel"><spring:message code="to"/></span><br>
                 <%@ include file="/edit/select-test-category-new.jsp" %>
          <span class="adminLabel" style="width:200px;"><spring:message code="number.questions.label"/></span><br>
          <span class="adminLabel"><spring:message code="from2"/></span>
          <input type="text" required id="from" name="FROM_NUMBER" class="selectWidth">
          <span class="adminLabel"><spring:message code="to2"/></span>
          <input type="text" required id="to" name="TO_NUMBER" class="selectWidth"><BR>
          <span class="wrongMessage">${message}</span><br>
          <input type="submit"  value="<spring:message code="move"/>" name="Save">
          </form>
                </c:if>
      </div>
    </jsp:body>
</t:wrapper>




