<%@tag description="Wrapper Tag with 3 columns" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="left" fragment="true" %>
<!DOCTYPE HTML>
<html lang="en">
        <head>
            <%@ include file="/WEB-INF/head_common.jsp" %>
            <!--[if IE]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
            <jsp:invoke fragment="header"/>
        </head>
        <body itemscope itemtype="http://schema.org/WebPage" class="mainStyle">
           <header>
               <div class="maxw">
                   <%@ include file="/menu.jsp" %>
               </div>
           </header>
           <div id="wrapper">
                 <div class="section maxw">
                      <div class="mainwrap">
                         <jsp:doBody/>
                      </div>
                      <aside id="s_left">
                           <jsp:invoke fragment="left"/>
                      </aside>
                      <aside id="s_right">
                          <jsp:include page="/WEB-INF/google-ads.jsp" />
                      </aside>
                 </div>
           </div>
           <footer>
                <div class="maxw">
                     <%@ include file="/WEB-INF/footer.jsp"%>
                </div>
           </footer>
        </body>
</html>