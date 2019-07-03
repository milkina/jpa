<%@taglib uri="/WEB-INF/tld/commentjsp-taglib.tld" prefix="comment"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page trimDirectiveWhitespaces="true"%>
<div class="comment-wrapper col-xs-12">
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
   <form action="<%=request.getContextPath()%>/save-comment" name="addCommentForm" id="addCommentForm">
      <input type="hidden" name="COMMENT_TYPE" value="${param.commentType}">
      <input type="hidden" name="REFERENCE_ID" value="${param.referenceId}">
      <div class="div-comment-text-area">
        <div class="col-xs-11">
         <textarea class="commentTextArea" name="commentText" id="commentText"
         placeholder="<spring:message code="leave.comment"/>"></textarea>
        </div>
        <div class="col-xs-1 comment-icon">
            <a href="#addComment" id="addComment" onclick="addUserComment('<%=request.getContextPath()%>')"></a>
        </div>
      </div>
   </form>
   <div class="modal fade" tabindex="-1" role="dialog" id="commentModal">
     <div class="modal-dialog" role="document">
       <div class="modal-content">
         <div class="modal-header">
           <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
         </div>
         <div class="modal-body">
           <p><spring:message code="comment.not.added"/></p>
         </div>
         <div class="modal-footer">
           <button type="button" class="btn btn-default" data-dismiss="modal">Ok</button>
         </div>
       </div>
     </div>
   </div>
</div>
