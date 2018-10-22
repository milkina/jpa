package data.questionEntry;

import model.*;
import model.person.Person;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 02.10.2012
 * Time: 10:05:56
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface QuestionEntryBeanI {
    List<AbstractQuestionEntry> getAllQuestions(Category category);

    List<AbstractQuestionEntry> getAllAbstractQuestions(Category category);

    List<AbstractQuestionEntry> getAllTestQuestions(Category category);

    AbstractQuestionEntry updateQuestionEntry(AbstractQuestionEntry questionEntry);

    AbstractQuestionEntry addQuestionEntry(AbstractQuestionEntry questionEntry);

    void deleteQuestionEntry(AbstractQuestionEntry questionEntry);

    AbstractQuestionEntry getQuestionEntry(int id);

    List<AbstractQuestionEntry> getNotAnsweredQuestions(Category category,
                                                        Person person);

    List<AbstractQuestionEntry> getAnsweredQuestions(Category category,
                                                     Person person);

    AbstractQuestionEntry getPreviousQuestionEntry(int orderColumn);

    void moveBatch(Category oldCategory, Category category,
                   Integer from, Integer to);

    List<Test> getQuestionEntryTests(int id);

    Test getFirstQuestionEntryTest(int id);

    void removeAnswer(Answer answer);

    Answer getAnswer(int id);

    Question getQuestion(int id);

    void changeQuestionType(int id, String type);

    List<TestQuestionEntry> getQuestionsForExam(Category category, int count);
}
