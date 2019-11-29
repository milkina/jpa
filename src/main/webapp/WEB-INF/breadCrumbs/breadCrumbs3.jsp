<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="/WEB-INF/tld/categoryjsp-taglib.tld" prefix="category"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="breadCrumbs">
<ol itemscope itemtype="http://schema.org/BreadcrumbList">
  <%@ include file="/WEB-INF/breadCrumbs/homeBreadCrumb.jsp"%>
  <%@ include file="/WEB-INF/breadCrumbs/testBreadCrumb.jsp"%>
  <c:set var="position" value="3"></c:set>
  <c:if test="${CATEGORY_ATTRIBUTE.parentCategory!=null}">
     <li itemprop="itemListElement" itemscope itemtype="http://schema.org/ListItem">
          <a itemprop="item" href="${pageContext.request.contextPath}/<spring:message code='menu.home'/>java/${param.TEST_PATH}/${CATEGORY_ATTRIBUTE.parentCategory.pathName}">
               <span itemprop="name">${CATEGORY_ATTRIBUTE.parentCategory.name}</span>
          </a>
          <meta itemprop="position" content="${position}"/>
          <c:set var="position" value="${position+1}" />
     </li>
  </c:if>
  <li>${CATEGORY_ATTRIBUTE.name}</li>
 </ol>
 </div>