<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/tld/cache-tagjsp-taglib.tld" prefix="cache"%>
<cache:cacheTag/>
<t:wrapper>
  <jsp:attribute name="header">
    <META NAME="Keywords" CONTENT="soap web service example,java web service example,how to create a web service,create web service,soap rpc,java web service client,soap client java,soap web service example in java">
    <META NAME="Description" CONTENT="Step by step example of deployment of the SOAP RPC Web Service using codefirst approach into a Java EE Servlet Container.">
    <title>Create a SOAP RPC Web Service in a Servlet Container</title>
 </jsp:attribute>
 <jsp:body>
 <div class="mainArea">
    <%@ include file="/WEB-INF/breadCrumbs/publicationsBreadCrumbs2.jsp" %>

    <main>
    <div class="postDate"><span class="entryDay">1</span>
        <span class="entryMonth">May</span>
        <span class="entryYear">2016</span></div>
        <h1 class="header1">Example of SOAP RPC Web Service in Java</h1>
        <p>In this article we will show <strong style="font-weight:normal;">how to create a simple RPC web service example in java using SOAP</strong>
        and associated with it client in a servlet container. This example is used further in
        <a href="${pageContext.request.contextPath}/publications/basic-authentication-in-soap-web-service.jsp">Secure Web Service with Basic Authentication</a> and
        <a href="${pageContext.request.contextPath}/publications/webservices/security/ssl-in-soap-web-service.jsp">Secure SOAP Web Service over SSL</a>.</p>
            <h2 class="header2">1. Create a Web Service Endpoint Interface</h2>
            <p>Let's start with creating SEI with two methods:</p>
               <pre class="language-java">
               <code class="language-java">
package example;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ExamClouds {
    @WebMethod
    String getSiteName();

    @WebMethod
    String getSiteDescription();
}</code></pre>
                <h2 class="header2">2. Create a Web Service Endpoint Implementation</h2>
                 <pre class="language-java">
                               <code class="language-java">
package example;

import javax.jws.WebService;

@WebService(endpointInterface = "example.ExamClouds")
public class ExamCloudsImpl implements ExamClouds {
    public String getSiteName() {
        return "http://www.examclouds.com";
    }

    public String getSiteDescription() {
        return "Java Tutorial";
    }
} </code>
                </pre>
                <h2 class="header2">3. Create web.xml</h2>
                               <pre class="language-markup">
                               <code class="language-markup">
&lt;servlet&gt;
    &lt;servlet-name&gt;ExamClouds&lt;/servlet-name&gt;
    &lt;servlet-class&gt;example.ExamCloudsImpl&lt;/servlet-class&gt;
&lt;/servlet&gt;

&lt;servlet-mapping&gt;
    &lt;servlet-name&gt;ExamClouds&lt;/servlet-name&gt;
    &lt;url-pattern&gt;/ExamClouds&lt;/url-pattern&gt;
&lt;/servlet-mapping&gt;</code>
                               </pre>
                 <h2 class="header2">4. <strong>Deploy Web Service</strong></h2>
                 <p>Compile and package the web service into a WAR file, and deploy it into a Java EE servlet container.</p>
                 <h2 class="header2">5. Test a Web Service</h2>
                 <p>The deployed web service can be tested by accessing the generated WSDL document via the URL “http://&lt;YOUR_HOST&gt;/ws1-1.0-SNAPSHOT/ExamClouds?wsdl”.</p>
                 <h2 class="header2">6. Create a Web Service Client</h2>
                 <p>The wsimport tool helps with <strong style="font-weight:normal;">creating soap web service client example in java</strong>:
                 <pre class="language-markup"><code class="language-markup">
wsimport -s src/main/java -p example.client -keep
http://&lt;YOUR_HOST&gt;/ws1-1.0-SNAPSHOT/ExamClouds?wsdl
                     </code></pre>
                 <h2 class="header2">7. Run the Client</h2>
                 <p>And finally let's call the web service:</p>
                   <pre class="language-java">
                      <code class="language-java">
ExamCloudsImplService service = new ExamCloudsImplService();
ExamClouds port = service.getExamCloudsImplPort();
System.out.println(port.getSiteName());
System.out.println(port.getSiteDescription());
                      </code></pre>
    </main>
    <%@ include file="/WEB-INF/socialButtons.jsp" %>
    <jsp:include page="/WEB-INF/comment/comments.jsp">
                                       <jsp:param name="referenceId" value="4" />
                                       <jsp:param name="commentType" value="ARTICLE" />
    </jsp:include>
    </div>
</jsp:body>
</t:wrapper>