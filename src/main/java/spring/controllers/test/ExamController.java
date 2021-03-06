package spring.controllers.test;

import model.AbstractExam;
import model.AbstractQuestionEntry;
import model.Category;
import model.TestExam;
import model.TestQuestionEntry;
import model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import spring.services.category.CategoryService;
import spring.services.question.QuestionService;
import util.CategoryUtility;
import util.GeneralUtility;
import util.exam.ExamUtility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

import static util.AllConstants.*;
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

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/start-test")
    public String startExam(@RequestParam(TEST_PATH) String testPath, Model model,
                            Locale locale, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        String[] categoryPaths = request.getParameterValues(CATEGORY_PATH);

        int count = ExamUtility.getCount(categoryPaths, request, Category::getTestsCount);

        List<AbstractQuestionEntry> questionEntries =
                questionService.getQuestionsForExam(categoryPaths, count);
        if (questionEntries.isEmpty()) {
            model.addAttribute(MESSAGE_ATTRIBUTE,
                    GeneralUtility.getResourceValue(locale, "exam.empty", "messages"));
            return SPRING_MESSAGE_PAGE;
        } else {
            ExamUtility.setTestExam(session, person, questionEntries, categoryPaths, count);
            String urlPattern = "forward:%s?%s=%s&%s=%s";
            String url = String.format(urlPattern, SHOW_EXAM_QUESTION,
                    TEST_PATH, testPath,
                    QUESTION_NUMBER, 0);
            return url;
        }
    }

    @RequestMapping(value = "/show-exam-question")
    public String showExamQuestion(HttpServletRequest request) {
        HttpSession session = GeneralUtility.getSession(true);
        AbstractExam exam = (AbstractExam) session.getAttribute(CURRENT_EXAM_ATTRIBUTE);
        int currentNumber = exam.getCurrentNumber();
        if (request.getParameter("PREVIOUS") != null) {
            currentNumber--;
        } else if (request.getParameter("NEXT") != null) {
            currentNumber++;
        } else if (request.getParameter(QUESTION_NUMBER) != null) {
            currentNumber = GeneralUtility.getIntegerValue(request, QUESTION_NUMBER);
        }
        return ExamUtility.updateCurrentQuestionEntry(currentNumber, exam);
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
                questionService.getQuestionsForQuiz(categoryPaths, person, questionType, count);
        if (questionEntries.isEmpty()) {
            model.addAttribute(MESSAGE_ATTRIBUTE,
                    GeneralUtility.getResourceValue(locale, "exam.empty", "messages"));
            return SPRING_MESSAGE_PAGE;
        } else {
            ExamUtility.setQuestionExam(session, person, questionEntries, categoryPaths, count);
            String urlPattern = "forward:%s?%s=%s&%s=%s";
            return String.format(urlPattern, SHOW_EXAM_QUESTION,
                    TEST_PATH, testPath,
                    QUESTION_NUMBER, 0);
        }
    }

    @RequestMapping(value = "/finish-exam")
    public String finishExam(HttpServletRequest request) {
        HttpSession session = GeneralUtility.getSession(true);
        TestExam exam = (TestExam) session.getAttribute(CURRENT_EXAM_ATTRIBUTE);

        ExamUtility.createExam(exam, request.getServletContext());
        return SHOW_TEST_RESULT;
    }

    @RequestMapping(value = "/add-person-answer")
    public String addPersonAnswer(HttpServletRequest request) {
        String testPath = request.getParameter(TEST_PATH);
        HttpSession session = request.getSession();
        TestExam exam = (TestExam) session.getAttribute(CURRENT_EXAM_ATTRIBUTE);
        TestQuestionEntry currentQuestionEntry = (TestQuestionEntry) exam.getCurrentQuestionEntry();

        ExamUtility.setUserAnswer(request, currentQuestionEntry);
        String url = String.format("/%s?%s=%s", SHOW_EXAM_QUESTION,
                TEST_PATH, testPath);
        if (exam.getCurrentNumber() != exam.getQuestionEntries().size() - 1) {
            url = url + "&NEXT=NEXT";
        }
        return "forward:" + url;
    }

}
