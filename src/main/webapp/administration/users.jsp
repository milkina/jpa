<%@ page import="data.person.PersonHandler" %>
<%@ page import="java.util.List"%>
<%@ page import="model.person.Person"%>
<%@ page import="util.GeneralUtility"%>
<%  PersonHandler personHandler = new PersonHandler();
    List<Person> personList = personHandler.getAllPersonsList();
%>
<h3>Users(<%=personList.size()%>)</h3>
<div class="adminUserHead">
  <div class="adminUserCellHead">Login</div>
  <div class="adminUserCellHead">Created Date</div>
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
            <a href="${pageContext.request.contextPath}/servlet/DeleteUser?USER_ID=<%=p.getID()%>">Delete</a></div>
       </div>
       <%}%>
</div>