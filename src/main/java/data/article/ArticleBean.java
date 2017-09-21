package main.java.data.article;

import main.java.model.Category;
import main.java.model.article.Article;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Tatyana on 08.05.2016.
 */
@Stateless
public class ArticleBean implements ArticleBeanI {
    @PersistenceContext(unitName = "primary")
    EntityManager entityManager;

    public Article getArticle(int id) {
        return entityManager.find(Article.class, id);
    }

    public Article addArticle(Article article) {
        entityManager.persist(article);
        return article;
    }

    public List<Article> getArticles() {
        Query query = entityManager.createNamedQuery("Article.getAllArticles");
        return query.getResultList();
    }

    public void updateArticle(Article article) {
        entityManager.merge(article);
    }

    public void deleteArticle(Article article){
        entityManager.remove(entityManager.merge(article));
    }

    public Article getArticleByUrl(String url){
        Query query = entityManager.createNamedQuery("Article.getArticleByUrl");
        query.setParameter("param",url);
        return (Article)query.getSingleResult();
    }
}
