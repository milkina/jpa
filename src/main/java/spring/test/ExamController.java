package spring.test;

import data.category.CategoryHandler;
import data.exam.ExamHandler;
import data.questionEntry.QuestionEntryHandler;
import model.AbstractExam;
import model.AbstractQuestionEntry;
import model.Category;
import model.QuestionExam;
import model.TestExam;
import model.TestQuestionEntry;
import model.person.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import util.CategoryUtility;
import util.GeneralUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static util.AllConstants.SHOW_EXAM_QUESTION;
import static util.AllConstants.SHOW_QUIZ_QUESTION_PAGE;
import static util.AllConstants.SHOW_TEST_QUESTION_PAGE;
import static util.AllConstants.SPRING_MESSAGE_PAGE;
import static util.AllConstantsAttribute.CATEGORIES;
import static util.AllConstantsAttribute.CATEGORY_ATTRIBUTE;
import static util.AllConstantsAttribute.CURRENT_EXAM_ATTRIBUTE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsParam.CATEGORY_PATH;
import static util.AllConstantsParam.NUMBER_OF_QUESTIONS;
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

        int count = getCount(categoryPaths, request);

        List<TestQuestionEntry> questionEntries =
                questionEntryHandler.getQuestionsForExam(categoryPaths, count);
        if (questionEntries.isEmpty()) {
            model.addAttribute(MESSAGE_ATTRIBUTE,
                    GeneralUtility.getResourceValue(locale, "exam.empty", "messages"));
            return SPRING_MESSAGE_PAGE;
        } else {
            setExam(session, person, questionEntries, categoryPaths, count);
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
        return updateCurrentQuestionEntry(currentNumber, exam);
    }

    @RequestMapping(value = "/start-quiz")
    public String startQuiz() {
        return "exam/start-quiz";
    }

    @RequestMapping(value = "/see-questions")
    public String seeQuestions(@RequestParam(CATEGORY_PATH) String categoryPath,
                               @RequestParam(TEST_PATH) String testPath, Model model, Locale locale) {
        HttpServletRequest request = GeneralUtility.getRequest();
        HttpSession session = GeneralUtility.getSession(true);
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        Category category = getCategory(categoryPath);
        List<AbstractQuestionEntry> questionEntries =
                getQuestionEntries(person, category, request);
        if (questionEntries.isEmpty()) {
            model.addAttribute(MESSAGE_ATTRIBUTE,
                    GeneralUtility.getResourceValue(locale, "exam.empty", "messages"));
            return SPRING_MESSAGE_PAGE;
        } else {
            setExam(session, person, questionEntries, category);
            session.setAttribute(CATEGORY_ATTRIBUTE, category);
            String urlPattern = "redirect:%s?%s=%s&%s=%s&%s=%s";
            return String.format(urlPattern, SHOW_EXAM_QUESTION,
                    CATEGORY_PATH, categoryPath,
                    TEST_PATH, testPath,
                    QUESTION_NUMBER, 0);
        }
    }

    @RequestMapping(value = "/finish-exam")
    public String finishExam() {
        HttpSession session = GeneralUtility.getSession(true);
        TestExam exam = (TestExam) session.getAttribute(CURRENT_EXAM_ATTRIBUTE);

        createExam(exam);
        return "/exam/show-test-result";
    }

    @RequestMapping(value = "/select-category-for-exam")
    public String selectCategoriesForTest(@RequestParam(TEST_PATH) String testPath) {
        List<Category> categories = categoryHandler.getCategories(testPath);
        CategoryUtility.selectCategoriesWithTests(categories);
        HttpServletRequest request = GeneralUtility.getRequest();
        request.setAttribute(CATEGORIES, categories);
        return "/exam/start-exam";
    }

    private void createExam(TestExam exam) {
        exam.setPercent(exam.getRightQuestionsCount() / exam.getQuestionEntries().size() * 100.0);
        exam.setDate(new Date());
        new ExamHandler().createExam(exam);
    }

    private List<Category> getCategories(String[] categoryPaths) {
        List<Category> categories = new ArrayList<>();
        for (String categoryPath : categoryPaths) {
            categories.add(categoryHandler.getCategory(categoryPath));
        }
        return categories;
    }

    private int getCount(String[] categoryPaths, HttpServletRequest request) {
        int count = 0;
        String countString = request.getParameter(NUMBER_OF_QUESTIONS);
        if (countString != null && !countString.isEmpty()) {
            count = Integer.parseInt(countString);
        } else if (categoryPaths.length == 1) {
            Category category = getCategory(categoryPaths[0]);
            count = category.getTestsCount() != 0 ? category.getTestsCount() : 20;
        }
        return count;
    }

    private TestExam setExam(HttpSession session, Person person,
                             List<TestQuestionEntry> questionEntries, String[] categoryPaths, int count) {
        List<Category> categories = getCategories(categoryPaths);
        TestExam exam = new TestExam();
        exam.setPerson(person);
        exam.setQuestionEntries(questionEntries);
        exam.setCurrentNumber(0);
        exam.setCategories(categories);
        exam.setCurrentQuestionEntry(questionEntries.get(0));
        exam.setAmount(count);
        session.setAttribute(CURRENT_EXAM_ATTRIBUTE, exam);
        return exam;
    }

    private Category getCategory(String categoryPath) {
        CategoryHandler categoryHandler = new CategoryHandler();
        return categoryHandler.getCategory(categoryPath);
    }

    private String updateCurrentQuestionEntry(int currentNumber, AbstractExam exam) {
        String url = null;
        exam.setCurrentNumber(currentNumber);
        AbstractQuestionEntry currentQuestionEntry =
                (AbstractQuestionEntry) exam.getQuestionEntries().get(currentNumber);
        if (exam instanceof QuestionExam) {
            url = SHOW_QUIZ_QUESTION_PAGE;
        } else {
            url = SHOW_TEST_QUESTION_PAGE;
        }
        exam.setCurrentQuestionEntry(currentQuestionEntry);
        return url;
    }

    private List<AbstractQuestionEntry> getQuestionEntries(Person person,
                                                           Category category,
                                                           HttpServletRequest request) {
        String questionType = request.getParameter("questionType");
        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        return questionEntryHandler.getQuestions(category,
                person, questionType);
    }

    private QuestionExam setExam(HttpSession session, Person person,
                                 List<AbstractQuestionEntry> questionEntries,
                                 Category category) {
        QuestionExam exam = new QuestionExam();
        exam.setPerson(person);
        exam.setQuestionEntries(questionEntries);
        exam.setCurrentNumber(0);
        exam.setCategory(category);
        exam.setCurrentQuestionEntry(questionEntries.get(0));
        session.setAttribute(CURRENT_EXAM_ATTRIBUTE, exam);
        return exam;
    }
}
