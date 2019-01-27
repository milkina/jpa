function deleteCategory(contextPath, test, category, message){
    if (confirm(message +" " + category + "?")) {
       window.location.href  = contextPath + "/delete-category?TEST_PATH="+
       test + "&CATEGORY_PATH="+category;
    }

}
function deleteFromTest(contextPath, test, category, message1, message2){
    if (confirm(message1 + " " + category +" "+ message2+" " + test +"?")) {
       window.location.href  = contextPath + "/delete-from-course?TEST_PATH="+
       test+"&CATEGORY_PATH="+category;
    }
}

function deleteCourse(contextPath, test, message){
    if (confirm(message + " " + test + "?")) {
       window.location.href  = contextPath + "/delete-course?TEST_PATH="+test;
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
     var url = contextPath + "/move-category";

     req.open("POST", url, true);
     req.setRequestHeader("Content-Type",
             "application/x-www-form-urlencoded");

     req.send("CATEGORY_PATH=" + category
              + "&PREVIOUS_CATEGORY_PATH=" + previousCategory
              + "&TEST_PATH=" + testPath);
 }

 function moveTestUp(testPath, previousTest, contextPath) {
     var url = contextPath + "/up-course";

     req.open("POST", url, true);
     req.setRequestHeader("Content-Type",
             "application/x-www-form-urlencoded");

     req.send("PREVIOUS_TEST_PATH=" + previousTest
              + "&TEST_PATH=" + testPath);
 }

