package data.questionEntry;

import model.Category;
import model.QuestionEntry;
import model.Test;
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
    List<QuestionEntry> getAllQuestions(Category category);

    QuestionEntry updateQuestionEntry(QuestionEntry questionEntry);

    QuestionEntry addQuestionEntry(QuestionEntry questionEntry);

    void deleteQuestionEntry(QuestionEntry questionEntry);

    QuestionEntry getQuestionEntry(int id);

    List<QuestionEntry> getNotAnsweredQuestions(Category category,
                                                Person person);

    List<QuestionEntry> getAnsweredQuestions(Category category,
                                             Person person);

    QuestionEntry getPreviousQuestionEntry(int orderColumn);

    void moveBatch(Category oldCategory, Category category,
                   Integer from, Integer to);

    List<Test> getQuestionEntryTests(int id);

    Test getFirstQuestionEntryTest(int id);
}
