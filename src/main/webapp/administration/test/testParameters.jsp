 <strong class="adminLabel">Test Name:</strong>
              <input name="testName" type="text" maxlength="70" required value="${TESTS[param.TEST_PATH].name}"> <br>
             <strong class="adminLabel" >Test Path Name:</strong>
             <input name="TEST_PATH" type="text" maxlength="70" required value="${TESTS[param.TEST_PATH].pathName}"> <br>
             <strong class="adminLabel">Tags:</strong>
             <input name="TEST_TAGS" type="text" maxlength="70" required value="${TESTS[param.TEST_PATH].tags}"> <br>
             <span class="adminLabel">Image Url:</span>
             <input type="text" name="ARTICLE_IMAGE" maxlength="150" value="${TESTS[param.TEST_PATH].article.image}" size="70"> <BR>
             <span class="adminLabel">Keywords:<span class="wrongMessage">*</span></span>
             <textarea rows="4" cols="40" maxlength="160" name="keywords" required id="keywords">${TESTS[param.TEST_PATH].article.keywords}</textarea>  <BR>
             <span class="adminLabel">Title:<span class="wrongMessage">*</span></span>
             <textarea rows="4" cols="40" maxlength="160" name="TITLE" required id="TITLE">${TESTS[param.TEST_PATH].article.title}</textarea>  <BR>
             <span class="adminLabel">Meta Description:<span class="wrongMessage">*</span></span>
             <textarea rows="4" cols="40" maxlength="160" name="description" required id="description">${TESTS[param.TEST_PATH].article.description}</textarea>  <BR>
             <span class="adminLabel">Icon Text:</span>
             <textarea rows="10" cols="80" name="ICON_TEXT" id="ICON_TEXT">${TESTS[param.TEST_PATH].iconText}
              </textarea>
             <span class="adminLabel">Text:</span>
             <textarea rows="25" cols="80" name="ARTICLE_TEXT" id="ARTICLE_TEXT">${TESTS[param.TEST_PATH].article.text}
             </textarea>
             <BR>