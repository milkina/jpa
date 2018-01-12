package controller.article;

import data.article.ArticleHandler;
import model.article.Article;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.AllConstants.SHOW_ARTICLE_PAGE;
import static util.AllConstantsAttribute.ARTICLE_ATTRIBUTE;
import static util.AllConstantsParam.URL_PARAM;

/**
 * Created by Tatyana on 07.07.2016.
 */
public class ShowArticleServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String articleUrl = request.getParameter(URL_PARAM);
        ArticleHandler articleHandler = new ArticleHandler();
        Article article =
                articleHandler.getArticleByUrl("publications/" + articleUrl);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(SHOW_ARTICLE_PAGE);
        request.setAttribute(ARTICLE_ATTRIBUTE, article);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

