<%@ taglib uri="/WEB-INF/tld/articlejsp-taglib.tld" prefix="article"%>
<h3 class="header3"><spring:message code="articles"/></h3>
<div>
  <div style="width:40%;display:inline-block">Id</div>
  <div style="width:40%;display:inline-block"><spring:message code="url"/></div>
  <div style="width:15%;display:inline-block"></div>
</div>
<div>
       <div>
         <article:articleList>
            <ol class="commentList">
                <article:article>
                <li>
                         <div style="width:38%;display:inline-block"><article:articleId/></div>
                         <div style="width:38%;display:inline-block">
                           <a href="${pageContext.request.contextPath}<article:articleUrl/>">
                              <article:articleUrl/>
                           </a>
                         </div>
                         <div style="width:10%;display:inline-block">
                           <a href="${pageContext.request.contextPath}/delete-article?ARTICLE_ID=<article:articleId/>"
                             id="deleteArticle<article:articleUrl/>"><spring:message code="delete"/></a>
                         </div>
                         <div style="width:10%;display:inline-block">
                           <a href="${pageContext.request.contextPath}/edit-article?ARTICLE_ID=<article:articleId/>"
                             id="editArticle<article:articleUrl/>"><spring:message code="edit"/></a>
                         </div>
                </li>
                </article:article>
            </ol>
            </article:articleList>
       </div>
</div>
<a href="${pageContext.request.contextPath}/add-article" id="addArticle">
   <spring:message code="add.article"/>
</a><BR>