package data.test;

import model.AbstractQuestionEntry;
import model.Category;
import model.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static util.AllBeanNameConstants.TEST_BEAN_NAME;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 13.03.2013
 * Time: 19:48:02
 * To change this template use File | Settings | File Templates.
 */
public class TestHandler {
    private TestBeanI testBean;
    private Context ct;

    public TestHandler() {
        try {
            ct = new InitialContext();
            testBean = (TestBeanI) ct.lookup(TEST_BEAN_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public TestHandler(TestBeanI testBean) {
        this.testBean = testBean;
    }

    public Map<String, Test> getAllTestsWithPath() {
        Map<String, Test> map = new LinkedHashMap<>();
        List<Object[]> list = testBean.getAllTests();
        if (list != null) {
            for (Object[] object : list) {
                Test test = (Test) object[0];
                test.setQuestionsNumber((Long) object[1]);
                map.put(test.getPathName(), test);
            }
        }
        return map;
    }

    public Map<Integer, Test> getAllTests() {
        Map<Integer, Test> map = new TreeMap<>();
        List<Object[]> list = testBean.getAllTests();
        if (list != null) {
            for (Object[] object : list) {
                Test test = (Test) object[0];
                test.setQuestionsNumber((Long) object[1]);
                map.put(test.getId(), test);
            }
        }
        return map;
    }

    public Test[] addTests(Test... tests) {
        for (int i = 0; i < tests.length; i++) {
            Test t = addTest(tests[i]);
            tests[i] = t;
        }
        return tests;
    }

    public Test addTest(Test test) {
        return testBean.addTest(test);
    }

    public Map<String, Integer> getPathName() {
        Map<String, Integer> result = new HashMap<>();
        List<Object[]> list = testBean.getPathName();
        for (Object[] r : list) {
            result.put(r[1].toString(), (Integer) r[0]);
        }
        return result;
    }

    public void setUpdatedDate(Test test) {
        testBean.setUpdatedDate(test);
    }

    public Test getTest(int id) {
        return testBean.getTest(id);
    }

    public Test updateTest(Test test) {
        return testBean.updateTest(test);
    }

    public void removeCategoryFromTest(Test test, Category category) {
        testBean.removeCategoryFromTest(test, category);
    }

    public void swapTests(Test test1, Test test2) {
        int id1 = test1.getOrderId();
        int id2 = test2.getOrderId();
        test1.setOrderId(id2);
        test2.setOrderId(id1);
        updateTest(test1);
        updateTest(test2);
    }

    public boolean deleteTest(Test test) {
        return testBean.deleteTest(test);
    }

    public Test getTestByQuestion(AbstractQuestionEntry questionEntry) {
        return testBean.getTestByQuestion(questionEntry);
    }

    public List<Test> getAllTestsWithNotEmptyTests() {
        return testBean.getAllTestsWithNotEmptyTests();
    }

    public List<Test> getAllCoursesWithNotEmptyQuestions() {
        return testBean.getAllCoursesWithNotEmptyQuestions();
    }

    public void moveTest(Test test, Test stopTest) {
        if (test.getOrderId() > stopTest.getOrderId()) {
            testBean.moveTestUp(test.getPathName(), stopTest.getPathName());
        } else {
            testBean.moveTestDown(test.getPathName(), stopTest.getPathName());
        }
    }

    public Test getCourse(Category category) {
        return testBean.getCourse(category);
    }
}
