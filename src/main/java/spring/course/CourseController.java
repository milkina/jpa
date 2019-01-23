package spring.course;

import data.test.TestHandler;
import model.Test;
import model.article.Article;
import model.person.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import util.GeneralUtility;
import util.LanguageUtility;
import util.TestUtility;
import util.article.ArticleUtility;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static util.AllConstants.SPRING_MESSAGE_PAGE;
import static util.AllConstants.TESTS_PAGE;
import static util.AllConstantsAttribute.COURSES_WITH_QUESTIONS;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsAttribute.TESTS;
import static util.AllConstantsAttribute.TESTS_WITH_TESTS;
import static util.AllConstantsParam.OLD_TEST_PATH;
import static util.GeneralUtility.getResourceValue;

@Controller
public class CourseController {
    private static TestHandler testHandler = new TestHandler();

    @RequestMapping(value = "/tests")
    public String selectCoursesWithTestsAndQuestions(HttpServletRequest request) {
        List<Test> coursesWithTests = testHandler.getAllTestsWithNotEmptyTests();
        request.setAttribute(TESTS_WITH_TESTS, coursesWithTests);

        List<Test> coursesWithQuestions = testHandler.getAllCoursesWithNotEmptyQuestions();
        request.setAttribute(COURSES_WITH_QUESTIONS, coursesWithQuestions);
        return TESTS_PAGE;
    }

    @RequestMapping(value = "/show-add-course")
    public ModelAndView showAddCourse(HttpServletRequest request) {
        Test newTest = new Test();
        newTest.setArticle(new Article());
        ModelAndView modelAndView = new ModelAndView("/course/add-course", "command", newTest);

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
        ArticleUtility.createArticle(newTest.getArticle(), person);

        TestHandler testHandler = new TestHandler();
        testHandler.addTest(newTest);

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
        ModelAndView modelAndView = new ModelAndView("/course/edit-course", "command", test);
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
        ArticleUtility.setArticleData(test.getArticle(), newTest.getArticle());

        TestHandler testHandler = new TestHandler();
        testHandler.updateTest(test);

        TestUtility.loadTestsToServletContext(request.getServletContext());
        String message = getResourceValue(locale, "course.updated", "messages");
        model.addAttribute(MESSAGE_ATTRIBUTE, message);
        return SPRING_MESSAGE_PAGE;
    }

    @RequestMapping(value = "show-course")
    public String showCourse() {
        return "course/show-course";
    }

    @RequestMapping(value = "/delete-course")
    public ModelAndView deleteCourse(Locale locale, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(SPRING_MESSAGE_PAGE);
        Test test = TestUtility.getTestFromServletContext(request);
        TestHandler testHandler = new TestHandler();
        if (testHandler.deleteTest(test)) {
            modelAndView.addObject(MESSAGE_ATTRIBUTE,
                    getResourceValue(locale, "course.deleted", "messages"));
        } else {
            modelAndView.addObject(MESSAGE_ATTRIBUTE,
                    getResourceValue(locale, "course.not.deleted", "messages"));
        }

        TestUtility.loadTestsToServletContext(request.getServletContext());

        return modelAndView;
    }
}
