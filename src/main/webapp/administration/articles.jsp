<%@ taglib uri="/WEB-INF/tld/articlejsp-taglib.tld" prefix="article"%>
<h3>Articles</h3>
<h4>
  <div style="width:40%;display:inline-block">Id</div>
  <div style="width:40%;display:inline-block">Url</div>
  <div style="width:15%;display:inline-block"></div>
</h4>
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
                           <a href="${pageContext.request.contextPath}/servlet/DeleteArticleServlet?ARTICLE_ID=<article:articleId/>"
                             name="deleteArticle<article:articleUrl/>">Delete</a>
                         </div>
                         <div style="width:10%;display:inline-block">
                           <a href="${pageContext.request.contextPath}/servlet/LoadEditArticleServlet?ARTICLE_ID=<article:articleId/>"
                             name="editArticle<article:articleUrl/>">Edit</a>
                         </div>
                </li>
                </article:article>
            </ol>
            </article:articleList>
       </div>
</div>
<a href="${pageContext.request.contextPath}/article/editArticle.jsp" name="addArticle">Add Article</a><BR>