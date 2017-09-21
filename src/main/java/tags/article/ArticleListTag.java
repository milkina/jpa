package main.java.tags.article;

import main.java.data.article.ArticleHandler;
import main.java.model.article.Article;

import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.List;

/**
 * Created by Tatyana on 10.05.2016.
 */
public class ArticleListTag extends BodyTagSupport {
    private List<Article> articleList;

    public int doStartTag() {
        ArticleHandler articleHandler = new ArticleHandler();
        articleList = articleHandler.getArticles();
        return (EVAL_BODY_INCLUDE);
    }

    public List<Article> getArticleList() {
        return articleList;
    }
}
