package spring.services.question;

import model.AbstractQuestionEntry;
import model.Category;
import model.Question;
import model.Test;
import model.person.Person;

import java.util.List;
import java.util.Map;

public interface QuestionService {
    List<AbstractQuestionEntry> getAllQuestions(Category category);

    List<AbstractQuestionEntry> getAllQuestions(List<Category> categories);

    List<AbstractQuestionEntry> getAllTestQuestions(Category category);

    List<AbstractQuestionEntry> getAnsweredQuestions(Category category, Person person);

    List<AbstractQuestionEntry> getAnsweredQuestions(List<Category> category, Person person);

    List<AbstractQuestionEntry> getNotAnsweredQuestions(Category category, Person person);

    List<AbstractQuestionEntry> getNotAnsweredQuestions(List<Category> categories, Person person);

    List<AbstractQuestionEntry> getQuestions(Category category,
                                             Person person,
                                             String questionType);

    List<AbstractQuestionEntry> getQuestions(List<Category> categories,
                                             Person person,
                                             String questionType);

    List<AbstractQuestionEntry> getQuestionsForQuiz(String[] categoryPaths,
                                                    Person person,
                                                    String questionType,
                                                    int count);

    void moveBatch(Category oldCategory, Category category,
                   Integer from, Integer to);

    Map<Integer, AbstractQuestionEntry> getAllAbstractQuestionsMap(Category category);

    AbstractQuestionEntry getQuestionEntry(int questionId);

    void removeAnswers(AbstractQuestionEntry questionEntry);

    void moveQuestionEntryUp(int questionId);

    AbstractQuestionEntry updateQuestionEntry(AbstractQuestionEntry questionEntry);

    void deleteQuestionEntry(int id);

    AbstractQuestionEntry addQuestionEntry(AbstractQuestionEntry questionEntry);

    Test getFirstQuestionEntryTest(int id);

    void changeTestQuestionToQuestion(int id);

    void changeQuestionToTestQuestion(int id);

    List<AbstractQuestionEntry> getQuestionsForExam(String[] categoryPaths, int count);

    List<AbstractQuestionEntry> getNotApprovedQuestions();

    List<AbstractQuestionEntry> getPersonQuestions(int person);

    void addQuestionEntries(AbstractQuestionEntry[] questionEntries);

    void swapQuestionEntries(AbstractQuestionEntry q1, AbstractQuestionEntry q2);

    AbstractQuestionEntry getPreviousQuestionEntry(int orderColumn);

    Question getQuestion(int id);
}
