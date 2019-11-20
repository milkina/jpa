<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/WEB-INF/tld/cache-tagjsp-taglib.tld" prefix="cache"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/menu-jsp-taglib.tld" prefix="menu"%>
<cache:cacheTag/>
<t:wrapper>
  <jsp:attribute name="header">
    <meta name="Keywords" content="${ARTICLE_ATTRIBUTE.keywords}">
    <meta name="Description" content="${ARTICLE_ATTRIBUTE.description}">
    <title>${ARTICLE_ATTRIBUTE.title}</title>
    <meta property = "og:title" content = "${ARTICLE_ATTRIBUTE.title}" />
    <meta property = "og:type" content = "article" />
    <c:if test="${ARTICLE_ATTRIBUTE.image != null &&  not empty ARTICLE_ATTRIBUTE.image}">
        <meta property = "og:image" content = "${ARTICLE_ATTRIBUTE.image}" />
    </c:if>
    <meta property = "og:description" content = "${ARTICLE_ATTRIBUTE.description}" />
    <meta property = "og:site_name" content="https://www.examclouds.com">
    <script async src="${pageContext.request.contextPath}/js/prism.js?ver=1"></script>
    <c:if test="${!ARTICLE_ATTRIBUTE.indexStatus}">
        <meta name="robots" content="noindex">
    </c:if>
 </jsp:attribute>
 <jsp:body>
    <div class="breadCrumbs">
      <ol itemscope itemtype="http://schema.org/BreadcrumbList">
        <%@ include file="/WEB-INF/breadCrumbs/homeBreadCrumb.jsp" %>
        <li itemprop="itemListElement" itemscope
          itemtype="http://schema.org/ListItem">
           <span itemprop="name"><a itemprop="item" href="<menu:articlesTag/>"><spring:message code="articles"/>
           </a></span><meta itemprop="position" content="2"/>
        </li>
        <li>${ARTICLE_ATTRIBUTE.title}</li>
       </ol>
     </div>
    <main>
      <article>
       <c:if test="${ARTICLE_ATTRIBUTE.image != null &&  not empty ARTICLE_ATTRIBUTE.image}">
          <img class="categoryImage" src="${ARTICLE_ATTRIBUTE.image}"
             alt="${ARTICLE_ATTRIBUTE.title}" title="${ARTICLE_ATTRIBUTE.title}" width="280" height="200">
       </c:if>
       <div class="article-desc">
       ${ARTICLE_ATTRIBUTE.text}
       </div>
      </article>
    </main>
    <%@ include file="/WEB-INF/socialButtons.jsp" %>
    <jsp:include page="/WEB-INF/comment/comments.jsp">
      <jsp:param name="referenceId" value="${ARTICLE_ATTRIBUTE.id}" />
      <jsp:param name="commentType" value="ARTICLE" />
    </jsp:include>
</jsp:body>
</t:wrapper>