<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form ACTION="${pageContext.request.contextPath}/start-quiz" METHOD="POST">
    <input type="hidden" name="CATEGORY_PATH" value="${param.CATEGORY_PATH}">
    <input type="hidden" name="TEST_PATH" value="${param.TEST_PATH}">
    <c:if test="${person != null}">
        <%@ include file="/WEB-INF/showQuestions/selectOptionsPanel.jsp" %>
    </c:if>
    <input type="submit" value="Start Quiz" id="startQuiz" class="submitButton">
</form>