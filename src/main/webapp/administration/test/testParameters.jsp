 <strong class="adminLabel"><spring:message	code="course.name"/>:<span class="wrongMessage">*</span></strong>
             <form:input path="name" maxlength="25" required="required"/> <br>
             <strong class="adminLabel"><spring:message	code="course.path"/>:<span class="wrongMessage">*</span></strong>
             <form:input path="pathName" maxlength="70" required="required"/> <br>
             <strong class="adminLabel"><spring:message	code="language"/>:</strong>
             <select name="LANGUAGE">
                            <c:forEach var="language" items="${LANGUAGES}">
                                <option value="${language.value.code}"
                                  <c:if test="${TESTS[param.TEST_PATH].language.code==language.value.code}">selected</c:if>
                                >${language.value.description}
                                </option>
                            </c:forEach>
             </select>
             <BR>
             <strong class="adminLabel"><spring:message	code="tags"/>:<span class="wrongMessage">*</span></strong>
             <form:input path="tags" maxlength="70" required="required"/> <br>
             <span class="adminLabel"><spring:message	code="image.url"/>:</span>
             <form:input path="article.image" maxlength="150" size="70"/> <BR>
             <span class="adminLabel"><spring:message code="keywords"/>:<span class="wrongMessage">*</span></span>
             <form:textarea rows="4" cols="40" maxlength="160" path="article.keywords" required="required" id="keywords"/>  <BR>
             <span class="adminLabel"><spring:message code="title"/>:<span class="wrongMessage">*</span></span>
             <form:textarea rows="4" cols="40" maxlength="160" path="article.title" required="required" id="TITLE"/>  <BR>
             <span class="adminLabel"><spring:message code="meta.description"/>:<span class="wrongMessage">*</span></span>
             <form:textarea rows="4" cols="40" maxlength="160" path="article.description" required="required" id="description"/><BR>
             <span class="adminLabel"><spring:message code="icon.text"/>:</span>
             <form:textarea rows="10" cols="80" path="iconText" id="iconText"/>
             <span class="adminLabel"><spring:message code="text"/>:</span>
             <form:textarea rows="25" cols="80" path="article.text" id="ARTICLE_TEXT"/>
             <BR>