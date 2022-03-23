<%@page trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="/WEB-INF/tld/menu-jsp-taglib.tld" prefix="menu"%>
<div class="navbar">
  <div class="container">
     <div class="navbar-header">
        <button class="hamburger hamburger--spin navbar-toggle collapsed" type="button" id="button-bar" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
           <span class="sr-only">Toggle navigation</span>
           <span class="hamburger-box">
             <span class="hamburger-inner"></span>
           </span>
        </button>
        <a href="<menu:homeTag/>" id="home" class="logo">
          <img src="${pageContext.request.contextPath}/images/general/logo.webp" alt="ExamClouds">
        </a>
     </div>
     <div class="collapse navbar-collapse navbar-nav" id="bs-example-navbar-collapse-1">
	    <div class="col-xs-3 col-sm-2 col-lg-1 flags navbar-right">
           <ul>
             <li>
                 <form method="post" action="<menu:languageRuTag/>">
                   <input type="hidden" name="lang" value="ru">
                   <button type="submit"><spring:message code="language.ru"/></button>
                 </form>
             </li>
             <li>
                 <form method="post" action="<menu:languageEnTag/>">
                    <input type="hidden" name="lang" value="en">
                    <button type="submit"><spring:message code="language.en"/></button>
                 </form>
             </li>
           </ul>
        </div>
		<c:choose>
          <c:when test="${param.param != null || person == null}">
             <div class="col-xs-9 col-sm-2 col-lg-2 topMenu navbar-right menuItem">
		       <a class="topMenu" href="${pageContext.request.contextPath}/show-login-page" id='my-profile'>
		         <spring:message code="log.in"/>
		       </a>
	         </div>
		  </c:when>
          <c:otherwise>
            <div class="col-xs-9 col-sm-2 col-lg-2 topMenu menuItemWithSub dropdown navbar-right glyphicon glyphicon-user">
              <a href="#" id="userLogin" class="user-login dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                ${person.login}
              </a>
              <ul class="dropdown-menu" aria-labelledby="userLogin">
                  <c:if test="${person.sysadmin}">
                      <li><a href="<%=request.getContextPath()%>/show-administration">
                                 <spring:message code="administration.panel"/></a></li>
                  </c:if>
                  <li><a href="${pageContext.request.contextPath}/show-user-profile">
                          <spring:message code="my.profile.label"/>
                       </a>
                  </li>
                  <li><a href="${pageContext.request.contextPath}/logout" id="isLogin"><spring:message code="logout"/></a></li>
              </ul>
            </div>
          </c:otherwise>
        </c:choose>
        <nav class="col-xs-12 col-md-8 topMenu nav navbar-nav">
           <ul class="row-no-gutters">
             <li class="dropdown menuItemWithSub">
                <a href="#" id="courses" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          	    <spring:message code="courses"/>
             	 </a>
                 <ul class="dropdown-menu" aria-labelledby="courses">
                    <c:forEach var="test" items="${TESTS}">
                        <li><a href="${pageContext.request.contextPath}/<spring:message code="menu.home"/>${test.value.fullPathName}" id="${test.value.pathName}">
                            ${test.value.name}</a></li>
                    </c:forEach>
                 </ul>
             </li>
             <li class="menuItem"><a href="<menu:testsTag/>" id="tests"><spring:message code="tests"/></a></li>
             <li class="menuItem"><a href="<menu:questionsTag/>" id="questions"><spring:message code="questions.interviews"/></a></li>
             <li class="menuItem"><a href="<menu:tasksTag/>" id="tasks"><spring:message code="menu.tasks.label"/></a></li>
             <li class="menuItem"><a href="<menu:articlesTag/>"><spring:message code="articles"/></a></li>
           </ul>
        </nav>
     </div>
  </div>
</div>