package data.category;

import data.questionEntry.QuestionEntryBeanI;
import data.questionEntry.QuestionEntryHandler;
import data.test.TestBeanI;
import model.Category;
import model.Test;
import util.CategoryUtility;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.AllBeanNameConstants.CATEGORY_BEAN_NAME;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 29.09.2012
 * Time: 20:29:47
 * To change this template use File | Settings | File Templates.
 */
public class CategoryHandler {

    private CategoryBeanI categoryBean;
    private Context ct;
    private QuestionEntryHandler questionEntryHandler;

    public CategoryHandler() {
        questionEntryHandler = new QuestionEntryHandler();
        try {
            ct = new InitialContext();
            categoryBean = (CategoryBeanI) ct.lookup(CATEGORY_BEAN_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public CategoryHandler(CategoryBeanI categoryBean, TestBeanI testBean, QuestionEntryBeanI questionEntryBean) {
        this.categoryBean = categoryBean;
        questionEntryHandler = new QuestionEntryHandler(questionEntryBean);
    }

    public void updateCategory(Category c) {
        categoryBean.updateCategory(c);
    }

    public Category[] createCategories(Category[] categories) {
        for (int i = 0; i < categories.length; i++) {
            Category c = createCategory(categories[i]);
            categories[i] = c;
        }
        return categories;
    }

    public Category addCategoryToTest(Test test, Category category) {
        return categoryBean.addCategoryToTest(test, category);
    }

    public Category createCategory(Category category) {
        return categoryBean.createCategory(category);
    }

    public Category getCategory(int categoryId) {
        return categoryBean.getCategory(categoryId);
    }

    public Map<String, Integer> getPathName() {
        Map<String, Integer> result = new HashMap<>();
        List<Object[]> list = categoryBean.getPathName();
        for (Object[] r : list) {
            result.put(r[1].toString(), (Integer) r[0]);
        }
        return result;
    }

    public Category getCategory(String pathName) {
        return categoryBean.getCategory(pathName);
    }

    public List<Category> getCategories(int testId) {
        return categoryBean.getCategories(testId);
    }

    public List<Category> getCategories(String testPath) {
        return categoryBean.getCategories(testPath);
    }

    public void removeCategory(Category category) {
        categoryBean.removeCategory(category);
    }

    public Map<String, Category> getDuplicateCategories() {
        Map<String, Category> result = new HashMap<>();
        List<Category> categories = categoryBean.getDuplicateCategories();
        for (Category category : categories) {
            result.put(category.getPathName(), category);
        }
        return result;
    }

    public void moveCategoryUp(Category category, Map<String, Category> categoryMap) {
        Category previousCategory = CategoryUtility.getPreviousCategory(category, categoryMap);
        if (previousCategory != null) {
            swapCategories(category, previousCategory);
        }
    }

    public void moveCategoryUp(Category category, String stopCategoryPath, String testPath) {
        categoryBean.moveCategoryUp(category, stopCategoryPath, testPath);
    }

    public void moveCategoryDown(Category category, String stopCategoryPath, String testPath) {
        categoryBean.moveCategoryDown(category, stopCategoryPath, testPath);
    }

    public void swapCategories(Category c1, Category c2) {
        int id1 = c1.getOrderId();
        int id2 = c2.getOrderId();
        c1.setOrderId(id2);
        c2.setOrderId(id1);
        updateCategory(c1);
        updateCategory(c2);
    }

    public void updateCategoryCounts(Category category) {
        int questionCount = questionEntryHandler.getAllQuestions(category).size();
        int testQuestionCount = questionEntryHandler.getAllTestQuestions(category).size();
        category.setTestsCount(testQuestionCount);
        category.setQuestionsCount(questionCount);
        categoryBean.updateCategory(category);
    }
}

