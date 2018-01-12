<%@ page import="controller.EditMode" %>
<%@ taglib uri="/WEB-INF/tld/commentjsp-taglib.tld" prefix="comment"%>
<h3>Comments</h3>
<h4>
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
                         <div class="adminComment" style="width:10%"><comment:createdDate/></div>
                         <div class="adminComment" style="width:14%"><comment:author/></div>
                         <div class="adminComment" style="width:60%" id="commentBody<comment:id/>"><comment:body/></div>
                         <div class="adminComment" style="width:10%"><a href="<comment:url/>"><comment:type/></a></div>
                         <div>
                           <a href="${pageContext.request.contextPath}/administration/comments/edit-comment.jsp?COMMENT_ID=<comment:id/>" id="Edit<comment:id/>">
                              Edit</a>&nbsp;
                           <a href="${pageContext.request.contextPath}/modify-comment?COMMENT_ID=<comment:id/>&EDIT_MODE_PARAM=<%=EditMode.DELETE%>" id="Delete<comment:id/>">
                           Delete</a>
                         </div>
                  </div>
                </li>
                </comment:comment>
            </ol>
            </comment:commentList>
       </div>
</div>