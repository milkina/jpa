package controller.category;

import data.category.CategoryHandler;
import model.Category;
import model.Test;
import util.AllConstantsAttribute;
import util.CategoryUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static util.AllConstants.MESSAGE_PAGE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsParam.OLD_TEST_PATH;
import static util.AllMessage.CATEGORY_ADDED_MESSAGE;
import static util.AllMessage.CATEGORY_NOT_ADDED_MESSAGE;

/**
 * Created by Tatyana on 10.12.2016.
 */
public class AddCategoryServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oldTestPath = request.getParameter(OLD_TEST_PATH);
        Category category =
                CategoryUtility.getCategoryFromServletContext(request);

        Map<String, Test> testMap = (Map<String, Test>)
                request.getServletContext()
                .getAttribute(AllConstantsAttribute.TESTS);
        Test test = testMap.get(oldTestPath);
        String message = CATEGORY_ADDED_MESSAGE;
        if (test.getCategories().containsKey(category.getPathName())) {
            message = CATEGORY_NOT_ADDED_MESSAGE;
        } else {
            test.addCategory(category);
            CategoryHandler categoryHandler = new CategoryHandler();
            categoryHandler.addCategoryToTest(test, category);
        }
        CategoryUtility.setDuplicateCategories(request.getServletContext());

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(MESSAGE_PAGE);
        request.setAttribute(MESSAGE_ATTRIBUTE, message);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
