package spring.controllers.course;

import com.google.gson.Gson;
import model.Category;
import model.Test;
import model.article.Article;
import model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import spring.services.course.CourseService;
import util.CategoryUtility;
import util.LanguageUtility;
import util.TestUtility;
import util.article.ArticleUtility;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import static util.AllConstants.QUESTIONS_PAGE;
import static util.AllConstants.QUESTIONS_RU_PAGE;
import static util.AllConstants.SPRING_MESSAGE_PAGE;
import static util.AllConstants.TASKS_PAGE;
import static util.AllConstants.TESTS_PAGE;
import static util.AllConstants.TESTS_RU_PAGE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsAttribute.TESTS;
import static util.AllConstantsParam.OLD_TEST_PATH;
import static util.AllConstantsParam.PREVIOUS_TEST_PATH;
import static util.AllConstantsParam.TEST_PATH;
import static util.GeneralUtility.getResourceValue;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/tests")
    public String showTests() {
        return TESTS_PAGE;
    }

    @RequestMapping(value = "ru/tests")
    public String showRuTests() {
        return TESTS_RU_PAGE;
    }

    @RequestMapping(value = "/questions")
    public String showQuestions() {
        return QUESTIONS_PAGE;
    }

    @RequestMapping(value = "ru/questions")
    public String showRuQuestions() {
        return QUESTIONS_RU_PAGE;
    }

    @RequestMapping(value = {"/practicheskie-zadachi", "ru/practicheskie-zadachi"})
    public String showTasks() {
        return TASKS_PAGE;
    }

    @RequestMapping(value = "/show-add-course")
    public ModelAndView showAddCourse(HttpServletRequest request) {
        Test newTest = new Test();
        newTest.setArticle(new Article());
        ModelAndView modelAndView = new ModelAndView("course/add-course", "command", newTest);

        Map<String, String> languages = LanguageUtility.getLanguagesMap(request.getServletContext());
        modelAndView.addObject("languagesMap", languages);
        return modelAndView;
    }

    @RequestMapping(value = "/add-course")
    public String addCourse(Model model, Locale locale, @RequestParam("LANGUAGE") String languageCode,
                            @ModelAttribute("SpringWeb") Test newTest, HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        Person person = (Person)
                request.getSession().getAttribute(PERSON_ATTRIBUTE);
        TestUtility.setTestData(newTest, newTest, languageCode, servletContext);
        ArticleUtility.createArticle(newTest.getArticle(), person, servletContext);

        courseService.create(newTest);

        TestUtility.loadTestsToServletContext(servletContext);
        model.addAttribute(MESSAGE_ATTRIBUTE, getResourceValue(locale, "course.added", "messages"));
        return SPRING_MESSAGE_PAGE;
    }

    @RequestMapping(value = "/show-edit-course")
    public ModelAndView showEditCourse(@RequestParam("TEST_PATH") String testPath,
                                       HttpServletRequest request) {
        Map<String, Test> testMap = (Map<String, Test>)
                request.getServletContext().getAttribute(TESTS);
        Test test = testMap.get(testPath);
        Map<String, String> languages = LanguageUtility.getLanguagesMap(request.getServletContext());
        ModelAndView modelAndView = new ModelAndView("course/edit-course", "command", test);
        modelAndView.addObject("languagesMap", languages);
        return modelAndView;
    }

    @RequestMapping(value = "/edit-course", method = RequestMethod.POST)
    public String editCourse(@RequestParam(OLD_TEST_PATH) String testPath,
                             @ModelAttribute("SpringWeb") Test newTest,
                             @RequestParam("LANGUAGE") String languageCode,
                             Model model, Locale locale, HttpServletRequest request) {
        Map<String, Test> testMap = (Map<String, Test>)
                request.getServletContext().getAttribute(TESTS);
        Test test = testMap.get(testPath);
        TestUtility.setTestData(test, newTest, languageCode, request.getServletContext());
        ArticleUtility.setArticleData(test.getArticle(), newTest.getArticle(), request.getServletContext());

        courseService.update(test);

        TestUtility.loadTestsToServletContext(request.getServletContext());
        String message = getResourceValue(locale, "course.updated", "messages");
        model.addAttribute(MESSAGE_ATTRIBUTE, message);
        return SPRING_MESSAGE_PAGE;
    }

    @RequestMapping(value = "show-course-admin")
    public String showCourseAdmin() {
        return "course/show-course-admin";
    }

    @RequestMapping(value = "/delete-course")
    public ModelAndView deleteCourse(Locale locale, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(SPRING_MESSAGE_PAGE);
        Test test = TestUtility.getTestFromServletContext(request);
        if (courseService.deleteCourse(test)) {
            modelAndView.addObject(MESSAGE_ATTRIBUTE,
                    getResourceValue(locale, "course.deleted", "messages"));
        } else {
            modelAndView.addObject(MESSAGE_ATTRIBUTE,
                    getResourceValue(locale, "course.not.deleted", "messages"));
        }

        TestUtility.loadTestsToServletContext(request.getServletContext());

        return modelAndView;
    }

    @RequestMapping(value = {"/show-course", "{langid}/show-course"})
    public String showCourse() {
        return "course/show-course";
    }

    @RequestMapping(value = "/up-course")
    public String upCourse(HttpServletRequest request) {
        String testPath = request.getParameter(TEST_PATH);
        String stopTestPath = request.getParameter(PREVIOUS_TEST_PATH);
        Map<String, Test> testMap = (Map<String, Test>)
                request.getServletContext().getAttribute(TESTS);
        Test test = testMap.get(testPath);
        Test stopTest = testMap.get(stopTestPath);

        courseService.moveCourse(test, stopTest);

        TestUtility.loadTestsToServletContext(request.getServletContext());
        CategoryUtility.setDuplicateCategories(request.getServletContext());
        return "forward:/show-administration";
    }

    @RequestMapping(value = "/change-course", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String
    doPost(HttpServletRequest request) {
        String testPath = request.getParameter(TEST_PATH);
        Map<String, Test> tests =
                (Map) request.getServletContext().getAttribute(TESTS);
        Test test = tests.get(testPath);

        Map<String, String> categoryMap = new TreeMap<>();
        for (Category category : test.getCategories().values()) {
            categoryMap.put(category.getPathName(), category.getName());
        }
        return new Gson().toJson(categoryMap);
    }
}
