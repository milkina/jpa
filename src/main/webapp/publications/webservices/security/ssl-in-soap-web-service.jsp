<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/tld/cache-tagjsp-taglib.tld" prefix="cache"%>
<cache:cacheTag/>
<t:wrapper>
  <jsp:attribute name="header">
    <META NAME="Keywords" CONTENT="basic authentication,security,web service,java">
    <META NAME="Description" CONTENT="Example of securing a SOAP web service over SSL on Jboss AS 7 server">
    <title>Secure SOAP Web Service over SSL on Jboss AS 7 Server</title>
 </jsp:attribute>
 <jsp:body>
   <div class="mainArea">
     <main>
        <%@ include file="/WEB-INF/breadCrumbs/publicationsBreadCrumbs2.jsp" %>

        <h1>Secure SOAP Web Service over SSL on Jboss AS 7 Server</h1>
        <p>SSL provides three security services: mutual authentication, confidentiality and integrity.
        Mutual or peer authentication is the process where the server and the client authenticate each other through
        verifying the provided digital certificate so that both parties are assured of the others' identity.
        But very often the server doesn't challenge client for authentication.
        The article describes both cases of authentication.</p>
        <h2>1. Server Authentication</h2>
        <p>Let's start with the case where server doesn't challenge client.</p>
            <h3>1.1 Create a Simple RPC Web Service</h3>
            <p>To add transport-layer security to the web service, please start
                with implementing simple <a href="${pageContext.request.contextPath}/publications/create-soap-rpc-web-service-in-servlet-container.jsp">RPC servlet-based web service with JAX-WS</a>.</p>
            <h3>1.2 Configure Jboss AS 7 Server</h3>
            <h4>Generate a Keystore</h4>
            <p>First we need generate a secret key/certificate and store it in a "key store" file.
            It can be done with keytool utility.
            The password for encryption is "clouds".</p><pre class="language-markup"><code class="language-markup">
keytool -genkey -alias examclouds -keyalg RSA -keystore examclouds.keystore -validity 10950
Enter keystore password: clouds
Re-enter new password: clouds
What is your first and last name?
  [Unknown]:  examclouds.com
What is the name of your organizational unit?
  [Unknown]:  Examclouds
What is the name of your organization?
   [Unknown]:  examclouds
What is the name of your City or Locality?
   [Unknown]:  Kharkiv
What is the name of your State or Province?
   [Unknown]:  Kharkiv
What is the two-letter country code for this unit?
   [Unknown]:  UA
Is CN=examclouds.com, OU=Examclouds, O=examclouds, L=Kharkiv, ST=Kharkiv, C=UA correct?
   [no]:  yes

Enter key password for &lt;examclouds&gt; clouds
    (RETURN if same as keystore password):
Re-enter new password: clouds</code></pre>
<h4>Configure SSL Support on Jboss</h4>
            <p>To do it add a "SSL HTTP/1.1 Connector" entry in standalone/configuration/ standalone.xml file:</p>
            <pre class="language-markup"><code class="language-markup">
&lt;subsystem xmlns="urn:jboss:domain:web:1.1" default-virtual-server="default-host" native="false"&gt;
    &lt;connector name="http" protocol="HTTP/1.1" scheme="http" socket-binding="http"/&gt;
    <b>&lt;connector name="https" protocol="HTTP/1.1" scheme="https" socket-binding="https" secure="true"&gt;
        &lt;ssl name="examclouds-ssl" key-alias="examclouds" password="clouds"
        certificate-key-file="../standalone/configuration/examclouds.keystore" protocol="TLSv1"/&gt;
    &lt;/connector&gt;</b>
    &lt;virtual-server name="default-host" enable-welcome-root="true"&gt;
               ...
    &lt;/virtual-server&gt;
&lt;/subsystem></code></pre>
<p>More details about server configuration can be found on <a href="https://docs.jboss.org/author/display/AS71/SSL+setup+guide">Jboss Documentation</a>.</p>
                 <h3>1.3 Import a Server Certificate to the Client Truststore</h3>
                 <h4>Export the Server Certificate</h4>
                 <pre class="language-markup"><code class="language-markup">
