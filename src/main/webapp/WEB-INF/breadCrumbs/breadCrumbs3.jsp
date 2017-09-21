<%@ taglib uri="/WEB-INF/tld/categoryjsp-taglib.tld" prefix="category"%>
<div class="breadCrumbs">
<ol itemscope itemtype="http://schema.org/BreadcrumbList">
  <%@ include file="/WEB-INF/breadCrumbs/homeBreadCrumb.jsp" %>
  <%@ include file="/WEB-INF/breadCrumbs/testBreadCrumb.jsp" %>
  <li itemprop="itemListElement" itemscope
        itemtype="http://schema.org/ListItem">
      &gt;
      <category:category categoryPath="${param.CATEGORY_PATH}">
        <category:parentCategory/>
        <a itemprop="item" href="${pageContext.request.contextPath}/java/${param.TEST_PATH}/${param.CATEGORY_PATH}">
          <span itemprop="name"><category:name/></span>
        </a>
      </category:category>
      <meta itemprop="position" content="3"/>
    </li>
 </ol>
 </div><br>