package controller.exam;

import data.category.CategoryHandler;
import data.questionEntry.QuestionEntryHandler;
import model.Category;
import model.TestExam;
import model.TestQuestionEntry;
import model.person.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static util.AllConstants.MESSAGE_PAGE;
import static util.AllConstants.SHOW_EXAM_QUESTION;
import static util.AllConstantsAttribute.*;
import static util.AllConstantsParam.*;
import static util.AllMessage.EXAM_EMPTY;

/**
 * Created by Tatyana on 29.04.2016.
 */
public class StartExamServlet extends HttpServlet {
    private static QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
    private static CategoryHandler categoryHandler = new CategoryHandler();

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        String[] categoryPaths = request.getParameterValues(CATEGORY_PATH);
        String testPath = request.getParameter(TEST_PATH);

        int count = getCount(categoryPaths, request);

        List<TestQuestionEntry> questionEntries =
                questionEntryHandler.getQuestionsForExam(categoryPaths, count);
        String url = MESSAGE_PAGE;
        if (questionEntries.isEmpty()) {
            url = url + "?" + MESSAGE_ATTRIBUTE + "=" + EXAM_EMPTY;
        } else {
            setExam(session, person, questionEntries, categoryPaths, count);
            String urlPattern = "/%s?%s=%s&%s=%s";
            url = String.format(urlPattern, SHOW_EXAM_QUESTION,
                    TEST_PATH, testPath,
                    QUESTION_NUMBER, 0);
        }
        response.sendRedirect(request.getContextPath() + url);
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

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
