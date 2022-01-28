<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
 <jsp:attribute name="header">
    <title>Edit Test</title>
    <script src="${pageContext.request.contextPath}/js/administration.js"></script>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.tablednd.js"></script>
    <meta name="robots" content="noindex">
 </jsp:attribute>
 <jsp:body>
    <div class="mainArea">
    <main>
    <c:if test="${person.sysadmin}">
    <h1 class="header1"><spring:message	code="course"/> ${TESTS[param.TEST_PATH].name}</h1>
        <table class="table-striped" id="table-1">
              <c:forEach var="category" items="${TESTS[param.TEST_PATH].categories}">
                        <tr id="${category.value.pathName}">
                             <td>
                               <a href="${pageContext.request.contextPath}/show-edit-category?CATEGORY_PATH=${category.value.pathName}&TEST_PATH=${param.TEST_PATH}"
                               id="edit${category.value.pathName}">
                                <c:if test="${category.value.parentCategory!=null}">
                                     ${category.value.parentCategory.name}.
                                </c:if>
                                ${category.value.name}
                                </a>
                             </td>
                             <td><a href="${pageContext.request.contextPath}/show-questions?CATEGORY_PATH=${category.value.pathName}&TEST_PATH=${param.TEST_PATH}&TYPE=QUESTION"
                             id="CATEGORY_PATH=${category.value.pathName}&TEST_PATH=${param.TEST_PATH}&TYPE=QUESTION">
                                      <spring:message code="questions"/> ${category.value.questionsCount}
                                 </a>
                             </td>
                             <td><a href="${pageContext.request.contextPath}/show-questions?CATEGORY_PATH=${category.value.pathName}&TEST_PATH=${param.TEST_PATH}&TYPE=TEST"
                             id="CATEGORY_PATH=${category.value.pathName}&TEST_PATH=${param.TEST_PATH}&TYPE=TEST">
                                      <spring:message code="tests"/> ${category.value.testsCount}
                                 </a>
                             </td>
                             <td>
                                 <a href="${pageContext.request.contextPath}/show-move-batch?OLD_TEST_PATH=${param.TEST_PATH}&OLD_CATEGORY_PATH=${category.value.pathName}"
                                 id="moveQuestions${category.value.pathName}">
                                 <spring:message code="move.questions"/></a>
                             </td>
                             <td>
                                <input type="button" value="<spring:message	code="delete"/>"
                                onclick="deleteCategory('${pageContext.request.contextPath}','${param.TEST_PATH}','${category.value.pathName}','<spring:message 	code="want.delete"/>');"
                                id="delete${category.value.pathName}">
                             </td>
                             <td>
                                 <a href="${pageContext.request.contextPath}/add-question?TEST_PATH=${param.TEST_PATH}&CATEGORY_PATH=${category.value.pathName}"
                                 id="addQuestion${category.value.pathName}">
                                    <spring:message	code="add.question.button"/></a>
                             </td>
                             <td>
                                 <input type="button" value="<spring:message code="delete.from.course"/>"
                                  onclick="deleteFromTest('${pageContext.request.contextPath}','${param.TEST_PATH}','${category.value.pathName}','<spring:message	code="want.delete"/>','<spring:message	code="from"/>');"
                                  id="removeFromTest${category.value.pathName}">
                             </td>
                        </tr>
                </c:forEach>
        </table><br>
        <script>
        $(document).ready(function() {
           $("#table-1").tableDnD({
              onDrop: function(table, row) {
                  var previousCategoryElement = row.previousElementSibling;
                  moveCategoryUp(row.id, previousCategoryElement.id, '${pageContext.request.contextPath}','${param.TEST_PATH}');
              }
           });
        });
        </script>
        <a href="${pageContext.request.contextPath}/show-create-category?TEST_PATH=${param.TEST_PATH}" id="createCategory">
          <spring:message	code="create.category"/>
        </a><br>
        <a href="${pageContext.request.contextPath}/show-add-category?TEST_PATH=${param.TEST_PATH}" id="addCategory">
          <spring:message	code="add.category"/>
        </a><br>
    </c:if>
    </main>
    </div>
 </jsp:body>
</t:wrapper>



