package spring.repositories;

import model.article.Article;
import model.person.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer> {
    @Query("SELECT a from Article a where a.hidden=false and " +
            "a.url is not null and a.title is not null order by a.createdDate desc")
    List<Article> getAllArticles();

    @Query("SELECT a from Article a where a.hidden=false and " +
            "a.url is not null and a.title is not null AND a.author=:param order by a.createdDate desc")
    List<Article> getPersonArticles(@Param("param") Person person);

    Article findByUrl(String url);
}
