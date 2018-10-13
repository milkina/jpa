<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/tld/examjsp-taglib.tld" prefix="exam" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<t:wrapper3>
<jsp:attribute name="header">
    <title>${CATEGORY_ATTRIBUTE.name} - ${TESTS[param.TEST_PATH].name}</title>
    <META NAME="Description" CONTENT="${CATEGORY_ATTRIBUTE.article.description}">
    <script type="text/javascript" async src="${pageContext.request.contextPath}/js/show_questions_min.js?v=4"></script>
    <script type="text/javascript" async src="${pageContext.request.contextPath}/js/prism.js?ver=1"></script>
    <meta name="robots" content="noindex">
</jsp:attribute>
<jsp:attribute name="left">
      <jsp:include page="/WEB-INF/categoryMenu.jsp"/>
</jsp:attribute>
<jsp:body>
     <div class="mainArea">
       <jsp:include page="/WEB-INF/breadCrumbs/breadCrumbs3.jsp"/>
        <h2 class="header2">${CATEGORY_ATTRIBUTE.name}
           <span class="questionEntryNumber" id="questionEntryNumber"><exam:number/>/<exam:size/></span>
        </h2>
        <form ACTION="${pageContext.request.contextPath}/add-user-answer?CATEGORY_PATH=${param.CATEGORY_PATH}&TEST_PATH=${param.TEST_PATH}"
              method="POST">
         <div class="questionEntryDiv" style="display:inline-block;width:95%">
              <div class="questionEntryBody">
                       <div class="questionText"><exam:number/>.&nbsp;${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.question.text}</div>
                       <c:set var="count" value="${0}"/>
                       <div id="answersDiv" >
                          <c:forEach var="answer" items="${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.userAnswers}">
                             <div id="answerblock${count}">
                                <input type="checkbox" name = "checkbox${count}" <c:if test="${answer.correct}">checked</c:if>>
                                    <div class="answerDiv">
                                           ${answer.text}
                                    </div>
                                    <c:set var="count" value="${count+1}" />
                             </div>
                          </c:forEach>
                          <input type="hidden" name="answerNumber" value="${count}">
                       </div>
               </div>
         </div>
         <div style="float:right;width:5%" >
             <c:forEach var="number"  begin="1" end="${fn:length(CURRENT_EXAM_ATTRIBUTE.questionEntries)}">
                 <span
                 <c:if test="${number==CURRENT_EXAM_ATTRIBUTE.currentNumber+1}">style="font-weight:bold" </c:if>
                 >${number}</span>
                 <c:if test="${CURRENT_EXAM_ATTRIBUTE.questionEntries[number-1].answered}">
                    &#10004;
                 </c:if>
                 <BR>
             </c:forEach>
         </div>
         <BR>
         <input type="submit" value="Answer" class="submitButton">
         </form>
          <BR>
         <div style="display:inline">
              <form ACTION="${pageContext.request.contextPath}/show-exam-question?CATEGORY_PATH=${param.CATEGORY_PATH}&TEST_PATH=${param.TEST_PATH}&QUESTION_NUMBER=<exam:number/>"
                        method="POST">
                          <exam:previousButton><input type="submit" value="Previous" name="PREVIOUS" class="submitButton" ></exam:previousButton>
                          <exam:nextButton><input type="submit" value="Next" name="NEXT" class="submitButton" style="float:right"></exam:nextButton>
              </form>
         </div>
     <jsp:include page="/WEB-INF/comment/comments.jsp">
           <jsp:param name="referenceId" value="${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.id}" />
           <jsp:param name="commentType" value="QUESTION" />
     </jsp:include>
     </div>
 </jsp:body>
</t:wrapper3>