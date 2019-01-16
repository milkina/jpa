<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:wrapper>
 <jsp:attribute name="header">
    <title>Edit Test</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/administration.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.tablednd.js"></script>
    <meta name="robots" content="noindex">
 </jsp:attribute>
 <jsp:body>
    <div class="mainArea">
    <main>
    <c:if test="${person.sysadmin}">
    <h1>Test Name - ${TESTS[param.TEST_PATH].name}</h1>
    <h2>Categories:</h2>
        <table class="table-striped" id="table-1">
            <thead><tr><td>Topic</td></tr></thead>
              <c:forEach var="category" items="${TESTS[param.TEST_PATH].categories}">
                        <tr id="${category.value.pathName}">
                             <td>
                               <a href="${pageContext.request.contextPath}/servlet/EditCategoryServlet?CATEGORY_PATH=${category.value.pathName}&TEST_PATH=${param.TEST_PATH}"
                               name="edit${category.value.pathName}">
                                <c:if test="${category.value.parentCategory!=null}">
                                     ${category.value.parentCategory.name}.
                                </c:if>
                                ${category.value.name}
                                </a>
                             </td>
                             <td><a href="${pageContext.request.contextPath}/show-questions?CATEGORY_PATH=${category.value.pathName}&TEST_PATH=${param.TEST_PATH}&TYPE=QUESTION"
                             name="CATEGORY_PATH=${category.value.pathName}&TEST_PATH=${param.TEST_PATH}&TYPE=QUESTION">
                                      Show Q-s ${category.value.questionsCount}
                                 </a>
                             </td>
                             <td><a href="${pageContext.request.contextPath}/ShowQuestions.jsp?CATEGORY_PATH=${category.value.pathName}&TEST_PATH=${param.TEST_PATH}&TYPE=TEST"
                             name="CATEGORY_PATH=${category.value.pathName}&TEST_PATH=${param.TEST_PATH}&TYPE=TEST">
                                      Show Test Q-s ${category.value.testsCount}
                                 </a>
                             </td>
                             <td>
                                 <a href="${pageContext.request.contextPath}/edit/move-questions.jsp?TEST_PATH=${param.TEST_PATH}&CATEGORY_PATH=${category.value.pathName}"
                                 name="moveQuestions${category.value.pathName}">
                                 Move Questions</a>
                             </td>
                             <td>
                                <input type="button" value="Delete" onclick="deleteCategory('${pageContext.request.contextPath}','${param.TEST_PATH}','${category.value.pathName}');"
                                name="delete${category.value.pathName}">
                             </td>
                             <td>
                                 <a href="${pageContext.request.contextPath}/add-question?TEST_PATH=${param.TEST_PATH}&CATEGORY_PATH=${category.value.pathName}"
                                 name="addQuestion${category.value.pathName}">Add Question</a>
                             </td>
                             <td>
                                 <input type="button" value="Delete from Test" onclick="deleteFromTest('${pageContext.request.contextPath}','${param.TEST_PATH}','${category.value.pathName}');"
                                  name="removeFromTest${category.value.pathName}">
                             </td>
                        </tr>
                </c:forEach>
        </table><br>
        <script type="text/javascript">
        $(document).ready(function() {
           $("#table-1").tableDnD({
              onDrop: function(table, row) {
                  var previousCategoryElement = row.previousElementSibling;
                  moveCategoryUp(row.id, previousCategoryElement.id, '${pageContext.request.contextPath}','${param.TEST_PATH}');
              }
           });
        });
        </script>
        <a href="${pageContext.request.contextPath}/createCategory.jsp?TEST_PATH=${param.TEST_PATH}" name="createCategory">
          Create Category
        </a><br>
        <a href="${pageContext.request.contextPath}/administration/category/addCategory.jsp?TEST_PATH=${param.TEST_PATH}" name="addCategory">
          Add Category
        </a><br>
    </c:if>
    </main>
    </div>
 </jsp:body>
</t:wrapper>



