<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
    <jsp:attribute name="header">
        <META NAME="Description"
                       CONTENT="Free online Java Courses for preparation to Oracle Java Certifications, interviews and learning Java.">
        <title><spring:message code="courses"/> | ExamClouds</title>
            <jsp:include page="/WEB-INF/google-ads-header.jsp" />
    </jsp:attribute>
     <jsp:body>
        <%@ taglib uri="/WEB-INF/tld/cache-tagjsp-taglib.tld" prefix="cache"%>
        <cache:cacheTag/>
        <div class="mainArea">
         <main>
          <h1 class="indexH1"><strong>IT <spring:message code="courses"/></strong></h1>
               <c:forEach var="test" items="${TESTS}">
                <c:set var="url" value="${'/'}" />
                <c:if test="${test.value.pathName ne 'ocjp' and test.value.pathName ne 'jpa' and test.value.pathName ne 'web-services' }">
                    <c:set var="url" value="${'/exam/'}" />
                </c:if>
                    <a href="${pageContext.request.contextPath}${url}${test.value.pathName}" id="${test.value.pathName}">
                      ${test.value.name}
                       <br>
                    </a>
            </c:forEach>
            <BR>
           </main>
           <div style="float:left">
           <%@ include file="/WEB-INF/socialButtons.jsp" %>
           </div>
     </div>
 </jsp:body>
</t:wrapper>