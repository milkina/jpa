<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="breadCrumbs">
<ol itemscope itemtype="http://schema.org/BreadcrumbList">
 <%@ include file="/WEB-INF/breadCrumbs/homeBreadCrumb.jsp"%>
 <li><spring:message code="articles"/></li>
 </ol>
 </div>