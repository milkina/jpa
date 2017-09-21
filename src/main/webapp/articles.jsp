<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/tld/articlejsp-taglib.tld" prefix="article"%>
<t:wrapper>
  <jsp:attribute name="header">
    <meta name="robots" content="noindex">
    <META NAME="Description" CONTENT="Articles about Java news, preparation for examination.">
    <title>Java Tutorial Articles</title>
  </jsp:attribute>
  <jsp:body>
  <div class="mainArea">
    <main>
        <%@ include file="/WEB-INF/breadCrumbs/publicationsBreadCrumbs2.jsp" %>
        <article:articleList>
           <div>
              <article:article>
                <div class="articleDescription">
                <article:articleImg/>
                 <h2 class="header2"><article:articleTitle/></h2>
                 <article:articleDescription/><br>
                  <a href="${pageContext.request.contextPath}<article:articleUrl/>">Read more</a>
                </div>
                <br>
              </article:article>
           </div>
        </article:articleList>
    </main>
  </div>
 </jsp:body>
</t:wrapper>