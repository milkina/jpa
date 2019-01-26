package util.article;

import data.article.ArticleHandler;
import data.category.CategoryHandler;
import data.test.TestHandler;
import model.Category;
import model.Test;
import model.article.Article;
import model.person.Person;
import util.CategoryUtility;
import util.ServletUtilities;
import util.TestUtility;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static util.AllConstants.GROUP_NAME;
import static util.AllConstantsParam.ARTICLE_TEXT;
import static util.AllConstantsParam.ARTICLE_IMAGE;
import static util.AllConstantsParam.ARTICLE_KEYWORDS;
import static util.AllConstantsParam.ARTICLE_DESCRIPTION;
import static util.AllConstantsParam.URL_PARAM;
import static util.AllConstantsParam.TITLE;
import static util.GeneralUtility.decodeRussianCharacters;
import static util.GeneralUtility.isEmpty;

/**
 * Created by Tatyana on 08.05.2016.
 */
public class ArticleUtility {
    private static ArticleHandler articleHandler = new ArticleHandler();

    public static String getArticleUrl(int articleId) {
        ArticleHandler articleHandler = new ArticleHandler();
        Article article = articleHandler.getArticle(articleId);
        String url = article.getUrl();
        if (url == null) {
            Category category = article.getCategory();
            Test course = new TestHandler().getCourse(category);
            url = String.format("%s/%s/%s"
                    , GROUP_NAME, course.getPathName(), category.getPathName());
        }
        return url;
    }

    public static Article createArticle(HttpServletRequest request,
                                        Person author) {
        Article article = new Article();
        setArticleData(article, request);
        article.setAuthor(author);
        article.setCreatedDate(new Date());

        article = articleHandler.addArticle(article);
        return article;
    }

    public static Article createArticle(Article article,
                                        Person author) {
        article.setText(decodeRussianCharacters(article.getText()));
        article.setUrl(decodeRussianCharacters(article.getUrl()));
        article.setImage(decodeRussianCharacters(article.getImage()));
        article.setDescription(decodeRussianCharacters(article.getDescription()));
        article.setKeywords(decodeRussianCharacters(article.getKeywords()));
        article.setTitle(decodeRussianCharacters(article.getTitle()));
        article.setAuthor(author);
        article.setCreatedDate(new Date());

        article = articleHandler.addArticle(article);
        return article;
    }

    public static void setArticleData(Article article, Article newArticle) {
        article.setText(decodeRussianCharacters(newArticle.getText()));
        article.setUrl(decodeRussianCharacters(newArticle.getUrl()));
        article.setImage(decodeRussianCharacters(newArticle.getImage()));
        article.setDescription(decodeRussianCharacters(newArticle.getDescription()));
        article.setKeywords(decodeRussianCharacters(newArticle.getKeywords()));
        article.setTitle(decodeRussianCharacters(newArticle.getTitle()));

        articleHandler.updateArticle(article);
    }


    public static void setArticleData(Article article,
                                      HttpServletRequest request) {
        String url = request.getParameter(URL_PARAM);
        String text = decodeRussianCharacters(
                request.getParameter(ARTICLE_TEXT));

        String image = request.getParameter(ARTICLE_IMAGE);
        String description = decodeRussianCharacters(
                request.getParameter(ARTICLE_DESCRIPTION));

        String keywords = decodeRussianCharacters(
                request.getParameter(ARTICLE_KEYWORDS));
        String title = decodeRussianCharacters(request.getParameter(TITLE));

        article.setText(text);
        article.setUrl(url);
        article.setImage(image);
        article.setDescription(description);
        article.setKeywords(keywords);
        article.setTitle(title);
    }

    public static void updateArticle(Article article,
                                     HttpServletRequest request) {
        setArticleData(article, request);
        articleHandler.updateArticle(article);
    }

    public static void updateArticle(int articleId,
                                     HttpServletRequest request) {
        Article article = articleHandler.getArticle(articleId);
        updateArticle(article, request);
    }

    public static void fixTinyMceIssue(Article article) {
        if (article != null && !isEmpty(article.getText())) {
            String articleText = article.getText();
            articleText = ServletUtilities.fixTinyMceIssue(articleText);
            article.setText(articleText);
        }
    }

    public static void removeArticleFromCategory(Article article) {
        Category category = article.getCategory();
        if (category != null) {
            category.setArticle(null);
            CategoryHandler categoryHandler = new CategoryHandler();
            categoryHandler.updateCategory(category);
        }
        article.setCategory(null);
    }
}
