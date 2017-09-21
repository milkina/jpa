<%@ taglib uri="/WEB-INF/tld/examjsp-taglib.tld" prefix="exam" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html lang="en">
        <head>
            <%@ include file="/WEB-INF/head_common.jsp" %>
            <!--[if IE]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
             <title>${QUESTION_ENTRY_ATTRIBUTE.category.parentCategory.name} ${QUESTION_ENTRY_ATTRIBUTE.category.name} - ${TESTS[param.TEST_PATH].name}</title>
                <META NAME="Description" CONTENT="${QUESTION_ENTRY_ATTRIBUTE.category.article.description}">
                <script type="text/javascript" async src="${pageContext.request.contextPath}/js/show_questions_min.js?v=4"></script>
                <script type="text/javascript" async src="${pageContext.request.contextPath}/js/prism.js?ver=1"></script>
                <meta name="robots" content="noindex">
                <c:choose>
                     <c:when test="${DUPLICATE_CATEGORIES[QUESTION_ENTRY_ATTRIBUTE.category.pathName]!=null && DUPLICATE_CATEGORIES[QUESTION_ENTRY_ATTRIBUTE.category.pathName].tests[0].pathName!=param.TEST_PATH}">
                             <link rel="canonical"
                                 href="http://www.examclouds.com/java/${DUPLICATE_CATEGORIES[QUESTION_ENTRY_ATTRIBUTE.category.pathName].tests[0].pathName}/${QUESTION_ENTRY_ATTRIBUTE.category.pathName}" />
                     </c:when>
                     <c:otherwise>
                           <link rel="canonical" href="http://www.examclouds.com/java/${param.TEST_PATH}/${QUESTION_ENTRY_ATTRIBUTE.category.pathName}" />
                     </c:otherwise>
                 </c:choose>
        </head>
        <body itemscope itemtype="http://schema.org/WebPage" class="mainStyle">
           <header>
               <div class="maxw">
                   <%@ include file="/menu.jsp" %>
               </div>
           </header>
           <div id="wrapper">
                 <div class="section maxw">
                      <div class="mainwrap2">
                                 <div class="mainArea">
                                          <jsp:include page="/WEB-INF/breadCrumbs/breadCrumbs3.jsp">
                                            <jsp:param name="TEST_PATH" value="${param.TEST_PATH}" />
                                            <jsp:param name="CATEGORY_PATH" value="${QUESTION_ENTRY_ATTRIBUTE.category.pathName}" />
                                          </jsp:include>
                                        <h2 class="header2">${QUESTION_ENTRY_ATTRIBUTE.category.name}
                                        </h2>
                                         <div class="questionEntryDiv"style="padding-left:8px">
                                              <div class="questionEntryBody">
                                                 <div class="questionText"style="width:470px">${QUESTION_ENTRY_ATTRIBUTE.question.text}</div>
                                              </div>
                                         </div>
                                         <div class="indentmenu"style="width:472px">
                                                                                                                 <ul>
                                                                                                                     <li style="width:451px"><a href=""style="width:474px;text-align:center">www.examclouds.com</a></li>
                                                                                                                 </ul>
                                         </div>

                                     </div>
                                     <div>
                                     ${TESTS[param.TEST_PATH].tags}<br>
                                       Read answer on <a href="${pageContext.request.contextPath}/show-question?QUESTION_ENTRY_ID_PARAM=${QUESTION_ENTRY_ATTRIBUTE.id}&TEST_PATH=${param.TEST_PATH}"
                                          class="showAnswer" name="readAnswer">http://www.examclouds.com/show-question?QUESTION_ENTRY_ID_PARAM=${QUESTION_ENTRY_ATTRIBUTE.id}&TEST_PATH=${param.TEST_PATH}</a>
                                     </div>
                      </div>
                      <aside id="s_right">
                          <jsp:include page="/WEB-INF/google-ads.jsp" />
                      </aside>
                 </div>
           </div>
           <footer>
                <div class="maxw">
                     <%@ include file="/WEB-INF/footer.jsp"%>
                </div>
           </footer>
        </body>
</html>