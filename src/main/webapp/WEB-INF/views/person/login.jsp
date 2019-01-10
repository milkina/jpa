<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
 <jsp:attribute name="header">
    <title><spring:message code="login.to"/> ExamClouds</title>
    <meta name="robots" content="noindex">
    <LINK TYPE="text/css" REL="stylesheet" HREF="${pageContext.request.contextPath}/css/login.css?v=1">
 </jsp:attribute>
 <jsp:body>
     <form ACTION="${pageContext.request.contextPath}/login" METHOD="POST" id="LoginForm" class="inlineForm">
        <section class="container">
            <div class="login">
              <form method="post" action="${pageContext.request.contextPath}/servlet/LoginServlet" id="LoginForm">
                <p><input type="text" name="login" value="${person.login}" placeholder="Login"></p>
                <p><input type="password" name="password" value="${person.password}" placeholder="Password"></p>
                <p class="remember">
                  <label>
                    <input type="checkbox" CHECKED name="Remember">
                    <spring:message code="remember.me"/>
                    <span class="wrongMessage" id="wrongMessage">${wrongLoginMessage}</span>
                  </label>
                </p>
                <p class="submit"><input type="submit" name="Enter" value="<spring:message code="log.in"/>"></p>
              </form>
            </div>
          </section>
     </form>
 </jsp:body>
</t:wrapper>