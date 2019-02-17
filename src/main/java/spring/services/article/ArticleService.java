package spring.services.article;

import model.article.Article;
import model.person.Person;

import java.util.List;

public interface ArticleService {
    Article addArticle(Article article);

    Article getArticle(int id);

    void deleteArticle(int id);

    Article update(Article article);

    List<Article> getArticles();

    List<Article> getArticles(Person person);

    Article getArticleByUrl(String url);
}
