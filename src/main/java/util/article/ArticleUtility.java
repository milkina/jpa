package util.article;

import model.Category;
import model.Language;
import model.Test;
import model.article.Article;
import model.person.Person;
import util.LanguageUtility;
import util.ServletUtilities;
import util.SpringUtility;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static util.AllConstants.GROUP_NAME;
import static util.AllConstantsParam.*;
import static util.GeneralUtility.decodeRussianCharacters;
import static util.GeneralUtility.isEmpty;

/**
 * Created by Tatyana on 08.05.2016.
 */
public class ArticleUtility extends SpringUtility {

    public static String getArticleUrl(int articleId, ServletContext servletContext) {
        Article article = getArticleService(servletContext).getArticle(articleId);
        String url = article.getUrl();
        if (url == null) {
            Category category = article.getCategory();
            Test course = getCourseService(servletContext).getCourse(category);
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
        article.setUrl(ARTICLE_URL_PREFIX + request.getParameter(URL_PARAM));

        article = getArticleService(request.getServletContext()).addArticle(article);
        return article;
    }

    public static Article createArticle(Article article,
                                        Person author,
                                        ServletContext servletContext) {
        article.setText(decodeRussianCharacters(article.getText()));
        article.setUrl(decodeRussianCharacters(article.getUrl()));
        article.setImage(decodeRussianCharacters(article.getImage()));
        article.setDescription(decodeRussianCharacters(article.getDescription()));
        article.setKeywords(decodeRussianCharacters(article.getKeywords()));
        article.setTitle(decodeRussianCharacters(article.getTitle()));
        article.setAuthor(author);
        article.setCreatedDate(new Date());
        getArticleService(servletContext).addArticle(article);
        return article;
    }

    public static void setArticleData(Article article, Article newArticle, ServletContext servletContexts) {
        article.setText(decodeRussianCharacters(newArticle.getText()));
        article.setUrl(decodeRussianCharacters(newArticle.getUrl()));
        article.setImage(decodeRussianCharacters(newArticle.getImage()));
        article.setDescription(decodeRussianCharacters(newArticle.getDescription()));
        article.setKeywords(decodeRussianCharacters(newArticle.getKeywords()));
        article.setTitle(decodeRussianCharacters(newArticle.getTitle()));

        getArticleService(servletContexts).update(article);
    }


    public static void setArticleData(Article article,
                                      HttpServletRequest request) {
        String text = decodeRussianCharacters(
                request.getParameter(ARTICLE_TEXT));

        String image = request.getParameter(ARTICLE_IMAGE);
        String description = decodeRussianCharacters(
                request.getParameter(ARTICLE_DESCRIPTION));

        String keywords = decodeRussianCharacters(
                request.getParameter(ARTICLE_KEYWORDS));
        String title = decodeRussianCharacters(request.getParameter(TITLE));
        String index = request.getParameter(ARTICLE_INDEX);
        String languageCode = request.getParameter(LANGUAGE);

        article.setText(text);
        article.setImage(image);
        article.setDescription(description);
        article.setKeywords(keywords);
        article.setTitle(title);
        article.setIndexStatus("on".equals(index));
        if (languageCode != null) {
            Language language = LanguageUtility.findLanguageInContext(request.getServletContext(), languageCode);
            article.setLanguage(language);
        }
    }

    public static void updateArticle(Article article,
                                     HttpServletRequest request) {
        setArticleData(article, request);
        article.setUrl(request.getParameter(URL_PARAM));
        getArticleService(request.getServletContext()).update(article);
    }

    public static void updateArticle(int articleId,
                                     HttpServletRequest request) {
        Article article = getArticleService(request.getServletContext()).getArticle(articleId);
        updateArticle(article, request);
    }

    public static void fixTinyMceIssue(Article article) {
        if (article != null && !isEmpty(article.getText())) {
            String articleText = article.getText();
            articleText = ServletUtilities.fixTinyMceIssue(articleText);
            article.setText(articleText);
        }
    }

    public static void removeArticleFromCategory(Article article, ServletContext servletContext) {
        Category category = article.getCategory();
        if (category != null) {
            category.setArticle(null);
            getCategoryService(servletContext).update(category);
        }
        article.setCategory(null);
    }
}
