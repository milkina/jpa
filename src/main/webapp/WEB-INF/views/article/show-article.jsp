<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/tld/cache-tagjsp-taglib.tld" prefix="cache"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<cache:cacheTag/>
<t:wrapper>
  <jsp:attribute name="header">
    <META NAME="Keywords" CONTENT="${ARTICLE_ATTRIBUTE.keywords}">
    <META NAME="Description" CONTENT="${ARTICLE_ATTRIBUTE.description}">
    <title>${ARTICLE_ATTRIBUTE.title}</title>
    <meta property = "og:title" content = "${ARTICLE_ATTRIBUTE.title}" />
    <meta property = "og:type" content = "article" />
    <c:if test="${ARTICLE_ATTRIBUTE.image != null &&  not empty ARTICLE_ATTRIBUTE.image}">
        <meta property = "og:image" content = "${ARTICLE_ATTRIBUTE.image}" />
    </c:if>
    <meta property = "og:description" content = "${ARTICLE_ATTRIBUTE.description}" />
    <meta property = "og:site_name" content="http://www.examclouds.com">
    <script type="text/javascript" async src="${pageContext.request.contextPath}/js/prism.js?ver=1"></script>
 </jsp:attribute>
 <jsp:body>
   <div class="mainArea">
    <%@ include file="/WEB-INF/breadCrumbs/publicationsBreadCrumbs2.jsp" %>
    <main>
      <article>
       <c:if test="${ARTICLE_ATTRIBUTE.image != null &&  not empty ARTICLE_ATTRIBUTE.image}">
          <img class="categoryImage" src="${ARTICLE_ATTRIBUTE.image}"
             alt="${ARTICLE_ATTRIBUTE.title}" title="${ARTICLE_ATTRIBUTE.title}" width="280" height="200">
       </c:if>
       ${ARTICLE_ATTRIBUTE.text}
      </article>
    </main>
    <%@ include file="/WEB-INF/socialButtons.jsp" %>
    <jsp:include page="/WEB-INF/comment/comments.jsp">
      <jsp:param name="referenceId" value="${ARTICLE_ATTRIBUTE.id}" />
      <jsp:param name="commentType" value="ARTICLE" />
    </jsp:include>
 </div>
</jsp:body>
</t:wrapper>