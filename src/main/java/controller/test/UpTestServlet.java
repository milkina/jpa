package main.java.controller.test;

import main.java.data.test.TestHandler;
import main.java.model.Test;
import main.java.util.CategoryUtility;
import main.java.util.TestUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static main.java.util.AllConstantsAttribute.TESTS;
import static main.java.util.AllConstantsParam.TEST_PATH;

/**
 * Created by Tatyana on 19.01.2017.
 */
public class UpTestServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String testPath = request.getParameter(TEST_PATH);
        Map<String, Test> testMap = (Map<String, Test>) request.getServletContext().getAttribute(TESTS);
        Test test = testMap.get(testPath);

        TestHandler testHandler = new TestHandler();
        testHandler.moveTestUp(test, testMap);

        TestUtility.loadTestsToServletContext(request.getServletContext());
        CategoryUtility.setDuplicateCategories(request.getServletContext());
        response.sendRedirect(request.getContextPath() + "/administration/welcome.jsp");
        // RequestDispatcher dispatcher = request.getRequestDispatcher("/administration/welcome.jsp");
        // dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
