package main.java.controller.exam;

import main.java.data.category.CategoryHandler;
import main.java.data.questionEntry.QuestionEntryHandler;
import main.java.model.Category;
import main.java.model.Exam;
import main.java.model.QuestionEntry;
import main.java.model.person.Person;
import main.java.util.AllConstantsAttribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static main.java.util.AllConstants.MESSAGE_PAGE;
import static main.java.util.AllConstants.SHOW_EXAM_QUESTION;
import static main.java.util.AllConstantsAttribute.*;
import static main.java.util.AllConstantsParam.*;
import static main.java.util.AllMessage.EXAM_EMPTY;

/**
 * Created by Tatyana on 29.04.2016.
 */
public class StartExamServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        String categoryPath = request.getParameter(CATEGORY_PATH);
        String testPath = request.getParameter(TEST_PATH);
        Category category = getCategory(categoryPath);
        List<QuestionEntry> questionEntries = getQuestionEntries(person, category, request);
        String url = MESSAGE_PAGE;
        if (questionEntries.isEmpty()) {
            url = url + "?" + MESSAGE_ATTRIBUTE + "=" + EXAM_EMPTY;
        } else {
            setExam(session, person, questionEntries, category);
            request.getSession().setAttribute(AllConstantsAttribute.CATEGORY_ATTRIBUTE, category);
            String URL_PATTERN = "/%s?%s=%s&%s=%s&%s=%s";
            url = String.format(URL_PATTERN, SHOW_EXAM_QUESTION,
                    CATEGORY_PATH, categoryPath,
                    TEST_PATH, testPath,
                    QUESTION_NUMBER,0);
        }
        response.sendRedirect(request.getContextPath() + url);
    }

    private List<QuestionEntry> getQuestionEntries(Person person, Category category, HttpServletRequest request) {
        String questionType = request.getParameter("questionType");
        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        return questionEntryHandler.getQuestions(category, person, questionType);
    }

    private Exam setExam(HttpSession session, Person person, List<QuestionEntry> questionEntries, Category category) {
        Exam exam = new Exam();
        exam.setPerson(person);
        exam.setQuestionEntries(questionEntries);
        exam.setCurrentNumber(0);
        exam.setCategory(category);
        exam.setCurrentQuestionEntry(questionEntries.get(0));
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
