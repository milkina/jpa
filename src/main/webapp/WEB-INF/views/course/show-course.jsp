<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:wrapper>
 <jsp:attribute name="language">lang="${TESTS[param.TEST_PATH].language.code}"</jsp:attribute>
 <jsp:attribute name="header">
     <meta name="Keywords" content="${TESTS[param.TEST_PATH].article.keywords}">
     <meta name="Description" content="${TESTS[param.TEST_PATH].article.description}">
     <title>${TESTS[param.TEST_PATH].article.title}</title>
     <style>
      <c:if test="${TESTS[param.TEST_PATH].article.image!=null && not empty TESTS[param.TEST_PATH].article.image}">
         .show-course-header:before{
           background: url('${pageContext.request.contextPath}${TESTS[param.TEST_PATH].article.image}') no-repeat;
           width: 164px
         }
      </c:if>
      .category-href:before{
        content:"<spring:message code="lesson"/> " counter(lesson)" - ";
      }
     </style>
 </jsp:attribute>
 <jsp:body>
    <%@ taglib uri="/WEB-INF/tld/cache-tagjsp-taglib.tld" prefix="cache"%>
    <cache:cacheTag/>
    <div class="breadCrumbs">
      <ol itemscope itemtype="http://schema.org/BreadcrumbList">
        <%@ include file="/WEB-INF/breadCrumbs/homeBreadCrumb.jsp"%>
        <li itemprop="itemListElement" itemscope itemtype="http://schema.org/ListItem"><span>${TESTS[param.TEST_PATH].name}</span><meta itemprop="position" content="2"/></li>
      </ol>
    </div>
    <main><article>
      <h1 class="show-course-header">${TESTS[param.TEST_PATH].name}</h1>
      <div class="course-desc">${TESTS[param.TEST_PATH].article.text}</div>
      <jsp:include page="/WEB-INF/views/category/category-menu-center.jsp"/>
    </article></main>
    <%@ include file="/WEB-INF/socialButtons.jsp"%>
    <jsp:include page="/WEB-INF/comment/comments.jsp">
       <jsp:param name="referenceId" value="${TESTS[param.TEST_PATH].id}"/>
       <jsp:param name="commentType" value="TEST"/>
    </jsp:include>
 </jsp:body>
</t:wrapper>