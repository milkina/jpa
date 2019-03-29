<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:wrapper>
    <jsp:attribute name="header">
        <META NAME="Keywords" CONTENT="oracle java,oracle certification,java oracle,java certification,oracle java tutorial,oracle java certification,java quiz,oracle certifications,java tutorial oracle">
        <META NAME="Description"
                       CONTENT="Free online Java Tutorial for preparation to Oracle Java Certifications. Practice questions will help to be prepared and pass Oracle exams.">
        <title>Free Oracle Java Certification Tutorial on ExamClouds</title>
            <jsp:include page="/WEB-INF/google-ads-header.jsp" />
    </jsp:attribute>
     <jsp:body>
        <%@ taglib uri="/WEB-INF/tld/cache-tagjsp-taglib.tld" prefix="cache"%>
        <cache:cacheTag/>
        <div class="mainArea">
         <main>
          <h1 class="indexH1"><strong>IT Courses, Articles, Tests and Questions.</strong></h1>
               <c:forEach var="test" items="${TESTS}">
                <div class="testList">
                <c:set var="url" value="${'/'}" />
                <c:if test="${test.value.pathName ne 'ocjp' and test.value.pathName ne 'jpa' and test.value.pathName ne 'web-services' }">
                    <c:set var="url" value="${'/exam/'}" />
                </c:if>
                    <a href="${pageContext.request.contextPath}${url}${test.value.pathName}">
                       ${test.value.iconText}
                       <span class="questionsNumber">
                        ${test.value.questionsNumber + test.value.testsNumber} questions
                       </span>
                       <br>
                    </a>
                </div>
            </c:forEach>
            <BR>
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


