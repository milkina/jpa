package main.java.controller.edit;

import main.java.model.Category;
import main.java.model.Test;
import main.java.model.article.Article;
import main.java.util.AllConstantsAttribute;
import main.java.util.CategoryUtility;
import main.java.util.article.ArticleUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static main.java.util.AllConstants.MESSAGE_PAGE;
import static main.java.util.AllConstantsAttribute.CATEGORY_ATTRIBUTE;
import static main.java.util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static main.java.util.AllConstantsParam.*;
import static main.java.util.AllMessage.CATEGORY_UPDATED_MESSAGE;

/**
 * Created by Tatyana on 09.12.2015.
 */
public class EditCategoryServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //TODO Return error if category with such name or pathName already exists
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
            url = String.format("/edit/editCategory.jsp?CATEGORY_PATH=%s&TEST_PATH=%s", categoryPath, testPath);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
