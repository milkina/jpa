<%@ page trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
    <jsp:attribute name="language">lang="<spring:message code='lang'/>"</jsp:attribute>
    <jsp:attribute name="header">
        <meta name="Keywords" content="java certification questions,java interview preparation,java interview questions,java interview questions and answers">
        <meta name="Description" content="【Questions and Answers】  - ☜ⒿⒶⓋⒶ☞ 💥Free, ‼Articles/Literature, ✅Preparation to Oracle Certifications">
        <title>Preparation to Oracle Java Certification, Interview Questions and Answers</title>
        <link href="${pageContext.request.contextPath}/css/multi-select.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/js/jquery.multi-select.js"></script>
        <style>
          .test_header>a:before{
              content:"Select categories";
          }
        </style>
        <link rel="canonical" href="https://www.examclouds.com/questions">
        <link rel="alternate" hreflang="ru" href="https://www.examclouds.com/ru/questions">
        <link rel="alternate" hreflang="en" href="https://www.examclouds.com/questions">
        <link rel="alternate" hreflang="x-default" href="https://www.examclouds.com/questions">

      <meta property="og:title" content="Preparation to Oracle Java Certification, Interview Questions and Answers"/>
      <meta property="og:type" content="article"/>
      <meta property="og:description" content="【Questions and Answers】  - ☜ⒿⒶⓋⒶ☞ 💥Free, ‼Articles/Literature, ✅Preparation to Oracle Certifications"/>
      <meta property="og:site_name" content="ExamClouds">
      <meta property="og:url" content="https://www.examclouds.com/questions">

      <meta property="twitter:title" content="Preparation to Oracle Java Certification, Interview Questions and Answers"/>
      <meta property="twitter:card" content="summary"/>
      <meta property="twitter:description" content="【Questions and Answers】  - ☜ⒿⒶⓋⒶ☞ 💥Free, ‼Articles/Literature, ✅Preparation to Oracle Certifications"/>
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
            <li>Interview Questions</li>
          </ol>
        </div>
        <main>
          <div>
            <h1 class="all-questions-header">Java Interview Questions</h1>
            <ul class="panel-group" id="accordion1" role="tablist" aria-multiselectable="true">
             <c:forEach var="test" items="${COURSES_WITH_QUESTIONS}">
              <li class="panel select-category-li">
                <div class="panel-heading" role="tab" id="heading_q_${test.pathName}">
                  <h2 class="panel-title test_header">
                      <a role="button" data-toggle="collapse" data-parent="#accordion1"
                           href="#collapse_q_${test.pathName}" class="collapsed"
                           aria-expanded="false" aria-controls="collapse_q_${test.pathName}">
                               ${test.name}
                      </a>
                  </h2>
                  </div>
                  <%@include file="/WEB-INF/views/test/start-course-quiz.jsp"%>
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
