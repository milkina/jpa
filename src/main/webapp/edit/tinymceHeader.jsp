 <script src="https://cdn.tiny.cloud/1/9httru8sz9dxl9e2e8l23kum4wshoqa8cmpt3ocjebd6fxj2/tinymce/5/tinymce.min.js"
 referrerpolicy="origin"></script>
    <%@ include file="/WEB-INF/head/prism.jsp"%>
    <script>
          tinymce.init({
           // add these two lines for absolute urls
            remove_script_host : false,
            convert_urls : false,
            selector: 'textarea#ARTICLE_TEXT',
            plugins: 'code codesample image imagetools link table',
            toolbar: "undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | codesample | code | image | link | table",
            browser_spellcheck: true
          });
           tinymce.init({
           // add these two lines for absolute urls
            remove_script_host : false,
            convert_urls : false,
            selector: 'textarea#iconText',
            plugins: 'code codesample',
            toolbar: "undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | codesample | code ",
            browser_spellcheck: true
          });
    </script>