package controller.person;

import controller.person.ChangePassword;
import org.mortbay.jetty.SessionManager;
import org.mortbay.jetty.servlet.HashSessionManager;
import org.mortbay.jetty.testing.HttpTester;
import org.mortbay.jetty.testing.ServletTester;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 12.03.2013
 * Time: 20:31:35
 * To change this template use File | Settings | File Templates.
 */
public class ChangePasswordTest {
    private static ServletTester tester;

    @BeforeClass
    public static void beforeClass(){
         SessionManager m =new HashSessionManager();
     //   m.get(null);
      //  m.(null);
        tester = new ServletTester();
    //    tester.getContext();
     //   tester.getContext().getSessionHandler();
     //   tester.getContext().getSessionHandler().setSessionManager(m);


        tester.addServlet(ChangePassword.class, "/ChangePassword");
        try {
            tester.start();

        }
        catch (Exception e) {
           System.out.println("Exception in changePasswordTest");
        } 
    }
    @Test
    public void changePasswordTest() throws Exception{
            HttpTester request = new HttpTester();
            request.setMethod("GET");
            request.setHeader("Host", "tester");
            request.setURI("/ChangePassword");
            request.setVersion("HTTP/1.0");

            HttpTester response = new HttpTester();
            response.parse(tester.getResponses(request.generate()));
            Assert.assertNotNull(response);
    }
}
