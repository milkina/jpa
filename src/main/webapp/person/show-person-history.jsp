<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:wrapper>
  <jsp:attribute name="header">
    <title>My Profile on ExamClouds</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/my_profile_min.js"></script>
    <meta name="robots" content="noindex">
 </jsp:attribute>
 <jsp:body>
    <div class="mainArea">
        <h2 class="header2">${SOME_USER.login} History</h2>
        <table width="100%">
        <tr>
                        <td width="10%"><B>Date</B></td>
                        <td width="10%"><B>Percent</B></td>
                        <td width="80%"><B>Category</B></td>
        </tr>
        <c:forEach var="exam" items="${USER_TEST_EXAMS}">
           <tr>
                <td width="10%">${exam.formattedDate}</td>
                <td width="10%">${exam.percent}%</td>
                <td width="80%">(${fn:length(exam.categories)})
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



