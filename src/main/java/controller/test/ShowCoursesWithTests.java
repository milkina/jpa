package controller.test;

import data.test.TestHandler;
import model.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static util.AllConstants.TESTS_PAGE;
import static util.AllConstantsAttribute.TESTS_WITH_TESTS;

public class ShowCoursesWithTests extends HttpServlet {
    private static TestHandler testHandler = new TestHandler();
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Test> testMap = testHandler.getAllTestsWithNotEmptyTests();
        request.setAttribute(TESTS_WITH_TESTS, testMap);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(TESTS_PAGE);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}