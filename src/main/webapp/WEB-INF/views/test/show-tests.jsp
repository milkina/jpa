<%@ page trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
    <jsp:attribute name="language">lang="<spring:message code='lang'/>"</jsp:attribute>
    <jsp:attribute name="header">
        <meta name="Keywords" content="java online test,java online quiz,java quiz questions and answers,oracle java certification exam">
        <meta name="Description" content="【Online Tests】  - ☜ⒿⒶⓋⒶ☞ 💥Free, ‼Articles/Literature, ✅Preparation to Oracle Certifications">
        <title>Free Java Online Tests and Questions for Learning Java on ExamClouds</title>
        <link href="${pageContext.request.contextPath}/css/multi-select.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/js/jquery.multi-select.js"></script>
        <style>
          .test_header>a:before{
              content:"Select categories";
          }
        </style>
        <link rel="canonical" href="https://www.examclouds.com/tests">
        <link rel="alternate" hreflang="ru" href="https://www.examclouds.com/ru/tests">
        <link rel="alternate" hreflang="en" href="https://www.examclouds.com/tests">
        <link rel="alternate" hreflang="x-default" href="https://www.examclouds.com/tests">

     <meta property="og:title" content="Free Java Online Test and Quiz for Learning Java on ExamClouds"/>
     <meta property="og:type" content="article"/>
     <meta property="og:description" content="【Online Tests】  - ☜ⒿⒶⓋⒶ☞ 💥Free, ‼Articles/Literature, ✅Preparation to Oracle Certifications"/>
     <meta property="og:site_name" content="ExamClouds">
     <meta property="og:url" content="https://www.examclouds.com/tests">

     <meta property="twitter:title" content="Задачи по java, тесты java, онлайн задачи по программированию java для начинающих, практические тестовые задачи с ответами по Java программированию"/>
     <meta property="twitter:card" content="summary"/>
     <meta property="twitter:description" content="【Online Tests】  - ☜ⒿⒶⓋⒶ☞ 💥Free, ‼Articles/Literature, ✅Preparation to Oracle Certifications"/>
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
            <li>Tests</li>
          </ol>
        </div>
        <main>
          <div>
            <h1 class="all-tests-header">Java Tests</h1>
            <ul class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <c:forEach var="test" items="${TESTS_WITH_TESTS}">
                    <li class="panel select-category-li">
                        <div class="panel-heading" role="tab" id="heading_${test.pathName}">
                            <h2 class="panel-title test_header">
                                <a role="button" data-toggle="collapse" data-parent="#accordion"
                                 href="#collapse_${test.pathName}" class="collapsed"
                                 aria-expanded="false" aria-controls="collapse_${test.pathName}">
                                    ${test.name}
                                </a>
                            </h2>
                        </div>
                        <%@include file="/WEB-INF/views/test/start-exam.jsp"%>
                    </li>
                </c:forEach>
             </ul>
          </div>
         </main>
         <script>
           $('select[multiple]').multiselect({
               columns: 2,
               placeholder: 'Select categories',
               selectAll : true,
               selectGroup:true,
               search:true
            });
         </script>
         <%@ include file="/WEB-INF/socialButtons.jsp"%>
 </jsp:body>
</t:wrapper>
