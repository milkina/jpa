package utils;

import main.java.model.Category;
import main.java.util.CategoryUtility;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Tatyana on 27.12.2016.
 */
public class CategoryUtilityTest {
    private static Category category1 = createCategory("category1");
    private static Category category2 = createCategory("category2");
    private static Category category3 = createCategory("category3");

    @DataProvider
    public static Object[][] data() {
        return new Object[][]
                {{null, null, null},
                        {null, new LinkedHashMap<String,Category>(), null},
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
        Category category = new Category();
        category.setPathName(name);
        category.setName(name);
        return category;
    }

    private static Map<String, Category> createMap(Category... categories) {
        Map<String, Category> map = new LinkedHashMap<String, Category>();
        for (Category category : categories) {
            map.put(category.getPathName(), category);
        }
        return map;
    }
}
