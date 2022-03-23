<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@taglib uri="/WEB-INF/tld/cache-tagjsp-taglib.tld" prefix="cache"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="/WEB-INF/tld/menu-jsp-taglib.tld" prefix="menu"%>
<!DOCTYPE html>
<html lang="en">
  <head>
        <%@ include file="/WEB-INF/head_common.jsp"%>
        <meta name="Description" content="【Tutorial Java】  - ☜ⒿⒶⓋⒶ☞ 💥Free, ‼Articles/Literature/Tests, ✅Learn Java">
        <title>Free Java Tutorial on ExamClouds</title>
        <link rel="alternate" hreflang="ru" href="https://www.examclouds.com/ru/">
        <link rel="alternate" hreflang="en" href="https://www.examclouds.com">
        <link rel="alternate" hreflang="x-default" href="https://www.examclouds.com">
        <link rel="canonical" href="https://www.examclouds.com">
        <meta property="og:title" content="Free Java Tutorial on ExamClouds"/>
        <meta property="og:type" content="article"/>
        <meta property="og:description" content="【Tutorial Java】  - ☜ⒿⒶⓋⒶ☞ 💥Free, ‼Articles/Literature/Tests, ✅Learn Java"/>
        <meta property="og:site_name" content="ExamClouds">
        <meta property="og:url" content="https://www.examclouds.com">

        <meta property="twitter:title" content="Free Java Tutorial on ExamClouds">
        <meta property="twitter:card" content="summary">
        <meta property="twitter:description" content="【Tutorial Java】  - ☜ⒿⒶⓋⒶ☞ 💥Free, ‼Articles/Literature/Tests, ✅Learn Java">
        <meta property="twitter:site" content="@ExamClouds">
        <meta property="og:image" content="/images/general/logo.webp">
        <meta property="twitter:image" content="https://www.examclouds.com/images/general/logo.webp">
        <style>
              .lessons-list>li>h3:before{
                content:'<spring:message code="lesson"/> ' counter(lesson) ' - ';
              }
        </style>
        <meta name="google-site-verification" content="WTpUYaoC4-6-_VPl3kwMu6auSphdQoEI6K1gvJ2Vp3o"/>
  </head>
    <body itemscope itemtype="http://schema.org/WebPage" class="scroll-style">
    <cache:cacheTag/>
         <div class="container-fluid menu indexMenu round-border-bottom">
          <%@ include file="/menu.jsp" %>
          <div class="container">
             <div class="row-no-gutters index-img-div">
                 <div class="col-xs-12 col-md-4">
                    <h1>Free Java Tutorial</h1>
                    <h2>Start learning Java? Welcome to ExamClouds</h2>
                 </div>
                 <div class="hidden-xs hidden-sm col-md-8 computer-img"></div>
             </div>
          </div>
        </div>
        <div class="container about-items">
         	    <main>
                <ul class="row index-items">
                  <li class="index-item index-item1 col-xs-12 col-sm-6 col-md-3">
                    <h3 class="index-image-item1">Tests and Answers to Questions</h3>
                    <div class="index-items-text">After each lesson you can pass tests to verify what you have learned and to answer to the questions left.</div>
                  </li>
                  <li class="index-item index-item2 col-xs-12 col-sm-6 col-md-3">
                    <h3 class="index-image-item2">Oracle Java Certifications</h3>
                    <div class="index-items-text">Oracle Corporation has a range of internationally recognized exams for IT specialists.</div>
                  </li>
                  <li class="clearfix visible-sm-block"></li>
                  <li class="index-item index-item3 col-xs-12 col-sm-6 col-md-3">
                    <h3 class="index-image-item3">Articles and Literature</h3>
                    <div class="index-items-text">Articles and literature for learning Java language.</div>
                  </li>
                 <li class="index-item index-item4 col-xs-12 col-sm-6 col-md-3">
                     <h3 class="index-image-item4">Web Services and JPA</h3>
                    <div class="index-items-text">Articles to learn Web Services and Java Persistence API.</div>
                  </li>
                </ul>
                <div class="lessons-list scroll-style">
                   <c:set var="count" value="${1}"/>
                   <c:forEach var="category" items="${TESTS['ocpjp8'].categories}">
                      <c:if test="${category.value.hidden==false && category.value.parentCategory==null}">
                        <div>
                           <a href="${pageContext.request.contextPath}/java/ocpjp8/${category.value.pathName}"
                            class="lesson-icon${count%3}"></a>
                           <h5>${category.value.name}</h5>
                           <c:set var="count" value="${count+1}"/>
                           <div>${category.value.article.description}</div>
                           <a href="${pageContext.request.contextPath}/java/ocpjp8/${category.value.pathName}">Full lesson</a>
                        </div>
                      </c:if>
                   </c:forEach>
                </div>
                <div class="row learn-java">
                  <h3 class="col-xs-12">Why should I learn Java?</h3>
                  <p class="col-xs-12 col-sm-offset-2 col-sm-8 col-md-offset-3 col-md-6 learn-java-text">Java has a rich API, which allows to solve different problems, but still is easy in learning. It has a lot of forums, resources and still is growing.</p>
                </div>
                <ul class="pig-globe row">
                    <li class="learn-java-item index-image-pig col-xs-12 col-md-4"><h4>High average salary</h4></li>
                    <li class="learn-java-item index-image-flags col-xs-12 col-md-4"><h4>Great possibilities</h4></li>
                    <li class="learn-java-item index-image-globe col-xs-12 col-md-4"><h4>Universal usage</h4></li>
                </ul>
               </main>
               <%@ include file="/WEB-INF/socialButtons.jsp"%>
               <jsp:include page="/WEB-INF/comment/comments.jsp">
                    <jsp:param name="referenceId" value="1"/>
                    <jsp:param name="commentType" value="ARTICLE"/>
               </jsp:include>
         </div>
        <%@include file="/WEB-INF/footer.jsp"%>
    </body>
    </html>