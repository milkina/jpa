 <script src='//cdn.tinymce.com/4/tinymce.min.js'></script>
    <%@ include file="/WEB-INF/head/prism.jsp"%>
    <script>
          tinymce.init({
           // add these two lines for absolute urls
            remove_script_host : false,
            convert_urls : false,
            selector: 'textarea#article.text',
            plugins: 'code codesample image imagetools link table',
            toolbar: "undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | codesample | code | image | link | table",
            code_dialog_height: 300,
            code_dialog_width: 500,
            browser_spellcheck: true
          });
           tinymce.init({
           // add these two lines for absolute urls
            remove_script_host : false,
            convert_urls : false,
            selector: 'textarea#iconText',
            plugins: 'code codesample',
            toolbar: "undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | codesample | code ",
            code_dialog_height: 300,
            code_dialog_width: 500,
            browser_spellcheck: true
          });
    </script>