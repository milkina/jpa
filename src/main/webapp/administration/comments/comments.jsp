<%@ page import="controller.EditMode" %>
<%@ taglib uri="/WEB-INF/tld/commentjsp-taglib.tld" prefix="comment"%>
<h3>Comments</h3>
<form ACTION="${pageContext.request.contextPath}/delete-comment"
    method="POST">
<h4>
<input type="submit" value="Delete Comment" name="DeleteCommentButton">
<div style="padding-left:20px;padding-right:20px;margin-left:10px;margin-right:10px">
  <div class="adminComment" style="width:10%">Created Date</div>
  <div class="adminComment" style="width:14%">Author</div>
  <div class="adminComment" style="width:60%">Comment</div>
  <div class="adminComment" style="width:10%">Type</div>
  </div>
</h4>
<div>
       <div>
         <comment:commentList amount="10" >
            <ol class="commentList">
                <comment:comment>
                <li>
                  <div class="commentBody" id="<comment:id/>">
                         <input type="checkbox" value="<comment:id/>" name="deleteComment">
                         <div class="adminComment" style="width:10%"><comment:createdDate/></div>
                         <div class="adminComment" style="width:14%"><comment:author/></div>
                         <div class="adminComment" style="width:60%" id="commentBody<comment:id/>"><comment:body/></div>
                         <div class="adminComment" style="width:10%"><a href="<comment:url/>"><comment:type/></a></div>
                         <div>
                           <a href="${pageContext.request.contextPath}/administration/comments/edit-comment.jsp?COMMENT_ID=<comment:id/>" id="Edit<comment:id/>">
                              Edit</a>&nbsp;
                         </div>
                  </div>
                </li>
                </comment:comment>
            </ol>
            </comment:commentList>
       </div>
</div>
</form>