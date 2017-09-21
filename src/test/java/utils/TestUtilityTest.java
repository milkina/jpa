package utils;

import main.java.util.TestUtility;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Tatyana on 19.01.2017.
 */
public class TestUtilityTest {
    private static main.java.model.Test test1 = createTest("test1");
    private static main.java.model.Test test2 = createTest("test2");
    private static main.java.model.Test test3 = createTest("test3");

    @DataProvider
    public static Object[][] data() {
        return new Object[][]
                {{null, null, null},
                        {null, new LinkedHashMap<String, main.java.model.Test>(), null},
                        {test1, createMap(test1, test2, test3), null},
                        {test2, createMap(test1, test2, test3), test1},
                };
    }


    @Test(dataProvider = "data")
    public void getPreviousTest(main.java.model.Test test, Map<String, main.java.model.Test> testMap, main.java.model.Test previousTest) {
        main.java.model.Test result = TestUtility.getPreviousTest(test, testMap);
        Assert.assertEquals(result, previousTest);
    }

    private static main.java.model.Test createTest(String name) {
        main.java.model.Test test = new main.java.model.Test();
        test.setPathName(name);
        test.setName(name);
        return test;
    }

    private static Map<String, main.java.model.Test> createMap(main.java.model.Test... tests) {
        Map<String, main.java.model.Test> map = new LinkedHashMap<String, main.java.model.Test>();
        for (main.java.model.Test test : tests) {
            map.put(test.getPathName(), test);
        }
        return map;
    }
}

