package spring.test;

import data.category.CategoryHandler;
import data.questionEntry.QuestionEntryHandler;
import model.AbstractExam;
import model.AbstractQuestionEntry;
import model.Category;
import model.TestExam;
import model.TestQuestionEntry;
import model.person.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import util.CategoryUtility;
import util.GeneralUtility;
import util.exam.ExamUtility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

import static util.AllConstants.SHOW_EXAM_QUESTION;
import static util.AllConstants.SPRING_MESSAGE_PAGE;
import static util.AllConstantsAttribute.CATEGORIES;
import static util.AllConstantsAttribute.CURRENT_EXAM_ATTRIBUTE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsParam.CATEGORY_PATH;
import static util.AllConstantsParam.QUESTION_NUMBER;
import static util.AllConstantsParam.TEST_PATH;

/**
 * Created by Tatyana on 29.04.2016.
 */
@Controller
public class ExamController {
    private static QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
    private static CategoryHandler categoryHandler = new CategoryHandler();

    @RequestMapping(value = "/start-test")
    public String startExam(@RequestParam(TEST_PATH) String testPath, Model model, Locale locale) {
        HttpServletRequest request = GeneralUtility.getRequest();
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        String[] categoryPaths = request.getParameterValues(CATEGORY_PATH);

        int count = ExamUtility.getCount(categoryPaths, request, Category::getTestsCount);

        List<AbstractQuestionEntry> questionEntries =
                questionEntryHandler.getQuestionsForExam(categoryPaths, count);
        if (questionEntries.isEmpty()) {
            model.addAttribute(MESSAGE_ATTRIBUTE,
                    GeneralUtility.getResourceValue(locale, "exam.empty", "messages"));
            return SPRING_MESSAGE_PAGE;
        } else {
            ExamUtility.setTestExam(session, person, questionEntries, categoryPaths, count);
            String urlPattern = "redirect:%s?%s=%s&%s=%s";
            String url = String.format(urlPattern, SHOW_EXAM_QUESTION,
                    TEST_PATH, testPath,
                    QUESTION_NUMBER, 0);
            return url;
        }
    }

    @RequestMapping(value = "/show-exam-question")
    public String showExamQuestion() {
        HttpSession session = GeneralUtility.getSession(true);
        HttpServletRequest request = GeneralUtility.getRequest();
        int i = 0;
        if (request.getParameter("PREVIOUS") != null) {
            i = -1;
        } else if (request.getParameter("NEXT") != null) {
            i = 1;
        }
        AbstractExam exam = (AbstractExam) session.getAttribute(CURRENT_EXAM_ATTRIBUTE);

        int currentNumber = exam.getCurrentNumber() + i;
        if (request.getParameter(QUESTION_NUMBER) != null) {
            currentNumber = GeneralUtility.getIntegerValue(request, QUESTION_NUMBER);
        }
        return ExamUtility.updateCurrentQuestionEntry(currentNumber, exam);
    }

    @RequestMapping(value = "/start-quiz")
    public String startQuiz() {
        return "exam/start-quiz";
    }

    @RequestMapping(value = "/see-questions")
    public String seeQuestions(@RequestParam(TEST_PATH) String testPath,
                               Model model, Locale locale,
                               HttpServletRequest request) {
        String[] categoryPaths = request.getParameterValues(CATEGORY_PATH);
        int count = ExamUtility.getCount(categoryPaths, request, Category::getQuestionsCount);
        HttpSession session = GeneralUtility.getSession(true);
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        String questionType = request.getParameter("questionType");
        List<AbstractQuestionEntry> questionEntries =
                new QuestionEntryHandler().getQuestionsForQuiz(categoryPaths, person, questionType, count);
        if (questionEntries.isEmpty()) {
            model.addAttribute(MESSAGE_ATTRIBUTE,
                    GeneralUtility.getResourceValue(locale, "exam.empty", "messages"));
            return SPRING_MESSAGE_PAGE;
        } else {
            ExamUtility.setQuestionExam(session, person, questionEntries, categoryPaths, count);
            String urlPattern = "redirect:%s?%s=%s&%s=%s";
            return String.format(urlPattern, SHOW_EXAM_QUESTION,
                    TEST_PATH, testPath,
                    QUESTION_NUMBER, 0);
        }
    }

    @RequestMapping(value = "/finish-exam")
    public String finishExam() {
        HttpSession session = GeneralUtility.getSession(true);
        TestExam exam = (TestExam) session.getAttribute(CURRENT_EXAM_ATTRIBUTE);

        ExamUtility.createExam(exam);
        return "/exam/show-test-result";
    }

    @RequestMapping(value = "/select-category-for-exam")
    public String selectCategoriesForTest(@RequestParam(TEST_PATH) String testPath,
                                          HttpServletRequest request) {
        List<Category> categories = categoryHandler.getCategories(testPath);
        CategoryUtility.selectCategories(categories,
                c -> c.getTestsCount() < 1);
        request.setAttribute(CATEGORIES, categories);
        return "/exam/start-exam";
    }

    @RequestMapping(value = "/select-categories-to-see-questions")
    public String selectCategoriesToSeeQuestions(@RequestParam(TEST_PATH) String testPath,
                                                 HttpServletRequest request) {
        List<Category> categories = categoryHandler.getCategories(testPath);
        CategoryUtility.selectCategories(categories,
                c -> c.getQuestionsCount() < 1);
        request.setAttribute(CATEGORIES, categories);
        return "/exam/start-course-quiz";
    }


}
