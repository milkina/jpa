<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
    <jsp:attribute name="language"> lang="<spring:message code="lang"/>"</jsp:attribute>
    <jsp:attribute name="header">
        <META NAME="Description"
                       CONTENT="<spring:message code="tests.description"/>">
        <title><spring:message code="tests.questions"/> | ExamClouds</title>
        <jsp:include page="/WEB-INF/google-ads-header.jsp"/>
        <link rel="alternate" hreflang="ru" href="http://www.examclouds.com/ru/tests">
        <link rel="alternate" hreflang="en" href="http://www.examclouds.com/tests">
        <link rel="alternate" hreflang="x-default" href="http://www.examclouds.com/tests">
    </jsp:attribute>
     <jsp:body>
        <%@ taglib uri="/WEB-INF/tld/cache-tagjsp-taglib.tld" prefix="cache"%>
        <cache:cacheTag/>
        <div class="mainArea">
         <main>
          <h1 class="header1"><spring:message code="tests.questions.java"/></h1>
          <div>
          <h2 class="indexH1"><strong><spring:message code="tests"/></strong></h2>
          <c:forEach var="test" items="${TESTS_WITH_TESTS}">
             <a href="${pageContext.request.contextPath}/select-category-for-exam?TEST_PATH=${test.pathName}"
             id="${test.pathName}Test">${test.name}</a><BR>
          </c:forEach>
          </div>
          <div>
           <h2 class="indexH1"><strong><spring:message code="questions"/></strong></h2>
          <c:forEach var="test" items="${COURSES_WITH_QUESTIONS}">
             <a href="${pageContext.request.contextPath}/select-categories-to-see-questions?TEST_PATH=${test.pathName}"
             id="${test.pathName}Question">${test.name}</a><BR>
          </c:forEach>
          </div>
         </main>
         <%@ include file="/WEB-INF/socialButtons.jsp" %>
        </div>
 </jsp:body>
</t:wrapper>
