<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper3>
 <jsp:attribute name="header">
     <META NAME="Keywords" CONTENT="${TESTS[param.TEST_PATH].article.keywords}">
     <META NAME="Description" CONTENT="${TESTS[param.TEST_PATH].article.description}">
     <title>${TESTS[param.TEST_PATH].article.title}</title>
 </jsp:attribute>
 <jsp:attribute name="left">
        <jsp:include page="/WEB-INF/categoryMenu.jsp"/>
 </jsp:attribute>
 <jsp:body>
    <%@ taglib uri="/WEB-INF/tld/cache-tagjsp-taglib.tld" prefix="cache"%>
    <cache:cacheTag/>
    <div class="mainArea">
        <jsp:include page="/WEB-INF/breadCrumbs/breadCrumbs2.jsp"/>
        <main><article>
           <div class="testIcon">
               ${TESTS[param.TEST_PATH].iconText}
               <span class="questionsNumber">
               ${TESTS[param.TEST_PATH].questionsNumber+TESTS[param.TEST_PATH].testsNumber} <spring:message	code="total.questions"/></span>
               <br>
            </div>
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
        </article></main>
        <%@ include file="/WEB-INF/socialButtons.jsp" %>
        <jsp:include page="/WEB-INF/comment/comments.jsp">
             <jsp:param name="referenceId" value="${TESTS[param.TEST_PATH].id}" />
             <jsp:param name="commentType" value="TEST"/>
        </jsp:include>
    </div>
 </jsp:body>
</t:wrapper3>