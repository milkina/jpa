<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
    <jsp:attribute name="header">
           <%@ include file="/WEB-INF/head/editableArea.jsp"%>
           <meta name="robots" content="noindex">
           <title><spring:message code="edit.question"/></title>
           <script src="${pageContext.request.contextPath}/js/common.js"></script>
  </jsp:attribute>
  <jsp:body>
       <div class="mainArea">
       <form action="${pageContext.request.contextPath}/edit-question" method="POST" id="editQuestionForm">
            <input type="hidden" name="QUESTION_ENTRY_ID_PARAM" id="QUESTION_ENTRY_ID_PARAM" value="${QUESTION_ENTRY_ATTRIBUTE.id}">
            <input type="hidden" name="OLD_CATEGORY_PATH" value="${param.CATEGORY_PATH}">
            <input type="hidden" name="OLD_TEST_PATH" id="TEST_ID_PARAM" value="${param.TEST_PATH}">
            <input type="hidden" name="EDIT_MODE_PARAM" id="EDIT_MODE_PARAM" value="SAVE">
             <BR> <BR>
               <%@ include file="/edit/select-test-category-new.jsp" %>
         &nbsp;  <a href="${pageContext.request.contextPath}/up-question?QUESTION_ENTRY_ID_PARAM=${QUESTION_ENTRY_ATTRIBUTE.id}&CATEGORY_PATH=${QUESTION_ENTRY_ATTRIBUTE.category.pathName}&TEST_PATH=${param.TEST_PATH}&EDIT_MODE_PARAM=UP_FROM_EDIT"
          class="showAnswer" id="up"><spring:message	code="up"/></a> <br> <br>
          <strong><spring:message code="question"/>:</strong>
          <textarea name="QUESTION_TEXT_PARAM" id="QUESTION_TEXT_PARAM" rows="20" cols="80">${QUESTION_ENTRY_ATTRIBUTE.question.text}
          </textarea>
          <BR> <BR>
          <strong><spring:message code="answers"/>:&nbsp;&nbsp;</strong><BR>
          <c:set var="count" value="${0}"/>
          <div id="answersDiv" class="answerBlockDiv">
          <c:forEach var="answer" items="${QUESTION_ENTRY_ATTRIBUTE.answers}">
            <c:set var="count" value="${count+1}" />
            <div id="answerblock${count}">
                 <input type="checkbox" name = "checkbox${count}" <c:if test="${answer.correct==true}">checked</c:if>>
                 <div class="answerDiv">
                    <textarea  name="ANSWER_TEXT_PARAM${count}" id="ANSWER_TEXT_PARAM${count}" rows="2" cols="80">${answer.text}</textarea>
                 </div>
                 <input type="button" onclick="deleteAnswer('${count}')" value="<spring:message	code="delete"/>"
                  id="deleteAnswer${count}"><BR><BR>
            </div>
          </c:forEach>
          </div>
          <BR> <BR>
          <input type="button" value="<spring:message code="save.button"/>" name="ok" onclick="editQuestion('${pageContext.request.contextPath}');">
          <input type="button" value="<spring:message code="add.next.answer"/>" onclick="addNextAnswer('<spring:message	code="delete"/>');" name="addAnswer">
          <input type="hidden" value="${count}" id="answerNumber" name="answerNumber">
        </form>
      </div>
 </jsp:body>
</t:wrapper>