package data.article;

import model.article.Article;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

import static util.AllBeanNameConstants.ARTICLE_BEAN_NAME;

/**
 * Created by Tatyana on 08.05.2016.
 */
public class ArticleHandler {
    private ArticleBeanI articleBean;
    private Context ct;

    public ArticleHandler() {
        try {
            ct = new InitialContext();
            articleBean = (ArticleBeanI) ct.lookup(ARTICLE_BEAN_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
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
