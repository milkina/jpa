<nav>
  <div class="categoryMenuDiv" id="categoryMenuDiv" >
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <c:forEach var="category" items="${TESTS[param.TEST_PATH].categories}">
                  <c:if test="${category.value.hidden==false && category.value.parentCategory==null}">
                         <c:choose>
                             <c:when test="${not empty category.value.subCategories}">
                               <div class="accordion">${category.value.name}</div>
                               <div class="panel">
                                 <ul>
                                   <c:forEach var="subCategory" items="${category.value.subCategories}">
                                     <c:if test="${subCategory.hidden==false}">
                                        <li><a href="${pageContext.request.contextPath}/java/${param.TEST_PATH}/${subCategory.pathName}"
                                        id="categoryItem${subCategory.pathName}">${subCategory.name}</a></li>
                                     </c:if>
                                   </c:forEach>
                                 </ul>
                               </div>
                             </c:when>
                             <c:otherwise>
                                <div class="accordionM">
                                 <a href="${pageContext.request.contextPath}/java/${param.TEST_PATH}/${category.value.pathName}"
                                 id="categoryItem${category.value.pathName}">${category.value.name}</a>
                               </div>
                             </c:otherwise>
                         </c:choose>
                  </c:if>
         </c:forEach>
         <script>
         var acc = document.getElementsByClassName("accordion");
         var i;
         for (i = 0; i < acc.length; i++) {
             acc[i].onclick = function(){
                 this.classList.toggle("active");
                 this.nextElementSibling.classList.toggle("show");
           }
         }
         </script>
  <br>
  </div>
</nav>
