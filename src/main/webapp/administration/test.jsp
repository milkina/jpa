     <form action="" id="editTest">
       <h3>Tests</h3>
       <table  class="table-striped">
       <thead><tr>
                <td>Name</td>
                <td>Questions Number</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
               </tr>
       </thead>
        <c:forEach var="test" items="${TESTS}">
          <tr><td><a href="${pageContext.request.contextPath}/administration/test/show-test.jsp?TEST_PATH=${test.value.pathName}"
          name="${test.value.pathName}Admin">
          ${test.value.name}</a></td>
              <td>${test.value.questionsNumber}</td>
              <td>
               <a href="${pageContext.request.contextPath}/administration/test/edit-test.jsp?TEST_PATH=${test.value.pathName}"
               name="editTest${test.value.pathName}">
                  Edit Test</a>
             </td>
             <td>
               <a href="${pageContext.request.contextPath}/servlet/UpTestServlet?TEST_PATH=${test.value.pathName}"
               name="up${test.value.pathName}">
                  Up</a>
             </td>
             <td>
               <input type="button" value="Delete" name="delete${test.value.pathName}"
               onclick="deleteTest('${pageContext.request.contextPath}','${test.value.pathName}');">
             </td>
          </tr>
         </c:forEach>
      </table><BR>
      <a href="${pageContext.request.contextPath}/addTest.jsp" name="addTest">Add Test</a>
      </form>