package main.java.data.test;

import main.java.model.Category;
import main.java.model.Test;

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
    EntityManager entityManager;

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
}
