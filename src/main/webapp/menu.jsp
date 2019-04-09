<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="menuDiv">
  <div style="display:inline-block;width:100%">
        <div id="sprite-examclouds" class="menuImageDiv"></div>
        <div style="float:left;">
        <aside>
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
               <a href="<%=request.getContextPath()%>/logout" id="isLogin" class="menuHref">
                  <spring:message code="logout"/></a>
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
    <li><a href="<%=request.getContextPath()%><spring:message code="menu.home"/>" id="home"><spring:message code="home"/></a></li>
    <li><a href="<%=request.getContextPath()%>/show-all-courses" id="courses"><spring:message code="courses"/></a></li>
    <li><a href="<%=request.getContextPath()%>/<spring:message code="menu.tests"/>" id="tests"><spring:message code="tests.questions"/></a></li>
    <li><a href="<%=request.getContextPath()%>/show-all-articles"><spring:message code="articles"/></a></li>
    <c:if test="${person!=null}">
       <li><a href="<%=request.getContextPath()%>/show-user-profile"><spring:message code="my.profile.label"/></a></li>
    </c:if>
    <c:if test="${person.sysadmin}">
            <li><a href="<%=request.getContextPath()%>/show-administration">
            <spring:message code="administration.panel"/></a></li>
    </c:if>
</ul>
</div>
</div>

