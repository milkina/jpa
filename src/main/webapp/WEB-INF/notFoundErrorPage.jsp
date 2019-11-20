<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
    <jsp:attribute name="language"> lang="<spring:message code="lang"/>"</jsp:attribute>
    <jsp:attribute name="header">
        <title>ExamClouds</title>
        <link rel="alternate" hreflang="ru" href="https://www.examclouds.com/ru/tests">
        <link rel="alternate" hreflang="en" href="https://www.examclouds.com/tests">
        <link rel="alternate" hreflang="x-default" href="https://www.examclouds.com/tests">
    </jsp:attribute>
    <jsp:body>
        <div>Page is removed or does not exist.</div>
    </jsp:body>
</t:wrapper>