keytool -export -alias examclouds -keystore examclouds.keystore -storepass clouds
-file server.cer
Certificate stored in file &lt;server.cer&gt;
                 </code></pre>
                  <h4>Deliver the Server Certificate to the Client</h4>
                  <p>Copy generated on the previous step file &lt;server.cer&gt; to the client location.</p>
                  <h4>Create the Client Truststore  and Import the Server Certificate to the Client Truststore</h4>
                   <pre class="language-markup"><code class="language-markup">
keytool -import -v -trustcacerts -alias examclouds -keystore client_ts.jks -storepass mypass
-keypass clouds -file server.cer
Owner: CN=examclouds.com, OU=Examclouds, O=examclouds, L=Kharkiv, ST=Kharkiv, C=UA
Issuer: CN=examclouds.com, OU=Examclouds, O=examclouds, L=Kharkiv, ST=Kharkiv, C=UA
Serial number: 44aca6f6
Valid from: Thu Mar 10 23:17:38 EET 2016 until: Sat Mar 03 23:17:38 EET 2046
Certificate fingerprints:
         MD5:  57:80:14:F8:5B:99:A4:47:96:B7:E2:64:91:40:F5:D6
         SHA1: EC:FB:9D:90:F6:5F:76:11:D9:BC:60:B2:2C:E6:BA:A5:17:5E:58:7A
         SHA256: 54:1C:4A:0A:14:9B:0E:DE:E0:49:9F:BF:A2:EC:1B:FA:A3:AB:59:41:30:31:60:B4:6E:72:E9:4C:9E:5A:C1:D6
         Signature algorithm name: SHA256withRSA
         Version: 3

Extensions:

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: 01 3E F0 55 0B 4C 04 42   A5 49 3D C8 8F 37 84 90  .>.U.L.B.I=..7..
0010: 90 CB 6E BE                                        ..n.
]
]

Trust this certificate? [no]:  yes
Certificate was added to keystore
[Storing client_ts.jks]
                   </code></pre>

            <h3>1.4 Modify web.xml</h3>
                <p> SSL requires CONFIDENTIAL transport-guarantee to be configured.</p>
                               <pre class="language-markup">
                               <code class="language-markup">
&lt;security-constraint&gt;
      &lt;web-resource-collection&gt;
            &lt;web-resource-name&gt;ECCollection&lt;/web-resource-name&gt;
            &lt;url-pattern&gt;/ExamClouds&lt;/url-pattern&gt;
            &lt;http-method&gt;POST&lt;/http-method&gt;
      &lt;/web-resource-collection&gt;
      &lt;user-data-constraint&gt;
            &lt;transport-guarantee&gt;CONFIDENTIAL&lt;/transport-guarantee&gt;
      &lt;/user-data-constraint&gt;
&lt;/security-constraint&gt;</code></pre>
                 <h3>1.5 Deploy an Application and Create a Web Service Client</h3>
                 <p>Deploy an application, test it and create a web service client how it is described in
                 <a href="${pageContext.request.contextPath}/publications/create-soap-rpc-web-service-in-servlet-container.jsp">RPC servlet-based web service with JAX-WS</a>.</p>
                 <h3>1.6 Run a Web Service Client</h3>
                 <p>Finally we are ready to invoke our web service.</p>
                 <h4>Configure HOSTS File</h4>
                 <p>If you run web service and client locally, make sure that your C:\Windows\System32\ drivers\etc\hosts file contains entry:</p>
 <pre class="language-markup">
                               <code class="language-markup">
