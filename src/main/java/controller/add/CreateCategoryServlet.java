package main.java.controller.add;

import main.java.data.category.CategoryHandler;
import main.java.model.Category;
import main.java.model.Test;
import main.java.util.AllConstantsAttribute;
import main.java.util.AllConstantsParam;
import main.java.util.CategoryUtility;
import main.java.util.TestUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static main.java.util.AllConstants.MESSAGE_PAGE;
import static main.java.util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static main.java.util.AllMessage.CATEGORY_CREATED_MESSAGE;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 29.09.2012
 * Time: 20:47:53
 * To change this template use File | Settings | File Templates.
 */
public class CreateCategoryServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//TODO Return error if category with such name or pathName already exists
        String testPath = request.getParameter(AllConstantsParam.TEST_PATH);
        Map<String, Test> testMap = (Map<String, Test>) request.getServletContext().getAttribute(AllConstantsAttribute.TESTS);
        Test test = testMap.get(testPath);

        Category category = new Category();
        CategoryUtility.setCategoryData(request, category);

        CategoryUtility.setCategoryArticle(request, category);
        CategoryHandler categoryHandler = new CategoryHandler();
        category = categoryHandler.createCategory(category);
        categoryHandler.addCategoryToTest(test, category);

        TestUtility.loadTestsToServletContext(request.getServletContext());

        RequestDispatcher dispatcher = request.getRequestDispatcher(MESSAGE_PAGE);
        request.setAttribute(MESSAGE_ATTRIBUTE, CATEGORY_CREATED_MESSAGE);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
