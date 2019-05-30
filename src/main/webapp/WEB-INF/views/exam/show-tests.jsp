<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
    <jsp:attribute name="language"> lang="<spring:message code="lang"/>"</jsp:attribute>
    <jsp:attribute name="header">
        <META NAME="Description"
                       CONTENT="<spring:message code="tests.description"/>">
        <title><spring:message code="tests.questions"/> | ExamClouds</title>
        <link href="${pageContext.request.contextPath}/css/multi-select.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/js/incrementing.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.multi-select.js"></script>
        <style>
          .test_header:after{
                    content:"<spring:message code="select.categories"/>";
          }
        </style>
        <link rel="alternate" hreflang="ru" href="http://www.examclouds.com/ru/tests">
        <link rel="alternate" hreflang="en" href="http://www.examclouds.com/tests">
        <link rel="alternate" hreflang="x-default" href="http://www.examclouds.com/tests">
     </jsp:attribute>
     <jsp:body>
        <%@ taglib uri="/WEB-INF/tld/cache-tagjsp-taglib.tld" prefix="cache"%>
        <cache:cacheTag/>
        <div class="breadCrumbs">
          <ol itemscope itemtype="http://schema.org/BreadcrumbList">
            <%@ include file="/WEB-INF/breadCrumbs/homeBreadCrumb.jsp"%>
            <li><spring:message code="tests.questions"/></li>
          </ol>
        </div>
         <main>
          <div>
            <h1 class="all-tests-header"><spring:message code="tests"/></h1>
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
                        <%@include file="/WEB-INF/views/exam/start-exam.jsp"%>
                    </li>
                </c:forEach>
             </ul>
          </div>
          <div>
            <h1 class="all-questions-header"><spring:message code="questions"/></h1>
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
                         placeholder: '<spring:message code="select.categories"/>',
                         selectAll : true,
                         selectGroup:true,
                         search:true
                     });
         </script>
         <%@ include file="/WEB-INF/socialButtons.jsp" %>
 </jsp:body>
</t:wrapper>
