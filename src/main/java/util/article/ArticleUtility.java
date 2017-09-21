package main.java.util.article;

import main.java.data.article.ArticleHandler;
import main.java.model.article.Article;
import main.java.model.person.Person;
import main.java.util.GeneralUtility;
import main.java.util.ServletUtilities;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static main.java.util.AllConstantsParam.*;

/**
 * Created by Tatyana on 08.05.2016.
 */
public class ArticleUtility {
    private static ArticleHandler articleHandler = new ArticleHandler();

    public static String getArticleUrl(int articleId) {
        ArticleHandler articleHandler = new ArticleHandler();
        Article article = articleHandler.getArticle(articleId);
        String url = article.getUrl();
        return url;
    }

    public static Article createArticle(HttpServletRequest request, Person author) {
        Article article = new Article();
        setArticleData(article, request);
        article.setAuthor(author);
        article.setCreatedDate(new Date());

        article = articleHandler.addArticle(article);
        return article;

    }

    public static void setArticleData(Article article, HttpServletRequest request) {
        String url = request.getParameter(URL_PARAM);
        String text = GeneralUtility.decodeRussianCharacters(request.getParameter(ARTICLE_TEXT));

        String image = request.getParameter(ARTICLE_IMAGE);
        String description = GeneralUtility.decodeRussianCharacters(request.getParameter(ARTICLE_DESCRIPTION));

        String keywords = GeneralUtility.decodeRussianCharacters(request.getParameter(ARTICLE_KEYWORDS));
        String title = GeneralUtility.decodeRussianCharacters(request.getParameter(TITLE));

        article.setText(text);
        article.setUrl(url);
        article.setImage(image);
        article.setDescription(description);
        article.setKeywords(keywords);
        article.setTitle(title);
    }



    public static void updateArticle(Article article, HttpServletRequest request) {
        setArticleData(article, request);
        articleHandler.updateArticle(article);
    }

    public static void updateArticle(int articleId, HttpServletRequest request) {
        Article article = articleHandler.getArticle(articleId);
        updateArticle(article, request);
    }

    public static void fixTinyMceIssue(Article article) {
        if (article != null && !GeneralUtility.isEmpty(article.getText())) {
            String articleText = article.getText();
            articleText = ServletUtilities.fixTinyMceIssue(articleText);
            article.setText(articleText);
        }
    }


}
