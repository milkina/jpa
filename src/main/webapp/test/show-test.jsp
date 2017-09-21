<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
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
               <span class="questionsNumber">${TESTS[param.TEST_PATH].questionsNumber} questions</span>
               <br>
            </div>
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