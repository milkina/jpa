package ejb.exam;

import model.AbstractExam;
import model.TestExam;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseIT;
import utils.TestUtils;

import java.util.List;

public class ExamHandlerIT extends BaseIT {
    @Test
    public void testCreateExam() {
        TestExam exam = TestUtils.createTestExam(categories[0], persons[2], 30);
        AbstractExam result = examHandler.createExam(exam);
        Assert.assertNotNull(result);
        Assert.assertEquals(result, exam);
    }

    @Test
    public void testGetExams() {
        List<TestExam> result = examHandler.getExams(persons[0]);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 2);
    }
}
