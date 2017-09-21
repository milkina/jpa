package main.java.controller.article;

import main.java.data.article.ArticleHandler;
import main.java.model.article.Article;
import main.java.util.GeneralUtility;
import main.java.util.article.ArticleUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static main.java.util.AllConstants.EDIT_ARTICLE;
import static main.java.util.AllConstantsAttribute.ARTICLE_ATTRIBUTE;
import static main.java.util.AllConstantsParam.ARTICLE_ID;

/**
 * Created by Tatyana on 29.06.2016.
 */
public class LoadEditArticleServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = GeneralUtility.getIntegerValue(request, ARTICLE_ID);
        ArticleHandler articleHandler = new ArticleHandler();
        Article article = articleHandler.getArticle(id);
        ArticleUtility.fixTinyMceIssue(article);

        RequestDispatcher dispatcher = request.getRequestDispatcher(EDIT_ARTICLE);
        request.setAttribute(ARTICLE_ATTRIBUTE, article);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

