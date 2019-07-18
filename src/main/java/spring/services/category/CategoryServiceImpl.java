package spring.services.category;

import model.Category;
import model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.repositories.CategoryRepository;
import spring.services.course.CourseService;
import spring.services.question.QuestionService;
import util.CategoryUtility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    CourseService courseService;

    @Override
    public Category findOne(int id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category create(Category category) {
        category = categoryRepository.save(category);
        category.setOrderId(category.getId() * 10);
        return category;
    }

    @Override
    public Category[] createCategories(Category[] categories) {
        for (int i = 0; i < categories.length; i++) {
            Category c = create(categories[i]);
            categories[i] = c;
        }
        return categories;
    }

    @Override
    @Transactional
    public void updateCategoryCounts(Category category) {
        int questionCount = questionService.getAllQuestions(category).size();
        int testQuestionCount = questionService.getAllTestQuestions(category).size();
        category.setTestsCount(testQuestionCount);
        category.setQuestionsCount(questionCount);
        categoryRepository.save(category);
    }

    public void moveCategoryUp(Category category, Map<String, Category> categoryMap) {
        Category previousCategory = CategoryUtility.getPreviousCategory(category, categoryMap);
        if (previousCategory != null) {
            swapCategories(category, previousCategory);
        }
    }

    @Override
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

    @Override
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

    @Transactional
    @Override
    public void swapCategories(Category c1, Category c2) {
        int id1 = c1.getOrderId();
        int id2 = c2.getOrderId();
        c1.setOrderId(id2);
        c2.setOrderId(id1);
        categoryRepository.save(c1);
        categoryRepository.save(c2);
    }

    public List<Category> getPreviousCategories(String testPath, String categoryPath) {
        return categoryRepository.getPreviousCategories(testPath, categoryPath);
    }

    public List<Category> getNextCategories(String testPath, String categoryPath) {
        return categoryRepository.getNextCategories(testPath, categoryPath);
    }

    @Override
    @Transactional
    public Category addCategoryToCourse(Test course, Category category) {
        course = courseService.update(course);
        category = update(category);

        course.addCategory(category);
        category.addTest(course);

        course = courseService.update(course);
        category = update(category);
        return category;
    }

    @Override
    public Map<String, Integer> getPathName() {
        Map<String, Integer> result = new HashMap<>();
        for (Category category : categoryRepository.findAll()) {
            result.put(category.getPathName(), category.getId());
        }
        return result;
    }

    @Override
    public Category getCategory(String pathName) {
        return categoryRepository.findByPathName(pathName);
    }

    @Override
    public List<Category> getCategories(String testPath) {
        return categoryRepository.findCategoriesByTestPath(testPath);
    }

    @Override
    @Transactional
    public void removeCategory(Category category) {
        category = categoryRepository.save(category);
        List<Test> tests = category.getTests();
        for (Test test : tests) {
            test.removeCategory(category);
        }
        category.setTests(null);
        categoryRepository.delete(category);
    }

    @Override
    public Map<String, Category> getDuplicateCategories() {
        Map<String, Category> result = new HashMap<>();
        List<Category> categories = categoryRepository.getDuplicateCategories();
        for (Category category : categories) {
            result.put(category.getPathName(), category);
        }
        return result;
    }
}
