
  <div id="collapse_${test.pathName}" class="panel-collapse collapse"
      role="tabpanel" aria-labelledby="heading_${test.pathName}">
       <div class="panel-body">
         <form ACTION="${pageContext.request.contextPath}/start-test?TEST_PATH=${test.pathName}"  method="POST">
   <select name="CATEGORY_PATH" id="CATEGORY_PATH_test_${test.pathName}" multiple="multiple"
    class="2col active" required>
      <c:forEach var="category" items="${test.categories}">
          <c:choose>
             <c:when test="${not empty category.value.subCategories}">
                <optgroup label="${category.value.name}">
                <c:forEach var="subCategory" items="${category.value.subCategories}">
                      <option value="${subCategory.pathName}">${subCategory.name}</option>
                </c:forEach>
                </optgroup>
             </c:when>
             <c:otherwise>
                <option value="${category.value.pathName}">${category.value.name}</option>
             </c:otherwise>
             </c:choose>
      </c:forEach>
   </select>
   <div style="padding:10px 0;">
     <span class="number-questions"><spring:message code="number.questions.label"/></span>
     <div class="dec button">-</div>
     <input type="number" value="25" name="NUMBER_OF_QUESTIONS" class="number-questions-input">
     <div class="inc button">+</div>
   </div>
   <div>
     <input type="submit" value="<spring:message code="start.test"/>" name="startTest" class="styled-button">
   </div>
   </form>
</div>
</div>
