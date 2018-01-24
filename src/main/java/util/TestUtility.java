package util;

import data.category.CategoryHandler;
import data.test.TestHandler;
import model.Category;
import model.Test;
import model.article.Article;
import model.person.Person;
import util.article.ArticleUtility;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.Map;

import static util.AllConstants.TEST_NAME_PARAM;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsAttribute.TESTS;
import static util.AllConstantsAttribute.TEST_PATHS_ATTRIBUTE;
import static util.AllConstantsParam.CATEGORY_ID_PARAMETER;
import static util.AllConstantsParam.TEST_ID_PARAM;
import static util.AllConstantsParam.TEST_PATH;
import static util.AllConstantsParam.TEST_TAGS;
import static util.AllConstantsParam.TEST_ICON_TEXT;
import static util.GeneralUtility.roundTime;
import static util.GeneralUtility.decodeRussianCharacters;
import static util.GeneralUtility.getIntegerValue;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 13.03.2013
 * Time: 20:53:09
 * To change this template use File | Settings | File Templates.
 */
public class TestUtility {
    private static TestHandler testHandler = new TestHandler();
    private static CategoryHandler categoryHandler = new CategoryHandler();


    public static Category getCategoryByParam(HttpServletRequest request) {
        Integer categoryId =
                getIntegerValue(request, CATEGORY_ID_PARAMETER);
        if (categoryId == null) {
            return null;
        }
        categoryHandler.getCategory(categoryId);
        return categoryHandler.getCategory(categoryId);
    }

    public static Test getTestByParam(HttpServletRequest request) {
        Integer testId = getIntegerValue(request, TEST_ID_PARAM);
        if (testId == null) {
            return null;
        }
        return testHandler.getTest(testId);
    }

    public static Person getPersonFromSession(HttpSession session) {
        Object personObj = session.getAttribute(PERSON_ATTRIBUTE);
        return personObj != null ? (Person) personObj : null;
    }

    public static Map<String, Integer> getTestPaths(
            ServletContext servletContext) {
        Map<String, Integer> testPaths;
        Object testPathsObj = servletContext.getAttribute(
                TEST_PATHS_ATTRIBUTE);
        if (testPathsObj == null) {
            testPaths = testHandler.getPathName();
            servletContext.setAttribute(TEST_PATHS_ATTRIBUTE, testPaths);
        } else {
            testPaths = (Map<String, Integer>) testPathsObj;
        }
        return testPaths;
    }

    public static Integer getTestIdByPath(HttpServletRequest request) {
        String testPathParameter = request.getParameter(TEST_ID_PARAM);
        Map<String, Integer> testPaths = getTestPaths(
                request.getServletContext());
        return testPaths.get(testPathParameter);
    }

    public static long getUpdatedDate(Test test) {
        Date updatedDate = test.getUpdatedDate();
        if (updatedDate == null) {
            return 0;
        }
        return roundTime(updatedDate.getTime());
    }

    public static void setIfModifiedSinceHeader(
            HttpServletRequest request,
            HttpServletResponse response, Test test) {
        GeneralUtility generalUtility = new GeneralUtility();
        generalUtility.setIfModifiedSinceHeader(request, response,
                getUpdatedDate(test));
    }

    public static String getTestUrl(int testId) {
        Test test = testHandler.getTest(testId);
        return test.getPathName();
    }

    public static Map<Integer, Test> getAllTests() {
        return testHandler.getAllTests();
    }

    public static void loadTestsToServletContext(
            ServletContext servletContext) {
        Map<String, Test> testMap = testHandler.getAllTestsWithPath();
        servletContext.setAttribute(TESTS, testMap);
    }

    public static Test getTestFromServletContext(HttpServletRequest request) {
        Map<String, Test> testMap = (Map<String, Test>)
                request.getServletContext().getAttribute(TESTS);
        String testPath = request.getParameter(TEST_PATH);
        Test test = testMap.get(testPath);
        return test;
    }

    public static Test getPreviousTest(Test test, Map<String, Test> testMap) {
        Test previousTest = null;
        if (testMap == null || testMap.isEmpty() || test == null) {
            return null;
        }
        for (Map.Entry<String, Test> testEntry : testMap.entrySet()) {
            if (test.equals(testEntry.getValue())) {
                break;
            }
            previousTest = testEntry.getValue();
        }
        return previousTest;
    }

    public static void setArticleData(Test test, HttpServletRequest request) {
        Article article = test.getArticle();
        if (article != null) {
            ArticleUtility.updateArticle(article, request);
        } else {
            Person person = (Person)
                    request.getSession().getAttribute(PERSON_ATTRIBUTE);
            article = ArticleUtility.createArticle(request, person);
        }
        test.setArticle(article);
    }

    public static void setTestData(Test test,
                                   HttpServletRequest request) {
        String newName = request.getParameter(TEST_NAME_PARAM);
        String newPathName = request.getParameter(TEST_PATH);
        String newTags = request.getParameter(TEST_TAGS);
        String iconText = request.getParameter(TEST_ICON_TEXT);

        test.setName(decodeRussianCharacters(newName));
        test.setPathName(newPathName);
        test.setTags(decodeRussianCharacters(newTags));
        test.setIconText(decodeRussianCharacters(iconText));
    }
}
