package ejb.category;

import model.Category;
import model.article.Article;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseIT;
import utils.TestUtils;

import java.util.List;
import java.util.Map;

import static utils.TestValues.CATEGORY_NAMES;
import static utils.TestValues.CATEGORY_PATHNAMES;


/**
 * Created by IntelliJ IDEA.
 * User: TanyaM
 * Date: Jul 14, 2011
 * Time: 5:07:16 PM
 * To change this template use File | Settings | File Templates.
 */


public class CategoryHandlerIT extends BaseIT {
    @Test
    public void testCreateCategory() {
        model.Test test = TestUtils.createTest(4);
        test = testHandler.addTest(test);
        Article article = TestUtils.createArticle(11, persons[0]);
        articleHandler.addArticle(article);
        Category category = TestUtils.createCategory(3, article);
        Category receivedCategory = categoryHandler.createCategory(category);
        categoryHandler.addCategoryToTest(test, category);
        test = testHandler.getTest(test.getId());
        Assert.assertNotNull(test);
        Assert.assertNotNull(test.getCategories());
        Assert.assertEquals(test.getCategories().size(), 1);
        Assert.assertTrue(test.getCategories().containsValue(receivedCategory));
    }

    @Test
    public void testGetCategoryWithNotValidId() {
        Category receivedCategory = categoryHandler.getCategory(-1);
        Assert.assertNull(receivedCategory);
    }

    @Test
    public void testGetCategoryWithValidId() {
        Category receivedCategory = categoryHandler.getCategory(categories[0].getId());
        Assert.assertNotNull(receivedCategory);
        Assert.assertEquals(receivedCategory.getId(), categories[0].getId());
    }

    @Test
    public void testGetPathName() {
        Map<String, Integer> receivedMap = categoryHandler.getPathName();
        Assert.assertNotNull(receivedMap);
        Assert.assertTrue(receivedMap.size() >= categories.length);
    }

    @Test
    public void testGetCategoryByPathName() {
        Category receivedCategory = categoryHandler.getCategory(CATEGORY_PATHNAMES[1]);
        Assert.assertNotNull(receivedCategory);
        Assert.assertEquals(receivedCategory, categories[1]);
    }

    @Test
    public void testGetCategories() {
        List<Category> receivedList = categoryHandler.getCategories(tests[0].getId());
        Assert.assertNotNull(receivedList);
        Assert.assertEquals(receivedList.size(), 2);
    }

    @Test
    public void testGetCategoriesByTestPathName() {
        List<Category> receivedList = categoryHandler.getCategories(tests[0].getPathName());
        Assert.assertNotNull(receivedList);
        Assert.assertEquals(receivedList.size(), 2);
    }

    @Test
    public void testUpdateCategory() {
        model.Test test1 = TestUtils.createTest(5);
        test1 = testHandler.addTest(test1);
        Category categoryToUpdate = createCategory(5, test1, null);

        categoryToUpdate = categoryHandler.getCategory(categoryToUpdate.getId());
        Assert.assertNotNull(categoryToUpdate);
        Assert.assertFalse(categoryToUpdate.getHidden());
        Assert.assertEquals(categoryToUpdate.getOrderId(), categoryToUpdate.getId());

        categoryToUpdate.setPathName(CATEGORY_PATHNAMES[6]);
        categoryToUpdate.setName(CATEGORY_NAMES[6]);
        categoryToUpdate.setHidden(true);

        categoryHandler.updateCategory(categoryToUpdate);
        Category receivedCategory = categoryHandler.getCategory(categoryToUpdate.getId());

        Assert.assertNotNull(receivedCategory);
        Assert.assertEquals(receivedCategory.getName(), CATEGORY_NAMES[6]);
        Assert.assertEquals(receivedCategory.getPathName(), CATEGORY_PATHNAMES[6]);
        Assert.assertTrue(receivedCategory.getHidden());
    }

    @Test(enabled = false)
    public void testAddCategoryToTest() {
        model.Test test1 = TestUtils.createTest(6);
        model.Test test2 = TestUtils.createTest(17);
        test1 = testHandler.addTest(test1);
        test2 = testHandler.addTest(test2);
        Category categoryToUpdate = createCategory(18, test1, null);
        categoryToUpdate = categoryHandler.getCategory(categoryToUpdate.getId());
        categoryHandler.addCategoryToTest(test2, categoryToUpdate);
        test2 = testHandler.getTest(test2.getId());
        Assert.assertNotNull(test2);
        Assert.assertNotNull(test2.getCategories());
        Assert.assertEquals(test2.getCategories().size(), 1);
        Assert.assertTrue(test2.getCategories().containsValue(categoryToUpdate));
    }

    @Test
    public void testRemoveCategory() {
        model.Test test = TestUtils.createTest(13);
        test = testHandler.addTest(test);
        Article article = TestUtils.createArticle(12, persons[0]);
        articleHandler.addArticle(article);
        Category category = createCategory(13, test, article);
        category = categoryHandler.getCategory(category.getId());
        Assert.assertNotNull(category);

        categoryHandler.removeCategory(category);
        category = categoryHandler.getCategory(category.getId());
        Assert.assertNull(category);
    }

    @Test
    public void testGetDuplicateCategories() {
        model.Test test1 = TestUtils.createTest(18);
        model.Test test2 = TestUtils.createTest(19);
        test1 = testHandler.addTest(test1);
        test2 = testHandler.addTest(test2);
        Category category = createCategory(19, test1, null);
        category = categoryHandler.addCategoryToTest(test2, category);

        Map<String, Category> result = categoryHandler.getDuplicateCategories();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() > 0);
        Assert.assertNotNull(result.get(category.getPathName()));
        Assert.assertNotNull(result.get(category.getPathName()).getTests());
        Assert.assertTrue(result.get(category.getPathName()).getTests().size() == 2);
        Assert.assertTrue(result.get(category.getPathName()).getTests().get(0).equals(test1));
        Assert.assertTrue(result.get(category.getPathName()).getTests().get(1).equals(test2));
    }

    @Test
    public void testSwapCategories() {
        model.Test test = TestUtils.createTest(20);
        test = testHandler.addTest(test);

        Category category1 = createCategoryWithArticle(test, 20);
        Category category2 = createCategoryWithArticle(test, 21);

        int orderColumn1 = category1.getOrderId();
        int orderColumn2 = category2.getOrderId();
        categoryHandler.swapCategories(category1, category2);

        Category updatedCategory1 = categoryHandler.getCategory(category1.getId());
        Category updatedCategory2 = categoryHandler.getCategory(category2.getId());
        Assert.assertNotNull(updatedCategory1);
        Assert.assertNotNull(updatedCategory2);
        Assert.assertEquals(updatedCategory1.getOrderId(), orderColumn2);
        Assert.assertEquals(updatedCategory2.getOrderId(), orderColumn1);
    }

    @Test
    public void testUpdateCategoryCounts() {
        Category category = categoryHandler.getCategory(categories[1].getId());
        category.setQuestionsCount(0);
        category.setTestsCount(0);
        categoryHandler.updateCategory(category);
        category = categoryHandler.getCategory(categories[1].getId());
        Assert.assertEquals(category.getQuestionsCount(), 0);
        Assert.assertEquals(category.getTestsCount(), 0);

        categoryHandler.updateCategoryCounts(category);
        category = categoryHandler.getCategory(categories[1].getId());
        Assert.assertEquals(category.getQuestionsCount(), 2);
        Assert.assertEquals(category.getTestsCount(), 2);
    }
}

