<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
 <jsp:attribute name="header">
    <title><spring:message	code="edit.comment"/></title>
    <script src="${pageContext.request.contextPath}/js/administration.js"></script>
    <meta name="robots" content="noindex">
 </jsp:attribute>
  <jsp:body>
    <div class="mainArea">
    <h1 class="header1"><spring:message	code="edit.comment"/></h1>
    <%@ taglib uri="/WEB-INF/tld/commentjsp-taglib.tld" prefix="comment"%>
    <%@ taglib uri="/WEB-INF/tld/select-tagjsp-taglib.tld" prefix="selectTag"%>
    <form method="post" action="${pageContext.request.contextPath}/modify-comment">
          <input type="hidden" name="COMMENT_ID" value="${param.COMMENT_ID}">
          <input type="hidden" name="EDIT_MODE_PARAM" value="EDIT">
       <%@ include file="edit-comment-entry.jsp" %>
       <input type="submit" value="<spring:message	code="save.button"/>">
     </form>
    </div>
 </jsp:body>
</t:wrapper>




