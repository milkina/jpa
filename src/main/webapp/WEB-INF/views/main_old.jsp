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
           <div class="indexColumn">
             <h2 class="header2">Oracle Certifications for Java Developers</h2>
             <p>Oracle Corporation has a range of internationally recognized exams for IT specialists. Oracle refreshes its certification exams with a new release of Java or a Java product. Software engineers should pass new tests to support their qualifying level. They are considered to be the best tests for java engineers.</p>
             <h2 class="header2">How to Get Certified in Java?</h2>
             <p><!--noindex--><a href="http://education.oracle.com/" target="_blank" rel="nofollow noopener">Oracle education site</a><!--/noindex--> contains such important info as topic's exam, training recommendation, exam duration and fee. <!--noindex--><a href="https://wsr.pearsonvue.com/testtaker/registration/SelectTestCenterProximity/ORACLE/290123" target="_blank" rel="nofollow noopener">Pearsonvue</a><!--/noindex--> can help to find the nearest test center, where it is possible to register for exam. Such exams as OCJP have very extended info of exam objects. But some lack detailed information, for example Web Service certification. <strong style="font-weight: normal;">Online java exam questions and answers quiz</strong> tutorials can be helpful in preparation. They will help to find out which topics are more important for exam and should be paid attention to. And which can be skipped. Feedbacks of those, who has already passed exams, is an invaluable thing as well. Discussions of certifications can be found on <!--noindex--><a href="http://www.coderanch.com/" target="_blank" rel="nofollow noopener">Coderanch</a><!--/noindex--> site.</p>
             <h2 class="header2">About Us</h2>
             <p>Study material for oracle java certification preparation on the ExamClouds will help to get prepared for exams. There are free tests and questions created by certified java engineers.</p>
             <h2 class="header2">FAQ</h2>
               <h3 class="header3">How much time should be spent on the preparation?</h3>
               <p>It depends on the person's background in the specified area. Some people can spend several months, for others a week is enough to pass an exam.</p>
               <h3 class="header3">Is Java Certification worth it?</h3>
               <p>Very often engineers work in some particular area, and learning something new is a good chance to broad your mind, get in a new technology and implement it in the daily work. Or just improve and structure your knowledge in the area of exam.</p>
           </div>
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


