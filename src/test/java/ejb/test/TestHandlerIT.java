package ejb.test;

import main.java.model.Category;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseIT;
import utils.TestUtils;

import java.util.Date;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 13.03.2013
 * Time: 20:07:36
 * To change this template use File | Settings | File Templates.
 */
public class TestHandlerIT extends BaseIT {
    @Test
    public void testGetAllTests() {
        Map<Integer, main.java.model.Test> receivedList = testHandler.getAllTests();
        Assert.assertNotNull(receivedList);
        Assert.assertTrue(receivedList.size() >= tests.length);
        for (main.java.model.Test test : tests) {
            main.java.model.Test receivedTest = receivedList.get(test.getId());
            Assert.assertNotNull(receivedTest);
            Assert.assertEquals(receivedTest, test);
        }
    }

    @Test
    public void testGetPathName() {
        Map<String, Integer> receivedMap = testHandler.getPathName();
        Assert.assertNotNull(receivedMap);
        Assert.assertTrue(receivedMap.size() >= tests.length);
        for (main.java.model.Test test : tests) {
            Integer receivedId = receivedMap.get(test.getPathName());
            Assert.assertNotNull(receivedId);
            Assert.assertEquals(receivedId, Integer.valueOf(test.getId()));
        }
    }

    @Test
    public void testSetUpdatedDate() {
        main.java.model.Test test = TestUtils.createTest(3);
        test = testHandler.addTest(test);
        Date oldDate = test.getUpdatedDate();
        testHandler.setUpdatedDate(test);
        main.java.model.Test receivedTest = testHandler.getTest(test.getId());

        Assert.assertNotNull(receivedTest);
        Assert.assertNotNull(receivedTest.getUpdatedDate());
        Assert.assertNotEquals(receivedTest.getUpdatedDate(), oldDate);
    }

    @Test
    public void testGetAllTestsWithPath() {
        Map<String, main.java.model.Test> result = testHandler.getAllTestsWithPath();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() >= 3);
        Assert.assertNotNull(result.get(tests[0].getPathName()));
        Assert.assertNotNull(result.get(tests[1].getPathName()));
        Assert.assertNotNull(result.get(tests[2].getPathName()));
        Assert.assertEquals(result.get(tests[0].getPathName()), tests[0]);
        Assert.assertEquals(result.get(tests[1].getPathName()), tests[1]);
        Assert.assertEquals(result.get(tests[2].getPathName()), tests[2]);
    }

    @Test
    public void testRemoveCategoryFromTest() {
        main.java.model.Test test = TestUtils.createTest(21);
        test = testHandler.addTest(test);
        Category category = createCategoryWithArticle(test, 22);
        test = testHandler.getTest(test.getId());

        testHandler.removeCategoryFromTest(test, category);

        test = testHandler.getTest(test.getId());
        Assert.assertNotNull(test);
        Assert.assertTrue(test.getCategories().isEmpty());

    }

    @Test
    public void testSwapTests() {
        main.java.model.Test test1 = TestUtils.createTest(22);
        test1 = testHandler.addTest(test1);
        main.java.model.Test test2 = TestUtils.createTest(23);
        test2 = testHandler.addTest(test2);

        int orderColumn1 = test1.getOrderId();
        int orderColumn2 = test2.getOrderId();
        testHandler.swapTests(test1, test2);

        main.java.model.Test updatedTest1 = testHandler.getTest(test1.getId());
        main.java.model.Test updatedTest2 = testHandler.getTest(test2.getId());
        Assert.assertNotNull(updatedTest1);
        Assert.assertNotNull(updatedTest2);
        Assert.assertEquals(updatedTest1.getOrderId(), orderColumn2);
        Assert.assertEquals(updatedTest2.getOrderId(), orderColumn1);
    }

    @Test
    public void testDeleteEmptyTests() {
        main.java.model.Test test = TestUtils.createTest(24);
        test = testHandler.addTest(test);
        int id = test.getId();

        main.java.model.Test result = testHandler.getTest(id);
        Assert.assertNotNull(result);
        Assert.assertEquals(id, result.getId());

        testHandler.deleteTest(test);
        result = testHandler.getTest(id);
        Assert.assertNull(result);
    }

    @Test
    public void testDeleteNotEmptyTests() {
        main.java.model.Test test = TestUtils.createTest(25);
        test = testHandler.addTest(test);
        Category category = createCategoryWithArticle(test, 23);

        int id = test.getId();

        test = testHandler.getTest(id);
        Assert.assertNotNull(test);
        Assert.assertEquals(id, test.getId());

        testHandler.deleteTest(test);
        main.java.model.Test result = testHandler.getTest(id);
        Assert.assertNotNull(result);
        category = categoryHandler.getCategory(category.getId());
        Assert.assertNotNull(category);
    }
}
