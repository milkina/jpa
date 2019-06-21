<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3 class="header3"><spring:message code="articles"/></h3>
<div>
  <div style="width:40%;display:inline-block">Id</div>
  <div style="width:40%;display:inline-block"><spring:message code="url"/></div>
  <div style="width:15%;display:inline-block"></div>
</div>
<div>
       <div>
       <ol class="commentList">
         <c:forEach var="article" items="${ARTICLES}">
                <li>
                         <div style="width:38%;display:inline-block">${article.id}</div>
                         <div style="width:38%;display:inline-block">
                           <a href="${pageContext.request.contextPath}/${article.url}">
                              ${article.url}
                           </a>
                         </div>
                         <div style="width:10%;display:inline-block">
                           <a href="${pageContext.request.contextPath}/delete-article?ARTICLE_ID=${article.id}"
                             id="deleteArticle/${article.url}"><spring:message code="delete"/></a>
                         </div>
                         <div style="width:10%;display:inline-block">
                           <a href="${pageContext.request.contextPath}/edit-article?ARTICLE_ID=${article.id}"
                             id="editArticle/${article.url}"><spring:message code="edit"/></a>
                         </div>
                </li>
            </c:forEach>
          </ol>
       </div>
</div>
<a href="${pageContext.request.contextPath}/add-article" id="addArticle">
   <spring:message code="add.article"/>
</a><BR>