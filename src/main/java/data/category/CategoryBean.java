package data.category;

import model.Category;
import model.Test;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

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
    private EntityManager entityManager;

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
        Query query = entityManager.createNamedQuery(
                "Category.findByPathName");
        query.setParameter("p", pathName);
        return (Category) query.getSingleResult();
    }

    public List<Category> getCategories(int testId) {
        Query query = entityManager.createNamedQuery(
                "Category.findCategories");
        query.setParameter("p", testId);
        return query.getResultList();
    }

    public List<Category> getCategories(String testPath) {
        Query query =
                entityManager.createNamedQuery(
                        "Category.findCategoriesByTestPath");
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
        Query query = entityManager.createNamedQuery(
                "Category.findDuplicateCategories");
        return query.getResultList();
    }

    public List<Category> getPreviousCategories(String testPath, String categoryPath) {
        Query query = entityManager.createNamedQuery(
                "Category.getPreviousCategory");
        query.setParameter("testPath", testPath);
        query.setParameter("categoryPath", categoryPath);
        List<Category> result = query.getResultList();
        return result;
    }

    public List<Category> getNextCategories(String testPath, String categoryPath) {
        Query query = entityManager.createNamedQuery(
                "Category.getNextCategory");
        query.setParameter("testPath", testPath);
        query.setParameter("categoryPath", categoryPath);
        return query.getResultList();
    }

    public void moveCategoryUp(Category category, String stopCategoryPath, String testPath) {
        List<Category> categories = getPreviousCategories(testPath, category.getPathName());
        for (int i = categories.size() - 1; i > 0; i--) {
            if (stopCategoryPath.equals(categories.get(i - 1).getPathName())) {
                break;
            }
            swapCategories(categories.get(i), categories.get(i - 1));

            Category tmp = categories.get(i);
            categories.set(i, categories.get(i - 1));
            categories.set(i - 1, tmp);
        }
    }

    public void moveCategoryDown(Category category, String stopCategoryPath, String testPath) {
        List<Category> categories = getNextCategories(testPath, category.getPathName());
        for (int i = 0; i < categories.size() - 1; i++) {
            swapCategories(categories.get(i), categories.get(i + 1));

            Category tmp = categories.get(i);
            categories.set(i, categories.get(i + 1));
            categories.set(i + 1, tmp);
            if (stopCategoryPath.equals(categories.get(i).getPathName())) {
                break;
            }
        }
    }

    private void swapCategories(Category c1, Category c2) {
        int id1 = c1.getOrderId();
        int id2 = c2.getOrderId();
        c1.setOrderId(id2);
        c2.setOrderId(id1);
    }
}
