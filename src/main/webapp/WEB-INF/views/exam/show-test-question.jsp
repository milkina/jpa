<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/tld/examjsp-taglib.tld" prefix="exam" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper3>
<jsp:attribute name="language">lang="${TESTS[param.TEST_PATH].language.code}"</jsp:attribute>
<jsp:attribute name="header">
    <title>${TESTS[param.TEST_PATH].name} Test</title>
    <script async src="${pageContext.request.contextPath}/js/show_questions_min.js?v=4"></script>
    <script async src="${pageContext.request.contextPath}/js/prism.js?ver=1"></script>
    <link rel="canonical" href="${pageContext.request.contextPath}/java/${param.TEST_PATH}/${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.category.pathName}"/>
</jsp:attribute>
<jsp:attribute name="left">
      <jsp:include page="/WEB-INF/categoryMenu.jsp"/>
</jsp:attribute>
<jsp:body>
     <div class="mainArea">
        <h2 class="header2">
        <c:if test="${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.category.parentCategory!=null}">
            ${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.category.parentCategory.name}.
        </c:if>
        ${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.category.name}
           <span class="questionEntryNumber" id="questionEntryNumber"><exam:number/>/<exam:size/></span>
        </h2>
        <form ACTION="${pageContext.request.contextPath}/add-person-answer?CATEGORY_PATH=${param.CATEGORY_PATH}&TEST_PATH=${param.TEST_PATH}"
              method="POST">
        <div class="examNumbers">
                      <c:forEach var="number"  begin="1" end="${fn:length(CURRENT_EXAM_ATTRIBUTE.questionEntries)}">
                          <span
                          <c:if test="${number==CURRENT_EXAM_ATTRIBUTE.currentNumber+1}">style="font-weight:bold" </c:if>
                          ><a href="${pageContext.request.contextPath}/show-exam-question?CATEGORY_PATH=${param.CATEGORY_PATH}&TEST_PATH=${param.TEST_PATH}&QUESTION_NUMBER=${number-1}">
                              ${number}</a></span>
                          <c:if test="${CURRENT_EXAM_ATTRIBUTE.questionEntries[number-1].answered}">
                             &#10004;
                          </c:if>
                          &nbsp;
                      </c:forEach>
         </div><BR>
         <div class="questionEntryDiv">
              <div class="questionEntryBody">
                       <div class="questionText"><exam:number/>.&nbsp;${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.question.text}</div>
                       <c:set var="count" value="${0}"/>
                       <div id="answersDiv" >
                          <c:forEach var="answer" items="${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.userAnswers}">
                             <div id="answerblock${count}">
                                <input type="checkbox" name = "checkbox${count}" <c:if test="${answer.correct}">checked</c:if>>
                                    <div class="answerDiv">
                                           ${answer.text}
                                    </div><BR><BR>
                                    <c:set var="count" value="${count+1}" />
                             </div>
                          </c:forEach>
                          <input type="hidden" name="answerNumber" value="${count}">
                       </div>
               </div>
         </div>

         <BR>
         <input type="submit" value="<spring:message code="answer"/>" name="answerBtn">
         </form>
         <form ACTION="${pageContext.request.contextPath}/finish-exam"
         method="POST">
               <input type="submit" value="<spring:message code="finish"/>" name="finishBtn">
         </form>
         <BR>
         <div style="display:inline">
              <form ACTION="${pageContext.request.contextPath}/show-exam-question?TEST_PATH=${param.TEST_PATH}&QUESTION_NUMBER=<exam:number/>"
                        method="POST">
                          <exam:previousButton><input type="submit" value="<spring:message code="previous"/>" name="PREVIOUS"></exam:previousButton>
                          <exam:nextButton><input type="submit" value="<spring:message code="next"/>" name="NEXT" style="float:right"></exam:nextButton>
              </form>
         </div>
     <jsp:include page="/WEB-INF/comment/comments.jsp">
           <jsp:param name="referenceId" value="${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.id}" />
           <jsp:param name="commentType" value="QUESTION" />
     </jsp:include>
     </div>
 </jsp:body>
</t:wrapper3>