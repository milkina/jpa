package utils;

import model.Category;
import util.CategoryUtility;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

/**
 * Created by Tatyana on 27.12.2016.
 */
public class CategoryUtilityTest {
    private static Category category1 = createCategory("category1");
    private static Category category2 = createCategory("category2", true, 0);
    private static Category category3 = createCategory("category3", true, 10);
    private static Category category4 = createCategory("category4", false, 10);
    private static Category category5Parent = createCategory("category5Parent", false, 0);
    private static Category category5ParentCopy = createCategory("category5Parent", false, 0);
    private static Category category6 = createCategory("category6", false, 10);
    private static Category category7 = createCategory("category7", false, 0);

    static {
        addCategories(category5Parent, category6, category7);
        addCategories(category5ParentCopy, category6);
    }

    @DataProvider
    public static Object[][] data() {
        return new Object[][]
                {{null, null, null},
                        {null, new LinkedHashMap<String, Category>(), null},
                        {category1, createMap(category1, category2, category3), null},
                        {category2, createMap(category1, category2, category3), category1},
                };
    }

    @Test(dataProvider = "data")
    public void getPreviousCategoryTest(Category category, Map<String, Category> categoryMap, Category previousCategory) {
        Category result = CategoryUtility.getPreviousCategory(category, categoryMap);
        Assert.assertEquals(result, previousCategory);
    }

    private static Category createCategory(String name) {
        return createCategory(name, false, 0);
    }

    private static Category createCategory(String name, boolean isHidden, int count) {
        Category category = new Category();
        category.setPathName(name);
        category.setName(name);
        category.setHidden(isHidden);
        category.setTestsCount(count);
        return category;
    }

    private static void addCategories(Category parent, Category... categories) {
        List<Category> subCategories = new ArrayList<>();

        for (Category category : categories) {
            category.setParentCategory(parent);
            subCategories.add(category);
        }

        parent.setSubCategories(subCategories);
    }

    private static Map<String, Category> createMap(Category... categories) {
        Map<String, Category> map = new LinkedHashMap<>();
        for (Category category : categories) {
            map.put(category.getPathName(), category);
        }
        return map;
    }

    @DataProvider
    public static Object[][] dataForSelect() {
        return new Object[][]
                {{new ArrayList<Category>(), new ArrayList<Category>()},
                        {createArrayList(category1, category2, category3, category4, category5Parent, category6, category7),
                                createArrayList(category3,category4, category5ParentCopy)}
                };
    }

    private static List<Category> createArrayList(Category... categories) {
        List<Category> result = new ArrayList<>();
        for (Category category : categories) {
            result.add(category);
        }
        return result;
    }

    @Test(dataProvider = "dataForSelect")
    public void selectCategoriesWithTestsTest(List<Category> categories, List<Category> result) {
        CategoryUtility.selectCategories(categories, c -> c.getTestsCount() < 1);
        Assert.assertEquals(categories, result);
    }
}
