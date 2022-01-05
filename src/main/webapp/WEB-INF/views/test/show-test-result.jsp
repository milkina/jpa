<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
    <jsp:attribute name="language">lang="${TESTS[param.TEST_PATH].language.code}"</jsp:attribute>
    <jsp:attribute name="header">
        <meta name="robots" content="noindex">
        <title><spring:message code="test.result"/>&nbsp;${person.login}</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/prism.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/circle.css">
        <script async src="${pageContext.request.contextPath}/js/prism.min.js?ver=1"></script>
   </jsp:attribute>
   <jsp:body>
        <main>
             <h1 class="header-test-result"><spring:message code="test.result"/>${person.login}</h1>
             <div class="row">
               <c:choose>
                  <c:when test="${CURRENT_EXAM_ATTRIBUTE.percent>=70}">
                  <div class="test-result-header-item face col-xs-6 col-sm-3 col-md-2">
                       <div class="smile">
                            <div class="happy"></div>
                       </div>
                  </div>
                  <div class="test-result-header-item test-passed col-xs-6 col-sm-3 col-md-2">
                     <spring:message code="test.passed"/>
                  </div>
                  </c:when>
                  <c:otherwise>
                   <div class="test-result-header-item face col-xs-6 col-sm-3 col-md-2">
                      <div class="smile">
                             <div class="sad"></div>
                      </div>
                   </div>
                   <div class="test-result-header-item col-xs-6 col-sm-3 col-md-2">
                      <div class="test-passed"><spring:message code="test.not.passed"/></div>
                   </div>
                  </c:otherwise>
               </c:choose>
               <div class="test-result-header-item diagram col-xs-6 col-sm-3 col-md-2">
                   <fmt:parseNumber var="percent" integerOnly="true" type="number" value="${CURRENT_EXAM_ATTRIBUTE.percent}"/>
                    <div class="c100 p${percent} small">
                       <span>${percent}%</span>
                       <div class="slice">
                          <div class="bar"></div>
                          <div class="fill"></div>
                       </div>
                    </div>
                </div>
                <div class="test-result-header-item col-xs-6 col-sm-3 col-md-2 col-lg-3">
                   <div class="answers-correct-label"><spring:message code="answers.correct"/></div>
                   <div class="from-100-percent-label"><spring:message code="from.100.percent"/></div>
                 </div>
                 <div class="test-result-header-item col-xs-3 col-xs-offset-1 col-sm-2 col-md-offset-0 col-md-1" class="right-wrong-answers">
                   <div class="right-answers-number">
                      <fmt:parseNumber var="intValue" integerOnly="true" type="number" value="${CURRENT_EXAM_ATTRIBUTE.rightQuestionsCount}"/>
                      <c:out value = "${intValue}"/>
                   </div>
                   <div class="wrong-answers-number">
                      ${fn:length(CURRENT_EXAM_ATTRIBUTE.questionEntries)-intValue}
                   </div>
                 </div>
                 <div class="test-result-header-item col-xs-3 col-xs-offset-2 col-sm-3 col-sm-offset-0 col-md-2">
                   <div class="right-answers-label">
                        <spring:message code="right.answers"/>
                   </div>
                   <div class="wrong-answers-label">
                       <spring:message code="wrong.answers"/>
                   </div>
                 </div>
             </div>
             <!-- TrustBox widget - Review Collector -->
             <div class="trustpilot-widget" data-locale="<spring:message code="trustpilot.locale"/>" data-template-id="56278e9abfbbba0bdcd568bc" data-businessunit-id="60103d74ba7c3600012452fe" data-style-height="52px" data-style-width="100%">
               <a href="https://<spring:message code="trustpilot.url"/>.trustpilot.com/review/examclouds.com" target="_blank" rel="noopener">Trustpilot</a>
             </div>
             <!-- End TrustBox widget -->
            <!-- TrustBox widget - Micro Review Count -->
            <div class="trustpilot-widget" data-locale="ru-RU" data-template-id="5419b6a8b0d04a076446a9ad" data-businessunit-id="60103d74ba7c3600012452fe" data-style-height="24px" data-style-width="100%" data-theme="light" data-stars="1,2,3,4,5" data-no-reviews="hide" data-scroll-to-list="true" data-allow-robots="true" data-min-review-count="10">
                <a href="https://ru.trustpilot.com/review/examclouds.com" target="_blank" rel="noopener">Trustpilot</a>
            </div>
            <!-- End TrustBox widget -->
             <ul class="showQuestionsList">
              <c:forEach var="number" begin="1" end="${fn:length(CURRENT_EXAM_ATTRIBUTE.questionEntries)}">
                 <li>
                   <div class="questionEntryBody">
                      <div class="questionText">${CURRENT_EXAM_ATTRIBUTE.questionEntries[number-1].question.text}</div>
                      <c:set var="answers" value="${CURRENT_EXAM_ATTRIBUTE.questionEntries[number-1].answers}"></c:set>
                      <c:set var="userAnswers" value="${CURRENT_EXAM_ATTRIBUTE.questionEntries[number-1].userAnswers}"></c:set>
                      <div class="your-answer"><spring:message code="your.answer"/></div>
                      <ul class="answersResult">
                        <c:forEach var="j" begin="0" end="${fn:length(answers)-1}">
                           <li class="your-answer-div
                              <c:if test="${not userAnswers[j].correct}"> answer-hidden</c:if>">${userAnswers[j].text}</li>
                        </c:forEach>
                      </ul>
                      <div class="right-answer"><spring:message code="right.answer"/></div>
                      <ul class="answersResult">
                         <c:forEach var="j" begin="0" end="${fn:length(answers)-1}">
                           <li class="right-answer-div
                             <c:if test="${not answers[j].correct}"> answer-hidden</c:if>">${answers[j].text}</li>
                         </c:forEach>
                      </ul>
                   </div>
                 </li>
              </c:forEach>
             </ul>
           </main>
 </jsp:body>
</t:wrapper>