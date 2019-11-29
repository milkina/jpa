<%@ taglib uri="/WEB-INF/tld/testjsp-taglib.tld" prefix="test"%>
<li itemprop="itemListElement" itemscope itemtype="http://schema.org/ListItem">
    <a itemprop="item" href="<%=request.getContextPath()%>/<spring:message code='menu.home'/>${TESTS[param.TEST_PATH].fullPathName}">
      <span itemprop="name">${TESTS[param.TEST_PATH].name}</span></a>
    <meta itemprop="position" content="2" />
</li>