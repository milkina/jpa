<%@ taglib uri="/WEB-INF/tld/categoryjsp-taglib.tld" prefix="category"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="breadCrumbs">
<ol itemscope itemtype="http://schema.org/BreadcrumbList">
  <%@ include file="/WEB-INF/breadCrumbs/homeBreadCrumb.jsp" %>
  <%@ include file="/WEB-INF/breadCrumbs/testBreadCrumb.jsp" %>
  <c:set var="position" value="3"></c:set>
  <c:if test="${CATEGORY_ATTRIBUTE.parentCategory!=null}">
     <li itemprop="itemListElement" itemscope itemtype="http://schema.org/ListItem">
          &gt;
          <a itemprop="item" href="${pageContext.request.contextPath}/java/${param.TEST_PATH}/${CATEGORY_ATTRIBUTE.parentCategory.pathName}">
              ${CATEGORY_ATTRIBUTE.parentCategory.name}
          </a>
          <meta itemprop="position" content="${position}"/>
          <c:set var="position" value="${position+1}" />
     </li>
  </c:if>
  <li itemprop="itemListElement" itemscope itemtype="http://schema.org/ListItem">
      &gt;
        <a itemprop="item" href="${pageContext.request.contextPath}/java/${param.TEST_PATH}/${param.CATEGORY_PATH}">
          <span itemprop="name">${CATEGORY_ATTRIBUTE.name}</span>
        </a>
        <meta itemprop="position" content="${position}"/>
 </ol>
 </div><br>