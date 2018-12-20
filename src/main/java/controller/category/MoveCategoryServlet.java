package controller.category;

import data.category.CategoryHandler;
import model.Category;
import util.CategoryUtility;
import util.TestUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.AllConstantsParam.PREVIOUS_CATEGORY_PATH;
import static util.AllConstantsParam.TEST_PATH;


/**
 * Created by Tatyana on 25.12.2016.
 */
public class MoveCategoryServlet extends HttpServlet {
    private static CategoryHandler categoryHandler = new CategoryHandler();

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        String previousCategoryPath = request.getParameter(PREVIOUS_CATEGORY_PATH);
        String testPath = request.getParameter(TEST_PATH);

        Category category = CategoryUtility.getCategoryByPath(request);
        Category previousCategory = categoryHandler.getCategory(request.getParameter(PREVIOUS_CATEGORY_PATH));

        if (category.getOrderId() > previousCategory.getOrderId()) {
            categoryHandler.moveCategoryUp(category, previousCategoryPath, testPath);
        } else {
            categoryHandler.moveCategoryDown(category, previousCategoryPath, testPath);
        }
        TestUtility.loadTestsToServletContext(request.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
