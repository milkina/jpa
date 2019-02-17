package spring.services.category;

import model.Category;
import model.Test;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    Category findOne(int id);

    Category update(Category category);

    Category create(Category category);

    Category[] createCategories(Category[] categories);

    void updateCategoryCounts(Category category);

    void moveCategoryUp(Category category, String stopCategoryPath, String testPath);

    void moveCategoryDown(Category category, String stopCategoryPath, String testPath);

    Category addCategoryToCourse(Test test, Category category);

    Map<String, Integer> getPathName();

    Category getCategory(String pathName);

    List<Category> getCategories(String testPath);

    void removeCategory(Category category);

    Map<String, Category> getDuplicateCategories();

    void swapCategories(Category c1, Category c2);

}
