package spring;

import model.article.Article;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestUtils;

import java.util.List;

/**
 * Created by Tatyana on 08.05.2016.
 */
@Test
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class ArticleServiceIT extends BaseIT {
    @Test
    public void testAddArticle() {
        Article article = TestUtils.createArticle(3, persons[0]);
        Article result = articleService.addArticle(article);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, article);
    }

    @Test
    public void testGetArticle() {
        Article result = articleService.getArticle(articles[0].getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(result, articles[0]);
    }

    @Test
    public void testGetArticles() {
        List<Article> result = articleService.getArticles();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() >= 3);
    }

    @Test
    public void testGetPersonArticles() {
        List<Article> result = articleService.getArticles(persons[0]);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() >= 2);
    }

    @Test
    public void testUpdateArticle() {
        String newArticleText = "newArticleText";
        Article article = TestUtils.createArticle(4, persons[0]);
        article = articleService.addArticle(article);
        article.setText(newArticleText);
        articleService.update(article);
        Article result = articleService.getArticle(article.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getText(), newArticleText);
    }

    @Test
    public void testDeleteArticle() {
        Article article = TestUtils.createArticle(5, persons[0]);
        article = articleService.addArticle(article);
        article = articleService.getArticle(article.getId());
        Assert.assertNotNull(article);
    }
}
