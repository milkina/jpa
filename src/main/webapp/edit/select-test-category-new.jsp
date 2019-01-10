<script src="${pageContext.request.contextPath}/js/jquery-1.12.2.min.js"></script>
<script>
   $(document).on("change", "#TEST_PATH", function() {
       $.get("${pageContext.request.contextPath}/servlet/ChangeTestServlet",{TEST_PATH: $("select#TEST_PATH").val()}, function(responseJson) {
           var $select = $("#CATEGORY_PATH");
           $select.find("option").remove();
           $.each(responseJson, function(key, value) {
               $("<option>").val(key).text(value).appendTo($select);
           });
       });
   });
</script>
<%@ taglib uri="/WEB-INF/tld/select-test-category-tagjsp-taglib.tld" prefix="select"%>
<strong class="adminLabel"><spring:message code="course"/>:</strong>
  <select class="selectWidth" name="TEST_PATH" id="TEST_PATH">
     <select:test>
          <option value="<select:testPathName/>" <select:testSelected/>> <select:testName/></option>
     </select:test>
  </select>&nbsp;
  <strong class="adminLabel"><spring:message code="category.label"/>:</strong>
    <select class="selectWidth" name="CATEGORY_PATH" id="CATEGORY_PATH">
      <select:category>
         <option value="<select:categoryPathName/>" <select:categorySelected/>><select:categoryName/></option>
      </select:category>
    </select>
<BR><BR>