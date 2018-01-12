package data.category;

import model.Category;
import model.Test;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 29.09.2012
 * Time: 20:28:29
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface CategoryBeanI {
    Category createCategory(Category category);

    Category getCategory(int id);

    Category getCategory(String pathName);

    void updateCategory(Category c);

    List<Object[]> getPathName();

    List<Category> getCategories(int testId);

    List<Category> getCategories(String testPath);

    void removeCategory(Category category);

    Category addCategoryToTest(Test test, Category category);

    List<Category> getDuplicateCategories();
}
