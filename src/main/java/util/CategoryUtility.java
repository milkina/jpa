package util;

import data.category.CategoryHandler;
import data.questionEntry.QuestionEntryHandler;
import model.AbstractQuestionEntry;
import model.Category;
import model.Test;
import model.article.Article;
import model.person.Person;
import util.article.ArticleUtility;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static util.AllConstantsAttribute.DUPLICATE_CATEGORIES;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsAttribute.TESTS;
import static util.AllConstantsParam.CATEGORY_HIDDEN;
import static util.AllConstantsParam.CATEGORY_NAME;
import static util.AllConstantsParam.CATEGORY_PARENT;
import static util.AllConstantsParam.CATEGORY_PATH;
import static util.AllConstantsParam.CATEGORY_PATH_NAME;
import static util.AllConstantsParam.TEST_PATH;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 30.09.2012
 * Time: 0:31:28
 * To change this template use File | Settings | File Templates.
 */
public class CategoryUtility {
    private static CategoryHandler categoryHandler =
            new CategoryHandler();
    private static QuestionEntryHandler questionEntryHandler =
            new QuestionEntryHandler();


    public static String getDescription(Category category) {
        if (category != null && category.getArticle() != null
                && category.getArticle().getDescription() != null) {
            return category.getArticle().getDescription();
        }
        return "";
    }

    public static String getName(Category category) {
        if (category != null && category.getName() != null) {
            return category.getName();
        }
        return "";
    }

    public static String getPathName(Category category) {
        if (category != null && category.getPathName() != null) {
            return category.getPathName();
        }
        return "";
    }

    public static void setCategoryData(HttpServletRequest request,
                                       Category category) {
        String categoryName = GeneralUtility.decodeRussianCharacters(
                request.getParameter(CATEGORY_NAME));
        String categoryPath = request.getParameter(CATEGORY_PATH_NAME);
        String categoryParentId = request.getParameter(CATEGORY_PARENT);
        String categoryHidden = request.getParameter(CATEGORY_HIDDEN);

        Category parent = null;
        if (!categoryParentId.isEmpty()) {
            parent = categoryHandler.getCategory(
                    Integer.parseInt(categoryParentId));
        }
        category.setName(categoryName.trim());
        category.setPathName(categoryPath.trim());
        category.setParentCategory(parent);
        category.setHidden("on".equals(categoryHidden));
    }

    public static Category getCategoryByPath(HttpServletRequest request) {
        return categoryHandler.getCategory(request.getParameter(CATEGORY_PATH));
    }


    public static Integer getCategoryIdByPath(HttpServletRequest request) {
        Category category = getCategoryByPath(request);
        return category.getId();
    }

    public static void updateCategory(HttpServletRequest request,
                                      Category category) {
        setCategoryData(request, category);
        setCategoryArticle(request, category);
        updateCategory(category);
        TestUtility.loadTestsToServletContext(request.getServletContext());
    }

    public static Category getCategoryFromServletContext(
            ServletRequest request) {
        Map<String, Category> categoryMap =
                getCategoriesFromServletContext(request);
        String categoryPath = request.getParameter(CATEGORY_PATH);
        return categoryMap.get(categoryPath);
    }

    public static Map<String, Category> getCategoriesFromServletContext(
            ServletRequest request) {
        Map<String, Test> testMap = (Map<String, Test>)
                request.getServletContext().getAttribute(TESTS);
        String testPath = request.getParameter(TEST_PATH);
        Test test = testMap.get(testPath);
        return test.getCategories();
    }

    public static void setCategoryArticle(
            HttpServletRequest request, Category category) {
        Article article = category.getArticle();
        if (article != null) {
            ArticleUtility.updateArticle(article, request);
        } else {
            Person person = (Person)
                    request.getSession().getAttribute(PERSON_ATTRIBUTE);
            article = ArticleUtility.createArticle(request, person);
        }
        category.setArticle(article);
    }

    public static void updateCategory(Category category) {
        categoryHandler.updateCategory(category);
    }

    public static boolean containArticle(Category category) {
        return category.getArticle() != null;
    }

    public static boolean containQuestionEntries(Category category) {
        Map<Integer, AbstractQuestionEntry> questions =
                questionEntryHandler.getAllAbstractQuestionsMap(category);
        return questions != null && !questions.isEmpty();
    }

    public static void setDuplicateCategories(ServletContext servletContext) {
        Map<String, Category> categories =
                categoryHandler.getDuplicateCategories();
        servletContext.setAttribute(DUPLICATE_CATEGORIES, categories);
    }

    public static Category getPreviousCategory(
            Category category, Map<String, Category> categoryMap) {
        Category previousCategory = null;
        if (categoryMap == null || categoryMap.isEmpty() || category == null) {
            return null;
        }
        for (Map.Entry<String, Category> c : categoryMap.entrySet()) {
            if (category.equals(c.getValue())) {
                break;
            }
            previousCategory = c.getValue();
        }
        return previousCategory;
    }

    public static void selectCategoriesWithTests(List<Category> categories) {
        categories.removeIf(Category::getHidden);
        categories.removeIf(category -> category.getParentCategory() != null);
        selectSubCategoriesWithTests(categories);
        categories.removeIf(c -> c.getParentCategory() == null
                && (c.getSubCategories() == null
                || c.getSubCategories().isEmpty()) && c.getTestsCount() < 1);
    }

    private static void selectSubCategoriesWithTests(List<Category> categories) {
        for (Category category : categories) {
            Collection<Category> subCategories = category.getSubCategories();
            if (subCategories != null && !subCategories.isEmpty()) {
                subCategories.removeIf(Category::getHidden);
                subCategories.removeIf(c -> c.getTestsCount() < 1);
            }
        }
    }
}
