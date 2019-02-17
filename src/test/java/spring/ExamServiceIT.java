package spring;

import model.AbstractExam;
import model.TestExam;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestUtils;

import java.util.List;

@Test
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class ExamServiceIT extends BaseIT {
    @Test
    public void testCreateExam() {
        TestExam exam = TestUtils.createTestExam(categories[0], persons[2], 30);
        AbstractExam result = examService.createExam(exam);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, exam);
    }

    @Test
    public void testGetExams() {
        List<TestExam> result = examService.getExams(persons[0]);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);
    }
}
