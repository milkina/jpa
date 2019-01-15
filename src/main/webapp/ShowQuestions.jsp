<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/categoryjsp-taglib.tld" prefix="category"%>
<%@ taglib uri="/WEB-INF/tld/question-entryjsp-taglib.tld" prefix="qe"%>
<category:category categoryPath="${param.CATEGORY_PATH}">
<t:wrapper>
    <jsp:attribute name="header">
        <meta name="robots" content="noindex">
        <title>Questions <category:name/> - ${TESTS[param.TEST_PATH].name}</title>
        <META NAME="Description" CONTENT="<category:description/>">
        <script type="text/javascript" async src="${pageContext.request.contextPath}/js/show_questions.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/prism_min.css">
        <script type="text/javascript" async src="${pageContext.request.contextPath}/js/prism.js?ver=1"></script>
   </jsp:attribute>
   <jsp:body>
     <div class="mainArea">
        <main>
             <h1><category:name/></h1>
          <form action="${pageContext.request.contextPath}/servlet/ClearHistoryServlet">
          <input type="hidden" name="CATEGORY_PATH" value="<category:pathName/>">
          <input type="hidden" name="TEST_PATH" value="${param.TEST_PATH}">
          <c:if test="${person!=null && (param.TYPE.equals('QUESTION') || param.TYPE.equals('TEST'))}">
               <input type="submit" value="Clear History" name="clearHistory">
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
