<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri = "http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<t:wrapper>
  <jsp:attribute name="header">
    <title><spring:message code="my.profile.label"/> | ExamClouds</title>
    <script src="${pageContext.request.contextPath}/js/my_profile.js"></script>
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
                    <td><B>E-mail:</B><span class="wrongMessage">*</span></td>
                    <td><form:input class="selectWidth" path="email" required="required"/><BR></td>
                </tr>
            </table>
            <br>
            <input type="submit" value="<spring:message code="save.button"/>" name="Save" id="Save">
            <input type="button" value="<spring:message code="change.password.button"/>" name="ChangePassword"
                   id="ChangePassword" onclick="openChangePasswordWindow('${pageContext.request.contextPath}')">
        </form:form>
        <h2 class="header2"><spring:message code="history.label"/></h2>
        <table style="width:100%">
        <tr>
                        <td style="width:8%"><B><spring:message code="date.label"/></B></td>
                        <td style="width:8%"><B><spring:message code="percent.label"/></B></td>
                        <td style="width:8%"><B><spring:message code="number.questions.label"/></B></td>
                        <td style="width:76%"><B><spring:message code="category.label"/></B></td>
        </tr>
        <c:forEach var="exam" items="${USER_TEST_EXAMS}">
           <tr>
                <td style="width:8%">${exam.formattedDate}</td>
                <td style="width:8%">${exam.percent}%</td>
                <td style="width:8%">${exam.amount}</td>
                <td style="width:76%">(${fn:length(exam.categories)})
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
        <a href="${pageContext.request.contextPath}/add-question" id="addQuestion">
           <spring:message code="add.question.button"/></a>
        <BR><a href="${pageContext.request.contextPath}/show-questions?TYPE=MY_QUESTIONS" id="myQuestions">
           <spring:message code="my.questions.button"/></a>
        <h2 class="header2"><spring:message code="my.articles"/></h2>
        <ol class="commentList">
        <c:forEach var="article" items="${ARTICLES}">
             <li>
                <div style="width:38%;display:inline-block">${article.id}</div>
                <div style="width:38%;display:inline-block">
                   <a href="${pageContext.request.contextPath}${article.url}">
                                       ${article.url}
                   </a>
                </div>
                <div style="width:10%;display:inline-block">
                   <a href="${pageContext.request.contextPath}/delete-article?ARTICLE_ID=${article.id}"
                                      id="deleteArticle${article.url}"><spring:message code="delete"/></a>
                </div>
                <div style="width:10%;display:inline-block">
                     <a href="${pageContext.request.contextPath}/edit-article?ARTICLE_ID=${article.id}"
                                      id="editArticle${article.url}"><spring:message code="edit"/></a>
                </div>
             </li>
        </c:forEach>
        </ol>
        <BR><a href="${pageContext.request.contextPath}/add-article" id="addArticle">
                      <spring:message code="add.article"/>
             </a>
    </div>
    <BR>
 </jsp:body>
 </t:wrapper>



