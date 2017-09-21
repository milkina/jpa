package main.java.controller.article;

import main.java.data.article.ArticleHandler;
import main.java.data.category.CategoryHandler;
import main.java.model.Category;
import main.java.model.article.Article;
import main.java.util.GeneralUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static main.java.util.AllConstants.MESSAGE_PAGE;
import static main.java.util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static main.java.util.AllConstantsParam.ARTICLE_ID;
import static main.java.util.AllMessage.ARTICLE_REMOVED_MESSAGE;

/**
 * Created by Tatyana on 23.06.2016.
 */
public class DeleteArticleServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = GeneralUtility.getIntegerValue(request, ARTICLE_ID);
        ArticleHandler articleHandler = new ArticleHandler();
        Article article = articleHandler.getArticle(id);
        removeArticleFromCategory(article);
        articleHandler.deleteArticle(article);

        request.setAttribute(MESSAGE_ATTRIBUTE, ARTICLE_REMOVED_MESSAGE);

        RequestDispatcher dispatcher = request.getRequestDispatcher(MESSAGE_PAGE);
        dispatcher.forward(request, response);
    }

    private void removeArticleFromCategory(Article article) {
        Category category = article.getCategory();
        if (category != null) {
            category.setArticle(null);
            CategoryHandler categoryHandler = new CategoryHandler();
            categoryHandler.updateCategory(category);
        }
        article.setCategory(null);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

