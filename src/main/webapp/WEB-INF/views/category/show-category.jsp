<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
    <jsp:attribute name="language">lang="${TESTS[param.TEST_PATH].language.code}"</jsp:attribute>
    <jsp:attribute name="header">
                <title>
                   <c:if test="${CATEGORY_ATTRIBUTE.parentCategory!=null}">
                      ${CATEGORY_ATTRIBUTE.parentCategory.name}.
                   </c:if>
                   ${CATEGORY_ATTRIBUTE.name} - ${TESTS[param.TEST_PATH].name}
                </title>
                <META NAME="Description" CONTENT="${CATEGORY_ATTRIBUTE.article.description}">
                <%@ include file="/edit/categoryOL.jsp" %>
                <script async src="${pageContext.request.contextPath}/js/prism.js?ver=1"></script>
                <c:if test="${DUPLICATE_CATEGORIES[CATEGORY_ATTRIBUTE.pathName]!=null && DUPLICATE_CATEGORIES[CATEGORY_ATTRIBUTE.pathName].tests[0].pathName!=param.TEST_PATH}">
                      <link rel="canonical"
                      href="${pageContext.request.contextPath}/java/${DUPLICATE_CATEGORIES[CATEGORY_ATTRIBUTE.pathName].tests[0].pathName}/${CATEGORY_ATTRIBUTE.pathName}" />
                </c:if>
                <c:if test="${CATEGORY_ATTRIBUTE.hidden || !CATEGORY_ATTRIBUTE.article.indexStatus}">
                     <meta name="robots" content="noindex">
                </c:if>
    </jsp:attribute>
    <jsp:body>
           <jsp:include page="/WEB-INF/breadCrumbs/breadCrumbs3.jsp"/>
            <main class="container">
              <article>
                    <c:if test="${CATEGORY_ATTRIBUTE.article.image != null &&  not empty CATEGORY_ATTRIBUTE.article.image}">
                           <img class="categoryImage" src="${CATEGORY_ATTRIBUTE.article.image}"
                           alt="${CATEGORY_ATTRIBUTE.name}" title="${CATEGORY_ATTRIBUTE.name}">
                    </c:if>
                    <h1 class="show-category-header">${CATEGORY_ATTRIBUTE.name}</h1>
                    <div class="category-article">${CATEGORY_ATTRIBUTE.article.text}</div>
                    <c:if test="${not empty CATEGORY_ATTRIBUTE.subCategories}">
                      <ol class="category-list">
                        <c:forEach var="subCategory" items="${CATEGORY_ATTRIBUTE.subCategories}">
                           <c:if test="${subCategory.hidden==false}">
                              <li><a href="${pageContext.request.contextPath}/java/${param.TEST_PATH}/${subCategory.pathName}"
                                     class="category-sub-href" id="categoryItem${subCategory.pathName}">${subCategory.name}
                                  </a>
                              </li>
                           </c:if>
                        </c:forEach>
                       </ol>
                    </c:if>
                    <c:if test="${CATEGORY_ATTRIBUTE.questionsCount!=0 or not empty CATEGORY_ATTRIBUTE.subCategories}">
                    <div class="read-questions-div">
                       <a href="${pageContext.request.contextPath}/see-questions?CATEGORY_PATH=${param.CATEGORY_PATH}&TEST_PATH=${param.TEST_PATH}"
                        id="startQuiz" class="read-questions-href">
                          <spring:message code="questions"/>
                       </a>
                    </div>
                    </c:if>
                     <c:if test="${CATEGORY_ATTRIBUTE.testsCount!=0 or not empty CATEGORY_ATTRIBUTE.subCategories}">
                       <input type="button" class="styled-button pass-test-btn" value="<spring:message code="start.test"/>" id="startTest"
                          onclick="window.location.href='${pageContext.request.contextPath}/start-test?CATEGORY_PATH=${param.CATEGORY_PATH}&TEST_PATH=${param.TEST_PATH}';">
                     </c:if>
              </article>
              <input type="button" class="styled-button pass-test-btn" value="<spring:message code="add.question.button"/>" id="AddQuestion"
                                         onclick="window.location.href='${pageContext.request.contextPath}/add-question?TEST_PATH=${param.TEST_PATH}&CATEGORY_PATH=${param.CATEGORY_PATH}';">

              <c:if test="${not empty CATEGORY_ATTRIBUTE.parentCategory.subCategories}">
               <div>
                  <spring:message code="read.also"/>:
                  <ul>
                  <c:forEach var="subCategory" items="${CATEGORY_ATTRIBUTE.parentCategory.subCategories}">
                     <li><a href="${pageContext.request.contextPath}/java/${param.TEST_PATH}/${subCategory.pathName}">${subCategory.name}
                         </a>
                     </li>
                  </c:forEach>
                  </ul>
               </div>
              </c:if>
             </main>
              <%@ include file="/WEB-INF/socialButtons.jsp" %>
            <jsp:include page="/WEB-INF/comment/comments.jsp">
                <jsp:param name="referenceId" value="${CATEGORY_ATTRIBUTE.article.id}" />
                <jsp:param name="commentType" value="ARTICLE" />
            </jsp:include>
    </jsp:body>
</t:wrapper>
