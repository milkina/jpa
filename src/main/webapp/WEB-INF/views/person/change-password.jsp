<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
  <jsp:attribute name="header">
     <TITLE><spring:message code="change.password.button"/></TITLE>
        <%@ include file="/WEB-INF/head_common.jsp" %>
        <meta name="robots" content="noindex">
 </jsp:attribute>
 <jsp:body>
    <div class="mainArea">
      <h3 class="header3"><spring:message code="change.password.button"/></h3>
      <form:form action="${pageContext.request.contextPath}/save-user-password" id="ChangePasswordForm"
       name="ChangePasswordForm" method="POST">
       <span class="wrongMessage">${message}</span><BR>
      <table>
        <tr>
            <td><spring:message code="login"/></td>
            <td><span id="login">${person.login}</span></td>
        <tr>
            <td><spring:message code="password"/><span class="wrongMessage">*</span></td>
            <td><input type="password" required maxlength="50" id="password" name="password"></td>
        </tr>
        <tr>
            <td><spring:message code="confirm.password"/> <span class="wrongMessage">*</span></td>
            <td><input type="password" required maxlength="50" id="confirmPassword" name="confirmPassword"></td>
        </tr>
      </table>
      <BR>
      <input type="submit" value="<spring:message code="save.button"/>" id="confirm">
      </form:form>
    </div>
 </jsp:body>
</t:wrapper>