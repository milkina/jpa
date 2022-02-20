<%@ page trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
    <jsp:attribute name="language">lang="<spring:message code='lang'/>"</jsp:attribute>
    <jsp:attribute name="header">
        <meta name="Keywords" content="задания java, задачи java">
        <meta name="Description" content="【Практические задачи】 - ☜ⒿⒶⓋⒶ☞ для начинающих 💥Бесплатно">
        <title>Практические задачи по программированию Java для начинающих</title>
         <link rel="canonical" href="https://www.examclouds.com/ru/practicheskie-zadachi">
        <link rel="alternate" hreflang="ru" href="https://www.examclouds.com/ru/practicheskie-zadachi">
        <link rel="alternate" hreflang="en" href="https://www.examclouds.com/practicheskie-zadachi">
        <link rel="alternate" hreflang="x-default" href="https://www.examclouds.com/practicheskie-zadachi">

     <meta property="og:title" content="Практические задачи по программированию Java для начинающих"/>
     <meta property="og:type" content="article"/>
     <meta property="og:description" content="【Практические задачи】 - ☜ⒿⒶⓋⒶ☞ для начинающих 💥Бесплатно"/>
     <meta property="og:site_name" content="ExamClouds">
     <meta property="og:url" content="https://www.examclouds.com/ru/practicheskie-zadaniya">

     <meta property="twitter:title" content="Практические задачи по программированию Java для начинающих"/>
     <meta property="twitter:card" content="summary"/>
     <meta property="twitter:description" content="【Практические задачи】 - ☜ⒿⒶⓋⒶ☞ для начинающих 💥Бесплатно"/>
     <meta property="twitter:site" content="@ExamClouds">
     <meta property="og:image" content="/images/general/logo.webp"/>
     <meta property="twitter:image" content="https://www.examclouds.com/images/general/logo.webp"/>
     </jsp:attribute>
    <jsp:body>
        <%@ taglib uri="/WEB-INF/tld/cache-tagjsp-taglib.tld" prefix="cache"%>
        <cache:cacheTag/>
        <div class="breadCrumbs">
            <ol itemscope itemtype="http://schema.org/BreadcrumbList">
                <%@ include file="/WEB-INF/breadCrumbs/homeBreadCrumb.jsp"%>
                <li><spring:message code="menu.tasks.label"/></li>
            </ol>
        </div>
        <main>
            <div class="category-article">
                <h1 class="all-tests-header"><spring:message code="menu.tasks.label"/></h1>
                <ul id="categories">
                    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
                    <c:forEach var="category" items="${TESTS['java-core-russian'].categories}">
                        <c:if test="${category.value.hidden==false && category.value.name.equals('Задания')}">
                            <li class="col-xs-12 col-sm-6 col-lg-6">
                                <div>
                                    <a href="${pageContext.request.contextPath}/<spring:message code='menu.home'/>java/${'java-core-russian'}/${category.value.pathName}"
                                       id="categoryItem${category.value.pathName}" class="category-href">
                                            ${category.value.parentCategory.name}
                                    </a>
                                </div>
                                <div class="category-desc">${category.value.article.description}</div>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
        </main>
        <%@ include file="/WEB-INF/socialButtons.jsp"%>
    </jsp:body>
</t:wrapper>