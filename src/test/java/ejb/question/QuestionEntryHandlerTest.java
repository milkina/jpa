package ejb.question;

import main.java.data.questionEntry.QuestionEntryBean;
import main.java.data.questionEntry.QuestionEntryBeanI;
import main.java.data.questionEntry.QuestionEntryHandler;
import main.java.model.Category;
import main.java.model.person.Person;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

/**
 * Created by Tatyana on 29.04.2016.
 */
public class QuestionEntryHandlerTest {
    @DataProvider
    public static Object[][] data() {
        return new Object[][]
                {{new Category(), null, "NOT_ANSWERED"},
                {new Category(), null, "ANSWERED"},
                {new Category(), null, "ALL"},
                {new Category(), new Person(), "ALL"},
                       };
    }

    @Test
    public void testGetNotAnsweredQuestions() {
        QuestionEntryBeanI questionEntryBean = mock(QuestionEntryBean.class);
        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler(questionEntryBean);
        questionEntryHandler.getQuestions(new Category(), new Person(), "NOT_ANSWERED");

        verify(questionEntryBean).getNotAnsweredQuestions((Category)anyObject(),(Person)anyObject());
    }

    @Test
    public void testGetAnsweredQuestions() {
        QuestionEntryBeanI questionEntryBean = mock(QuestionEntryBean.class);
        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler(questionEntryBean);
        questionEntryHandler.getQuestions(new Category(), new Person(), "ANSWERED");

        verify(questionEntryBean).getAnsweredQuestions((Category) anyObject(), (Person) anyObject());
    }

    @Test(dataProvider = "data")
    public void testGetQuestions(Category category,Person person,String questionType) {
        QuestionEntryBeanI questionEntryBean = mock(QuestionEntryBean.class);
        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler(questionEntryBean);
        questionEntryHandler.getQuestions(category, person, questionType);

        verify(questionEntryBean).getAllQuestions((Category) anyObject());
    }

}
