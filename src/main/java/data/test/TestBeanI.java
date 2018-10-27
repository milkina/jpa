package data.test;

import model.AbstractQuestionEntry;
import model.Category;
import model.Test;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 13.03.2013
 * Time: 19:48:27
 * To change this template use File | Settings | File Templates.
 */
public interface TestBeanI {
    List<Object[]> getAllTests();

    Test addTest(Test test);

    List<Object[]> getPathName();

    void setUpdatedDate(Test test);

    Test getTest(int id);

    Test updateTest(Test test);

    void removeCategoryFromTest(Test test, Category category);

    boolean deleteTest(Test test);

    Test getTestByQuestion(AbstractQuestionEntry questionEntry);
}
