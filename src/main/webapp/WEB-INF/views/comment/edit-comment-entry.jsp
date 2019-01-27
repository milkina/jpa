<%@ page import="model.comment.CommentType" %>
<comment:comment commentId="${param.COMMENT_ID}">
           <div><span class="adminLabel"><spring:message code="comment.id"/></span> <comment:id/></div>
           <div><span class="adminLabel"><spring:message code="created.date"/></span> <comment:createdDate/></div>
           <div><span class="adminLabel"><spring:message code="author"/></span> <comment:author/></div>
           <div><span class="adminLabel"><spring:message code="url"/></span> <a href="<comment:url/>"><comment:url/></a></div>
           <div><span class="adminLabel"><spring:message code="reference.id"/></span>
              <input type="text" value="<comment:referenceId/>" name="REFERENCE_ID">
           </div>
           <div><span class="adminLabel"><spring:message code="type"/></span>
             <selectTag:select options="<%=CommentType.values()%>" name="COMMENT_TYPE">
              <jsp:attribute name="selected" >
                   <comment:type/>
              </jsp:attribute>
             </selectTag:select>
           </div><BR>
           <div><span class="adminLabel"><spring:message code="comment"/></span>
            <textarea rows="5" cols="50" maxlength="350" name="COMMENT_BODY" required><comment:body/>
            </textarea>
           </div>
</comment:comment>