package utils;

import main.java.util.question.QuestionEntryUtility;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * Created by Tatyana on 27.03.2016.
 */
public class QuestionEntryUtilityTest {

    @DataProvider
    public Object[][] isValidNumbersData() {
        return new Object[][]
                {{1, 3, 4, true},
                        {1, 3, 3, true},
                        {2, 5, 5, true},
                        {2, 5, 6, true},
                        {3, 2, 4, false},
                        {2, 5, 4, false},
                        {4, 6, 3, false}
                };
    }

    @Test(dataProvider = "isValidNumbersData")
    public void isValidNumbers(int from, int to, long questionsNumber, boolean expectedResult) throws IOException {
        boolean result = QuestionEntryUtility.isValidNumbers(from, to, questionsNumber);
        Assert.assertEquals(result, expectedResult);
    }
}

