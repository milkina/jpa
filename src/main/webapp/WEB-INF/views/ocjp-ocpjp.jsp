<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper3>
    <jsp:attribute name="header">
        <META NAME="Keywords" CONTENT="scjp,ocjp,ocpjp,sun certified java programmer,scjp certification,ocp certification,oracle certified java programmer,ocjp certification,oracle java se,ocjp exam">
        <META NAME="Description"
                       CONTENT="Free Java Tutorial for preparation to Oracle Java SE Programmer Certification (OCJP/OCPJP/OCP). Practice questions will help to be prepared and pass exams.">
        <title>Free Oracle Java SE Programmer Certification Tutorial on ExamClouds</title>
    </jsp:attribute>
    <jsp:attribute name="left">
           <h2 class="header2">Associate</h2>
                          <div>
                           <div class="testList" style="width:210px">
                                                         <a href="${pageContext.request.contextPath}/exam/ocajp7">
                                                            <h4>OCAJP 7 (1Z0-803)</h4>
                                                            <span class="questionsNumber">${TESTS["ocajp7"].questionsNumber+TESTS["ocajp7"].testsNumber} questions</span>
                                                            <br>
                                                         </a>
                                                    </div>
                                                    <div class="testList" style="width:210px">
                                                         <a href="${pageContext.request.contextPath}/exam/ocajp8">
                                                            <h4>OCAJP 8 (1Z0-808)</h4>
                                                            <span class="questionsNumber">${TESTS["ocajp8"].questionsNumber+TESTS["ocajp8"].testsNumber} questions</span>
                                                            <br>
                                                         </a>
                                                    </div>
                           </div>
                          <h2 class="header2">Professional</h2>

                                          <div class="testList" style="width:210px">
                                              <a href="${pageContext.request.contextPath}/exam/ocpjp8">
                                                  <h4>OCPJP 8 (1Z0-809)</h4>
                                                  <span class="questionsNumber">${TESTS["ocpjp8"].questionsNumber+TESTS["ocpjp8"].testsNumber} questions</span>
                                                  <br>
                                              </a>
                                          </div>
                                          <div class="testList" style="width:210px">
                                                              <a href="${pageContext.request.contextPath}/ocpjp8-update-from-java6">
                                                                 <h4>OCPJP 8 Upgrade 6<BR>(1Z0-813)</h4>
                                                                 <span class="questionsNumber">
                                                                  ${TESTS["ocpjp8-update-from-java6"].questionsNumber+TESTS["ocpjp8-update-from-java6"].testsNumber} questions</span>
                                                                 <br>
                                                              </a>
                                                         </div>
                                          <div class="testList" style="width:210px">
                                               <a href="${pageContext.request.contextPath}/exam/ocpjp8-update-from-java7">
                                                  <h4>OCPJP 8 Upgrade 7<BR>(1Z0-810)</h4>
                                                  <span class="questionsNumber">${TESTS["ocpjp8-update-from-java7"].questionsNumber+TESTS["ocpjp8-update-from-java7"].testsNumber} questions</span>
                                                  <br>
                                               </a>
                                          </div>
                                           <div class="testList" style="width:210px">
                                                                        <a href="${pageContext.request.contextPath}/exam/ocpjp7">
                                                                            <h4>OCPJP 7 (1Z0-804)</h4>
                                                                            <span class="questionsNumber">${TESTS["ocpjp7"].questionsNumber+TESTS["ocpjp7"].testsNumber} questions</span>
                                                                            <br>
                                                                        </a>
                                                                    </div>
                               <div class="testList" style="width:210px">
                                                             <a href="${pageContext.request.contextPath}/ocjp">
                                                                 <h4>OCJP 6 (1Z0-851)</h4>
                                                                 <span class="questionsNumber">${TESTS["ocjp"].questionsNumber+TESTS["ocjp"].testsNumber} questions</span>
                                                                 <BR>
                                                             </a>
                                                        </div>
    </jsp:attribute>
     <jsp:body>
        <%@ taglib uri="/WEB-INF/tld/cache-tagjsp-taglib.tld" prefix="cache"%>
        <cache:cacheTag/>
         <div class="mainArea">
         <main><article>
          <h1 class="indexH1">Oracle Java Programmer Certification</h1>
          <h2 class="header2"><strong>What is SCJP/OCJP/OCPJP/OCP Java Certification</strong>?</h2>
          <p>The OCP (OCPJP, OCJP or SCJP) exams are the most famous among the range of Oracle Java Certification exams.
          They are updated with every new version of Java.
          The Programmer exam can be useful both for novices in Java and for already experienced engineers,
          who wants to refresh their knowledge.
          The OCP Programmer certification is also required if you decide to pass any Java EE Certification.</p>
          <p>Starting from Java 7, the Oracle divided the OCJP exam into two levels - the OCAJP (Associate Programmer)
          and the OCPJP (Professional Programmer). You cannot take OCPJP exam without having certificate in the OCAJP.</p>
          <p>The OCAJP exam verifies the basic knowledge of Java language: handling exceptions, working with Java data types,
           flow controls, principles of the OOP and so on. The OCPJP certification exam verifies more advanced knowledge
           of Java language like Java class design, generics, collections, concurrency.</p>

          <h2 class="header2"><strong>How to become a Sun (Oracle) certified Java Programmer</strong>?</h2>
          <p>If you don't have any previous certification, you should pass the OCAJP 8 and then the OCPJP 8.</p>
          <p>If you passed the OCJP 6 or any prior versions of the OCJP, you can choose the OCPJP 8 Upgrade (1Z0-813) certification exam
          to receive Oracle Certified Professional, Java SE 8 Programmer certificate.</p>
          <p>If you passed the OCPJP 7, you can choose OCPJP 8 Upgrade (1Z0-810) certification exam
                    to receive Oracle Certified Professional, Java SE 8 Programmer certificate.</p>
          <h2 class="header2">What does ExamClouds propose?</h2>
          <p>The ExamClouds has started to publish articles and quizzes which can be a good study guide for the OCP exams.
              Select the <strong style="font-weight:normal">OCJP exam</strong> and you will find more info about the certification exam, available articles and quizzes.
              If you have some questions, propositions or found a mistake, write a comment to the corresponded quiz or an article.</p>
             </article>
           </main>
           <%@ include file="/WEB-INF/socialButtons.jsp" %>
                        <jsp:include page="/WEB-INF/comment/comments.jsp">
                           <jsp:param name="referenceId" value="95" />
                           <jsp:param name="commentType" value="ARTICLE" />
                        </jsp:include>
</div>

 </jsp:body>
</t:wrapper3>


