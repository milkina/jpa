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
        <h2 class="header2">Personal Data</h2>
        <form action="" id="ProfileForm">
            <span class="wrongMessage">${message}</span><BR>
            <table>
                <tr>
                    <td><B> Login:</B><span class="wrongMessage">*</span></td>
                    <td><input type="text" class="selectWidth" name="login" value="${person.login}"><BR></td>
                </tr>
                <tr>
                    <td><B>First Name:</B></td>
                    <td><input type="text" class="selectWidth" name="firstName"
                               value="${person.personInfo.firstName}"><BR></td>
                </tr>
                <tr>
                    <td><B>Last Name: </B></td>
                    <td><input type="text" class="selectWidth" name="lastName"
                               value="${person.personInfo.lastName}"><BR></td>
                </tr>
                <tr>
                    <td><B>Email:</B><span class="wrongMessage">*</span></td>
                    <td><input type="text" class="selectWidth" name="email" value="${person.personInfo.email}"><BR></td>
                </tr>
            </table>
            <br>
            <input type="button" class="wideSubmitButton" value="Save" name="Save" id="Save"
                   onclick="saveUserSettings('${pageContext.request.contextPath}')">
            <input type="button" class="wideSubmitButton" value="Change Password" name="ChangePassword"
                   id="ChangePassword" onclick="openChangePasswordWindow('${pageContext.request.contextPath}')">
        </form>
        <h2 class="header2">History</h2>
        <table width="100%">
        <tr>
                        <td width="8%"><B>Date</B></td>
                        <td width="8%"><B>Percent</B></td>
                        <td width="8%"><B>Number of Questions</B></td>
                        <td width="76%"><B>Category</B></td>
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
        <a href="${pageContext.request.contextPath}/addQuestion.jsp" name="addQuestion">Add Question</a>
        <BR><a href="${pageContext.request.contextPath}/ShowQuestions.jsp?TYPE=MY_QUESTIONS" name="myQuestions">My Questions</a>
    </div>
    <BR>
 </jsp:body>
 </t:wrapper>



