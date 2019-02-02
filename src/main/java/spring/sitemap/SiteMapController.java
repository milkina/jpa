package spring.sitemap;

import data.article.ArticleHandler;
import model.Category;
import model.Test;
import model.article.Article;
import model.sitemap.UrlSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.SiteMapUtility;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static util.AllConstantsAttribute.DUPLICATE_CATEGORIES;
import static util.AllConstantsAttribute.TESTS;

/**
 * Created by Tatyana on 07.02.2017.
 */
@Controller
public class SiteMapController {
    @RequestMapping(value = "/sitemap.xml")

    public @ResponseBody
    UrlSet getSiteMap(HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        Map<String, Category> duplicateCategories = (Map<String, Category>)
                servletContext.getAttribute(DUPLICATE_CATEGORIES);
        Map<String, Test> testMap = (Map<String, Test>)
                servletContext.getAttribute(TESTS);

        ArticleHandler articleHandler = new ArticleHandler();
        List<Article> articles = articleHandler.getArticles();

        SiteMapUtility siteMapUtility = new SiteMapUtility(duplicateCategories,
                testMap, articles);

        return siteMapUtility.buildLinks();
    }
}

