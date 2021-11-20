<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="/WEB-INF/tld/examjsp-taglib.tld" prefix="exam"%>
<%@ taglib uri="/WEB-INF/tld/canonical-jsp-taglib.tld" prefix="ca"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="/WEB-INF/tld/menu-jsp-taglib.tld" prefix="menu"%>
<t:wrapper>
<jsp:attribute name="language">lang="${TESTS[param.TEST_PATH].language.code}"</jsp:attribute>
<jsp:attribute name="header">
     <title>${QUESTION_ENTRY_ATTRIBUTE.category.parentCategory.name} ${QUESTION_ENTRY_ATTRIBUTE.category.name} - ${TESTS[TEST_PATH].name}</title>
    <meta name="Description" content="${QUESTION_ENTRY_ATTRIBUTE.category.article.description}">
    <script async src="${pageContext.request.contextPath}/js/show_questions.js?v=4"></script>
    <script async src="${pageContext.request.contextPath}/js/prism.min.js?ver=1"></script>
    <link rel="canonical" href="<ca:canonicalTag/>">
</jsp:attribute>
<jsp:body>
     <div class="breadCrumbs">
       <ol itemscope itemtype="http://schema.org/BreadcrumbList">
           <%@ include file="/WEB-INF/breadCrumbs/homeBreadCrumb.jsp"%>
           <li itemprop="itemListElement" itemscope
                     itemtype="http://schema.org/ListItem">
                     <a itemprop="item" href="<menu:testsTag/>"><span itemprop="name"><spring:message code="tests.questions"/></span></a>
                     <meta itemprop="position" content="2"/>
           </li>
           <li>${TESTS[param.TEST_PATH].name}</li>
       </ol>
     </div>
         <h2 class="header2">${QUESTION_ENTRY_ATTRIBUTE.category.name}
         </h2>
         <div class="questionEntryDiv questionEntryDivPicture">
            <div class="questionEntryBody">
                 <div class="questionText">${QUESTION_ENTRY_ATTRIBUTE.question.text}</div>
                 <c:if test="${QUESTION_ENTRY_ATTRIBUTE.type=='TEST'}">
                   <c:set var="count" value="${0}"/>
                   <ul id="answersDiv">
                     <c:forEach var="answer" items="${QUESTION_ENTRY_ATTRIBUTE.answers}">
                         <li id="answerblock${count}">
                            <input type="checkbox" disabled class="test-checkbox" name = "checkbox${count}" id = "checkbox${count}">
                            <label class="answerDiv" for="checkbox${count}">${answer.text}</label>
                            <c:set var="count" value="${count+1}" />
                         </li>
                     </c:forEach>
                   </ul>
                 </c:if>
            </div>
          </div>
          <div>
            <a href="${pageContext.request.contextPath}" class="examclouds-label">www.examclouds.com</a>
          </div>
      <div>
         ${TESTS[TEST_PATH].tags}
         <spring:message code="read.answer.on"/>
         <a href="${pageContext.request.contextPath}/show-question?QUESTION_ENTRY_ID_PARAM=${QUESTION_ENTRY_ATTRIBUTE.id}&TEST_PATH=${TEST_PATH}"
          id="readAnswer" class="picture-wrap-href">
            ${pageContext.request.contextPath}/show-question?QUESTION_ENTRY_ID_PARAM=${QUESTION_ENTRY_ATTRIBUTE.id}&TEST_PATH=${TEST_PATH}</a>
      </div>
    </jsp:body>
   </t:wrapper>