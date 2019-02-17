package spring;

import model.Category;
import model.article.Article;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
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

@Test
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class CategoryServiceIT extends BaseIT {
    @Test
    public void testCreateCategory() {
        model.Test course = TestUtils.createTest(4);
        course = courseService.create(course);
        Article article = TestUtils.createArticle(11, persons[0]);
        articleService.addArticle(article);
        Category category = TestUtils.createCategory(3, article);
        category = categoryService.create(category);
        category = categoryService.addCategoryToCourse(course, category);
        course = courseService.getCourse(course.getId());
        Assert.assertNotNull(course);
        Assert.assertNotNull(course.getCategories());
        Assert.assertEquals(course.getCategories().size(), 1);
        Assert.assertTrue(course.getCategories().containsValue(category));
    }

    @Test
    public void testGetPathName() {
        Map<String, Integer> receivedMap = categoryService.getPathName();
        Assert.assertNotNull(receivedMap);
        Assert.assertTrue(receivedMap.size() >= categories.length);
    }

    @Test
    public void testGetCategoryByPathName() {
        Category receivedCategory = categoryService.getCategory(CATEGORY_PATHNAMES[1]);
        Assert.assertNotNull(receivedCategory);
        Assert.assertEquals(receivedCategory, categories[1]);
    }

    @Test
    public void testGetCategoriesByTestPathName() {
        List<Category> receivedList = categoryService.getCategories(tests[0].getPathName());
        Assert.assertNotNull(receivedList);
        Assert.assertEquals(receivedList.size(), 2);
    }

    @Test
    public void testUpdateCategory() {
        model.Test test1 = TestUtils.createTest(5);
        test1 = courseService.create(test1);
        Category categoryToUpdate = createCategory(5, test1, null);

        categoryToUpdate = categoryService.getCategory(categoryToUpdate.getPathName());
        Assert.assertNotNull(categoryToUpdate);
        Assert.assertFalse(categoryToUpdate.getHidden());
        Assert.assertEquals(Integer.valueOf(categoryToUpdate.getOrderId()), categoryToUpdate.getId());

        categoryToUpdate.setPathName(CATEGORY_PATHNAMES[6]);
        categoryToUpdate.setName(CATEGORY_NAMES[6]);
        categoryToUpdate.setHidden(true);

        categoryService.update(categoryToUpdate);
        Category receivedCategory = categoryService.getCategory(categoryToUpdate.getPathName());

        Assert.assertNotNull(receivedCategory);
        Assert.assertEquals(receivedCategory.getName(), CATEGORY_NAMES[6]);
        Assert.assertEquals(receivedCategory.getPathName(), CATEGORY_PATHNAMES[6]);
        Assert.assertTrue(receivedCategory.getHidden());
    }

    @Test
    public void testAddCategoryToTest() {
        model.Test test1 = TestUtils.createTest(6);
        model.Test test2 = TestUtils.createTest(17);
        test1 = courseService.create(test1);
        test2 = courseService.create(test2);
        Category categoryToUpdate = createCategory(18, test1, null);
        categoryToUpdate = categoryService.getCategory(categoryToUpdate.getPathName());
        categoryService.addCategoryToCourse(test2, categoryToUpdate);
        test2 = courseService.getCourse(test2.getId());
        Assert.assertNotNull(test2);
        Assert.assertNotNull(test2.getCategories());
        Assert.assertEquals(test2.getCategories().size(), 1);
        Assert.assertTrue(test2.getCategories().containsValue(categoryToUpdate));
    }

    @Test
    public void testRemoveCategory() {
        model.Test test = TestUtils.createTest(13);
        test = courseService.create(test);
        Article article = TestUtils.createArticle(12, persons[0]);
        articleService.addArticle(article);
        Category category = createCategory(13, test, article);
        category = categoryService.getCategory(category.getPathName());
        Assert.assertNotNull(category);

        categoryService.removeCategory(category);
        category = categoryService.getCategory(category.getPathName());
        Assert.assertNull(category);
    }

    @Test
    public void testGetDuplicateCategories() {
        model.Test test1 = TestUtils.createTest(18);
        model.Test test2 = TestUtils.createTest(19);
        test1 = courseService.create(test1);
        test2 = courseService.create(test2);
        Category category = createCategory(19, test1, null);
        category = categoryService.addCategoryToCourse(test2, category);

        Map<String, Category> result = categoryService.getDuplicateCategories();
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
        test = courseService.create(test);

        Category category1 = createCategoryWithArticle(test, 20);
        Category category2 = createCategoryWithArticle(test, 21);

        int orderColumn1 = category1.getOrderId();
        int orderColumn2 = category2.getOrderId();
        categoryService.swapCategories(category1, category2);

        Category updatedCategory1 = categoryService.getCategory(category1.getPathName());
        Category updatedCategory2 = categoryService.getCategory(category2.getPathName());
        Assert.assertNotNull(updatedCategory1);
        Assert.assertNotNull(updatedCategory2);
        Assert.assertEquals(updatedCategory1.getOrderId(), orderColumn2);
        Assert.assertEquals(updatedCategory2.getOrderId(), orderColumn1);
    }

    @Test
    public void testUpdateCategoryCounts() {
        Category category = categoryService.getCategory(categories[1].getPathName());
        category.setQuestionsCount(0);
        category.setTestsCount(0);
        categoryService.update(category);
        category = categoryService.getCategory(categories[1].getPathName());
        Assert.assertEquals(category.getQuestionsCount(), 0);
        Assert.assertEquals(category.getTestsCount(), 0);

        categoryService.updateCategoryCounts(category);
        category = categoryService.getCategory(categories[1].getPathName());
        Assert.assertEquals(category.getQuestionsCount(), 2);
        Assert.assertEquals(category.getTestsCount(), 2);
    }
}

