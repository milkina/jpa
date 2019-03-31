     <div id="editTest">
       <h3 class="header3"><spring:message	code="courses"/></h3>
       <table class="table-striped" id="table-1">
       <thead><tr>
                <td><spring:message	code="course.name"/></td>
                <td><spring:message	code="number.questions.label"/></td>
                <td>&nbsp;</td><td>&nbsp;</td>
               </tr>
       </thead>
        <c:forEach var="test" items="${TESTS}">
          <tr id="${test.value.pathName}">
              <td><a href="${pageContext.request.contextPath}/show-course-admin?TEST_PATH=${test.value.pathName}"
                     id="${test.value.pathName}Admin">
                       ${test.value.name}</a></td>
              <td>${test.value.questionsNumber}</td>
              <td>
               <a href="${pageContext.request.contextPath}/show-edit-course?TEST_PATH=${test.value.pathName}"
               id="editTest${test.value.pathName}">
                  <spring:message	code="edit.course"/></a>
             </td>
             <td>
               <input type="button" value="<spring:message	code="delete"/>" id="delete${test.value.pathName}"
               onclick="deleteCourse('${pageContext.request.contextPath}','${test.value.pathName}','<spring:message	code="want.delete"/>');">
             </td>
          </tr>
         </c:forEach>
      </table><BR>
       <script>
              $(document).ready(function() {
                 $("#table-1").tableDnD({
                    onDrop: function(table, row) {
                        var previousTestElement = row.previousElementSibling;
                        moveTestUp(row.id, previousTestElement.id,
                        '${pageContext.request.contextPath}');
                    }
                 });
              });
              </script>
      <a href="${pageContext.request.contextPath}/show-add-course" id="addTest"><spring:message	code="add.course"/></a>
      </div>