package main.java.controller.sitemap;

import main.java.model.Category;
import main.java.model.Test;
import main.java.model.article.Article;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static main.java.util.AllConstants.SITE_NAME;

/**
 * Created by Tatyana on 07.02.2017.
 */
public class SiteMapUtility {
    private UrlSet links;
    private Map<String, Category> duplicateCategories;
    private Map<String, Test> testMap;
    private List<Article> articles;

    public SiteMapUtility(Map<String, Category> duplicateCategories, Map<String, Test> testMap, List<Article> articles) {
        this.duplicateCategories = duplicateCategories;
        this.testMap = testMap;
        this.articles = articles;
        links = new UrlSet();
    }

    public Map<String, Category> getDuplicateCategories() {
        return duplicateCategories;
    }

    public void setDuplicateCategories(Map<String, Category> duplicateCategories) {
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

    public void setArticleLinks() {
        for (Article article : articles) {
            double priority = 0.5;
            if (article.getUrl().trim().isEmpty()) {
                priority = 1;
            }
            UrlEntity urlEntity = createUrlEntity(SITE_NAME + article.getUrl(), priority, "monthly");
            links.addUrlEntity(urlEntity);
        }
    }

    public void setTestLinks() {
        for (Test test : testMap.values()) {
            setTestLink(test);
        }
    }

    private void setTestLink(Test test) {
        String testPathName = SITE_NAME + test.getPathName();
        if (!isExceptionTestPath(test.getPathName())) {
            testPathName = SITE_NAME + "exam/" + test.getPathName();
        }
        UrlEntity urlEntity = createUrlEntity(testPathName, 0.8, "weekly");

        links.addUrlEntity(urlEntity);

        setCategoryLinks(test);
    }

    private UrlEntity createUrlEntity(String testPathName, double priority, String freq) {
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
            UrlEntity urlEntity = createUrlEntity(SITE_NAME + "java/" + test.getPathName() + "/" + category.getPathName(), 0.5, "weekly");
            links.addUrlEntity(urlEntity);
        }
    }

    private boolean isCategoryLinkable(Test test, Category category) {
        if (category.getHidden() || (category.getSubCategories() != null && !category.getSubCategories().isEmpty())) {
            return false;
        }
        if (isDuplicateCategory(duplicateCategories.get(category.getPathName()), test)) {
            return false;
        }
        return true;
    }

    private boolean isDuplicateCategory(Category category, Test test) {
        if (category == null) {
            return false;
        }
        Test firstTest = category.getTests().get(0);
        return !firstTest.equals(test);
    }

    private boolean isExceptionTestPath(String testPath) {
        String[] list = {"ocjp", "jpa", "web-services"};
        for (String t : list) {
            if (testPath.equals(t)) {
                return true;
            }
        }
        return false;
    }
}
