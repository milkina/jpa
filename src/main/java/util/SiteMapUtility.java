package util;

import model.Category;
import model.Test;
import model.article.Article;
import model.sitemap.UrlEntity;
import model.sitemap.UrlSet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static util.AllConstants.SITE_NAME;

/**
 * Created by Tatyana on 07.02.2017.
 */
public class SiteMapUtility {
    public static final double NORM_PRIORITY = 0.5;
    public static final double HIGH_PRIORITY = 0.8;
    private UrlSet links;
    private Map<String, Category> duplicateCategories;
    private Map<String, Test> testMap;
    private List<Article> articles;

    public SiteMapUtility(Map<String, Category> duplicateCategories,
                          Map<String, Test> testMap, List<Article> articles) {
        this.duplicateCategories = duplicateCategories;
        this.testMap = testMap;
        this.articles = articles;
        links = new UrlSet();
    }

    public Map<String, Category> getDuplicateCategories() {
        return duplicateCategories;
    }

    public void setDuplicateCategories(
            Map<String, Category> duplicateCategories) {
        this.duplicateCategories = duplicateCategories;
    }

    public UrlSet getLinks() {
        return links;
    }

    public void setLinks(UrlSet links) {
        this.links = links;
    }

    public UrlSet buildLinks() {
        setTestLinks();
        setArticleLinks();
        return links;
    }

    private void setArticleLinks() {
        for (Article article : articles) {
            if (article.isIndexStatus()) {
                double priority = NORM_PRIORITY;
                if (article.getUrl().trim().isEmpty()) {
                    priority = 1;
                }
                UrlEntity urlEntity = createUrlEntity(SITE_NAME
                        + article.getUrl(), priority, "monthly");
                links.addUrlEntity(urlEntity);
            }
        }
    }

    private void setTestLinks() {
        for (Test test : testMap.values()) {
            setTestLink(test);
        }
    }

    private void setTestLink(Test test) {
        String testPathName = SITE_NAME + test.getFullPathName();
        UrlEntity urlEntity =
                createUrlEntity(testPathName, HIGH_PRIORITY, "weekly");

        links.addUrlEntity(urlEntity);
        setCategoryLinks(test);
    }

    private UrlEntity createUrlEntity(String testPathName,
                                      double priority, String freq) {
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setLoc(testPathName);
        urlEntity.setChangefreq(freq);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);

        urlEntity.setLastmod(reportDate);
        urlEntity.setPriority(priority);
        return urlEntity;
    }

    private void setCategoryLinks(Test test) {
        for (Category category : test.getCategories().values()) {
            setCategoryLink(test, category);
        }
    }

    private void setCategoryLink(Test test, Category category) {
        if (isCategoryLinkable(test, category)) {
            UrlEntity urlEntity = createUrlEntity(SITE_NAME + "java/"
                    + test.getPathName() + "/"
                    + category.getPathName(), NORM_PRIORITY, "weekly");
            links.addUrlEntity(urlEntity);
        }
    }

    private boolean isCategoryLinkable(Test test, Category category) {
        if (category.getHidden() || !category.getArticle().isIndexStatus() || (category.getSubCategories() != null
                && !category.getSubCategories().isEmpty())) {
            return false;
        }
        return !isDuplicateCategory(
                duplicateCategories.get(category.getPathName()), test);
    }

    private boolean isDuplicateCategory(Category category, Test test) {
        if (category == null) {
            return false;
        }
        Test firstTest = category.getTests().get(0);
        return !firstTest.equals(test);
    }
}
