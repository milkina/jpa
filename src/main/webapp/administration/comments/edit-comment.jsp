<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
 <jsp:attribute name="header">
    <META NAME="Description" CONTENT="Edit Comment">
    <title>Edit Comment</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/administration.js"></script>
    <meta name="robots" content="noindex">
 </jsp:attribute>
  <jsp:body>
    <div class="mainArea">
    <h1>Edit Comment</h1>
    <%@ taglib uri="/WEB-INF/tld/commentjsp-taglib.tld" prefix="comment"%>
    <%@ taglib uri="/WEB-INF/tld/select-tagjsp-taglib.tld" prefix="selectTag"%>
    <form method="post" action="${pageContext.request.contextPath}/modify-comment">
          <input type="hidden" name="COMMENT_ID" value="${param.COMMENT_ID}">
          <input type="hidden" name="EDIT_MODE_PARAM" value="EDIT">
       <%@ include file="edit-comment-entry.jsp" %>
       <input type="submit" value="Save">
     </form>
    </div>
 </jsp:body>
</t:wrapper>




