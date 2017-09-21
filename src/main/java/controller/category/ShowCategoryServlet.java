package main.java.controller.category;

import main.java.model.Category;
import main.java.util.CategoryUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static main.java.util.AllConstantsAttribute.ARTICLE_ATTRIBUTE;
import static main.java.util.AllConstantsAttribute.CATEGORY_ATTRIBUTE;
import static main.java.util.AllConstantsParam.CATEGORY_PATH;
import static main.java.util.AllConstantsParam.TEST_PATH;

/**
 * Created by Tatyana on 17.06.2016.
 */
public class ShowCategoryServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryPath = request.getParameter(CATEGORY_PATH);
        String testPath = request.getParameter(TEST_PATH);

        Map<String, Category> categoryMap = CategoryUtility.getCategoriesFromServletContext(request);
        Category category = categoryMap.get(categoryPath);
        request.setAttribute(CATEGORY_ATTRIBUTE, category);
        request.setAttribute(ARTICLE_ATTRIBUTE, category.getArticle());

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                String.format("/show-category.jsp?CATEGORY_PATH=%s&TEST_PATH=%s", categoryPath, testPath));
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
