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
