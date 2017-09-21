<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
  <jsp:attribute name="header">
    <title>My Profile on ExamClouds</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/my_profile_min.js"></script>
    <meta name="robots" content="noindex">
 </jsp:attribute>
 <jsp:body>
    <div class="mainArea">
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
    </div>
    <BR>
 </jsp:body>
 </t:wrapper>



