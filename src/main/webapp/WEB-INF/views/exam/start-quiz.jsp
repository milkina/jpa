<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/categoryjsp-taglib.tld" prefix="category"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<t:wrapper>
    <jsp:attribute name="header">
                <category:category categoryPath="${param.CATEGORY_PATH}">
                <title>
                    <category:parentCategory/> <category:name/> - ${TESTS[param.TEST_PATH].name}
                </title>
                </category:category>
                <link rel="canonical" href="${pageContext.request.contextPath}/java/${param.TEST_PATH}/${param.CATEGORY_PATH}"/>
                <%@ include file="/edit/categoryOL.jsp" %>
                <script async src="${pageContext.request.contextPath}/js/prism.js?ver=1"></script>
    </jsp:attribute>
    <jsp:body>
         <div class="mainArea">
           <jsp:include page="/WEB-INF/breadCrumbs/breadCrumbs2.jsp"/>
            <main>
              <form ACTION="${pageContext.request.contextPath}/see-questions" METHOD="POST">
                  <input type="hidden" name="CATEGORY_PATH" value="${param.CATEGORY_PATH}">
                  <input type="hidden" name="TEST_PATH" value="${param.TEST_PATH}">
                  <c:if test="${person != null}">
                      <%@ include file="/WEB-INF/showQuestions/selectOptionsPanel.jsp" %>
                  </c:if>
                  <input type="submit" value="<spring:message code="start"/>" id="startQuiz" class="submitButton">
              </form>
            </main>
         </div>
    </jsp:body>
</t:wrapper>
