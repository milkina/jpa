<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="questionEntryCheckboxDiv">
  <input type="checkbox" id="isAnswered<qe:id/>"
         title="Mark question as answered and filter questions" <qe:checkbox/>
         onchange="markAnswered('${pageContext.request.contextPath}',<qe:id/>);">
</div>
<div class="questionEntryBody">
     <div class="questionText"><qe:number/>.&nbsp;<qe:question/></div>
     <qe:answers>
       <div class="answer" id="answer<qe:number/>"><qe:answer>
       <div><input type="checkbox" disabled <qe:answercheckbox/> ><qe:answertext/></div>
       </qe:answer></div>
     </qe:answers>
     <aside>
       <c:if test="${person.sysadmin}">
             <a href="<qe:up/>&TEST_PATH=${param.TEST_PATH}" class="showAnswer">Up</a>
             <a href="<qe:show/>&TEST_PATH=${param.TEST_PATH}" class="showAnswer" name="editQuestion">Edit</a>
             <a href="${pageContext.request.contextPath}/show-question?QUESTION_ENTRY_ID_PARAM=<qe:id/>&TEST_PATH=${param.TEST_PATH}"
             class="showAnswer" name="goToQuestion">Go To</a>
             <a href="${pageContext.request.contextPath}/show-question?QUESTION_ENTRY_ID_PARAM=<qe:id/>&MODE=PICTURE&TEST_PATH=${param.TEST_PATH}"
             class="showAnswer" name="showPicture">Show picture</a>
             <a href="#" class="showAnswer" name="deleteQuestion"
             onclick="deleteQuestion('${pageContext.request.contextPath}',<qe:id/>,'<category:pathName/>','${param.TEST_PATH}');">Delete</a>
        </c:if>
       <input type="button" value="Read Answer" onclick="showAnswer('<qe:number/>');" id="a<qe:number/>">
       </aside>
</div>