<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<li itemprop="itemListElement" itemscope itemtype="http://schema.org/ListItem">
    <a itemprop="item" href="${pageContext.request.contextPath}/<spring:message code="menu.home"/>">
         <span itemprop="name"><spring:message code="home"/></span></a>
    <meta itemprop="position" content="1"/>
</li>