127.0.0.1 localhost</code></pre>
                 <h4>Modify the Client</h4>
                 <p>Our client requires several changes:</p>
                  <ol><li>Modify the URL of the web service to use HTTPS instead of HTTP;
                  and port 8443, which is the default one for JBoss Web to listen for secure connections.</li>
                      <li>Add following JVM parameters to use the client truststore:
                        <pre class="language-markup"><code class="language-markup">
   -Djavax.net.ssl.trustStore=client_ts.jks
   -Djavax.net.ssl.trustStorePassword=mypass
   -Djavax.net.debug=all</code></pre>
                      </li>
                      <li>Code in a static initialization block is used for localhost test only.
                      Its purpose is to solve the issue which happens when CN (Common Name) in the certificate mismatches with
                      host name in the URL.
                      </li>
                  </ol>
                   <pre class="language-java">
                      <code class="language-java">
package example.client;

import java.net.MalformedURLException;
import java.net.URL;

public class ExamCloudsClient {
    <b>public static String CLIENT_WSDL = "https://localhost:8443/ws1-1.0-SNAPSHOT/ExamClouds?wsdl";

    static {
        //for localhost testing only
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                new javax.net.ssl.HostnameVerifier() {

                    public boolean verify(String hostname,
                                          javax.net.ssl.SSLSession sslSession) {
                        return hostname.equals("localhost");
                    }
                });
    }
</b>
    public static void main(String[] args) throws MalformedURLException {
        ExamCloudsImplService service = new ExamCloudsImplService(new URL(CLIENT_WSDL));
        ExamClouds port = service.getExamCloudsImplPort();

        System.out.println(port.getSiteName());
        System.out.println(port.getSiteDescription());
    }
}
                      </code></pre>
            <h2>2. Server and Client Authentication</h2>
            <p>To add client authentication we need to change a previous example a little bit.</p>
            <h3>2.1 Create a Simple RPC Web Service</h3>
            <p>The same actions as in 1.1.</p>
            <h3>2.2 Configure Jboss AS 7 Server</h3>
            <p>Generate the server keystore and configure SSL support on jboss server as described in 1.2.
            Add verify-client element to standalone.xml file, which makes the server to challenge the client.
            And specify truststore for the server. A keystore and a truststore can be the same file, as we did it:</p>
 <pre class="language-markup"><code class="language-markup">
&lt;subsystem xmlns="urn:jboss:domain:web:1.1" default-virtual-server="default-host" native="false"&gt;
    &lt;connector name="http" protocol="HTTP/1.1" scheme="http" socket-binding="http"/&gt;
    &lt;connector name="https" protocol="HTTP/1.1" scheme="https" socket-binding="https" secure="true"&gt;
        &lt;ssl name="examclouds-ssl" key-alias="examclouds" password="clouds"
         certificate-key-file="../standalone/configuration/examclouds.keystore" protocol="TLSv1"
        <b> verify-client="true" ca-certificate-file="../standalone/configuration/examclouds.keystore"</b>/&gt;
    &lt;/connector&gt;
    &lt;virtual-server name="default-host" enable-welcome-root="true"&gt;
               ...
    &lt;/virtual-server&gt;
&lt;/subsystem></code></pre>
<h3>2.3 Create a Client Keystore and Key</h3>
<pre class="language-markup"><code class="language-markup">
keytool -genkey -alias client -keypass mypass -storepass mypass -keystore client_ks.jks
What is your first and last name?
 [Unknown]: Exam Client
What is the name of your organizational unit?
 [Unknown]: Exam Client Org Unit
What is the name of your organization?
 [Unknown]: Exam Client Org
What is the name of your City or Locality?
 [Unknown]: Kharkiv
What is the name of your State or Province?
 [Unknown]: Kharkiv
What is the two-letter country code for this unit?
 [Unknown]: UA
Is CN=Exam Client, OU=Exam Client Org Unit, O=Exam Client Org, L=Kharkiv, ST=Kharkiv, C=UA correct?
 [no]: yes
</code></pre>
<h3>2.4 Import a Client Certificate to the Server Truststore</h3>
                 <h4>Export the Client Certificate</h4>
