<div class="container-fluid footer menu round-border-top">
  	<div class="container">
       <footer class="row">
          <div class="col-xs-12 col-md-4 col-md-push-4 footerItem">
            <ul>
              <li><span class="external-reference" data-link="http://vk.com/club44323672">
                     <span id="sprite-icon_vkontakte" class="socialIcon"></span>
                   </span>
    		  </li>
    		  <li>
    		    <span class="external-reference" data-link="http://www.facebook.com/Examclouds">
    		  	   <span id="sprite-icon_facebook" class="socialIcon"></span>
    			 </span>
    		  </li>
    		  <li>
    			 <span class="external-reference" data-link="https://twitter.com/ExamClouds">
    			    <span id="sprite-icon_twitter" class="socialIcon"></span>
                 </span>
              </li>
             </ul>
          </div>
          <div class="col-xs-12 col-md-4 col-md-push-4 contactUsEmail footerItem">
             <address class="glyphicon glyphicon-envelope">
    		  <a href="mailto:tatyana.milkina@gmail.com">tatyana.milkina@gmail.com</a>
    		 </address>
          </div>
          <div class="col-xs-12 col-md-4 col-md-pull-8 copyright footerItem">
              <p>Copyright &#169; 2012-2022 ExamClouds</p>
              <p><a href="<menu:privacyTag/>"><spring:message code="privacy"/></a></p>
          </div>
       </footer>
    </div>
</div>
<script>
+function($){
  $(document).ready(function(){
    $('.external-reference').replaceWith (function (){return'<a onclick="return !window.open(this.href)" href="'+$(this).data('link')+'" title="'+$(this).text()+'" >'+$(this).html()+'</a>';});
  });
}(jQuery);
</script>
