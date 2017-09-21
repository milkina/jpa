package main.java.controller.category;

import main.java.data.test.TestHandler;
import main.java.model.Category;
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

import static main.java.util.AllConstants.MESSAGE_PAGE;
import static main.java.util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static main.java.util.AllConstantsParam.CATEGORY_PATH;
import static main.java.util.AllMessage.CATEGORY_REMOVED_FROM_TEST_MESSAGE;

/**
 * Created by Tatyana on 12.12.2016.
 */
public class DeleteFromTestServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Test test = TestUtility.getTestFromServletContext(request);
        String categoryPath = request.getParameter(CATEGORY_PATH);
        Map<String, Category> categories = test.getCategories();
        Category category = categories.get(categoryPath);

        TestHandler testHandler = new TestHandler();
        testHandler.removeCategoryFromTest(test, category);

        TestUtility.loadTestsToServletContext(request.getServletContext());
        CategoryUtility.setDuplicateCategories(request.getServletContext());

        RequestDispatcher dispatcher = request.getRequestDispatcher(MESSAGE_PAGE);
        request.setAttribute(MESSAGE_ATTRIBUTE, CATEGORY_REMOVED_FROM_TEST_MESSAGE);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

