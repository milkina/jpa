package utils;

import main.java.util.ServletUtilities;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Tatyana on 01.02.2016.
 */
public class ServletUtilitiesTest {
    @DataProvider
    public static Object[][] data() {
        return new Object[][]
                {{null, null},
                 {"", ""},
                 {"&lt;", "&amp;lt;"},
                 {"&gt;", "&amp;gt;"},
                 {"&lt;&gt;", "&amp;lt;&amp;gt;"},
                 {"&lt;value&gt;", "&amp;lt;value&amp;gt;"}};
    }

    @Test(dataProvider = "data")
    public void fixTinyMceIssue(String input, String result) throws IOException {
        String output = ServletUtilities.fixTinyMceIssue(input);
        Assert.assertEquals(result, output);
    }

}
