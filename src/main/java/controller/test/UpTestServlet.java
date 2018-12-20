package controller.test;

import data.test.TestHandler;
import model.Test;
import util.CategoryUtility;
import util.TestUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static util.AllConstantsAttribute.TESTS;
import static util.AllConstantsParam.PREVIOUS_TEST_PATH;
import static util.AllConstantsParam.TEST_PATH;

/**
 * Created by Tatyana on 19.01.2017.
 */
public class UpTestServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        String testPath = request.getParameter(TEST_PATH);
        String stopTestPath = request.getParameter(PREVIOUS_TEST_PATH);
        Map<String, Test> testMap = (Map<String, Test>)
                request.getServletContext().getAttribute(TESTS);
        Test test = testMap.get(testPath);
        Test stopTest = testMap.get(stopTestPath);

        TestHandler testHandler = new TestHandler();
        testHandler.moveTest(test, stopTest);

        TestUtility.loadTestsToServletContext(request.getServletContext());
        CategoryUtility.setDuplicateCategories(request.getServletContext());
        response.sendRedirect(request.getContextPath()
                + "/administration/welcome.jsp");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
