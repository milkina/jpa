<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/WEB-INF/tld/examjsp-taglib.tld" prefix="exam"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="/WEB-INF/tld/canonical-jsp-taglib.tld" prefix="ca"%>
<%@taglib uri="/WEB-INF/tld/menu-jsp-taglib.tld" prefix="menu"%>
<t:wrapper>
<jsp:attribute name="language">lang="${TESTS[param.TEST_PATH].language.code}"</jsp:attribute>
<jsp:attribute name="header">
    <title>${TESTS[param.TEST_PATH].name}&nbsp;<spring:message code="tests"/></title>
    <script async src="${pageContext.request.contextPath}/js/prism.min.js?ver=1"></script>
    <link rel="canonical" href="<ca:examCanonicalTag/>">
    <style>
      .questionText:before{
          content:"<exam:number/>";
      }
    </style>
</jsp:attribute>
<jsp:body>
     <div class="breadCrumbs">
       <ol itemscope itemtype="http://schema.org/BreadcrumbList">
           <%@ include file="/WEB-INF/breadCrumbs/homeBreadCrumb.jsp"%>
           <li itemprop="itemListElement" itemscope
                                    itemtype="http://schema.org/ListItem">
                <a itemprop="item" href="<menu:testsTag/>">
                 <span itemprop="name"><spring:message code="tests"/>
                 </span>
                 </a><meta itemprop="position" content="2"/></li>
           <li>${TESTS[param.TEST_PATH].name}</li>
       </ol>
     </div>
     <main>
        <c:if test="${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.category.parentCategory!=null}">
          <h1 class="exam-header1">
            ${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.category.parentCategory.name}
          </h1>
        </c:if>
        <div class="clearfix">
          <h2 class="exam-header2">
           ${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.category.name}
          </h2>
          <div class="questionEntryNumber" id="questionEntryNumber"><exam:number/>/<exam:size/></div>
        </div>
        <form ACTION="${pageContext.request.contextPath}/add-person-answer?TEST_PATH=${param.TEST_PATH}"
              method="POST">
         <div class="questionEntryDiv">
           <div class="questionText">${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.question.text}</div>
           <c:set var="count" value="${0}"/>
           <ul id="answersDiv">
             <c:forEach var="answer" items="${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.userAnswers}">
               <li id="answerblock${count}">
                 <input type="checkbox" class="test-checkbox" name = "checkbox${count}" id = "checkbox${count}" <c:if test="${answer.correct}">checked</c:if>>
                 <label class="answerDiv" for="checkbox${count}">${answer.text}</label>
                 <c:set var="count" value="${count+1}"/>
               </li>
             </c:forEach>
           </ul>
           <input type="hidden" name="answerNumber" value="${count}">
           <div class="row">
             <div class="col-xs-6">
              <input type="submit" value="<spring:message code="answer"/>" name="answerBtn" class="styled-button">
             </div>
             <div class="finish-exam-button col-xs-6">
               <a href="${pageContext.request.contextPath}/finish-exam" name="finishBtn"><spring:message code="finish"/></a>
             </div>
           </div>
         </div>
         </form>
         <div style="margin-top:50px" class="row">
           <div class="previous-exam-button col-xs-6 col-lg-2">
             <exam:previousButton>
               <a href="${pageContext.request.contextPath}/show-exam-question?TEST_PATH=${param.TEST_PATH}&QUESTION_NUMBER=<exam:number/>&PREVIOUS=PREVIOUS" class="previousHref">
                 <spring:message code="previous"/>
               </a>
              </exam:previousButton>
            </div>
            <div class="next-exam-button col-xs-6 col-lg-2 col-lg-push-8">
              <exam:nextButton>
                  <a href="${pageContext.request.contextPath}/show-exam-question?TEST_PATH=${param.TEST_PATH}&QUESTION_NUMBER=<exam:number/>&NEXT=NEXT" class="nextHref">
                    <spring:message code="next"/>
                  </a>
              </exam:nextButton>
            </div>
            <div class="examNumbers col-md-12 col-lg-8 col-lg-pull-2">
              <ul>
               <c:choose>
                 <c:when test="${fn:length(CURRENT_EXAM_ATTRIBUTE.questionEntries)<11}">
                   <c:set var="start" value="${1}"/>
                   <c:set var="end" value="${fn:length(CURRENT_EXAM_ATTRIBUTE.questionEntries)}"/>
                 </c:when>
                 <c:otherwise>
                   <c:set var="start" value="${CURRENT_EXAM_ATTRIBUTE.currentNumber-4>0?CURRENT_EXAM_ATTRIBUTE.currentNumber-4:1}"/>
                   <c:set var="end" value="${start+9<fn:length(CURRENT_EXAM_ATTRIBUTE.questionEntries)?start+9:fn:length(CURRENT_EXAM_ATTRIBUTE.questionEntries)}"/>
                   <c:set var="start" value="${fn:length(CURRENT_EXAM_ATTRIBUTE.questionEntries)-CURRENT_EXAM_ATTRIBUTE.currentNumber>4?start:(fn:length(CURRENT_EXAM_ATTRIBUTE.questionEntries)-9)}"/>
                 </c:otherwise>
               </c:choose>
                <c:if test="${start!=1}">
                   <li>..</li>
                </c:if>
                <c:forEach var="number"  begin="${start}" end="${end}">
                <li
                   <c:if test="${number==CURRENT_EXAM_ATTRIBUTE.currentNumber+1}">class="selected" </c:if>
                      ><a href="${pageContext.request.contextPath}/show-exam-question?TEST_PATH=${param.TEST_PATH}&QUESTION_NUMBER=${number-1}">
                          ${number}</a>
                   <c:if test="${CURRENT_EXAM_ATTRIBUTE.questionEntries[number-1].answered}">
                   &#10004;
                   </c:if>
                </li>
                </c:forEach>
                <c:if test="${end!=fn:length(CURRENT_EXAM_ATTRIBUTE.questionEntries)}">
                   <li>..</li>
                </c:if>
              </ul>
            </div>
         </div>
     <jsp:include page="/WEB-INF/comment/comments.jsp">
           <jsp:param name="referenceId" value="${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.id}" />
           <jsp:param name="commentType" value="QUESTION" />
     </jsp:include>
     </main>
 </jsp:body>
</t:wrapper>