<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
<jsp:attribute name="language">lang="<spring:message code='lang'/>"</jsp:attribute>
 <jsp:attribute name="header">
    <title>ExamClouds</title>
    <meta name="Description" content="${message} ${param.message} - ExamClouds">
 </jsp:attribute>
 <jsp:body>
 <h1>ExamClouds</h1>
 <BR><BR>
     <span id="message">${message} ${param.message}</span>
 </jsp:body>
</t:wrapper>