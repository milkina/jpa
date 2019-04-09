<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tld/cache-tagjsp-taglib.tld" prefix="cache"%>
<t:wrapper>
    <jsp:attribute name="language"> lang="<spring:message code="lang"/>"</jsp:attribute>
    <jsp:attribute name="header">
        <META NAME="Description" CONTENT="<spring:message code="main.description"/>">
        <title><spring:message code="main.title"/></title>
        <jsp:include page="/WEB-INF/google-ads-header.jsp" />
        <link rel="alternate" hreflang="ru" href="http://www.examclouds.com/ru/">
        <link rel="alternate" hreflang="en" href="http://www.examclouds.com/">
        <link rel="alternate" hreflang="x-default" href="http://www.examclouds.com/">
    </jsp:attribute>
    <jsp:body>
        <cache:cacheTag/>
        <div class="mainArea">
         <main>
          <h1 class="indexH1"><spring:message code="main.header"/></h1>
               <c:forEach var="test" items="${TESTS}">
                <div class="testList">
                    <a href="${pageContext.request.contextPath}/${test.value.fullPathName}">
                       ${test.value.iconText}
                       <span class="questionsNumber">
                        ${test.value.questionsNumber + test.value.testsNumber} questions
                       </span>
                       <br>
                    </a>
                </div>
            </c:forEach>
           </main>
           <div style="float:left">
           <%@ include file="/WEB-INF/socialButtons.jsp" %>
           <jsp:include page="/WEB-INF/comment/comments.jsp">
                <jsp:param name="referenceId" value="1" />
                <jsp:param name="commentType" value="ARTICLE" />
           </jsp:include>
           </div>
     </div>
 </jsp:body>
</t:wrapper>


