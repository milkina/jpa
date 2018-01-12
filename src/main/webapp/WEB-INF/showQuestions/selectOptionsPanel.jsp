<%@ page import="static util.AllConstants.MARK_QUESTION_HINT" %>
<div class="selectOptionsPanel" id="selectOptionsPanel">
Show All <input type="radio" name="questionType" value="ALL" checked
                                   onclick="changeShowingMode(this);" title="<%=MARK_QUESTION_HINT%>">
Show Answered<input type="radio" name="questionType" value="ANSWERED" onclick="changeShowingMode(this);"
                                   title="<%=MARK_QUESTION_HINT%>">
Show Not Answered<input type="radio" name="questionType" value="NOT_ANSWERED"
                                   onclick="changeShowingMode(this);" title="<%=MARK_QUESTION_HINT%>">
</div>