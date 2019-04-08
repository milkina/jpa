<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
 <jsp:attribute name="language">lang="${TESTS[param.TEST_PATH].language.code}"</jsp:attribute>
 <jsp:attribute name="header">
     <META NAME="Description" CONTENT="${TESTS[param.TEST_PATH].article.description}">
     <title>${TESTS[param.TEST_PATH].article.title}</title>
 </jsp:attribute>
 <jsp:body>
    <%@ taglib uri="/WEB-INF/tld/cache-tagjsp-taglib.tld" prefix="cache"%>
    <cache:cacheTag/>
    <div class="mainArea">
        <jsp:include page="/WEB-INF/breadCrumbs/breadCrumbs2.jsp"/>
        <main><article>
            <c:if test="${TESTS[param.TEST_PATH].testsNumber>0}">
              <form action="${pageContext.request.contextPath}/select-category-for-exam">
                 <input type="submit" value="<spring:message code="start.test"/>">
                 <input type="hidden" value="${param.TEST_PATH}" name="TEST_PATH">
              </form>
            </c:if>
            <c:if test="${TESTS[param.TEST_PATH].questionsNumber>0}">
              <form action="${pageContext.request.contextPath}/select-categories-to-see-questions">
                 <input type="hidden" value="${param.TEST_PATH}" name="TEST_PATH">
                 <input type="submit" value="<spring:message code="questions"/>" id="startQuiz">
              </form>
            </c:if>
            ${TESTS[param.TEST_PATH].article.text}
            <jsp:include page="/WEB-INF/views/category/category-menu-center.jsp"/>
        </article></main>
        <%@ include file="/WEB-INF/socialButtons.jsp" %>
        <jsp:include page="/WEB-INF/comment/comments.jsp">
             <jsp:param name="referenceId" value="${TESTS[param.TEST_PATH].id}" />
             <jsp:param name="commentType" value="TEST"/>
        </jsp:include>
    </div>
 </jsp:body>
</t:wrapper>