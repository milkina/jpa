package controller.add;

import data.test.TestHandler;
import model.Test;
import util.TestUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.AllConstants.MESSAGE_PAGE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllMessage.TEST_ADDED_MESSAGE;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 06.05.2013
 * Time: 20:51:40
 * To change this template use File | Settings | File Templates.
 */
public class AddTestServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Test newTest = new Test();

        TestUtility.setTestData(newTest, request);
        TestUtility.setArticleData(newTest, request);

        TestHandler testHandler = new TestHandler();
        testHandler.addTest(newTest);

        TestUtility.loadTestsToServletContext(request.getServletContext());

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(MESSAGE_PAGE);
        request.setAttribute(MESSAGE_ATTRIBUTE, TEST_ADDED_MESSAGE);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
