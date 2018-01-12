package data.questionEntry;

import model.Category;
import model.QuestionEntry;
import model.Test;
import model.person.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static util.AllConstants.*;

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

    public List<QuestionEntry> getAllQuestions(Category category) {
        List<QuestionEntry> list;
        Query query = entityManager.createNamedQuery(GET_ALL_QUESTION_ENTRIES);
        query.setParameter("param", category);
        list = query.getResultList();
        return list;
    }

    public List<QuestionEntry> getAnsweredQuestions(Category category, Person person) {
        List<QuestionEntry> list;
        Query query = entityManager.createNamedQuery(
                "QuestionEntry.GET_ANSWERED_QUESTION_ENTRIES");
        query.setParameter("category", category);
        query.setParameter("person", person);
        list = query.getResultList();
        return list;
    }

    public List<QuestionEntry> getNotAnsweredQuestions(Category category, Person person) {
        List<QuestionEntry> list;
        Query query = entityManager.createNamedQuery(
                "QuestionEntry.GET_NOT_ANSWERED_QUESTION_ENTRIES");
        query.setParameter("category", category);
        query.setParameter("person", person);
        list = query.getResultList();
        return list;
    }

    public QuestionEntry updateQuestionEntry(QuestionEntry questionEntry) {
        return entityManager.merge(questionEntry);

    }

    public QuestionEntry addQuestionEntry(QuestionEntry questionEntry) {
        entityManager.persist(questionEntry);
        questionEntry.setOrderColumn(questionEntry.getId());
        return questionEntry;
    }

    public void deleteQuestionEntry(QuestionEntry questionEntry) {
        entityManager.remove(entityManager.merge(questionEntry));
    }

    public QuestionEntry getQuestionEntry(int id) {
        return entityManager.find(QuestionEntry.class, id);
    }

    public QuestionEntry getPreviousQuestionEntry(int orderColumn) {
        Query query = entityManager.createNamedQuery(
                "QuestionEntry.getPreviousQuestionEntry");
        query.setParameter("param", orderColumn);
        List<QuestionEntry> list = query.getResultList();
        QuestionEntry result = null;
        if (!list.isEmpty()) {
            result = list.get(0);
        }
        return result;
    }

    public void moveBatch(Category oldCategory, Category category,
                          Integer from, Integer to) {
        List<QuestionEntry> list = getAllQuestions(oldCategory);
        for (int i = from - 1; i < to; i++) {
            QuestionEntry questionEntry = list.get(i);
            questionEntry.setCategory(category);
        }
    }

    public List<Test> getQuestionEntryTests(int id) {
        QuestionEntry questionEntry =
                entityManager.find(QuestionEntry.class, id);
        return questionEntry.getCategory().getTests();
    }

    public Test getFirstQuestionEntryTest(int id) {
        QuestionEntry questionEntry =
                entityManager.find(QuestionEntry.class, id);
        return questionEntry.getCategory().getTests().get(0);
    }
}

