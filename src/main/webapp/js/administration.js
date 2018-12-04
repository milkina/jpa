function deleteCategory(contextPath, test, category){
    if (confirm("Do you want to delete " + category + " permanently?")) {
       window.location.href  = contextPath + "/servlet/DeleteCategoryServlet?TEST_PATH="+
       test+"&CATEGORY_PATH="+category;
    }

}
function deleteFromTest(contextPath, test, category){
    if (confirm("Do you want to delete " + category + " category from " + test +" test?")) {
       window.location.href  = contextPath + "/servlet/DeleteFromTestServlet?TEST_PATH="+
       test+"&CATEGORY_PATH="+category;
    }
}

function deleteTest(contextPath, test){
    if (confirm("Do you want to delete " + test +" test?")) {
       window.location.href  = contextPath + "/servlet/DeleteTestServlet?TEST_PATH="+test;
    }
}
 function newXMLHttpRequest() {
     var xmlreq = false;
     if (window.XMLHttpRequest) {
         xmlreq = new XMLHttpRequest();
     } else if (window.ActiveXObject) {
         try {
             xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
         } catch (e1) {
             try {
                 xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
             } catch (e2) {
             }
         }
     }
     return xmlreq;
 }
 var req = newXMLHttpRequest();
 function moveCategoryUp(category, previousCategory, contextPath, testPath) {
     var url = contextPath + "/servlet/MoveCategoryServlet";

     req.open("POST", url, true);
     req.setRequestHeader("Content-Type",
             "application/x-www-form-urlencoded");

     req.send("CATEGORY_PATH=" + category
              + "&PREVIOUS_CATEGORY_PATH=" + previousCategory
              + "&TEST_PATH=" + testPath);
 }

