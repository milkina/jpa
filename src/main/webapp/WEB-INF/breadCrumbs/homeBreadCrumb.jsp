<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<li itemprop="itemListElement" itemscope itemtype="http://schema.org/ListItem">
    <a itemprop="item" href="${pageContext.request.contextPath}/<spring:message code="menu.home"/>">
        <spring:message code="home"/></a>
    <meta itemprop="position" content="1" />
</li>