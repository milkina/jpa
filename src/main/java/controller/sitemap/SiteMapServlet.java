package controller.sitemap;

import data.article.ArticleHandler;
import model.Category;
import model.Test;
import model.article.Article;
import model.sitemap.UrlSet;
import util.SiteMapUtility;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static util.AllConstantsAttribute.DUPLICATE_CATEGORIES;
import static util.AllConstantsAttribute.TESTS;

/**
 * Created by Tatyana on 07.02.2017.
 */
public class SiteMapServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = request.getServletContext();
        Map<String, Category> duplicateCategories = (Map<String, Category>)
                servletContext.getAttribute(DUPLICATE_CATEGORIES);
        Map<String, Test> testMap = (Map<String, Test>)
                servletContext.getAttribute(TESTS);

        ArticleHandler articleHandler = new ArticleHandler();
        List<Article> articles = articleHandler.getArticles();

        SiteMapUtility siteMapUtility = new SiteMapUtility(duplicateCategories,
                testMap, articles);
        UrlSet urlset = siteMapUtility.buildLinks();

        response.setContentType("text/xml");
        JAXB.marshal(urlset, response.getOutputStream());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

