<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
  <jsp:attribute name="header">
      <%@ include file="/WEB-INF/head_common.jsp" %>
      <meta name="robots" content="noindex">
      <title><spring:message code="registration"/> | ExamClouds</title>
  </jsp:attribute>
  <jsp:body>
    <div class="register-form">
         <div class="white-bg">
            <div class="top-detail"></div>
              <form:form action="${pageContext.request.contextPath}/addPerson"
                   method="POST" id="confirmRegistration" name="confirmRegistration">
                  <h1 class="register-header"><spring:message code="registration"/></h1>
                  <span class="wrongMessage" id="registerWrongMessage"> ${requestScope.message}</span>
                  <div class="register-label"><spring:message code="login"/></div>
                  <div><form:input required="required" maxlength="70" path="login" class="register-input"/></div>
                  <div class="register-label"><spring:message	code="password"/> </div>
                  <div><form:input type="password" path="password" required="required" maxlength="50" class="register-input"/></div>
                  <div class="register-label"><spring:message	code="confirm.password"/></div>
                  <div><input type="password" required maxlength="50" name="confPassword" class="register-input"></div>
                  <div class="register-label">e-mail:</div>
                  <div><form:input type="email" path="email" required="required" maxlength="50" class="register-input"/></div>
                  <div class="register-button"><input type="submit" value="<spring:message	code="registration"/>" name="Confirm" id="Confirm" class="styled-button"></div>
              </form:form>
           </div>
    </div>
  </div>
</jsp:body>
</t:wrapper>