<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<t:wrapper>
 <jsp:attribute name="header">
    <title><spring:message	code="add.course"/></title>
    <jsp:include page="/edit/tinymceHeader.jsp"/>
</jsp:attribute>
 <jsp:body>
   <div class="mainArea">
        <form:form id="addTestForm" action="${pageContext.request.contextPath}/add-course" method="POST" >
            <br>
            <%@ include file="/administration/test/testParameters.jsp" %>
            <input type="submit" value="<spring:message	code="save.button"/>" id="Save" name="Save">
        </form:form>
    </div>
 </jsp:body>
</t:wrapper>

