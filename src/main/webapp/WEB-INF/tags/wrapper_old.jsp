<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<!DOCTYPE HTML>
<html lang="en">
        <head>
            <%@ include file="/WEB-INF/head_common.jsp" %>
            <jsp:invoke fragment="header"/>
        </head>
        <body itemscope itemtype="http://schema.org/WebPage" class="mainStyle">
            <header>
              <%@ include file="/menu.jsp" %>
            </header>
            <div class="mainPanel">
                <div class="leftPanel">
                   <jsp:doBody/>
                </div>
              <%@ include file="/right.jsp"%>
            </div>
            <%@ include file="/WEB-INF/footer.jsp"%>
        </body>
</html>
