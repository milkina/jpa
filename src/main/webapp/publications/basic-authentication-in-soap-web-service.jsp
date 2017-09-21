<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/tld/cache-tagjsp-taglib.tld" prefix="cache"%>
<cache:cacheTag/>
<t:wrapper>
  <jsp:attribute name="header">
    <META NAME="Keywords" CONTENT="basic authentication,security,web service,java">
    <META NAME="Description" CONTENT="Add security level to a SOAP web service with basic authentication using Jboss AS 7 Server">
    <title>Secure Web Service with Basic Authentication in Jboss AS 7 Server</title>
 </jsp:attribute>
 <jsp:body>
   <div class="mainArea">
    <%@ include file="/WEB-INF/breadCrumbs/publicationsBreadCrumbs2.jsp" %>

    <main>
        <div class="postDate"><span class="entryDay">2</span>
            <span class="entryMonth">May</span>
            <span class="entryYear">2016</span></div>
        <h1 class="header1">Secure Web Service with Basic Authentication in Jboss AS 7 Server</h1>
        <p>In this article we will implement a web service with basic authentication using Jboss AS 7 server.</p>
           <ol>
            <li><h2>Create a Simple Web Service</h2>
            <p>Start with implementing simple <a href="${pageContext.request.contextPath}/publications/create-soap-rpc-web-service-in-servlet-container.jsp" rel="nofollow">RPC servlet-based web service with JAX-WS</a>.</p>
            </li>
            <li><h2>Create a User in Jboss AS 7</h2>
            <p>New user in Jboss AS 7 is added with add-user utility.
            Run add-user.bat which is located in {jboss.home}/bin:</p>
            <img src="${pageContext.request.contextPath}/images/basic-auth1.png" alt="add-user.bat" style="width:514px;height:251px;">
               <p>More details about "Add-user utility" read at
               <a href="https://docs.jboss.org/author/display/AS71/add-user+utility">Jboss Documentation</a>.</p>
             </li>
             <li><h2>Add allowed roles to Web Service Endpoint Implementation</h2>
              <pre class="language-java">
                                            <code class="language-java">
<b>@RolesAllowed({"newrole"})</b>
@WebService(endpointInterface = "example.ExamClouds")
public class ExamCloudsImpl implements ExamClouds {
   ...
} </code>
                             </pre>
             </li>
                <li><h2>Modify web.xml</h2>
                <p>Add security-role, security-constraint and login-config elements.</p>
                               <pre class="language-markup">
                               <code class="language-markup">
&lt;security-role&gt;
    &lt;role-name&gt;newrole&lt;/role-name&gt;
&lt;/security-role&gt;

&lt;security-constraint&gt;
     &lt;web-resource-collection&gt;
            &lt;web-resource-name&gt;ECCollection&lt;/web-resource-name&gt;
            &lt;url-pattern&gt;/ExamClouds&lt;/url-pattern&gt;
            &lt;http-method&gt;POST&lt;/http-method&gt;
     &lt;/web-resource-collection&gt;
     &lt;auth-constraint&gt;
            &lt;role-name&gt;newrole&lt;/role-name&gt;
     &lt;/auth-constraint&gt;
&lt;/security-constraint&gt;

&lt;login-config&gt;
      &lt;auth-method&gt;BASIC&lt;/auth-method&gt;
      &lt;realm-name&gt;ApplicationRealm&lt;/realm-name&gt;
&lt;/login-config&gt;</code>
                               </pre>
                 </li>
                 <li><h2>Create jboss-web.xml</h2>
                 <pre class="language-markup">
                                                <code class="language-markup">
&lt;jboss-web&gt;
    &lt;security-domain&gt;java:/jaas/other&lt;/security-domain&gt;
&lt;/jboss-web&gt;</code>
                                                            </pre>
                 </li>
                 <li><h2>Add authentication credentials to the client</h2>
                   <pre class="language-java">
                      <code class="language-java">
ExamCloudsImplService service = new ExamCloudsImplService();
ExamClouds port = service.getExamCloudsImplPort();
<B>
BindingProvider prov = (BindingProvider) port;
prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "newuser");
prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "new");</B>

System.out.println(port.getSiteName());
System.out.println(port.getSiteDescription());
                      </code></pre>
                 </li>
           </ol>
    </main>
    <%@ include file="/WEB-INF/socialButtons.jsp" %>
    <jsp:include page="/WEB-INF/comment/comments.jsp">
                                   <jsp:param name="referenceId" value="3" />
                                   <jsp:param name="commentType" value="ARTICLE" />
     </jsp:include>
  </div>
</jsp:body>
</t:wrapper>