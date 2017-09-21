<span class="adminLabel">Category Name:<span class="wrongMessage">*</span></span>
<input type="text" name="categoryName" maxlength="70" required value="${CATEGORY_ATTRIBUTE.name}" size="70">
<BR>
<span class="adminLabel">Category Path:<span class="wrongMessage">*</span></span>
<input type="text" name="categoryPathName" maxlength="70" required value="${param.CATEGORY_PATH}" size="70">
<BR>
<span class="adminLabel">Category Parent:</span>
<select name="categoryParent">
   <option value="" <c:if test="${CATEGORY_ATTRIBUTE.parentCategory==null}">
                          selected
                       </c:if>></option>
   <c:forEach var="category" items="${TESTS[param.TEST_PATH].categories}">
       <option value="${category.value.id}"
            <c:if test="${CATEGORY_ATTRIBUTE.parentCategory.id==category.value.id}">
                  selected
            </c:if>
       >${category.value.name}</option>
   </c:forEach>
</select>
<selectTag:select options="${TESTS[param.TEST_PATH].categories}" name="categoryParent" selected=""/>
<BR>
<span class="adminLabel">Hidden?</span>
<input type="checkbox" name="hidden" <c:if test="${CATEGORY_ATTRIBUTE.hidden}">checked</c:if>>
<BR>
<span class="adminLabel">Image Url:</span>
<input type="text" name="ARTICLE_IMAGE" maxlength="150" value="${CATEGORY_ATTRIBUTE.article.image}" size="70"> <BR>
<span class="adminLabel">Keywords:<span class="wrongMessage">*</span></span>
<textarea rows="4" cols="40" maxlength="160" name="keywords" required id="keywords">${CATEGORY_ATTRIBUTE.article.keywords}</textarea>  <BR>
<span class="adminLabel">Meta Description:<span class="wrongMessage">*</span></span>
<textarea rows="4" cols="40" maxlength="160" name="description" required id="description">${CATEGORY_ATTRIBUTE.article.description}</textarea>  <BR>
<span class="adminLabel">Text:</span>
<textarea rows="25" cols="80" name="ARTICLE_TEXT" id="ARTICLE_TEXT">${CATEGORY_ATTRIBUTE.article.text}</textarea> <BR>

