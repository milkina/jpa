<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="breadCrumbs">
<ol itemscope itemtype="http://schema.org/BreadcrumbList">
 <%@ include file="/WEB-INF/breadCrumbs/homeBreadCrumb.jsp" %>
 <li itemprop="itemListElement" itemscope
      itemtype="http://schema.org/ListItem">
    &gt;
    <a itemprop="item" href="${pageContext.request.contextPath}/show-all-articles">
      <span itemprop="name"><spring:message code="articles"/></span></a>
    <meta itemprop="position" content="2" />
  </li>
 </ol>
 </div>