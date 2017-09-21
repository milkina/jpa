<script src='//cdn.tinymce.com/4/tinymce.min.js'></script>
<%@ include file="/WEB-INF/head/prism.jsp"%>
<script>
      tinymce.init({
        selector: 'textarea',
        plugins: 'code codesample',
        toolbar: "undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | codesample | code",
        code_dialog_height: 300,
        code_dialog_width: 500,
        browser_spellcheck: true
      });
</script>