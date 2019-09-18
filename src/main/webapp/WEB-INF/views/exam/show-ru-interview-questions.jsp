<%@ page trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
    <jsp:attribute name="language">lang="ru"</jsp:attribute>
    <jsp:attribute name="header">
        <meta name="Keywords" content="Java вопросы на собеседовании">
        <meta name="Description" content="Онлайн подготовка к собеседованию по Java - примеры вопросов и ответов для разработчиков. Что должен знать Java программист для успешного прохождения интервью?">
        <title>Онлайн Java вопросы на собеседовании программисту на ExamClouds</title>
        <link href="${pageContext.request.contextPath}/css/multi-select_min.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/js/jquery.multi-select.js"></script>
        <style>
          .test_header>a:before{
              content:"Выбрать категории";
          }
        </style>
        <link rel="alternate" hreflang="ru" href="http://www.examclouds.com/ru/questions">
        <link rel="alternate" hreflang="en" href="http://www.examclouds.com/questions">
        <link rel="alternate" hreflang="x-default" href="http://www.examclouds.com/questions">
     </jsp:attribute>
     <jsp:body>
        <%@ taglib uri="/WEB-INF/tld/cache-tagjsp-taglib.tld" prefix="cache"%>
        <cache:cacheTag/>
        <div class="breadCrumbs">
          <ol itemscope itemtype="http://schema.org/BreadcrumbList">
            <%@ include file="/WEB-INF/breadCrumbs/homeBreadCrumb.jsp"%>
            <li>Вопросы собеседований</li>
          </ol>
        </div>
        <main>
          <div>
            <h1 class="all-questions-header">Java вопросы на собеседовании</h1>
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
                  <%@include file="/WEB-INF/views/exam/start-course-quiz.jsp"%>
              </li>
            </c:forEach>
          </ul>
          </div>
         </main>
         <script>
           $('select[multiple]').multiselect({
               columns: 2,
               placeholder: 'Выбрать категории',
               selectAll: true,
               selectGroup:true,
               search:true
            });
         </script>
         <%@ include file="/WEB-INF/socialButtons.jsp"%>
 </jsp:body>
</t:wrapper>
