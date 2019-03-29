<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/tld/examjsp-taglib.tld" prefix="exam" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper3>
<jsp:attribute name="header">
    <title>${TESTS[param.TEST_PATH].name} Quiz</title>
    <META NAME="Description" CONTENT="${CATEGORY_ATTRIBUTE.article.description}">
    <script async src="${pageContext.request.contextPath}/js/show_questions.js"></script>
    <script async src="${pageContext.request.contextPath}/js/prism.js?ver=1"></script>
    <link rel="canonical" href="http://www.examclouds.com/java/${param.TEST_PATH}/${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.category.pathName}" />
</jsp:attribute>
<jsp:attribute name="left">
      <jsp:include page="/WEB-INF/categoryMenu.jsp"/>
</jsp:attribute>
<jsp:body>
     <div class="mainArea">
        <h2 class="header2">${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.category.name}
           <span class="questionEntryNumber" id="questionEntryNumber"><exam:number/>/<exam:size/></span>
        </h2>
         <div class="questionEntryDiv">
              <div class="questionEntryCheckboxDiv">
                  <exam:checkbox/>
              </div>
              <div class="questionEntryBody">
                       <div class="questionText"><exam:number/>.&nbsp;${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.question.text}</div>
                       <input type="button" value="<spring:message code="read.answer"/>"
                        onclick="showAnswer('1','<spring:message code="read.answer"/>','<spring:message code="hide.answer"/>');"
                        id="a1" class="wideSubmitButton">
                       <div class="answer" id="answer1">${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.answer.text}</div>
               </div>
         </div><BR>
         <div style="display:inline">
              <form ACTION="${pageContext.request.contextPath}/show-exam-question?CATEGORY_PATH=${param.CATEGORY_PATH}&TEST_PATH=${param.TEST_PATH}"
                        method="POST">
                          <exam:previousButton><input type="submit" value="<spring:message code="previous"/>" name="PREVIOUS" ></exam:previousButton>
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