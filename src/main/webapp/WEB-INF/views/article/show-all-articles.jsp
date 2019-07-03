<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:wrapper>
  <jsp:attribute name="header">
    <meta name="Description" content="Articles about Java news, preparation for examination.">
    <title><spring:message code="articles"/> | ExamClouds</title>
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
           <a href="${pageContext.request.contextPath}/${article.url}" class="article-url"><spring:message code="read.more"/></a>
         </li>
        </c:forEach>
      </ul>
    </main>
 </jsp:body>
</t:wrapper>