<pre class="language-markup"><code class="language-markup">
keytool -export -alias client -keystore client_ks.jks -storepass mypass -file client.cer
Certificate stored in file &lt;client.cer&gt;</code></pre>
<h4>Deliver Client Certificate to the Server</h4>
<p>The &lt;client.cer&gt; file should be stored on the server, in "../standalone/configuration" directory for Jboss AS 7.</p>
<h4>Add the Client Certificate to the Server Truststore.</h4>
<p>Import client certificate with keytool utility to the server truststore:</p>
<pre class="language-markup"><code class="language-markup">
keytool -import -v -trustcacerts -alias client -keystore examclouds.keystore -keypass clouds
-file client.cer
Enter keystore password: clouds
Owner: CN=Exam Client, OU=Exam Client Org Unit, O=Exam Client Org, L=Kharkiv, ST=Kharkiv, C=UA
Issuer: CN=Exam Client, OU=Exam Client Org Unit, O=Exam Client Org, L=Kharkiv, ST=Kharkiv, C=UA
Serial number: 20062a5b
Valid from: Thu Mar 10 23:51:31 EET 2016 until: Thu Jun 09 00:51:31 EEST 2016
Certificate fingerprints:
         MD5:  99:7C:2B:14:D0:74:E0:C8:41:E6:6E:27:BC:7C:E0:9C
         SHA1: 30:55:A0:41:55:85:F8:99:8D:FD:64:71:C2:F7:C0:83:44:EA:E1:7E
         SHA256: 12:B5:43:8A:16:0E:38:DF:35:3E:25:DD:3B:2D:53:1B:52:BF:2B:D1:72:2E:D7:69:C7:DF:27:09:CE:DC:F2:E2
         Signature algorithm name: SHA1withDSA
         Version: 3

Extensions:

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: 06 CF 99 31 E3 76 B6 63   E2 1C 00 BD A8 5E 92 6B  ...1.v.c.....^.k
0010: AD E6 61 50                                        ..aP
]
]

Trust this certificate? [no]:  yes
Certificate was added to keystore
[Storing examclouds.keystore]
</code></pre>
<h3>2.5 Import a Server Certificate to the Client Truststore</h3>
<p>The same as described in 1.3 above.</p>
<h3>2.6 Modify web.xml</h3>
  <p>Authentication method should be set to CLIENT-CERT.</p>
                               <pre class="language-markup">
                               <code class="language-markup">
&lt;security-constraint&gt;
      &lt;web-resource-collection&gt;
            &lt;web-resource-name&gt;ECCollection&lt;/web-resource-name&gt;
            &lt;url-pattern&gt;/ExamClouds&lt;/url-pattern&gt;
            &lt;http-method&gt;POST&lt;/http-method&gt;
      &lt;/web-resource-collection&gt;
      &lt;user-data-constraint&gt;
            &lt;transport-guarantee&gt;CONFIDENTIAL&lt;/transport-guarantee&gt;
      &lt;/user-data-constraint&gt;
&lt;/security-constraint&gt;
<b>
&lt;login-config&gt;
      &lt;auth-method&gt;CLIENT-CERT&lt;/auth-method&gt;
&lt;/login-config&gt;
</b>
</code></pre>
<h3>2.7 Deploy an Application and Create a Web Service Client</h3>
<p>The same as in 1.5.</p>
<h3>2.8 Run a Web Service Client</h3>
<p>The same as in 1.6. The only change that should be done is adding client keystore to the JVM parameters:</p>
<pre class="language-markup"><code class="language-markup">
-Djavax.net.ssl.trustStore=client_ts.jks
-Djavax.net.ssl.trustStorePassword=mypass
-Djavax.net.ssl.keyStore=client_ks.jks
-Djavax.net.ssl.keyStorePassword=mypass
-Djavax.net.debug=all</code></pre>
    </main>
    <%@ include file="/WEB-INF/socialButtons.jsp" %>
     <jsp:include page="/WEB-INF/comment/comments.jsp">
                                            <jsp:param name="referenceId" value="5" />
                                            <jsp:param name="commentType" value="ARTICLE" />
     </jsp:include>
      </div>
</jsp:body>
</t:wrapper>