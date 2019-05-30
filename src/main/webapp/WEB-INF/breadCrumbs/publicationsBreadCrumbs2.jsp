<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="breadCrumbs">
<ol itemscope itemtype="http://schema.org/BreadcrumbList">
 <%@ include file="/WEB-INF/breadCrumbs/homeBreadCrumb.jsp" %>
 <li itemprop="itemListElement" itemscope
      itemtype="http://schema.org/ListItem"><spring:message code="articles"/><meta itemprop="position" content="2"/></li>
 </ol>
 </div>