<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:wrapper>
  <jsp:attribute name="language">lang="<spring:message code='lang'/>"</jsp:attribute>
  <jsp:attribute name="header">
    <meta name="Description" content="<spring:message code='show-all-articles.description'/>">
    <title><spring:message code="show-all-articles.title"/></title>
    <link rel="canonical" href="https://www.examclouds.com/show-all-articles">
    <link rel="alternate" hreflang="ru" href="https://www.examclouds.com/ru/show-all-articles">
    <link rel="alternate" hreflang="en" href="https://www.examclouds.com/show-all-articles">
    <link rel="alternate" hreflang="x-default" href="https://www.examclouds.com/show-all-articles">
    <meta property="og:title" content="<spring:message code="articles"/>"/>
    <meta property="og:type" content="article"/>
    <meta property="og:description" content="<spring:message code='show-all-articles.description'/>"/>
    <meta property="og:site_name" content="ExamClouds">
    <meta property="og:url" content="https://www.examclouds.com/show-all-articles">
    <meta property="twitter:title" content="<spring:message code="articles"/>"/>
    <meta property="twitter:card" content="summary"/>
    <meta property="twitter:description" content="<spring:message code='show-all-articles.description'/>"/>
    <meta property="twitter:site" content="@ExamClouds">
    <meta property="og:image" content="/images/general/logo.webp"/>
    <meta property="twitter:image" content="https://www.examclouds.com/images/general/logo.webp"/>

  </jsp:attribute>
  <jsp:body>
    <main>
     <%@ include file="/WEB-INF/breadCrumbs/publicationsBreadCrumbs2.jsp"%>
     <h1 class="article-head"><spring:message code="articles"/></h1>
     <input type="button" class="styled-button add-article-btn" value="<spring:message code="add.article"/>" id="addArticle"
      onclick="window.location.href='${pageContext.request.contextPath}/add-article';">
      <ul class="article-list">
        <c:forEach var="article" items="${ARTICLES}">
         <li>
           <div class="row article-head-date">
             <h2 class="article-head col-xs-12 col-sm-9">${article.title}</h2>
             <div class="col-xs-12 col-sm-3 article-date">${article.formattedDate}</div>
           </div>
           <div class="article-author">${article.author.login}</div>
           <div class="article-desc">${article.description}</div>
           <a href="${pageContext.request.contextPath}/<spring:message code='menu.home'/>${article.url}" class="article-url"><spring:message code="read.more"/></a>
         </li>
        </c:forEach>
      </ul>
    </main>
 </jsp:body>
</t:wrapper>