<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
    <jsp:attribute name="header">
       <%@ include file="/WEB-INF/head/editableArea.jsp"%>
       <meta name="robots" content="noindex">
       <title>Add Question</title>
       <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
  </jsp:attribute>
  <jsp:body>
        <div class="mainArea">
        <form action="${pageContext.request.contextPath}/servlet/AddQuestionEntry" method="POST" id="addQuestionForm" >
            <BR> <BR>
            <input type="hidden" value="1" id="answerNumber" name="answerNumber">
            <%@ include file="/edit/select-test-category-new.jsp" %>
            <strong>Question:</strong>
            <textarea name="QUESTION_TEXT_PARAM" id="QUESTION_TEXT_PARAM">
            </textarea> <BR> <BR>
            <strong>Answers:</strong>
            <div id="answersDiv" class="answerBlockDiv">
              <div id="answerblock1">
                 <input type="checkbox" name = "checkbox1">
                 <div class="answerDiv" id="answer1">
                    <textarea name="ANSWER_TEXT_PARAM1" id="ANSWER_TEXT_PARAM1"></textarea>
                 </div>
                 <input type="button" onclick="deleteAnswer('1')" value="Delete" id="deleteAnswer1">
              </div>
            </div>
            <BR>
            <input type="button" value="Ok" name="ok" id="ok" onclick="saveQuestion('${pageContext.request.contextPath}')">
            <input type="button" value="Add Next Answer" onclick="addNextAnswer();" name="addAnswer">
        </form>
        <br>
     </div>
 </jsp:body>
</t:wrapper>