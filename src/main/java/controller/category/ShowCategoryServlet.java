package controller.category;

import model.Category;
import util.CategoryUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static util.AllConstantsAttribute.ARTICLE_ATTRIBUTE;
import static util.AllConstantsAttribute.CATEGORY_ATTRIBUTE;
import static util.AllConstantsParam.CATEGORY_PATH;
import static util.AllConstantsParam.TEST_PATH;

/**
 * Created by Tatyana on 17.06.2016.
 */
public class ShowCategoryServlet extends HttpServlet {

    public static final String STRING =
            "/show-category.jsp?CATEGORY_PATH=%s&TEST_PATH=%s";

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        String categoryPath = request.getParameter(CATEGORY_PATH);
        String testPath = request.getParameter(TEST_PATH);

        Map<String, Category> categoryMap =
                CategoryUtility.getCategoriesFromServletContext(request);
        Category category = categoryMap.get(categoryPath);
        request.setAttribute(CATEGORY_ATTRIBUTE, category);
        request.setAttribute(ARTICLE_ATTRIBUTE, category.getArticle());

        RequestDispatcher dispatcher = request.getRequestDispatcher(
                String.format(STRING, categoryPath, testPath));
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
