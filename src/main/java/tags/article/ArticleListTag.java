package tags.article;

import model.article.Article;
import spring.services.article.ArticleService;
import util.SpringUtility;

import javax.servlet.ServletContext;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.List;

/**
 * Created by Tatyana on 10.05.2016.
 */
public class ArticleListTag extends BodyTagSupport {
    private List<Article> articleList;
    private ArticleService articleService;

    private ArticleService getArticleService(ServletContext servletContext) {
        if (articleService == null) {
            articleService = SpringUtility.getService(servletContext, ArticleService.class);
        }
        return articleService;
    }

    public int doStartTag() {
        articleList = getArticleService(pageContext.getServletContext()).getArticles();
        return EVAL_BODY_INCLUDE;
    }

    public List<Article> getArticleList() {
        return articleList;
    }
}
