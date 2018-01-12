package ejb.article;

import junit.framework.Assert;
import model.article.Article;
import util.article.ArticleUtility;
import org.testng.annotations.Test;
import utils.BaseIT;
import utils.TestUtils;

import java.util.List;

/**
 * Created by Tatyana on 08.05.2016.
 */
public class ArticleHandlerIT extends BaseIT {
    @Test
    public void testAddArticle() {
        Article article = TestUtils.createArticle(3, persons[0]);
        Article result = articleHandler.addArticle(article);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, article);
    }

    @Test
    public void testGetArticle() {
        Article result = articleHandler.getArticle(articles[0].getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(result, articles[0]);
    }

    @Test
    public void testGetArticles() {
        List<Article> result = articleHandler.getArticles();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() >= 3);
    }

    @Test
    public void testUpdateArticle() {
        String newArticleText = "newArticleText";
        Article article = TestUtils.createArticle(4, persons[0]);
        article = articleHandler.addArticle(article);
        article.setText(newArticleText);
        articleHandler.updateArticle(article);
        Article result = articleHandler.getArticle(article.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getText(), newArticleText);
    }

    @Test
    public void testDeleteArticle() {
        Article article = TestUtils.createArticle(5, persons[0]);
        article = articleHandler.addArticle(article);
        article = articleHandler.getArticle(article.getId());
        Assert.assertNotNull(article);
    }
}
