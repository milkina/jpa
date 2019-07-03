<div id="collapse_q_${test.pathName}" class="panel-collapse collapse"
      role="tabpanel" aria-labelledby="heading_q_${test.pathName}">
       <div class="panel-body">
        <form action="${pageContext.request.contextPath}/see-questions?TEST_PATH=${test.pathName}" method="post">
         <select name="CATEGORY_PATH" id="CATEGORY_PATH_quiz_${test.pathName}" multiple="multiple" class="2col active" required>
          <c:set var="count" value="${1}"/>
          <c:forEach var="category" items="${test.categories}">
           <c:choose>
             <c:when test="${not empty category.value.subCategories}">
                <optgroup label="<spring:message code="lesson"/>&nbsp;${count} - ${category.value.name}">
                <c:forEach var="subCategory" items="${category.value.subCategories}">
                   <option value="${subCategory.pathName}">${subCategory.name}</option>
                </c:forEach>
                </optgroup>
             </c:when>
             <c:otherwise>
                <option value="${category.value.pathName}"><spring:message code="lesson"/>&nbsp;${count} - ${category.value.name}</option>
             </c:otherwise>
             </c:choose>
             <c:set var="count" value="${count+1}"/>
      </c:forEach>
   </select>
   <div class="inc-div">
     <span class="number-questions"><spring:message code="number.questions.label"/></span>
     <div class="dec button">-</div>
     <input type="text" value="25" name="NUMBER_OF_QUESTIONS" class="number-questions-input">
     <div class="inc button">+</div>
   </div>
   <div>
     <input type="submit" value="<spring:message code="start"/>" name="startTest" class="styled-button">
   </div>
</form>
</div>
</div>