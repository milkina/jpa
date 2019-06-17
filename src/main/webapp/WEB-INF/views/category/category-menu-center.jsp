 <ul id="categories">
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <c:forEach var="category" items="${TESTS[param.TEST_PATH].categories}">
        <c:if test="${category.value.hidden==false && category.value.parentCategory==null}">
          <li class="col-xs-12 col-sm-6 col-lg-6">
             <h2>
               <a href="${pageContext.request.contextPath}/java/${param.TEST_PATH}/${category.value.pathName}"
                                 id="categoryItem${category.value.pathName}" class="category-href">
                                 ${category.value.name}
               </a>
               </h2>
               <div class="category-desc">${category.value.article.description}</div>
          </li>
        </c:if>
     </c:forEach>
  </ul>