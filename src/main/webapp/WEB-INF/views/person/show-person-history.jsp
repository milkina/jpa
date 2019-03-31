<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:wrapper>
  <jsp:attribute name="header">
    <title>${SOME_USER.login} <spring:message code="history.label"/> | ExamClouds</title>
    <script src="${pageContext.request.contextPath}/js/my_profile_min.js"></script>
    <meta name="robots" content="noindex">
 </jsp:attribute>
 <jsp:body>
    <div class="mainArea">
        <h2 class="header2">${SOME_USER.login} <spring:message code="history.label"/></h2>
        <table style="width:100%">
        <tr>
                        <td style="width:10%"><B><spring:message code="date.label"/></B></td>
                        <td style="width:10%"><B><spring:message code="percent.label"/></B></td>
                        <td style="width:10%"><B><spring:message code="number.questions.label"/></B></td>
                        <td style="width:70%"><B><spring:message code="category.label"/></B></td>
        </tr>
        <c:forEach var="exam" items="${USER_TEST_EXAMS}">
           <tr>
                <td style="width:10%">${exam.formattedDate}</td>
                <td style="width:10%">${exam.percent}%</td>
                <td style="width:10%">${exam.amount}</td>
                <td style="width:70%">(${fn:length(exam.categories)})
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
    </div>
    <BR>
 </jsp:body>
 </t:wrapper>



