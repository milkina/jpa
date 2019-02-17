<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3><spring:message	code="users"/>(${SIZE})</h3>
<div class="adminUserHead">
  <div class="adminUserCellHead"><spring:message code="login"/></div>
  <div class="adminUserCellHead"><spring:message code="created.date"/></div>
  <div class="adminUserCellHead">&nbsp;</div>
  <div class="adminUserCellHead">&nbsp;</div>
</div>
<div class="adminUserTable" id="adminUserTable">
       <c:forEach var="person" items="${personList}">
          <div id="user${person.login}">
            <div class="adminUserCell">${person.login}&nbsp;</div>
            <div class="adminUserCell">${person.formattedCreatedDate}&nbsp;</div>
            <div class="adminUserCell">
              <a href="${pageContext.request.contextPath}/delete-person?USER_ID=${person.id}">
               <spring:message code="delete"/></a></div>
            <div class="adminUserCell">
               <a href="${pageContext.request.contextPath}/show-person-history?USER_ID=${person.id}"
             id="seeHistory${person.login}"><spring:message code="see.history"/></a></div>
          </div>
       </c:forEach>
</div>