<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/tld/articlejsp-taglib.tld" prefix="article"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
  <jsp:attribute name="header">
    <META NAME="Description" CONTENT="Articles about Java news, preparation for examination.">
    <title><spring:message code="articles"/> | ExamClouds</title>
  </jsp:attribute>
  <jsp:body>
    <main>
        <%@ include file="/WEB-INF/breadCrumbs/publicationsBreadCrumbs2.jsp" %>
        <BR>
        <a href="${pageContext.request.contextPath}/add-article" id="addArticle">
           <spring:message code="add.article"/>
        </a><BR>
        <article:articleList>
           <div>
              <article:article>
                <div class="articleDescription">
                <article:articleImg/>
                 <h2 class="header2"><article:articleTitle/></h2>
                 <article:articleDescription/><br>
                  <a href="${pageContext.request.contextPath}<article:articleUrl/>"><spring:message code="read.more"/></a>
                </div>
                <br>
              </article:article>
           </div>
        </article:articleList>
    </main>
 </jsp:body>
</t:wrapper>