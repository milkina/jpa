<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<t:wrapper>
    <jsp:attribute name="header">
        <meta name="robots" content="noindex">
        <title>${person.login} Test Result</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/prism_min.css">
        <script type="text/javascript" async src="${pageContext.request.contextPath}/js/prism.js?ver=1"></script>
   </jsp:attribute>
   <jsp:body>
     <div class="mainArea">
        <main>
             <h1 class="header1">${person.login} Test Result</h1>
              <c:forEach var="number"  begin="1" end="${fn:length(CURRENT_EXAM_ATTRIBUTE.questionEntries)}">
                 <ul class="showQuestionsList">
                     <li>
                        <div class="questionEntryBody">
                              <div class="questionText">${number}.${CURRENT_EXAM_ATTRIBUTE.questionEntries[number-1].question.text}</div>
                               <c:set var="answers" value="${CURRENT_EXAM_ATTRIBUTE.questionEntries[number-1].answers}"></c:set>
                               <c:set var="userAnswers" value="${CURRENT_EXAM_ATTRIBUTE.questionEntries[number-1].userAnswers}"></c:set>
                               <c:forEach var="j" begin="0" end="${fn:length(answers)-1}">
                                 <div class="answer"
                                 style="display:block;<c:if test="${answers[j]!=userAnswers[j]}">text-decoration:line-through"</c:if>"><div><input type="checkbox" disabled <c:if test="${userAnswers[j].correct}">checked</c:if>>${answers[j].text}</div></div>
                               </c:forEach>
                        </div>
                     </li>
                 </ul>
              </c:forEach>
             ${CURRENT_EXAM_ATTRIBUTE.percent>=70?"Test is passed.":"Test is not passed."}<BR>
             ${CURRENT_EXAM_ATTRIBUTE.percent}% answers are correct.<BR>
              Answered ${CURRENT_EXAM_ATTRIBUTE.rightQuestionsCount} from ${fn:length(CURRENT_EXAM_ATTRIBUTE.questionEntries)}
        </main>
      </div>
 </jsp:body>
</t:wrapper>
