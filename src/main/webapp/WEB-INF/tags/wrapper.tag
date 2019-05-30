<%@tag description="Wrapper Tag with 2 columns" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="language" fragment="true" %>
<!DOCTYPE HTML>
<html <jsp:invoke fragment="language"/>>
        <head>
            <%@ include file="/WEB-INF/head_common.jsp" %>
            <jsp:invoke fragment="header"/>
        </head>
        <body itemscope itemtype="http://schema.org/WebPage" class="scroll-style">
           <header>
               <div class="container-fluid menu top round-border-bottom">
                    <%@ include file="/menu.jsp" %>
               </div>
           </header>
           <div class="wrapper container">
                   <jsp:doBody/>
           </div>
           <%@ include file="/WEB-INF/footer.jsp"%>
        </body>
</html>