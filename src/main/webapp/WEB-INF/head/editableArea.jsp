 <script src="https://cdn.tiny.cloud/1/9httru8sz9dxl9e2e8l23kum4wshoqa8cmpt3ocjebd6fxj2/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
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