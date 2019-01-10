<%@ page import="static util.AllConstants.MARK_QUESTION_HINT" %>
<div class="selectOptionsPanel" id="selectOptionsPanel">
<spring:message code="show.all"/> <input type="radio" name="questionType" value="ALL" checked
                                   onclick="changeShowingMode(this);" title="<%=MARK_QUESTION_HINT%>">
<spring:message code="show.answered"/><input type="radio" name="questionType" value="ANSWERED" onclick="changeShowingMode(this);"
                                   title="<%=MARK_QUESTION_HINT%>">
<spring:message code="show.not.answered"/><input type="radio" name="questionType" value="NOT_ANSWERED"
                                   onclick="changeShowingMode(this);" title="<%=MARK_QUESTION_HINT%>">
</div>