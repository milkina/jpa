package util;

import model.Category;
import model.Language;
import model.Test;
import model.article.Article;
import model.person.Person;
import util.article.ArticleUtility;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static util.AllConstantsAttribute.CATEGORIES;
import static util.AllConstantsAttribute.COURSES_WITH_QUESTIONS;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsAttribute.TESTS;
import static util.AllConstantsAttribute.TESTS_WITH_TESTS;
import static util.AllConstantsAttribute.TEST_PATHS_ATTRIBUTE;
import static util.AllConstantsParam.CATEGORY_ID_PARAMETER;
import static util.AllConstantsParam.TEST_ID_PARAM;
import static util.AllConstantsParam.TEST_PATH;
import static util.GeneralUtility.decodeRussianCharacters;
import static util.GeneralUtility.getIntegerValue;
import static util.GeneralUtility.roundTime;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 13.03.2013
 * Time: 20:53:09
 * To change this template use File | Settings | File Templates.
 */
public class TestUtility extends SpringUtility {
    public static Category getCategoryByParam(HttpServletRequest request) {
        Integer categoryId =
                getIntegerValue(request, CATEGORY_ID_PARAMETER);
        if (categoryId == null) {
            return null;
        }
        return getCategoryService(request.getServletContext()).findOne(categoryId);
    }

    public static Test getTestByParam(HttpServletRequest request) {
        Integer testId = getIntegerValue(request, TEST_ID_PARAM);
        if (testId == null) {
            return null;
        }
        return getCourseService(request.getServletContext()).getCourse(testId);
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
            testPaths = getCourseService(servletContext).getPathName();
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

    public static String getTestUrl(int testId, ServletContext servletContext) {
        Test test = getCourseService(servletContext).getCourse(testId);
        return test.getPathName();
    }

    public static Map<Integer, Test> getAllTests(ServletContext servletContext) {
        return getCourseService(servletContext).getAllCourses();
    }

    public static void loadTestsToServletContext(
            ServletContext servletContext) {
        Map<String, Test> testMap = getCourseService(servletContext).getAllCoursesWithPath();
        servletContext.setAttribute(TESTS, testMap);
    }

    public static Test getTestFromServletContext(HttpServletRequest request) {
        Map<String, Test> testMap = (Map<String, Test>)
                request.getServletContext().getAttribute(TESTS);
        String testPath = request.getParameter(TEST_PATH);
        Test test = testMap.get(testPath);
        return test;
    }

    public static void loadCoursesForTests(ServletContext servletContext) {
        List<Test> coursesWithTests = getCourseService(servletContext).getAllTestsWithNotEmptyTests();
        for (Test test : coursesWithTests) {
            CategoryUtility.selectCategories(test.getCategories().values(),
                    c -> c.getTestsCount() < 1);
        }
        servletContext.setAttribute(TESTS_WITH_TESTS, coursesWithTests);
    }

    public static void loadCoursesForQuestions(ServletContext servletContext) {
        List<Test> coursesWithQuestions = getCourseService(servletContext).getAllCoursesWithNotEmptyQuestions();
        for (Test test : coursesWithQuestions) {
            CategoryUtility.selectCategories(test.getCategories().values(),
                    c -> c.getQuestionsCount() < 1);
        }
        servletContext.setAttribute(COURSES_WITH_QUESTIONS, coursesWithQuestions);
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

    public static void setTestData(Test test, Test newTest, String languageCode, ServletContext servletContext) {
        test.setName(decodeRussianCharacters(newTest.getName()));
        test.setTags(decodeRussianCharacters(newTest.getTags()));
        test.setIconText(decodeRussianCharacters(newTest.getIconText()));
        test.setPathName(decodeRussianCharacters(newTest.getPathName()));
        Language language = LanguageUtility.findLanguageInContext(servletContext, languageCode);
        test.setLanguage(language);
    }
}
