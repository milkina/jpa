<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="questionEntryCheckboxDiv">
  <input type="checkbox" id="isAnswered<qe:id/>"
         title="<spring:message	code="mark.question"/>" <qe:checkbox/>
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
           <c:if test="${param.TYPE.equals('TEST') || param.TYPE.equals('QUESTION')}">
             <a href="<qe:up/>&TEST_PATH=${param.TEST_PATH}&TYPE=${param.TYPE}" class="showAnswer">
                <spring:message	code="up"/></a>
           </c:if>
             <a href="<qe:show/>&TEST_PATH=${param.TEST_PATH}" class="showAnswer editQuestion">
                <spring:message	code="edit"/></a>
             <a href="${pageContext.request.contextPath}/show-question?QUESTION_ENTRY_ID_PARAM=<qe:id/>&TEST_PATH=${param.TEST_PATH}"
             class="showAnswer goToQuestion"><spring:message	code="go.to"/></a>
             <a href="${pageContext.request.contextPath}/show-question-picture?QUESTION_ENTRY_ID_PARAM=<qe:id/>&TEST_PATH=${param.TEST_PATH}"
             class="showAnswer showPicture"><spring:message	code="show.picture"/></a>
             <a href="#" class="showAnswer deleteQuestion"
             onclick="deleteQuestion('${pageContext.request.contextPath}',<qe:id/>,'<category:pathName/>','${param.TEST_PATH}');">
             <spring:message code="delete"/></a>
            <c:if test="${param.TYPE.equals('NOT_APPROVED')}">
               <a href="#" class="showAnswer approveQuestion"
              onclick="approveQuestion('${pageContext.request.contextPath}',<qe:id/>);"><spring:message code="approve"/></a>
            </c:if>
       <input type="button" value="<spring:message code="read.answer"/>"
           onclick="showAnswer('<qe:number/>','<spring:message code="read.answer"/>','<spring:message code="hide.answer"/>');" id="a<qe:number/>">
       <qe:createdDate/>&nbsp;<qe:author/>&nbsp;<qe:approved/>
       </aside>
</div>