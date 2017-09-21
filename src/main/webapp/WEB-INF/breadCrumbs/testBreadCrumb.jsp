<%@ taglib uri="/WEB-INF/tld/testjsp-taglib.tld" prefix="test"%>
<li itemprop="itemListElement" itemscope
      itemtype="http://schema.org/ListItem">
    &gt;
    <a itemprop="item" href="<%=request.getContextPath()%>/exam/${param.TEST_PATH}">
      <span itemprop="name">${TESTS[param.TEST_PATH].name}</span>
    </a>
    <meta itemprop="position" content="2" />
</li>