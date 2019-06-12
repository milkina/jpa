<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="/WEB-INF/tld/menu-jsp-taglib.tld" prefix="menu"%>
<div class="navbar">
     	<div class="container">
     	     	    <div class="navbar-header">
     	     	          <button class="hamburger hamburger--spin navbar-toggle collapsed" type="button"  id="button-bar" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                           <span class="sr-only">Toggle navigation</span>
                           <span class="hamburger-box">
                           <span class="hamburger-inner"></span>
                           </span>
                          </button>
                           <a href="<menu:homeTag/>" id="home" class="logo">
                               <img src="${pageContext.request.contextPath}/images/logo.png" alt="ExamClouds">
                           </a>
                    </div>
     	  <div class="collapse navbar-collapse navbar-nav" id="bs-example-navbar-collapse-1">
			  <div class="col-xs-3 col-sm-2 col-lg-1 flags navbar-right">
                            <ul>
                              <li><a href="?lang=ru" id="ru-flag"></a></li>
                              <li><a href="?lang=en" id="en-flag"></a></li>
                            </ul>
              </div>
				 <c:choose>
                   <c:when test="${param.param != null || person == null}">
                    <div class="col-xs-9 col-sm-2 col-lg-2 navbar-right menuItem">
				      <a class="topMenu" href="${pageContext.request.contextPath}/show-login-page" id='my-profile'>
				        <spring:message code="log.in"/>
				      </a>
	                </div>
				   </c:when>
                   <c:otherwise>
                    <div class="col-xs-9 col-sm-2 col-lg-2 topMenu menuItemWithSub dropdown navbar-right">
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
                               <nav class="col-xs-12 col-md-6 topMenu nav navbar-nav pull-right">
                               <ul class="row-no-gutters">
                                <li class="dropdown col-xs-3 col-md-4 col-lg-4 menuItemWithSub">
                                 <a href="#" id="courses" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                 				    <spring:message code="courses"/>
                 				 </a>
                                 <ul class="dropdown-menu" aria-labelledby="courses">
                                      <c:forEach var="test" items="${TESTS}">
                                         <li><a href="${pageContext.request.contextPath}/${test.value.fullPathName}" id="${test.value.pathName}">
                                                            ${test.value.name}</a></li>
                                      </c:forEach>
                                  </ul>
                                  </li>
                 				 <li class="col-xs-6 col-md-4 col-lg-4 menuItem"><a href="<menu:testsTag/>" id="tests"><spring:message code="tests.questions"/></a></li>
                 				 <li class="col-xs-3 col-md-3 col-lg-3 menuItem"><a href="<menu:articlesTag/>"><spring:message code="articles"/></a></li>
                 				</ul>
                 			  </nav>
          </div>
       </div>
</div>
<script>
$(document).ready(function(){
     $('.menuItemWithSub').hover(function(){
        if($(window).width()<768){
           return;
        }
        if($(this).hasClass('open')){
            $(this).removeClass('open');
            $(this).find(">a").attr( "aria-expanded", "false" );

        }else{
            $(this).addClass('open');
            $(this).find(">a").attr( "aria-expanded", "true" );
        }
     });
});
$(document).ready(function(){
          $('#button-bar').click(function(){
             if($(this).hasClass('is-active')){
                 $(this).removeClass('is-active');
             }else{
                 $(this).addClass('is-active');
             }
          });
});
</script>