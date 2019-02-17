package spring.services.article;

import model.article.Article;
import model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.repositories.ArticleRepository;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository repository;

    @Override
    public Article addArticle(Article article) {
        return repository.save(article);
    }

    @Override
    public Article getArticle(int id) {
        return repository.findOne(id);
    }

    @Override
    public void deleteArticle(int id) {
        repository.delete(id);
    }

    @Override
    public Article update(Article article) {
        return repository.save(article);
    }

    @Override
    public List<Article> getArticles() {
        return repository.getAllArticles();
    }

    @Override
    public List<Article> getArticles(Person person) {
        return repository.getPersonArticles(person);
    }

    @Override
    public Article getArticleByUrl(String url) {
        return repository.findByUrl(url);
    }
}
