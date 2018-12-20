     <form action="" id="editTest">
       <h3>Tests</h3>
       <table class="table-striped" id="table-1">
       <thead><tr>
                <td>Name</td>
                <td>Questions Number</td>
                <td>&nbsp;</td>
               </tr>
       </thead>
        <c:forEach var="test" items="${TESTS}">
          <tr id="${test.value.pathName}">
              <td><a href="${pageContext.request.contextPath}/administration/test/show-test.jsp?TEST_PATH=${test.value.pathName}"
                     name="${test.value.pathName}Admin">
                       ${test.value.name}</a></td>
              <td>${test.value.questionsNumber}</td>
              <td>
               <a href="${pageContext.request.contextPath}/administration/test/edit-test.jsp?TEST_PATH=${test.value.pathName}"
               name="editTest${test.value.pathName}">
                  Edit Test</a>
             </td>
             <td>
               <input type="button" value="Delete" name="delete${test.value.pathName}"
               onclick="deleteTest('${pageContext.request.contextPath}','${test.value.pathName}');">
             </td>
          </tr>
         </c:forEach>
      </table><BR>
       <script type="text/javascript">
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
      <a href="${pageContext.request.contextPath}/addTest.jsp" name="addTest">Add Test</a>
      </form>