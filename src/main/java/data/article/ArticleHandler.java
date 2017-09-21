package main.java.data.article;

import main.java.data.category.CategoryHandler;
import main.java.model.Category;
import main.java.model.article.Article;
import main.java.model.person.Person;
import main.java.util.article.ArticleUtility;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Date;
import java.util.List;

import static main.java.util.AllBeanNameConstants.ARTICLE_BEAN_NAME;

/**
 * Created by Tatyana on 08.05.2016.
 */
public class ArticleHandler {
    ArticleBeanI articleBean;
    Context ct;

    public ArticleHandler() {
        try {
            ct = new InitialContext();
            articleBean = (ArticleBeanI) ct.lookup(ARTICLE_BEAN_NAME);
        } catch (NamingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public ArticleHandler(ArticleBeanI articleBean) {
        this.articleBean = articleBean;
    }

    public Article getArticle(int id) {
        return articleBean.getArticle(id);
    }

    public Article[] addArticles(Article[] articles) {
        for (Article article : articles) {
            article = addArticle(article);
        }
        return articles;
    }

    public Article addArticle(Article article) {
        return articleBean.addArticle(article);
    }

    public List<Article> getArticles() {
        return articleBean.getArticles();
    }

    public void updateArticle(Article article) {
        articleBean.updateArticle(article);
    }

    public void deleteArticle(Article article) {
        articleBean.deleteArticle(article);
    }

    public void deleteArticle(int id) {
        Article article = getArticle(id);
        articleBean.deleteArticle(article);
    }

    public Article getArticleByUrl(String url) {
        return articleBean.getArticleByUrl(url);
    }
}
