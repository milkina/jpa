<%@ page trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
    <jsp:attribute name="language"> lang="<spring:message code="lang"/>"</jsp:attribute>
    <jsp:attribute name="header">
        <meta name="Keywords" content="<spring:message code="tests.keywords"/>">
        <meta name="Description" content="<spring:message code="tests.description"/>">
        <title><spring:message code="tests.title"/></title>
        <link href="${pageContext.request.contextPath}/css/multi-select_min.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/js/jquery.multi-select.js"></script>
        <style>
          .test_header>a:before{
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
            <li><spring:message code="tests"/></li>
          </ol>
        </div>
        <main>
          <div>
            <h1 class="all-tests-header"><spring:message code="tests.header"/></h1>
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
         <%@ include file="/WEB-INF/socialButtons.jsp"%>
 </jsp:body>
</t:wrapper>
