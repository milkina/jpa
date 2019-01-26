<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="menuDiv">
  <div style="display:inline-block;width:100%">
        <div id="sprite-examclouds" class="menuImageDiv"></div>
        <div style="float:left;">
        <aside>
        <style>
        .ad3 { width: 234px; height: 60px; }
        @media(min-width: 400px) { .ad3 { width: 234px; height: 60px;} }
        @media(min-width: 480px) { .ad3 {width: 468px; height: 60px; } }
        @media(min-width: 800px) { .ad3 {width: 468px; height: 60px; } }
        </style>
        <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
        <!-- ad3 -->
       <ins class="adsbygoogle ad3"
            style="display:block"
            data-ad-client="ca-pub-7148099013705432"
            data-ad-slot="1741470902"
            data-ad-format="auto"></ins>
        <script>
          (adsbygoogle = window.adsbygoogle || []).push({});
        </script>
        </aside>
        </div>
        <div class="menuLoginPart">
        <c:choose>
            <c:when test="${param.param != null || person == null}">
                <jsp:include page="/login.jsp"/>
            </c:when>
            <c:otherwise>
               <span id="personLogin">${person.login}</span><br>
               <a href="<%=request.getContextPath()%>/logout" id="isLogin" class="menuHref">log out</a>
            </c:otherwise>
        </c:choose><BR>
        <span class="menuHref">
            <a href="?lang=en">en</a>
            |
            <a href="?lang=ru">ru</a>
        </span>
        </div>
  </div>
<div class="indentmenu">
<ul>
    <li><a href="<%=request.getContextPath()%>/" id="home">Home</a></li>
    <li><a href="<%=request.getContextPath()%>/tests" id="tests">Tests&Questions</a></li>
    <li><a href="<%=request.getContextPath()%>/ocjp-ocpjp.jsp" id="ocjp-ocpjp">OCJP/OCPJP</a></li>
    <li><a href="<%=request.getContextPath()%>/jpa" id="jpa">OCEJPAD 6</a></li>
    <li><a href="<%=request.getContextPath()%>/web-services" id="web-services">OCEJWSD 6</a></li>
    <li><a href="<%=request.getContextPath()%>/exam/java-core-russian" id="java-core-russian">Java Core</a></li>
    <c:if test="${person!=null}">
       <li><a href="<%=request.getContextPath()%>/show-user-profile">My Profile</a></li>
    </c:if>
    <c:if test="${person.sysadmin}">
            <li><a href="<%=request.getContextPath()%>/show-administration">Administration</a></li>
    </c:if>
    <li><a href="<%=request.getContextPath()%>/show-all-articles">Articles</a></li>
</ul>
</div>
</div>

