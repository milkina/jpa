<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:wrapper>
 <jsp:attribute name="header">
    <title>Administration Panel of ExamClouds</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/administration.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.tablednd.js"></script>
    <meta name="robots" content="noindex">
</jsp:attribute>
 <jsp:body>
    <div class="mainArea">
    <c:if test="${person.sysadmin}">
          <%@ include file="/administration/test.jsp" %>
          <a href="${pageContext.request.contextPath}/show-questions?TYPE=NOT_APPROVED" name="viewNotApprovedQuestions">
          View Not Approved Questions</a>
          <%@ include file="/administration/users.jsp" %>
          <%@ include file="/administration/comments/comments.jsp" %>
          <%@ include file="/administration/articles.jsp" %>
          <a href="${pageContext.request.contextPath}/add-question" name="addQuestion">Add Question</a>
     </c:if>
    </div>
 </jsp:body>
</t:wrapper>
