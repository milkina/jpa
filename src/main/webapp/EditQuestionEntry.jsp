<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
    <jsp:attribute name="header">
           <%@ include file="/WEB-INF/head/editableArea.jsp"%>
           <meta name="robots" content="noindex">
           <title>Edit Question</title>
           <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
  </jsp:attribute>
  <jsp:body>
       <div class="mainArea">
        <form action="${pageContext.request.contextPath}/servlet/EditQuestionEntryServlet" method="POST"  id="editQuestionForm">
            <input type="hidden" name="QUESTION_ENTRY_ID_PARAM" id="QUESTION_ENTRY_ID_PARAM" value="${QUESTION_ENTRY_ATTRIBUTE.id}">
            <input type="hidden" name="OLD_CATEGORY_PATH" value="${CATEGORY_PATH}">
            <input type="hidden" name="OLD_TEST_PATH" id="TEST_ID_PARAM" value="${TEST_PATH}">
            <input type="hidden" name="EDIT_MODE_PARAM" id="EDIT_MODE_PARAM" value="SAVE">
             <BR> <BR>
               <%@ include file="/edit/select-test-category-new.jsp" %>
         &nbsp;  <a href="${pageContext.request.contextPath}/servlet/EditQuestionEntryServlet?QUESTION_ENTRY_ID_PARAM=${QUESTION_ENTRY_ATTRIBUTE.id}&CATEGORY_PATH=${QUESTION_ENTRY_ATTRIBUTE.category.pathName}&TEST_PATH=${param.TEST_PATH}&EDIT_MODE_PARAM=UP_FROM_EDIT"
          class="showAnswer" name="up">Up</a> <br> <br>
           <strong> Question:</strong>
            <textarea  name="QUESTION_TEXT_PARAM" id="QUESTION_TEXT_PARAM">${QUESTION_ENTRY_ATTRIBUTE.question.text}
            </textarea>   <BR> <BR>
          <strong>  Answer:&nbsp;&nbsp;</strong>
            <textarea  name="ANSWER_TEXT_PARAM" id="ANSWER_TEXT_PARAM">${QUESTION_ENTRY_ATTRIBUTE.answer.text}</textarea>
            <BR> <BR>
            <input type="button" value="Ok" class="submitButton" name="ok" onclick="editQuestion('${pageContext.request.contextPath}');">
        </form>
      </div>
 </jsp:body>
</t:wrapper>