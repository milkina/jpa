package controller.test;

import data.test.TestHandler;
import model.Test;
import util.TestUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static util.AllConstants.MESSAGE_PAGE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsAttribute.TESTS;
import static util.AllConstantsParam.OLD_TEST_PATH;
import static util.AllMessage.TEST_UPDATED_MESSAGE;

/**
 * Created by Tatyana on 01.01.2016.
 */
public class EditTestServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, Test> testMap = (Map<String, Test>)
                request.getServletContext().getAttribute(TESTS);
        String testPath = request.getParameter(OLD_TEST_PATH);
        Test test = testMap.get(testPath);

        TestUtility.setTestData(test, request);
        TestUtility.setArticleData(test, request);

        TestHandler testHandler = new TestHandler();
        testHandler.updateTest(test);

        TestUtility.loadTestsToServletContext(request.getServletContext());

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(MESSAGE_PAGE);
        request.setAttribute(MESSAGE_ATTRIBUTE, TEST_UPDATED_MESSAGE);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
