<%@ taglib uri="/WEB-INF/tld/testjsp-taglib.tld" prefix="test"%>
<li itemprop="itemListElement" itemscope itemtype="http://schema.org/ListItem">
    <a itemprop="item" href="<%=request.getContextPath()%>/exam/${param.TEST_PATH}">${TESTS[param.TEST_PATH].name}</a>
    <meta itemprop="position" content="2" />
</li>