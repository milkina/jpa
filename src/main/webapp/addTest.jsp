<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
 <jsp:attribute name="header">
    <title>Add Test</title>
    <jsp:include page="/edit/tinymceHeader.jsp"/>
</jsp:attribute>
 <jsp:body>
   <div class="mainArea">
        <form id="addTestForm" action="${pageContext.request.contextPath}/servlet/AddTestServlet" method="POST" >
            <br>
            <%@ include file="/administration/test/testParameters.jsp" %>
            <input type="submit" value="Save" id="Save" name="Save">
        </form>
    </div>
 </jsp:body>
</t:wrapper>

