<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/tld/examjsp-taglib.tld" prefix="exam" %>
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
         <div class="questionEntryDiv">
              <div class="questionEntryCheckboxDiv">
                  <exam:checkbox/>
              </div>
              <div class="questionEntryBody">
                       <div class="questionText"><exam:number/>.&nbsp;${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.question.text}</div>
                       <input type="button" value="Read Answer" onclick="showAnswer('1');" id="a1"  class="wideSubmitButton">
                       <div class="answer" id="answer1">${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.answer.text}</div>
               </div>
         </div><BR>
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