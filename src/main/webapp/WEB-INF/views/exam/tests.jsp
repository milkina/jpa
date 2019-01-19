<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
    <jsp:attribute name="header">
        <META NAME="Keywords" CONTENT="java tests">
        <META NAME="Description"
                       CONTENT="Бесплатные online Java тесты для подготовки к сертификациям и собеседованиям.">
        <title><spring:message code="tests"/></title>
            <jsp:include page="/WEB-INF/google-ads-header.jsp" />
    </jsp:attribute>
     <jsp:body>
        <%@ taglib uri="/WEB-INF/tld/cache-tagjsp-taglib.tld" prefix="cache"%>
        <cache:cacheTag/>
        <div class="mainArea">
         <main>
          <h1 class="indexH1"><strong><spring:message code="tests"/></strong></h1>
          <c:forEach var="test" items="${TESTS_WITH_TESTS}">
             <a href="${pageContext.request.contextPath}/select-category-for-exam?TEST_PATH=${test.pathName}"
             id="${test.name}">${test.name}</a><BR>
          </c:forEach>
         </main>
         <%@ include file="/WEB-INF/socialButtons.jsp" %>
        </div>
 </jsp:body>
</t:wrapper>