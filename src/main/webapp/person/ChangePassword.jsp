<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
  <jsp:attribute name="header">
     <TITLE>Change Password</TITLE>
        <%@ include file="/WEB-INF/head_common.jsp" %>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/change_password.js"></script>
        <meta name="robots" content="noindex">
 </jsp:attribute>
 <jsp:body>
    <div class="mainArea">
      <h3>Change Password</h3>
      <form action="${pageContext.request.contextPath}/servlet/ChangePassword" id="ChangePasswordForm" name="ChangePasswordForm">
      <table>
        <tr>
            <td>Login</td>
            <td><span id="login">${person.login}</span></td>
        <tr>
            <td>Password<span class="wrongMessage">*</span></td>
            <td><input type="password" required maxlength="50" id="password" name="password"></td>
        </tr>
        <tr>
            <td>Confirm Password <span class="wrongMessage">*</span></td>
            <td><input type="password" required maxlength="50" id="confirmPassword" name="confirmPassword"></td>
        </tr>
      </table>
      <BR>
      <input type="button" value="Confirm" id="confirm" onclick="changePassword('${pageContext.request.contextPath}')">
      </form>
    </div>
 </jsp:body>
</t:wrapper>