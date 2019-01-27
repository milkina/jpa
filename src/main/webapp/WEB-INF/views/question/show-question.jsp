<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/tld/examjsp-taglib.tld" prefix="exam" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper3>
<jsp:attribute name="header">
    <title>${QUESTION_ENTRY_ATTRIBUTE.category.parentCategory.name} ${QUESTION_ENTRY_ATTRIBUTE.category.name} - ${TESTS[param.TEST_PATH].name}</title>
    <META NAME="Description" CONTENT="${QUESTION_ENTRY_ATTRIBUTE.category.article.description}">
    <script type="text/javascript" async src="${pageContext.request.contextPath}/js/show_questions.js?v=4"></script>
    <script type="text/javascript" async src="${pageContext.request.contextPath}/js/prism.js?ver=1"></script>
    <c:choose>
       <c:when test="${DUPLICATE_CATEGORIES[QUESTION_ENTRY_ATTRIBUTE.category.pathName]!=null && DUPLICATE_CATEGORIES[QUESTION_ENTRY_ATTRIBUTE.category.pathName].tests[0].pathName!=param.TEST_PATH}">
           <link rel="canonical"
                href="http://www.examclouds.com/java/${DUPLICATE_CATEGORIES[QUESTION_ENTRY_ATTRIBUTE.category.pathName].tests[0].pathName}/${QUESTION_ENTRY_ATTRIBUTE.category.pathName}" />
       </c:when>
       <c:otherwise>
            <link rel="canonical" href="http://www.examclouds.com/java/${param.TEST_PATH}/${QUESTION_ENTRY_ATTRIBUTE.category.pathName}" />
       </c:otherwise>
    </c:choose>
</jsp:attribute>
<jsp:attribute name="left">
       <jsp:include page="/WEB-INF/categoryMenu.jsp">
                  <jsp:param name="TEST_PATH" value="${param.TEST_PATH}" />
       </jsp:include>
</jsp:attribute>
<jsp:body>
     <div class="mainArea">
          <jsp:include page="/WEB-INF/breadCrumbs/breadCrumbs3.jsp">
            <jsp:param name="TEST_PATH" value="${param.TEST_PATH}" />
            <jsp:param name="CATEGORY_PATH" value="${QUESTION_ENTRY_ATTRIBUTE.category.pathName}" />
          </jsp:include>
        <h2 class="header2">${QUESTION_ENTRY_ATTRIBUTE.category.name}
        </h2>
         <div class="questionEntryDiv">
              <div class="questionEntryBody">
                       <div class="questionText">${QUESTION_ENTRY_ATTRIBUTE.question.text}</div>
                       <input type="button" value="<spring:message code="read.answer"/>"
                       onclick="showAnswer('1','<spring:message code="read.answer"/>','<spring:message code="hide.answer"/>');" id="a1">
                       <c:set var="count" value="${1}"/>
                       <div class="answer" id="answer1">
                          <c:forEach var="answer" items="${QUESTION_ENTRY_ATTRIBUTE.answers}">
                             <div><input type="checkbox" name = "checkbox${count}"
                               <c:if test="${answer.correct==true}">checked</c:if>>${answer.text}
                             </div>
                             <c:set var="count" value="${count+1}" />
                          </c:forEach>
                       </div>
               </div>
         </div><BR>
          <a href="${pageContext.request.contextPath}/see-questions?CATEGORY_PATH=${QUESTION_ENTRY_ATTRIBUTE.category.pathName}&TEST_PATH=${param.TEST_PATH}"
          name="seeOtherQuestions">
          <spring:message code="see.other.questions"/></a><BR>
              <jsp:include page="/WEB-INF/comment/comments.jsp">
                    <jsp:param name="referenceId" value="${QUESTION_ENTRY_ATTRIBUTE.id}" />
                    <jsp:param name="commentType" value="QUESTION" />
              </jsp:include>
     </div>

 </jsp:body>
</t:wrapper3>