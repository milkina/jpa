<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
 <jsp:attribute name="header">
    <meta name="robots" content="noindex">
    <title><spring:message code="edit.article"/></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
    <jsp:include page="/edit/tinymceHeader.jsp"/>
 </jsp:attribute>
 <jsp:body>
       <div class="mainArea">
        <form action="${pageContext.request.contextPath}/save-article" method="POST" >
            <input type="hidden" name="ARTICLE_ID" value="${ARTICLE_ATTRIBUTE.id}">
            <span class="adminLabel"><spring:message code="url"/>:<span class="wrongMessage">*</span></span>
            <input type="text" name="URL_PARAM" maxlength="70" required value="${ARTICLE_ATTRIBUTE.url}" size="70"><BR>
            <span class="adminLabel"><spring:message code="title"/>:<span class="wrongMessage">*</span></span>
            <input type="text" name="TITLE" maxlength="70" required value="${ARTICLE_ATTRIBUTE.title}" size="70"><BR>
            <span class="adminLabel"><spring:message code="image.url"/>:</span>
            <input type="text" name="ARTICLE_IMAGE" maxlength="70" value="${ARTICLE_ATTRIBUTE.image}"><BR>
            <span class="adminLabel"><spring:message code="keywords"/>:<span class="wrongMessage">*</span></span>
            <textarea rows="4" cols="40" maxlength="160" name="keywords" required id="keywords">${ARTICLE_ATTRIBUTE.keywords}</textarea>  <BR>
            <span class="adminLabel"><spring:message code="meta.description"/>:<span class="wrongMessage">*</span></span>
            <textarea rows="4" cols="40" maxlength="160" name="description" required id="description">${ARTICLE_ATTRIBUTE.description}</textarea>  <BR>
            <span class="adminLabel"><spring:message code="text"/>:<span class="wrongMessage">*</span></span>
            <textarea rows="25" cols="80" name="ARTICLE_TEXT" id="ARTICLE_TEXT">${ARTICLE_ATTRIBUTE.text}</textarea> <BR>
            <input type="submit" value="<spring:message code="save.button"/>" name="Save" id="save"><br>
        </form>
      </div>
  </jsp:body>
</t:wrapper>