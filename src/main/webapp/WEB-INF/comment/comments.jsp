<%@taglib uri="/WEB-INF/tld/commentjsp-taglib.tld" prefix="comment"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="comment-wrapper">
  <div class="comment-label"><spring:message code="comments"/></div>
  <div class="commentDiv scroll-style" id="commentDiv">
   <comment:commentList type="${param.commentType}" referenceId="${param.referenceId}">
       <comment:comment>
           <div class="comment-item">
              <div class="comment-user"><comment:author/></div>
              <div class="comment-date"><comment:createdDate/></div>
              <div class="comment-text"><comment:body/></div>
           </div>
       </comment:comment>
   </comment:commentList>
   </div>
    <script>
                   function addUserComment(contextPath, message) {
                       var isLoggedIn = document.getElementById("isLogin");
                       if (!isLoggedIn) {
                           alert(message);
                           return;
                       }
                       var form = document.getElementById("addCommentForm");
                       form.action = contextPath + "/save-comment";
                       form.method = "POST";
                       form.submit();
                   }
                </script>
   <form action="<%=request.getContextPath()%>/save-comment" name="addCommentForm" id="addCommentForm">
      <input type="hidden" name="COMMENT_TYPE" value="${param.commentType}">
      <input type="hidden" name="REFERENCE_ID" value="${param.referenceId}">
      <textarea class="commentTextArea" name="commentText" id="commentText"
      placeholder="<spring:message code="leave.comment"/>"></textarea>
      <input type="button" class="styled-button" value="<spring:message code="post"/>" id="addComment" name="addComment"
      onclick="addUserComment('<%=request.getContextPath()%>','<spring:message code="comment.not.added"/>')">
   </form>
</div>
