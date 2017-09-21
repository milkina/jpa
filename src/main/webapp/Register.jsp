<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
  <jsp:attribute name="header">
      <%@ include file="/WEB-INF/head_common.jsp" %>
      <meta name="robots" content="noindex">
      <title>Register Form on ExamClouds</title>
  </jsp:attribute>
  <jsp:body>
<div class="mainArea">
<FORM ACTION="${pageContext.request.contextPath}/servlet/CreatePerson" METHOD="POST" id="confirmRegistration" name="confirmRegistration">
    <H1 class="header1">Register Form</H1>

<span class="wrongMessage" id="registerWrongMessage"> ${requestScope.message}</span>
    <table width="100%" border=0>
        <tr>
            <td width="20%"> Login: <span class="wrongMessage">*</span></td>
            <td><INPUT TYPE="TEXT" required maxlength="70" NAME="NEW_LOGIN" Value='${newPerson.login}' class="selectWidth"><BR></td>
        </tr>
        <tr>
            <td> Password: <span class="wrongMessage">*</span></td>
            <td><INPUT TYPE="PASSWORD" required maxlength="50" NAME="NEW_PASSWORD" class="selectWidth"><BR></td>
        </tr>
        <tr>
            <td>Confirm Password: <span class="wrongMessage">*</span></td>
            <td><INPUT TYPE="PASSWORD" required maxlength="50" NAME="confPassword" class="selectWidth"><BR></td>
        </tr>
        <tr>
            <td>First Name:</td>
            <td><INPUT TYPE="TEXT" NAME="firstName" maxlength="50" Value='${newPerson.personInfo.firstName}' class="selectWidth"><BR></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><INPUT TYPE="TEXT" NAME="lastName" maxlength="50" Value='${newPerson.personInfo.lastName}' class="selectWidth"><BR></td>
        </tr>
         <tr>
            <td>Email:<span class="wrongMessage">*</span></td>
            <td><INPUT TYPE="TEXT" required maxlength="50" NAME="email" Value='${newPerson.personInfo.email}' class="selectWidth"><BR></td>
        </tr>
        <tr>
            <td></td>
            <td><INPUT TYPE="submit" VALUE="Confirm" name="Confirm"></td>
        </tr>
    </table>
</FORM>
 </div>
 <BR>
  </jsp:body>
  </t:wrapper>