package main.java.controller.category;

import main.java.data.category.CategoryHandler;
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

import static main.java.util.AllConstantsParam.*;

/**
 * Created by Tatyana on 25.12.2016.
 */
public class UpCategoryServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryPath = request.getParameter(CATEGORY_PATH);
        String testPath = request.getParameter(TEST_PATH);

        Test test = TestUtility.getTestFromServletContext(request);
        Map<String, Category> categoryMap = test.getCategories();
        Category category = categoryMap.get(categoryPath);

        CategoryHandler categoryHandler = new CategoryHandler();
        categoryHandler.moveCategoryUp(category, categoryMap);

        TestUtility.loadTestsToServletContext(request.getServletContext());
        String url = String.format("/administration/test/show-test.jsp?TEST_PATH=%s", testPath);
        if (PAGE_VALUE.equals(request.getParameter(PAGE_PARAM))) {
            url = String.format("/servlet/EditCategoryServlet?CATEGORY_PATH=%s&TEST_PATH=%s", categoryPath, testPath);
        }
        response.sendRedirect(request.getContextPath() + url);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
