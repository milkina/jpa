<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
    <jsp:attribute name="language">lang="<spring:message code='lang'/>"</jsp:attribute>
    <jsp:attribute name="header">
    <c:set var="titleName" value="${CATEGORY_ATTRIBUTE.name} - ${TESTS[param.TEST_PATH].name} | ExamClouds"/>
    <c:set var="header1" value="${CATEGORY_ATTRIBUTE.name}"/>
    <c:if test="${CATEGORY_ATTRIBUTE.parentCategory!=null && (CATEGORY_ATTRIBUTE.name.equals('Задания') || CATEGORY_ATTRIBUTE.name.equals('Слайды'))}">
       <c:set var="titleName" value="${CATEGORY_ATTRIBUTE.parentCategory.name}. ${titleName}"/>
       <c:set var="header1" value="${CATEGORY_ATTRIBUTE.parentCategory.name}. ${CATEGORY_ATTRIBUTE.name}"/>
    </c:if>
    <title><c:out value="${titleName}"/></title>
    <meta name="Keywords" content="${CATEGORY_ATTRIBUTE.article.keywords}">
    <meta name="Description" content="${CATEGORY_ATTRIBUTE.article.description}">
    <%@ include file="/edit/categoryOL.jsp"%>
    <script async src="${pageContext.request.contextPath}/js/prism.min.js?ver=1"></script>
       <c:choose>
       <c:when test="${DUPLICATE_CATEGORIES[CATEGORY_ATTRIBUTE.pathName]!=null}">
         <link rel="canonical"
         href="https://www.examclouds.com/${TESTS[param.TEST_PATH].language.code.path}java/${DUPLICATE_CATEGORIES[CATEGORY_ATTRIBUTE.pathName].tests[0].pathName}/${CATEGORY_ATTRIBUTE.pathName}" />
         <meta property="og:url" content="https://www.examclouds.com/${TESTS[param.TEST_PATH].language.code.path}java/${DUPLICATE_CATEGORIES[CATEGORY_ATTRIBUTE.pathName].tests[0].pathName}/${CATEGORY_ATTRIBUTE.pathName}">
       </c:when>
       <c:otherwise>
           <link rel="canonical"
             href="https://www.examclouds.com/${TESTS[param.TEST_PATH].language.code.path}java/${param.TEST_PATH}/${CATEGORY_ATTRIBUTE.pathName}"/>
           <meta property="og:url" content="https://www.examclouds.com/${TESTS[param.TEST_PATH].language.code.path}java/${param.TEST_PATH}/${CATEGORY_ATTRIBUTE.pathName}">
       </c:otherwise>
       </c:choose>
       <link rel="alternate" hreflang="ru" href="https://www.examclouds.com/ru/java/${param.TEST_PATH}/${CATEGORY_ATTRIBUTE.pathName}">
       <link rel="alternate" hreflang="en" href="https://www.examclouds.com/java/${param.TEST_PATH}/${CATEGORY_ATTRIBUTE.pathName}">
       <link rel="alternate" hreflang="x-default" href="https://www.examclouds.com/java/${param.TEST_PATH}/${CATEGORY_ATTRIBUTE.pathName}">

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
             alt="${CATEGORY_ATTRIBUTE.name}&nbsp;<spring:message code='photo'/>" title="${CATEGORY_ATTRIBUTE.name}&nbsp;<spring:message code='photo'/>">
         </c:if>
         <h1 class="show-category-header">${header1}</h1>
         <div class="category-article">
            <c:if test="${CATEGORY_ATTRIBUTE.videoPath!=null && not empty CATEGORY_ATTRIBUTE.videoPath}">
                  <iframe width="100%" height="315" src="${fn:replace(CATEGORY_ATTRIBUTE.videoPath, "youtu.be", "www.youtube.com/embed")}"
                      frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
                  </iframe>
            </c:if>
            ${CATEGORY_ATTRIBUTE.article.text}
         </div>
           <c:if test="${not empty CATEGORY_ATTRIBUTE.subCategories}">
            <ol class="category-list">
              <c:forEach var="subCategory" items="${CATEGORY_ATTRIBUTE.subCategories}">
                <c:if test="${subCategory.hidden==false}">
                  <li><a href="${pageContext.request.contextPath}/<spring:message code='menu.home'/>java/${param.TEST_PATH}/${subCategory.pathName}"
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
         <div class="row">
           <div class="col-xs-6 previous-exam-button">
             <c:if test="${PREVIOUS_CATEGORY!=null}">
               <a href="${pageContext.request.contextPath}/<spring:message code='menu.home'/>java/${param.TEST_PATH}/${PREVIOUS_CATEGORY.pathName}">
                 <spring:message code="previous"/>
               </a>
             </c:if>
           </div>
           <div class="col-xs-6 next-exam-button">
             <c:if test="${NEXT_CATEGORY!=null}">
               <a href="${pageContext.request.contextPath}/<spring:message code='menu.home'/>java/${param.TEST_PATH}/${NEXT_CATEGORY.pathName}">
                 <spring:message code="next"/>
               </a>
              </c:if>
           </div>
         </div>
         <c:if test="${not empty CATEGORY_ATTRIBUTE.parentCategory.subCategories}">
           <div>
             <spring:message code="read.also"/>:
              <ul>
                  <c:forEach var="subCategory" items="${CATEGORY_ATTRIBUTE.parentCategory.subCategories}">
                   <c:if test="${subCategory!=CATEGORY_ATTRIBUTE}">
                     <li><a href="${pageContext.request.contextPath}/<spring:message code='menu.home'/>java/${param.TEST_PATH}/${subCategory.pathName}">${subCategory.name}
                         </a>
                     </li>
                   </c:if>
                  </c:forEach>
              </ul>
           </div>
         </c:if>
      </main>
      <%@ include file="/WEB-INF/socialButtons.jsp"%>
      <jsp:include page="/WEB-INF/comment/comments.jsp">
        <jsp:param name="referenceId" value="${CATEGORY_ATTRIBUTE.article.id}"/>
        <jsp:param name="commentType" value="ARTICLE"/>
      </jsp:include>
    </jsp:body>
</t:wrapper>
