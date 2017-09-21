<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
 <jsp:attribute name="header">
    <title>Login to ExamClouds</title>
    <meta name="robots" content="noindex">
    <LINK TYPE="text/css" REL="stylesheet" HREF="${pageContext.request.contextPath}/css/login.css?v=1">
 </jsp:attribute>
 <jsp:body>
     <form ACTION="${pageContext.request.contextPath}/servlet/LoginServlet" METHOD="POST" id="LoginForm" class="inlineForm">
        <section class="container">
            <div class="login">
              <h1>Login</h1>
              <form method="post" action="${pageContext.request.contextPath}/servlet/LoginServlet" id="LoginForm">
                <p><input type="text" name="login" value="${person.login}" placeholder="Login"></p>
                <p><input type="password" name="password" value="${person.password}" placeholder="Password"></p>
                <p class="remember">
                  <label>
                    <input type="checkbox" CHECKED name="Remember">
                    Remember me
                    <span class="wrongMessage" id="wrongMessage">${wrongLoginMessage}</span>
                  </label>
                </p>
                <p class="submit"><input type="submit" name="Enter" value="Enter"></p>
              </form>
            </div>
          </section>
     </form>
 </jsp:body>
</t:wrapper>