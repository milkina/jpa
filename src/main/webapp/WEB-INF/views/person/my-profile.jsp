<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri = "http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<t:wrapper>
  <jsp:attribute name="header">
    <title><spring:message code="my.profile.label"/> | ExamClouds</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/my_profile.js"></script>
    <meta name="robots" content="noindex">
 </jsp:attribute>
 <jsp:body>
    <div class="mainArea">
        <h2 class="header2"><spring:message code="personal.data.label"/></h2>
        <form:form action="${pageContext.request.contextPath}/change-user-settings" id="ProfileForm" method="POST">
            <span class="wrongMessage">${message}</span><BR>
            <table>
                <tr>
                    <td><B><spring:message code="login"/>:</B><span class="wrongMessage">*</span></td>
                    <td><form:input class="selectWidth" path="login" required="required"/><BR></td>
                </tr>
                <tr>
                    <td><B><spring:message	code="firstName"/>:</B></td>
                    <td><form:input class="selectWidth" path="personInfo.firstName"/><BR></td>
                </tr>
                <tr>
                    <td><B><spring:message	code="lastName"/>:</B></td>
                    <td><form:input class="selectWidth" path="personInfo.lastName"/><BR></td>
                </tr>
                <tr>
                    <td><B>E-mail:</B><span class="wrongMessage">*</span></td>
                    <td><form:input class="selectWidth" path="personInfo.email" required="required"/><BR></td>
                </tr>
            </table>
            <br>
            <input type="submit" class="wideSubmitButton" value="<spring:message code="save.button"/>" name="Save" id="Save">
            <input type="button" class="wideSubmitButton" value="<spring:message code="change.password.button"/>" name="ChangePassword"
                   id="ChangePassword" onclick="openChangePasswordWindow('${pageContext.request.contextPath}')">
        </form:form>
        <h2 class="header2"><spring:message code="history.label"/></h2>
        <table width="100%">
        <tr>
                        <td width="8%"><B><spring:message code="date.label"/></B></td>
                        <td width="8%"><B><spring:message code="percent.label"/></B></td>
                        <td width="8%"><B><spring:message code="number.questions.label"/></B></td>
                        <td width="76%"><B><spring:message code="category.label"/></B></td>
        </tr>
        <c:forEach var="exam" items="${USER_TEST_EXAMS}">
           <tr>
                <td width="8%">${exam.formattedDate}</td>
                <td width="8%">${exam.percent}%</td>
                <td width="8%">${exam.amount}</td>
                <td width="76%">(${fn:length(exam.categories)})
                  <c:forEach var="category" items="${exam.categories}">
                     <c:if test="${category!=null && category.parentCategory!=null}">
                        ${category.parentCategory.name}.
                     </c:if>
                     ${category.name}&nbsp;&nbsp;&nbsp;
                  </c:forEach>
                </td>
           </tr>
        </c:forEach>
        </table>
        <a href="${pageContext.request.contextPath}/add-question" name="addQuestion">
           <spring:message code="add.question.button"/></a>
        <BR><a href="${pageContext.request.contextPath}/show-questions?TYPE=MY_QUESTIONS" name="myQuestions">
           <spring:message code="my.questions.button"/></a>
        <BR><a href="${pageContext.request.contextPath}/add-article" name="addArticle">
                      <spring:message code="add.article"/>
             </a>
    </div>
    <BR>
 </jsp:body>
 </t:wrapper>



