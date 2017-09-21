<%@ page import="main.java.model.comment.CommentType" %>
<comment:comment commentId="${param.COMMENT_ID}">
           <div><span class="adminLabel">Comment Id</span> <comment:id/></div>
           <div><span class="adminLabel">Created Date</span> <comment:createdDate/></div>
           <div><span class="adminLabel">Author</span> <comment:author/></div>
           <div><span class="adminLabel">Url</span> <a href="<comment:url/>"><comment:url/></a></div>
           <div><span class="adminLabel">Reference Id</span>
              <input type="text" value="<comment:referenceId/>" name="REFERENCE_ID">
           </div>
           <div><span class="adminLabel">Type</span>
             <selectTag:select options="<%=CommentType.values()%>" name="COMMENT_TYPE">
              <jsp:attribute name="selected" >
                   <comment:type/>
              </jsp:attribute>
             </selectTag:select>
           </div><BR>
           <div><span class="adminLabel">Comment</span>
            <textarea rows="5" cols="50" maxlength="350" name="COMMENT_BODY" required><comment:body/>
            </textarea>
           </div>
</comment:comment>