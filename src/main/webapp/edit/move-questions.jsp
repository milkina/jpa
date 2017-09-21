<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/categoryjsp-taglib.tld" prefix="category"%>
<t:wrapper>
    <jsp:attribute name="header">
        <title>Move Questions</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/administration.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
        <meta name="robots" content="noindex">
    </jsp:attribute>
    <jsp:body>
      <div class="mainArea">
           <c:if test="${person.sysadmin}">
              <h1 class="header1">Move Questions</h1>
              <form action="${pageContext.request.contextPath}/servlet/EditQuestionEntryServlet" method="POST" id="addQuestionForm">
                 <input type="hidden" name="EDIT_MODE_PARAM" value="MOVE_BATCH">
                 <input type="hidden" name="OLD_TEST_PATH" value="${param.TEST_PATH}">
                 <input type="hidden" name="OLD_CATEGORY_PATH" value="${param.CATEGORY_PATH}">
                 <span class="adminLabel">From</span><br>
                 <span class="adminLabel">Test:</span> ${TESTS[param.TEST_PATH].name}&nbsp;
                 <span class="adminLabel">Category:</span>&nbsp;<category:category categoryPath="${param.CATEGORY_PATH}">
                 <category:name/></category:category><BR><BR>
                 <span class="adminLabel">To</span><br>
                 <%@ include file="/edit/select-test-category-new.jsp" %>
          <span class="adminLabel" style="width:200px;">Question's Numbers</span><br>
          <span class="adminLabel">From</span>
          <input type="text" required id="from" name="FROM_NUMBER" class="selectWidth">
          <span class="adminLabel">To</span>
          <input type="text" required id="to" name="TO_NUMBER" class="selectWidth"><BR>
          <span class="wrongMessage">${message}</span><br>
          <input type="submit"  value="Ok" name="Save">
          </form>
                </c:if>
      </div>
    </jsp:body>
</t:wrapper>




