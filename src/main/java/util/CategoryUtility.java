package util;

import model.AbstractQuestionEntry;
import model.Category;
import model.Test;
import model.article.Article;
import model.person.Person;
import spring.services.category.CategoryService;
import util.article.ArticleUtility;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static util.AllConstantsAttribute.DUPLICATE_CATEGORIES;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsAttribute.TESTS;
import static util.AllConstantsParam.CATEGORY_HIDDEN;
import static util.AllConstantsParam.CATEGORY_NAME;
import static util.AllConstantsParam.CATEGORY_PARENT;
import static util.AllConstantsParam.CATEGORY_PATH;
import static util.AllConstantsParam.CATEGORY_PATH_NAME;
import static util.AllConstantsParam.CATEGORY_VIDEO_PATH;
import static util.AllConstantsParam.TEST_PATH;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 30.09.2012
 * Time: 0:31:28
 * To change this template use File | Settings | File Templates.
 */
public class CategoryUtility extends SpringUtility {
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
        String videoPath = request.getParameter(CATEGORY_VIDEO_PATH);

        Category parent = null;
        if (!categoryParentId.isEmpty()) {
            parent = getCategoryService(request.getServletContext()).findOne(
                    Integer.parseInt(categoryParentId));
        }
        category.setName(categoryName.trim());
        category.setPathName(categoryPath.trim());
        category.setParentCategory(parent);
        category.setHidden("on".equals(categoryHidden));
        category.setVideoPath(videoPath.trim());
    }

    public static Category getCategoryByPath(HttpServletRequest request) {
        CategoryService categoryService = getCategoryService(request.getServletContext());
        return categoryService.getCategory(request.getParameter(CATEGORY_PATH));
    }


    public static Integer getCategoryIdByPath(HttpServletRequest request) {
        Category category = getCategoryByPath(request);
        return category.getId();
    }

    public static void updateCategory(HttpServletRequest request,
                                      Category category) {
        setCategoryData(request, category);
        setCategoryArticle(request, category);
        updateCategory(category, request.getServletContext());
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

    public static void updateCategory(Category category, ServletContext servletContext) {
        getCategoryService(servletContext).update(category);
    }

    public static boolean containArticle(Category category) {
        return category.getArticle() != null;
    }

    public static boolean containQuestionEntries(Category category, ServletContext servletContext) {
        Map<Integer, AbstractQuestionEntry> questions =
                getQuestionService(servletContext).getAllAbstractQuestionsMap(category);
        return questions != null && !questions.isEmpty();
    }

    public static void setDuplicateCategories(ServletContext servletContext) {
        Map<String, Category> categories =
                getCategoryService(servletContext).getDuplicateCategories();
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

    public static void selectCategories(Collection<Category> categories,
                                        Predicate<Category> filter) {
        categories.removeIf(category -> category.getParentCategory() != null);
        selectSubCategories(categories, filter);
        Predicate<Category> removeFilter = c -> c.getParentCategory() == null
                && (c.getSubCategories() == null
                || c.getSubCategories().isEmpty());
        removeFilter = removeFilter.and(filter);
        categories.removeIf(removeFilter);
    }

    private static void selectSubCategories(Collection<Category> categories, Predicate<Category> filter) {
        for (Category category : categories) {
            Collection<Category> subCategories = category.getSubCategories();
            if (subCategories != null && !subCategories.isEmpty()) {
                subCategories.removeIf(filter);
            }
        }
    }
}
