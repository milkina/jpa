package spring;

import model.Category;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 13.03.2013
 * Time: 20:07:36
 * To change this template use File | Settings | File Templates.
 */
@Test
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class CourseServiceIT extends BaseIT {
    @Test
    public void testGetAllTests() {
        Map<Integer, model.Test> receivedList = courseService.getAllCourses();
        Assert.assertNotNull(receivedList);
        Assert.assertTrue(receivedList.size() >= tests.length);
        for (model.Test test : tests) {
            model.Test receivedTest = receivedList.get(test.getId());
            Assert.assertNotNull(receivedTest);
            Assert.assertEquals(receivedTest, test);
        }
    }

    @Test
    public void testGetPathName() {
        Map<String, Integer> receivedMap = courseService.getPathName();
        Assert.assertNotNull(receivedMap);
        Assert.assertTrue(receivedMap.size() >= tests.length);
        for (model.Test test : tests) {
            Integer receivedId = receivedMap.get(test.getPathName());
            Assert.assertNotNull(receivedId);
            Assert.assertEquals(receivedId, test.getId());
        }
    }

    @Test
    public void testSetUpdatedDate() {
        model.Test test = TestUtils.createTest(3);
        test = courseService.create(test);
        Date oldDate = test.getUpdatedDate();
        courseService.setUpdatedDate(test);
        model.Test receivedTest = courseService.getCourse(test.getId());

        Assert.assertNotNull(receivedTest);
        Assert.assertNotNull(receivedTest.getUpdatedDate());
        Assert.assertNotEquals(receivedTest.getUpdatedDate(), oldDate);
    }

    @Test
    public void testGetAllTestsWithPath() {
        Map<String, model.Test> result = courseService.getAllCoursesWithPath();
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
        model.Test test = TestUtils.createTest(21);
        test = courseService.create(test);
        Category category = createCategoryWithArticle(test, 22);
        test = courseService.getCourse(test.getId());

        courseService.removeCategoryFromCourse(test, category);

        test = courseService.getCourse(test.getId());
        Assert.assertNotNull(test);
        Assert.assertTrue(test.getCategories().isEmpty());
    }

    @Test
    public void testSwapCourses() {
        model.Test course1 = TestUtils.createTest(22);
        course1 = courseService.create(course1);
        model.Test course2 = TestUtils.createTest(23);
        course2 = courseService.create(course2);

        int orderColumn1 = course1.getOrderId();
        int orderColumn2 = course2.getOrderId();
        courseService.swapCourses(course1, course2);

        model.Test updatedCourse1 = courseService.getCourse(course1.getId());
        model.Test updatedCourse2 = courseService.getCourse(course2.getId());
        Assert.assertNotNull(updatedCourse1);
        Assert.assertNotNull(updatedCourse2);
        Assert.assertEquals(updatedCourse1.getOrderId(), orderColumn2);
        Assert.assertEquals(updatedCourse2.getOrderId(), orderColumn1);
    }

    @Test
    public void testDeleteEmptyTests() {
        model.Test test = TestUtils.createTest(24);
        test = courseService.create(test);
        Integer id = test.getId();

        model.Test result = courseService.getCourse(id);
        Assert.assertNotNull(result);
        Assert.assertEquals(id, result.getId());

        courseService.deleteCourse(test);
        result = courseService.getCourse(id);
        Assert.assertNull(result);
    }

    @Test
    public void testDeleteNotEmptyTests() {
        model.Test test = TestUtils.createTest(25);
        test = courseService.create(test);
        Category category = createCategoryWithArticle(test, 23);

        Integer id = test.getId();

        test = courseService.getCourse(id);
        Assert.assertNotNull(test);
        Assert.assertEquals(id, test.getId());

        courseService.deleteCourse(test);
        model.Test result = courseService.getCourse(id);
        Assert.assertNotNull(result);
        category = categoryService.findOne(category.getId());
        Assert.assertNotNull(category);
    }

    @Test
    public void testGetTestByQuestion() {
        model.Test result = courseService.getCourseByQuestion(testQuestionEntries[0]);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, tests[0]);
    }

    @Test
    public void testGetAllTestsWithNotEmptyTests() {
        List<model.Test> result = courseService.getAllTestsWithNotEmptyTests();
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0), tests[0]);
    }

    @Test
    public void testGetAllCoursesWithNotEmptyQuestions() {
        List<model.Test> result = courseService.getAllCoursesWithNotEmptyQuestions();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() >= 3);
    }
}
