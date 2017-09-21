package main.java.data.category;

import main.java.model.Category;
import main.java.model.Test;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 29.09.2012
 * Time: 20:27:36
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class CategoryBean implements CategoryBeanI {
    @PersistenceContext(unitName = "primary")
    EntityManager entityManager;

    public Category createCategory(Category category) {
        entityManager.persist(category);
        category.setOrderId(category.getId());
        return category;
    }

    public Category getCategory(int id) {
        return entityManager.find(Category.class, id);
    }

    public void updateCategory(Category c) {
        entityManager.merge(c);
    }

    public List<Object[]> getPathName() {
        Query query = entityManager.createNamedQuery("Category.findPathName");
        return query.getResultList();
    }

    public Category getCategory(String pathName) {
        Query query = entityManager.createNamedQuery("Category.findByPathName");
        query.setParameter("p", pathName);
        return (Category) query.getSingleResult();
    }

    public List<Category> getCategories(int testId) {
        Query query = entityManager.createNamedQuery("Category.findCategories");
        query.setParameter("p", testId);
        return query.getResultList();
    }

    public List<Category> getCategories(String testPath) {
        Query query = entityManager.createNamedQuery("Category.findCategoriesByTestPath");
        query.setParameter("p", testPath);
        return query.getResultList();
    }

    public void removeCategory(Category category) {
        category = entityManager.merge(category);
        List<Test> tests = category.getTests();
        for (Test test : tests) {
            test.removeCategory(category);
        }
        category.setTests(null);
        entityManager.remove(category);
    }

    public Category addCategoryToTest(Test test, Category category) {
        test = entityManager.merge(test);
        category = entityManager.merge(category);
        test.addCategory(category);
        category.addTest(test);
        return category;
    }

    public List<Category> getDuplicateCategories() {
        Query query = entityManager.createNamedQuery("Category.findDuplicateCategories");
        return query.getResultList();
    }
}
