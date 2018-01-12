package data.article;

import model.article.Article;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Tatyana on 08.05.2016.
 */
@Local
public interface ArticleBeanI {
    Article getArticle(int id);

    Article addArticle(Article article);

    List<Article> getArticles();

    void updateArticle(Article article);

    void deleteArticle(Article article);

    Article getArticleByUrl(String url);
}
