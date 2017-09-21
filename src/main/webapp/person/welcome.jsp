<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
 <jsp:attribute name="header">
    <title>Welcome on ExamClouds</title>
    <meta name="robots" content="noindex">
 </jsp:attribute>
 <jsp:body> <BR><BR>
     <span id="welcomeText">${USER_NAME}, thanks for registering! <BR>
     Registered users are able to post comments and pass quizzes. </span>
 </jsp:body>
</t:wrapper>