package data.test;

import model.AbstractQuestionEntry;
import model.Category;
import model.Test;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 13.03.2013
 * Time: 19:48:13
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class TestBean implements TestBeanI {
    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;

    public List<Object[]> getAllTests() {
        Query query = entityManager.createNamedQuery("Test.findAllTests");
        return query.getResultList();
    }

    public Test addTest(Test test) {
        entityManager.persist(test);
        test.setOrderId(test.getId());
        return test;
    }

    public List<Object[]> getPathName() {
        Query query = entityManager.createNamedQuery("Test.findPathName");
        return query.getResultList();
    }

    public void setUpdatedDate(Test test) {
        test.setUpdatedDate(new Date());
        entityManager.merge(test);
    }

    public Test updateTest(Test test) {
        return entityManager.merge(test);
    }

    public Test getTest(int id) {
        return entityManager.find(Test.class, id);
    }

    public void removeCategoryFromTest(Test test, Category category) {
        test = entityManager.merge(test);
        category = entityManager.merge(category);
        test.removeCategory(category);
        category.removeTest(test);
    }

    public boolean deleteTest(Test test) {
        test = entityManager.merge(test);
        if (test.getCategories() == null || test.getCategories().isEmpty()) {
            entityManager.remove(test);
            return true;
        }
        return false;
    }

    public Test getTestByQuestion(AbstractQuestionEntry questionEntry) {
        Query query = entityManager.createNamedQuery("Test.getTestByQuestion");
        query.setParameter("param", questionEntry);
        List<Test> tests = query.getResultList();
        return tests.get(0);
    }

    public List<Test> getAllTestsWithNotEmptyTests() {
        Query query = entityManager.createNamedQuery("Test.findAllWithNotEmptyTests");
        return query.getResultList();
    }

    public List<Test> getAllCoursesWithNotEmptyQuestions() {
        Query query = entityManager.createNamedQuery("Course.findAllWithNotEmptyQuestions");
        return query.getResultList();
    }

    private List<Test> getPreviousTests(String testPath) {
        Query query = entityManager.createNamedQuery("Test.getPreviousTests");
        query.setParameter("param", testPath);
        return query.getResultList();
    }

    private List<Test> getNextTests(String testPath) {
        Query query = entityManager.createNamedQuery("Test.getNextTests");
        query.setParameter("param", testPath);
        return query.getResultList();
    }

    public void moveTestUp(String testPath, String stopTestPath) {
        List<Test> tests = getPreviousTests(testPath);
        for (int i = tests.size() - 1; i > 0; i--) {
            if (stopTestPath.equals(tests.get(i - 1).getPathName())) {
                break;
            }
            swapTests(tests.get(i), tests.get(i - 1));

            Test tmp = tests.get(i);
            tests.set(i, tests.get(i - 1));
            tests.set(i - 1, tmp);
        }
    }

    public void moveTestDown(String testPath, String stopTestPath) {
        List<Test> tests = getNextTests(testPath);
        for (int i = 0; i < tests.size() - 1; i++) {
            swapTests(tests.get(i), tests.get(i + 1));

            Test tmp = tests.get(i);
            tests.set(i, tests.get(i + 1));
            tests.set(i + 1, tmp);
            if (stopTestPath.equals(tests.get(i).getPathName())) {
                break;
            }
        }
    }

    private void swapTests(Test test1, Test test2) {
        int id1 = test1.getOrderId();
        int id2 = test2.getOrderId();
        test1.setOrderId(id2);
        test2.setOrderId(id1);
    }

    public Test getCourse(Category category) {
        category = entityManager.merge(category);
        return category.getTests().get(0);
    }
}
