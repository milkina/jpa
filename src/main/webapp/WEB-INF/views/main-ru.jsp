<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/cache-tagjsp-taglib.tld" prefix="cache"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="/WEB-INF/tld/menu-jsp-taglib.tld" prefix="menu"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <%@ include file="/WEB-INF/head_common.jsp" %>
	<title>Бесплатные онлайн курсы по Java программированию</title>
	<meta name="Description" content="Бесплатные онлайн курсы, тесты и вопросы по изучению Java программирования и подготовке к Oracle Java сертификациям и собеседованиям.">
	<link rel="alternate" hreflang="ru" href="http://www.examclouds.com/ru/">
    <link rel="alternate" hreflang="en" href="http://www.examclouds.com/">
    <link rel="alternate" hreflang="x-default" href="http://www.examclouds.com/">
    <style>
      .lessons-list>li>h3:before{
        content:'<spring:message code="lesson"/> ' counter(lesson) ' - ';
      }
    </style>
</head>
<body itemscope itemtype="http://schema.org/WebPage" class="scroll-style">
<cache:cacheTag/>
     <div class="container-fluid menu indexMenu round-border-bottom">
      <%@ include file="/menu.jsp" %>
      <div class="container">
         <div class="row-no-gutters index-img-div">
             <div class="col-xs-12 col-md-4">
                <h1>Бесплатное обучение по Java</h1>
                <h2>Начинаете изучать язык Java? Вам на ExamClouds</h2>
                 <c:if test="${param.param != null || person == null}">
                   <a class="start-learning" href="${pageContext.request.contextPath}/show-login-page">Начать обучение</a>
                 </c:if>
             </div>
             <div class="hidden-xs hidden-sm col-md-8 computer-img"></div>
         </div>
      </div>
    </div>
    <div class="container" style="padding-bottom:142px">
     	    <main>
            <ul class="row index-items">
              <li class="index-item index-item1 col-xs-12 col-sm-6 col-md-3">
                <h3 class="index-image-item1">Тесты и ответы на вопросы</h3>
                <div class="index-items-text">После каждого урока можно пройти тест на проверку полученных знаний и узнать ответ на оставшийся вопрос.</div>
              </li>
              <li class="index-item index-item2 col-xs-12 col-sm-6 col-md-3">
                <h3 class="index-image-item2">Подготовка к сертификации</h3>
                <div class="index-items-text">Java SE 8 Programmer I, Java SE 8 Programmer II</div>
              </li>
              <li class="clearfix visible-sm-block"></li>
              <li class="index-item index-item3 col-xs-12 col-sm-6 col-md-3">
                <h3 class="index-image-item3">Статьи и литература</h3>
                <div class="index-items-text">Статьи и литература по Java для начинающих</div>
              </li>
             <li class="index-item index-item4 col-xs-12 col-sm-6 col-md-3">
                 <h3 class="index-image-item4">Полный список лекций</h3>
                <div class="index-items-text">Полный список лекций по изучению Java Core</div>
              </li>
            </ul>
            <ul class="lessons-list scroll-style">
               <c:set var="count" value="${1}" />
               <c:forEach var="category" items="${TESTS['java-core-russian'].categories}">
                  <c:if test="${category.value.hidden==false && category.value.parentCategory==null}">
                    <li>
                       <a href="${pageContext.request.contextPath}/java/java-core-russian/${category.value.pathName}"
                        class="lesson-icon${count%3}"></a>
                       <h3>${category.value.name}</h3>
                       <c:set var="count" value="${count+1}" />
                       <div>${category.value.article.description}</div>
                       <a href="${pageContext.request.contextPath}/java/java-core-russian/${category.value.pathName}">Полный урок</a>
                    </li>
                  </c:if>
               </c:forEach>
            </ul>
            <div class="row learn-java">
              <h3 class="col-xs-12">Зачем изучать Java?</h3>
              <p class="col-xs-12 col-sm-offset-2 col-sm-8 col-md-offset-3 col-md-6 learn-java-text">В этом вопросе лучше довериться профессионалам! Ниже приводим обоснованное мнение о Java одного из таких профессионалов — Коди Вебера (Cody Weber). Он Software Engineer в американской компании Centene.</p>
            </div>
            <ul class="pig-globe row">
                <li class="learn-java-item index-image-pig col-xs-12 col-md-4"><h4>Высокая средняя зарплата</h4></li>
                <li class="learn-java-item index-image-flags col-xs-12 col-md-4"><h4>Шикарные перспективы</h4></li>
                <li class="learn-java-item index-image-globe col-xs-12 col-md-4"><h4>Java‑программисты нужны в каждой стране мира</h4></li>
            </ul>
           </main>
           <%@ include file="/WEB-INF/socialButtons.jsp"%>
           <jsp:include page="/WEB-INF/comment/comments.jsp">
                <jsp:param name="referenceId" value="398" />
                <jsp:param name="commentType" value="ARTICLE" />
           </jsp:include>
     </div>
    <%@include file="/WEB-INF/footer.jsp"%>
</body>
</html>