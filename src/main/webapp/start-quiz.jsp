<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:wrapper3>
    <jsp:attribute name="header">
                <title>
                   <c:if test="${CATEGORY_ATTRIBUTE.parentCategory!=null}">
                      ${CATEGORY_ATTRIBUTE.parentCategory.name}.
                   </c:if>
                   ${CATEGORY_ATTRIBUTE.name} - ${TESTS[param.TEST_PATH].name}
                </title>
                <meta name="robots" content="noindex">
                <%@ include file="/edit/categoryOL.jsp" %>
                <script type="text/javascript" async src="${pageContext.request.contextPath}/js/prism.js?ver=1"></script>
    </jsp:attribute>
    <jsp:attribute name="left">
                     <jsp:include page="/WEB-INF/categoryMenu.jsp"/>
    </jsp:attribute>
    <jsp:body>
         <div class="mainArea">
           <jsp:include page="/WEB-INF/breadCrumbs/breadCrumbs3.jsp"/>
            <main>
              <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
              <form ACTION="${pageContext.request.contextPath}/see-questions" METHOD="POST">
                  <input type="hidden" name="CATEGORY_PATH" value="${param.CATEGORY_PATH}">
                  <input type="hidden" name="TEST_PATH" value="${param.TEST_PATH}">
                  <c:if test="${person != null}">
                      <%@ include file="/WEB-INF/showQuestions/selectOptionsPanel.jsp" %>
                  </c:if>
                  <input type="submit" value="Start" id="startQuiz" class="submitButton">
              </form>
            </main>
         </div>
    </jsp:body>
</t:wrapper3>
