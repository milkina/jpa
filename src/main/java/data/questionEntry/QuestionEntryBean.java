package data.questionEntry;

import model.AbstractQuestionEntry;
import model.Answer;
import model.Category;
import model.Question;
import model.QuestionEntry;
import model.Test;
import model.TestQuestionEntry;
import model.person.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static util.AllConstants.GET_ALL_QUESTION_ENTRIES;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 02.10.2012
 * Time: 10:03:48
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class QuestionEntryBean implements QuestionEntryBeanI {
    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;

    public List<AbstractQuestionEntry> getAllQuestions(Category category) {
        Query query = entityManager.createNamedQuery(GET_ALL_QUESTION_ENTRIES);
        query.setParameter("param", category);
        return query.getResultList();
    }

    public List<AbstractQuestionEntry> getAllAbstractQuestions(Category category) {
        Query query = entityManager.createNamedQuery("AbstractQuestionEntry.getAllQuestions");
        query.setParameter("param", category);
        return query.getResultList();
    }

    public List<AbstractQuestionEntry> getAllTestQuestions(Category category) {
        Query query = entityManager.createNamedQuery("TestQuestionEntry.getAllQuestions");
        query.setParameter("param", category);
        return query.getResultList();
    }

    public List<AbstractQuestionEntry> getAnsweredQuestions(Category category, Person person) {
        Query query = entityManager.createNamedQuery(
                "QuestionEntry.GET_ANSWERED_QUESTION_ENTRIES");
        query.setParameter("category", category);
        query.setParameter("person", person);
        return query.getResultList();
    }

    public List<AbstractQuestionEntry> getNotAnsweredQuestions(Category category, Person person) {
        List<AbstractQuestionEntry> list;
        Query query = entityManager.createNamedQuery(
                "QuestionEntry.GET_NOT_ANSWERED_QUESTION_ENTRIES");
        query.setParameter("category", category);
        query.setParameter("person", person);
        list = query.getResultList();
        return list;
    }

    public AbstractQuestionEntry updateQuestionEntry(AbstractQuestionEntry questionEntry) {
        entityManager.merge(questionEntry.getCategory());
        return entityManager.merge(questionEntry);
    }

    public AbstractQuestionEntry addQuestionEntry(AbstractQuestionEntry questionEntry) {
        entityManager.persist(questionEntry);
        questionEntry.setOrderColumn(questionEntry.getId());
        entityManager.merge(questionEntry.getCategory());
        return questionEntry;
    }

    public void deleteQuestionEntry(AbstractQuestionEntry questionEntry) {
        questionEntry = entityManager.merge(questionEntry);
        entityManager.remove(questionEntry);
    }

    public AbstractQuestionEntry getQuestionEntry(int id) {
        return entityManager.find(AbstractQuestionEntry.class, id);
    }

    public AbstractQuestionEntry getPreviousQuestionEntry(int orderColumn) {
        Query query = entityManager.createNamedQuery(
                "QuestionEntry.getPreviousQuestionEntry");
        query.setParameter("param", orderColumn);
        List<QuestionEntry> list = query.getResultList();
        AbstractQuestionEntry result = null;
        if (!list.isEmpty()) {
            result = list.get(0);
        }
        return result;
    }

    public void moveBatch(Category oldCategory, Category category,
                          Integer from, Integer to) {
        List<AbstractQuestionEntry> list = getAllAbstractQuestions(oldCategory);
        for (int i = from - 1; i < to; i++) {
            AbstractQuestionEntry questionEntry = list.get(i);
            questionEntry.setCategory(category);
        }
    }

    public List<Test> getQuestionEntryTests(int id) {
        QuestionEntry questionEntry =
                entityManager.find(QuestionEntry.class, id);
        return questionEntry.getCategory().getTests();
    }

    public Test getFirstQuestionEntryTest(int id) {
        AbstractQuestionEntry questionEntry =
                entityManager.find(AbstractQuestionEntry.class, id);
        return questionEntry.getCategory().getTests().get(0);
    }

    public void removeAnswer(Answer answer) {
        entityManager.remove(entityManager.merge(answer));
    }

    public Answer getAnswer(int id) {
        return entityManager.find(Answer.class, id);
    }

    public Question getQuestion(int id) {
        return entityManager.find(Question.class, id);
    }

    public void changeQuestionType(int id, String type) {
        Query query = entityManager.createNativeQuery("UPDATE QUESTIONS SET QTYPE = ? WHERE entry_id = ?");
        query.setParameter(1, type);
        query.setParameter(2, id);
        query.executeUpdate();
    }

    public List<TestQuestionEntry> getQuestionsForExam(String categoryPath) {
        Query query = entityManager.createNamedQuery(
                "TestQuestionEntry.getAllQuestionsForExam");
        query.setParameter("param", categoryPath);
        return query.getResultList();
    }

    public List<AbstractQuestionEntry> getNotApprovedQuestions() {
        Query query = entityManager.createNamedQuery(
                "AbstractQuestionEntry.getNotApprovedQuestions");
        return query.getResultList();
    }

    public List<AbstractQuestionEntry> getPersonQuestions(int person) {
        Query query = entityManager.createNamedQuery(
                "AbstractQuestionEntry.getPersonQuestions");
        query.setParameter("param", person);
        return query.getResultList();
    }
}

