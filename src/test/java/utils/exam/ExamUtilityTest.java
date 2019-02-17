package utils.exam;

import model.AbstractQuestionEntry;
import model.Category;
import model.QuestionEntry;
import model.QuestionExam;
import model.article.Article;
import model.person.Person;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import spring.services.person.PersonService;
import util.exam.ExamUtility;
import utils.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Tatyana on 30.04.2016.
 */
public class ExamUtilityTest {
    private QuestionExam exam;
    Category category;
    private List<QuestionEntry> questionEntries;
    private int questionsArraySize = 4;
    private Person person;

    @BeforeTest
    public void beforeMethod() {
        model.Test test = TestUtils.createTest(0);
        person = new Person();
        Article article = TestUtils.createArticle(0, person);
        category = TestUtils.createCategory(0, article);
        category.addTest(test);


        questionEntries = new ArrayList<>();
        for (int i = 0; i < questionsArraySize; i++) {
            questionEntries.add(TestUtils.createQuestionEntry(i, category, person));
        }
        exam = new QuestionExam();
        exam.setQuestionEntries(questionEntries);

        List<AbstractQuestionEntry> answeredQuestions = new ArrayList<>();
        answeredQuestions.add(questionEntries.get(0));
        answeredQuestions.add(questionEntries.get(3));
        person.setAnsweredQuestions(answeredQuestions);
    }

    @DataProvider
    public Object[][] getCurrentQuestionEntry() {
        return new Object[][]
                {{null, null, null},
                        {new ArrayList<QuestionEntry>(), null, null},
                        {new ArrayList<QuestionEntry>(), 1, null},
                        {questionEntries, null, null},
                        {questionEntries, 0, questionEntries.get(0)},
                        {questionEntries, 3, questionEntries.get(3)},
                };
    }

    @Test(dataProvider = "getCurrentQuestionEntry")
    public void testGetCurrentQuestionEntry(List<QuestionEntry> list, Integer currentNumber, QuestionEntry expected) {
        QuestionExam exam = new QuestionExam();
        exam.setQuestionEntries(list);
        exam.setCurrentNumber(currentNumber);
        exam.setCategory(category);
        AbstractQuestionEntry result = ExamUtility.getCurrentQuestionEntry(exam);
        Assert.assertEquals(result, expected);
    }

    @DataProvider
    public Object[][] isCurrentQuestionChecked() {
        return new Object[][]
                {{null, null, null, false},
                        {new QuestionExam(), null, null, false},
                        {exam, null, null, false},
                        {exam, new Person(), null, false},
                        {exam, person, null, false},
                        {exam, person, 0, true},
                        {exam, person, 1, false},
                        {exam, person, 3, true},
                };
    }

    @Test(dataProvider = "isCurrentQuestionChecked")
    public void testIsCurrentQuestionChecked(QuestionExam exam, Person person, Integer currentNumber, boolean expectedResult) {
        if (exam != null) {
            exam.setPerson(person);
            exam.setCurrentNumber(currentNumber);
        }
        PersonService personBeanI = mock(PersonService.class);
        List<AbstractQuestionEntry> answeredQuestions = person != null ? person.getAnsweredQuestions() : null;
        when(personBeanI.findAnsweredQuestions(anyInt())).thenReturn(answeredQuestions);
        boolean result = ExamUtility.isCurrentQuestionChecked(exam, answeredQuestions);
        Assert.assertEquals(result, expectedResult);
    }

    @DataProvider
    public Object[][] isLastQuestion() {
        return new Object[][]
                {{0, false},
                        {3, true},
                        {2, false}
                };
    }

    @Test(dataProvider = "isLastQuestion")
    public void testIsLastQuestion(int currentNumber, boolean expectedResult) {
        exam.setCurrentNumber(currentNumber);
        boolean result = ExamUtility.isLastQuestion(exam);
        Assert.assertEquals(result, expectedResult);
    }
}
