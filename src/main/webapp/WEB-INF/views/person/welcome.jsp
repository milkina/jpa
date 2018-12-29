<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
 <jsp:attribute name="header">
    <title><spring:message code="welcome.on"/> ExamClouds</title>
    <meta name="robots" content="noindex">
 </jsp:attribute>
 <jsp:body> <BR><BR>
     <span id="welcomeText">${USER_NAME}, <spring:message code="thanks.for.register"/></span>
 </jsp:body>
</t:wrapper>