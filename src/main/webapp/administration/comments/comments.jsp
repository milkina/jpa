<%@ page import="util.EditMode" %>
<%@ taglib uri="/WEB-INF/tld/commentjsp-taglib.tld" prefix="comment"%>
<h3 class="header3"><spring:message	code="comments"/></h3>
<form ACTION="${pageContext.request.contextPath}/delete-comment"
    method="POST">
<div>
<input type="submit" value="<spring:message	code="delete.comments"/>" name="DeleteCommentButton">
<div style="padding-left:20px;padding-right:20px;margin-left:10px;margin-right:10px">
  <div class="adminComment" style="width:10%"><spring:message	code="created.date"/></div>
  <div class="adminComment" style="width:14%"><spring:message	code="author"/></div>
  <div class="adminComment" style="width:60%"><spring:message	code="comment"/></div>
  <div class="adminComment" style="width:10%"><spring:message code="type"/></div>
  </div>
</div>
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
                           <a href="${pageContext.request.contextPath}/show-edit-comment?COMMENT_ID=<comment:id/>" id="Edit<comment:id/>">
                              <spring:message code="edit"/></a>&nbsp;
                         </div>
                  </div>
                </li>
                </comment:comment>
            </ol>
            </comment:commentList>
       </div>
</div>
</form>