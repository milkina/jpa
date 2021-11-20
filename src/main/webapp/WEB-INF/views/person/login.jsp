<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
 <jsp:attribute name="language">lang="<spring:message code='lang'/>"</jsp:attribute>
 <jsp:attribute name="header">
    <title><spring:message code="login.to"/> ExamClouds</title>
    <meta name="robots" content="noindex">
    <meat name="Description" content="<spring:message code="login.description"/>">
    <link type="text/css" REL="stylesheet" HREF="${pageContext.request.contextPath}/css/login.css">
 </jsp:attribute>
 <jsp:body>
    <h1><spring:message code="log.in"/></h1>
     <form ACTION="${pageContext.request.contextPath}/login" METHOD="POST" id="LoginForm" class="inlineForm">
        <section class="container">
            <div class="login">
                <p><input type="text" name="login" value="${person.login}" placeholder="Login"></p>
                <p><input type="password" name="password" value="${person.password}" placeholder="Password"></p>
                <p class="remember">
                  <label>
                    <input type="checkbox" CHECKED name="Remember">
                    <spring:message code="remember.me"/>
                    <span class="wrongMessage" id="wrongMessage">${wrongLoginMessage}</span>
                  </label>
                </p>
                <p class="submit"><input type="submit" class="styled-button" name="Enter" value="<spring:message code="log.in"/>"></p>
            </div>
          </section>
     </form>
     <spring:message code="new.to.examclouds"/>
    <a href="${pageContext.request.contextPath}/register"><spring:message code="register"/></a>
 </jsp:body>
</t:wrapper>