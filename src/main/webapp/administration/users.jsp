<%@ page import="data.person.PersonHandler" %>
<%@ page import="java.util.List"%>
<%@ page import="model.person.Person"%>
<%@ page import="util.GeneralUtility"%>
<%  PersonHandler personHandler = new PersonHandler();
    List<Person> personList = personHandler.getAllPersonsList();
%>
<h3><spring:message	code="users"/>(<%=personList.size()%>)</h3>
<div class="adminUserHead">
  <div class="adminUserCellHead"><spring:message code="login"/></div>
  <div class="adminUserCellHead"><spring:message code="created.date"/></div>
  <div class="adminUserCellHead">&nbsp;</div>
  <div class="adminUserCellHead">&nbsp;</div>
</div>
<div class="adminUserTable" id="adminUserTable">
       <%for(Person p:personList){
          String createdDate = p.getCreatedDate() != null?GeneralUtility.formatDate(p.getCreatedDate()):"&nbsp;";
       %>
       <div id="user<%=p.getLogin()%>">
         <div class="adminUserCell"><%=p.getLogin()%>&nbsp;</div>
         <div class="adminUserCell"><%=createdDate%></div>
         <div class="adminUserCell">
            <a href="${pageContext.request.contextPath}/servlet/DeleteUser?USER_ID=<%=p.getID()%>">
               <spring:message code="delete"/></a></div>
         <div class="adminUserCell">
            <a href="${pageContext.request.contextPath}/show-person-history?USER_ID=<%=p.getID()%>"
             id="seeHistory<%=p.getLogin()%>"><spring:message code="see.history"/></a></div>
       </div>
       <%}%>
</div>