<%@ taglib uri="/WEB-INF/tld/commentjsp-taglib.tld" prefix="comment"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<BR>
<div class="commentDiv" id="commentDiv">
<form action="<%=request.getContextPath()%>/save-comment" name="addCommentForm" id="addCommentForm">
<input type="hidden" name="COMMENT_TYPE" value="${param.commentType}">
<input type="hidden" name="REFERENCE_ID" value="${param.referenceId}">
   <BR>
   <comment:commentList type="${param.commentType}" referenceId="${param.referenceId}">
   <div class="commentAmount"><comment:amount/> <spring:message code="comments.number"/></div>
   <ol class="commentList">
       <comment:comment>
       <li>
            <div class="commentBody">
               <div class="commentAuthor"><comment:author/></div>
               <div class="commentDate"><comment:createdDate/></div>
               <comment:body/>
            </div>
       </li>
       </comment:comment>
   </ol>
   </comment:commentList>
    <script type="text/javascript">
                   function addUserComment(contextPath) {
                       var isLoggedIn = document.getElementById("isLogin");
                       if (!isLoggedIn) {
                           alert("Please log in or register to have a possibility to add comment.");
                           return;
                       }
                       var form = document.getElementById("addCommentForm");
                       form.action = contextPath + "/save-comment";
                       form.method = "POST";
                       form.submit();
                   }
                </script>
   <spring:message code="leave.comment"/>:<BR>
   <textarea rows="7" class="commentTextArea" maxlength="350" name="commentText" required id="commentText"></textarea><BR>
   <input type="button" value="<spring:message code="post"/>" id="addComment" name="addComment"
    onclick="addUserComment('<%=request.getContextPath()%>')">
   <BR><BR>
</form>
</div>