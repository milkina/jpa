package utils;

import util.TestUtility;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Tatyana on 19.01.2017.
 */
public class TestUtilityTest {
    private static model.Test test1 = createTest("test1");
    private static model.Test test2 = createTest("test2");
    private static model.Test test3 = createTest("test3");

    @DataProvider
    public static Object[][] data() {
        return new Object[][]
                {{null, null, null},
                        {null, new LinkedHashMap<String, model.Test>(), null},
                        {test1, createMap(test1, test2, test3), null},
                        {test2, createMap(test1, test2, test3), test1},
                };
    }


    @Test(dataProvider = "data")
    public void getPreviousTest(model.Test test, Map<String, model.Test> testMap, model.Test previousTest) {
        model.Test result = TestUtility.getPreviousTest(test, testMap);
        Assert.assertEquals(result, previousTest);
    }

    private static model.Test createTest(String name) {
        model.Test test = new model.Test();
        test.setPathName(name);
        test.setName(name);
        return test;
    }

    private static Map<String, model.Test> createMap(model.Test... tests) {
        Map<String, model.Test> map = new LinkedHashMap<String, model.Test>();
        for (model.Test test : tests) {
            map.put(test.getPathName(), test);
        }
        return map;
    }
}

