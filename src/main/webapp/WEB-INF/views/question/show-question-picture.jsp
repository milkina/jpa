<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/tld/examjsp-taglib.tld" prefix="exam" %>
<%@ taglib uri="/WEB-INF/tld/canonical-jsp-taglib.tld" prefix="ca"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
<jsp:attribute name="language">lang="${TESTS[param.TEST_PATH].language.code}"</jsp:attribute>
<jsp:attribute name="header">
     <title>${QUESTION_ENTRY_ATTRIBUTE.category.parentCategory.name} ${QUESTION_ENTRY_ATTRIBUTE.category.name} - ${TESTS[TEST_PATH].name}</title>
    <META NAME="Description" CONTENT="${QUESTION_ENTRY_ATTRIBUTE.category.article.description}">
    <script async src="${pageContext.request.contextPath}/js/show_questions.js?v=4"></script>
    <script async src="${pageContext.request.contextPath}/js/prism.js?ver=1"></script>
    <link rel="canonical" href="<ca:canonicalTag/>">
</jsp:attribute>
<jsp:body>
     <div class="mainArea">
     <div class="breadCrumbs">
       <ol itemscope itemtype="http://schema.org/BreadcrumbList">
           <%@ include file="/WEB-INF/breadCrumbs/homeBreadCrumb.jsp"%>
           <li><a href="<menu:testsTag/>"><spring:message code="tests"/></a><meta itemprop="position" content="2"/></li>
           <li><span>${TESTS[param.TEST_PATH].name}</span><meta itemprop="position" content="3"/></li>
       </ol>
     </div>
         <h2 class="header2">${QUESTION_ENTRY_ATTRIBUTE.category.name}
         </h2>
         <div class="questionEntryDiv" style="padding-left:8px">
            <div class="questionEntryBody">
                 <div class="questionText" style="width:470px">${QUESTION_ENTRY_ATTRIBUTE.question.text}</div>
            </div>
          </div>
          <div class="indentmenu" style="width:472px">
            <ul>
               <li style="width:451px"><a href="${pageContext.request.contextPath}" style="width:474px;text-align:center">www.examclouds.com</a></li>
            </ul>
          </div>
      </div>
      <div>
         ${TESTS[TEST_PATH].tags}<br>
         <spring:message code="read.answer.on"/> <a href="${pageContext.request.contextPath}/show-question?QUESTION_ENTRY_ID_PARAM=${QUESTION_ENTRY_ATTRIBUTE.id}&TEST_PATH=${TEST_PATH}"
               class="showAnswer" id="readAnswer">${pageContext.request.contextPath}/show-question?QUESTION_ENTRY_ID_PARAM=${QUESTION_ENTRY_ATTRIBUTE.id}&TEST_PATH=${TEST_PATH}</a>
      </div>
    </jsp:body>
   </t:wrapper>