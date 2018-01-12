package controller.category;

import data.category.CategoryHandler;
import model.Category;
import model.Test;
import util.TestUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static util.AllConstantsParam.PAGE_VALUE;
import static util.AllConstantsParam.PAGE_PARAM;
import static util.AllConstantsParam.CATEGORY_PATH;
import static util.AllConstantsParam.TEST_PATH;

/**
 * Created by Tatyana on 25.12.2016.
 */
public class UpCategoryServlet extends HttpServlet {

    public static final String SHOW_TEST =
            "/administration/test/show-test.jsp?TEST_PATH=%s";
    public static final String EDIT_CATEGORY =
            "/servlet/EditCategoryServlet?CATEGORY_PATH=%s&TEST_PATH=%s";

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        String categoryPath = request.getParameter(CATEGORY_PATH);
        String testPath = request.getParameter(TEST_PATH);

        Test test = TestUtility.getTestFromServletContext(request);
        Map<String, Category> categoryMap = test.getCategories();
        Category category = categoryMap.get(categoryPath);

        CategoryHandler categoryHandler = new CategoryHandler();
        categoryHandler.moveCategoryUp(category, categoryMap);

        TestUtility.loadTestsToServletContext(request.getServletContext());
        String url = String.format(SHOW_TEST, testPath);
        if (PAGE_VALUE.equals(request.getParameter(PAGE_PARAM))) {
            url = String.format(EDIT_CATEGORY, categoryPath, testPath);
        }
        response.sendRedirect(request.getContextPath() + url);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
