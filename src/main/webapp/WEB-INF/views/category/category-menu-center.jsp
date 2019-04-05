 <div id="categories">
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <c:forEach var="category" items="${TESTS[param.TEST_PATH].categories}">
                  <c:if test="${category.value.hidden==false && category.value.parentCategory==null}">
                                <div style="padding: 5px 0">
                                 <p style="display:inline;float:left"><a href="${pageContext.request.contextPath}/java/${param.TEST_PATH}/${category.value.pathName}"
                                 id="categoryItem${category.value.pathName}">${category.value.name}</a> - </p>
                                 <h2 style="font-size:14pt;">${category.value.article.description}</h2>
                               </div>
                  </c:if>
         </c:forEach>
  </div>