<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/WEB-INF/tld/examjsp-taglib.tld" prefix="exam"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/canonical-jsp-taglib.tld" prefix="ca"%>
<%@taglib uri="/WEB-INF/tld/menu-jsp-taglib.tld" prefix="menu"%>
<t:wrapper>
<jsp:attribute name="language">lang="${TESTS[param.TEST_PATH].language.code}"</jsp:attribute>
<jsp:attribute name="header">
    <title>${TESTS[param.TEST_PATH].name}&nbsp;<spring:message code="questions"/></title>
    <meta name="Description" content="${CATEGORY_ATTRIBUTE.article.description}">
    <script async src="${pageContext.request.contextPath}/js/show_questions.js"></script>
    <script async src="${pageContext.request.contextPath}/js/prism.min.js?ver=1"></script>
    <link rel="canonical" href="<ca:examCanonicalTag/>">
    <style>
      .questionText:before{
        content:"<exam:number/>";
      }
      .read-answer.collapsed:after{
        content:'<spring:message code="read.answer"/>';
        font:700 19px proxima;
      }
      .read-answer:after{
        content:'<spring:message code="hide.answer"/>';
        font:700 19px proxima;
      }
    </style>
</jsp:attribute>
<jsp:body>
  <div class="breadCrumbs">
    <ol itemscope itemtype="http://schema.org/BreadcrumbList">
      <%@ include file="/WEB-INF/breadCrumbs/homeBreadCrumb.jsp"%>
      <li itemprop="itemListElement" itemscope
                               itemtype="http://schema.org/ListItem"><a itemprop="item" href="<menu:questionsTag/>">
         <span itemprop="name"><spring:message code="questions.interviews"/></span></a>
         <meta itemprop="position" content="2"/></li>
      <li>${TESTS[param.TEST_PATH].name}</li>
    </ol>
  </div>
   <main>
     <c:if test="${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.category.parentCategory!=null}">
        <h1 class="exam-header1">${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.category.parentCategory.name}</h1>
     </c:if>
     <div class="clearfix">
       <h2 class="exam-header2">${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.category.name}</h2>
       <div class="questionEntryNumber" id="questionEntryNumber"><exam:number/>/<exam:size/></div>
     </div>
     <div class="questionEntryDiv">
       <div class="questionEntryCheckboxDiv">
         <exam:checkbox/>
       </div>
       <div class="questionEntryBody">
         <div class="questionText">${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.question.text}</div>
         <a class="read-answer collapsed" role="button" data-toggle="collapse" id="a1"
          href="#answer1" aria-expanded="false" aria-controls="answer1">
              <spring:message code="read.answer"/>
          </a>
          <div class="answer collapse" id="answer1">${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.answer.text}</div>
       </div>
     </div>
     <div style="display:inline">
       <form action="${pageContext.request.contextPath}/show-exam-question?CATEGORY_PATH=${param.CATEGORY_PATH}&TEST_PATH=${param.TEST_PATH}"
          method="post">
          <div style="margin-top:50px" class="row">
             <div class="previous-exam-button col-xs-6">
                <exam:previousButton>
                <a href="${pageContext.request.contextPath}/show-exam-question?CATEGORY_PATH=${param.CATEGORY_PATH}&TEST_PATH=${param.TEST_PATH}&PREVIOUS=PREVIOUS" class="previousHref">
                   <spring:message code="previous"/>
                </a>
                </exam:previousButton>
             </div>
             <div class="next-exam-button col-xs-6">
                <exam:nextButton>
                <a href="${pageContext.request.contextPath}/show-exam-question?TEST_PATH=${param.TEST_PATH}&CATEGORY_PATH=${param.CATEGORY_PATH}&NEXT=NEXT" class="nextHref">
                  <spring:message code="next"/>
                </a>
                </exam:nextButton>
             </div>
           </div>
       </form>
     </div>
     <jsp:include page="/WEB-INF/comment/comments.jsp">
       <jsp:param name="referenceId" value="${CURRENT_EXAM_ATTRIBUTE.currentQuestionEntry.id}"/>
       <jsp:param name="commentType" value="QUESTION"/>
     </jsp:include>
     </main>
 </jsp:body>
</t:wrapper>