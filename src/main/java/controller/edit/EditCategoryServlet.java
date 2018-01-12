package controller.edit;

import model.Category;
import model.article.Article;
import util.CategoryUtility;
import util.article.ArticleUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.AllConstants.MESSAGE_PAGE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsAttribute.CATEGORY_ATTRIBUTE;
import static util.AllConstantsParam.TEST_PATH;
import static util.AllConstantsParam.CATEGORY_PATH;
import static util.AllConstantsParam.CATEGORY_NAME;
import static util.AllMessage.CATEGORY_UPDATED_MESSAGE;

/**
 * Created by Tatyana on 09.12.2015.
 */
public class EditCategoryServlet extends HttpServlet {

    public static final String STRING =
            "/edit/editCategory.jsp?CATEGORY_PATH=%s&TEST_PATH=%s";

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //TODO Return error if category with such name or pathName
        // already exists
        String url = MESSAGE_PAGE;
        Category category = CategoryUtility.getCategoryByPath(request);
        if (request.getParameter(CATEGORY_NAME) != null) {
            CategoryUtility.updateCategory(request, category);
            request.setAttribute(MESSAGE_ATTRIBUTE, CATEGORY_UPDATED_MESSAGE);
        } else {
            String categoryPath = request.getParameter(CATEGORY_PATH);
            String testPath = request.getParameter(TEST_PATH);
            Article article = category.getArticle();
            ArticleUtility.fixTinyMceIssue(article);
            request.setAttribute(CATEGORY_ATTRIBUTE, category);
            url = String.format(STRING, categoryPath, testPath);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
