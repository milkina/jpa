<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
 <jsp:attribute name="header">
    <title>ExamClouds</title>
    <meta name="robots" content="noindex">
 </jsp:attribute>
 <jsp:body> <BR><BR>
     <span id="message">${message} ${param.message}</span>
 </jsp:body>
</t:wrapper>