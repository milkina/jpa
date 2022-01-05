<%@tag description="Wrapper Tag with 2 columns" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="language" fragment="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="/WEB-INF/tld/menu-jsp-taglib.tld" prefix="menu"%>
<!DOCTYPE html>
<html <jsp:invoke fragment="language"/>>
        <head>
            <%@ include file="/WEB-INF/head_common.jsp"%>
            <jsp:invoke fragment="header"/>
        </head>
        <body itemscope itemtype="http://schema.org/WebPage" class="scroll-style">
        <!-- Google Tag Manager (noscript) -->
        <noscript><iframe src="https://www.googletagmanager.com/ns.html?id=GTM-KMPGXK7"
        height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
        <!-- End Google Tag Manager (noscript) -->
           <header>
             <div class="container-fluid menu top round-border-bottom">
               <jsp:include page="/menu.jsp"/>
             </div>
           </header>
           <div class="wrapper container">
              <jsp:doBody/>
           </div>
           <%@ include file="/WEB-INF/footer.jsp"%>
        </body>
</html>