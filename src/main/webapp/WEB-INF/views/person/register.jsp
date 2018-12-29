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
<div class="mainArea">
  <form:form ACTION="${pageContext.request.contextPath}/addPerson"
       METHOD="POST" id="confirmRegistration" name="confirmRegistration">
    <H1 class="header1"><spring:message	code="registration"/></H1>

<span class="wrongMessage" id="registerWrongMessage"> ${requestScope.message}</span>
    <table width="100%" border=0>
        <tr>
            <td width="20%"><spring:message	code="login"/>: <span class="wrongMessage">*</span></td>
            <td><form:input required="required" maxlength="70" path="login" class="selectWidth"/><BR></td>
        </tr>
        <tr>
            <td><spring:message	code="password"/>: <span class="wrongMessage">*</span></td>
            <td><form:input TYPE="PASSWORD" path="password" required="required" maxlength="50" class="selectWidth"/><BR></td>
        </tr>
        <tr>
            <td><spring:message	code="confirm.password"/>: <span class="wrongMessage">*</span></td>
            <td><INPUT TYPE="PASSWORD" required maxlength="50" NAME="confPassword" class="selectWidth"><BR></td>
        </tr>
        <tr>
            <td><spring:message	code="firstName"/>:</td>
            <td><form:input maxlength="50" path="personInfo.firstName" class="selectWidth"/><BR></td>
        </tr>
        <tr>
            <td><spring:message	code="lastName"/>:</td>
            <td><form:input maxlength="50" path="personInfo.lastName" class="selectWidth"/><BR></td>
        </tr>
         <tr>
            <td>E-mail:<span class="wrongMessage">*</span></td>
            <td><form:input TYPE="email" path="personInfo.email" required="required" maxlength="50" class="selectWidth"/><BR></td>
        </tr>
        <tr>
            <td></td>
            <td><INPUT TYPE="submit" VALUE="<spring:message	code="registration"/>" name="Confirm"></td>
        </tr>
    </table>
    </form:form>
 </div>
<BR>
</jsp:body>
</t:wrapper>