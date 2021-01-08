<script src='https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js'
referrerpolicy="origin"></script>
<%@ include file="/WEB-INF/head/prism.jsp"%>
<script>
function addWYSIWG() {
      tinymce.init({
        selector: 'textarea',
        plugins: 'code codesample',
        toolbar: "undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | codesample | code",
        browser_spellcheck: true
      });
 }
 addWYSIWG();
</script>