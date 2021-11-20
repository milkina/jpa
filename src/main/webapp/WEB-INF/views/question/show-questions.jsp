<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/categoryjsp-taglib.tld" prefix="category"%>
<%@ taglib uri="/WEB-INF/tld/question-entryjsp-taglib.tld" prefix="qe"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<category:category categoryPath="${param.CATEGORY_PATH}">
<t:wrapper>
    <jsp:attribute name="header">
        <meta name="robots" content="noindex">
        <title><spring:message code="questions"/> <category:name/> - ${TESTS[param.TEST_PATH].name}</title>
        <meta name="Description" content="<category:description/>">
        <script async src="${pageContext.request.contextPath}/js/show_questions.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/prism.css">
        <script async src="${pageContext.request.contextPath}/js/prism.min.js?ver=1"></script>
   </jsp:attribute>
   <jsp:body>
     <div class="mainArea">
        <main>
          <h1 class="header1">
                <category:name/>
                <c:if test="${param.TYPE.equals('NOT_APPROVED')}"><spring:message code="not.approved"/></c:if>
          </h1>
          <form action="${pageContext.request.contextPath}/clear-history">
          <input type="hidden" name="CATEGORY_PATH" value="<category:pathName/>">
          <input type="hidden" name="TEST_PATH" value="${param.TEST_PATH}">
          <c:if test="${person!=null && (param.TYPE.equals('QUESTION') || param.TYPE.equals('TEST'))}">
               <input type="submit" value="<spring:message	code="clear.history"/>" name="clearHistory">
               <%@ include file="/WEB-INF/showQuestions/selectOptionsPanel.jsp" %>
          </c:if>
          </form>
          <category:questionsCount/>
          <qe:qeList>
            <ul class="showQuestionsList">
              <qe:qe>
                  <li class="<qe:type/>" id="li<qe:id/>">
                     <div class="questionEntryDiv">
                         <%@ include file="/WEB-INF/showQuestions/questionEntry.jsp" %>
                      </div>
                  </li>
              </qe:qe>
            </ul>
          </qe:qeList>
        </main>
      </div>
 </jsp:body>
</t:wrapper>
</category:category>
