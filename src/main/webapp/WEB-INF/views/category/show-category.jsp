<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper3>
    <jsp:attribute name="header">
                <title>
                   <c:if test="${CATEGORY_ATTRIBUTE.parentCategory!=null}">
                      ${CATEGORY_ATTRIBUTE.parentCategory.name}.
                   </c:if>
                   ${CATEGORY_ATTRIBUTE.name} - ${TESTS[param.TEST_PATH].name}
                </title>
                <META NAME="Keywords" CONTENT="${CATEGORY_ATTRIBUTE.article.keywords}">
                <META NAME="Description" CONTENT="${CATEGORY_ATTRIBUTE.article.description}">
                <%@ include file="/edit/categoryOL.jsp" %>
                <script type="text/javascript" async src="${pageContext.request.contextPath}/js/prism.js?ver=1"></script>
                <c:if test="${DUPLICATE_CATEGORIES[CATEGORY_ATTRIBUTE.pathName]!=null && DUPLICATE_CATEGORIES[CATEGORY_ATTRIBUTE.pathName].tests[0].pathName!=param.TEST_PATH}">
                      <link rel="canonical"
                      href="http://www.examclouds.com/java/${DUPLICATE_CATEGORIES[CATEGORY_ATTRIBUTE.pathName].tests[0].pathName}/${CATEGORY_ATTRIBUTE.pathName}" />
                </c:if>
                <c:if test="${CATEGORY_ATTRIBUTE.hidden || not empty CATEGORY_ATTRIBUTE.subCategories}">
                     <meta name="robots" content="noindex">
                </c:if>
    </jsp:attribute>
    <jsp:attribute name="left">
                     <jsp:include page="/WEB-INF/categoryMenu.jsp"/>
    </jsp:attribute>
    <jsp:body>
         <div class="mainArea">
           <jsp:include page="/WEB-INF/breadCrumbs/breadCrumbs3.jsp"/>
            <main>
              <article>
                    <c:if test="${CATEGORY_ATTRIBUTE.article.image != null &&  not empty CATEGORY_ATTRIBUTE.article.image}">
                           <img class="categoryImage" src="${CATEGORY_ATTRIBUTE.article.image}"
                           alt="${CATEGORY_ATTRIBUTE.name}" title="${CATEGORY_ATTRIBUTE.name}" width="280" height="200">
                    </c:if>
                    <c:if test="${CATEGORY_ATTRIBUTE.parentCategory!=null}">
                         <h1 class="header1">${CATEGORY_ATTRIBUTE.parentCategory.name}</h1>
                    </c:if>
                    <h1 class="header1">${CATEGORY_ATTRIBUTE.name}</h1>
                    <c:if test="${CATEGORY_ATTRIBUTE.testsCount!=0}">
                       <input type="button" value="<spring:message code="start.test"/>" id="startTest"
                    onclick="window.location.href='${pageContext.request.contextPath}/start-test?CATEGORY_PATH=${param.CATEGORY_PATH}&TEST_PATH=${param.TEST_PATH}';">
                    </c:if>
                     <c:if test="${CATEGORY_ATTRIBUTE.questionsCount!=0}">
                       <input type="button" value="<spring:message code="questions"/>" id="startQuiz"
                    onclick="window.location.href='${pageContext.request.contextPath}/start-quiz.jsp?CATEGORY_PATH=${param.CATEGORY_PATH}&TEST_PATH=${param.TEST_PATH}';">
                   </c:if>
                    ${CATEGORY_ATTRIBUTE.article.text}
                    <c:if test="${not empty CATEGORY_ATTRIBUTE.subCategories}">
                      <ol>
                        <c:forEach var="subCategory" items="${CATEGORY_ATTRIBUTE.subCategories}">
                           <c:if test="${subCategory.hidden==false}">
                              <li><a href="${pageContext.request.contextPath}/java/${param.TEST_PATH}/${subCategory.pathName}"
                                                            id="categoryItem${subCategory.pathName}">${subCategory.name}
                                  </a>
                              </li>
                           </c:if>
                        </c:forEach>
                       </ol>
                    </c:if>
                    <c:if test="${CATEGORY_ATTRIBUTE.testsCount!=0}">
                       <input type="button" value="<spring:message code="start.test"/>" id="startTest"
                                        onclick="window.location.href='${pageContext.request.contextPath}/start-test?CATEGORY_PATH=${param.CATEGORY_PATH}&TEST_PATH=${param.TEST_PATH}';">
                    </c:if>
                    <c:if test="${CATEGORY_ATTRIBUTE.questionsCount!=0}">
                      <input type="button" value="<spring:message code="questions"/>" id="startQuiz"
                                        onclick="window.location.href='${pageContext.request.contextPath}/start-quiz.jsp?CATEGORY_PATH=${param.CATEGORY_PATH}&TEST_PATH=${param.TEST_PATH}';">
                    </c:if>
              </article>
            </main><BR>
            <input type="button" value="<spring:message code="add.question.button"/>" id="AddQuestion"
             onclick="window.location.href='${pageContext.request.contextPath}/add-question?TEST_PATH=${param.TEST_PATH}&CATEGORY_PATH=${param.CATEGORY_PATH}';">
            <%@ include file="/WEB-INF/socialButtons.jsp" %>
            <jsp:include page="/WEB-INF/comment/comments.jsp">
                <jsp:param name="referenceId" value="${CATEGORY_ATTRIBUTE.article.id}" />
                <jsp:param name="commentType" value="ARTICLE" />
            </jsp:include>
         </div>
    </jsp:body>
</t:wrapper3>
