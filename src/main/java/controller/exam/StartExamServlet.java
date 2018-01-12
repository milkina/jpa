package controller.exam;

import data.category.CategoryHandler;
import data.questionEntry.QuestionEntryHandler;
import model.Category;
import model.Exam;
import model.QuestionEntry;
import model.person.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static util.AllConstants.SHOW_EXAM_QUESTION;
import static util.AllConstants.MESSAGE_PAGE;
import static util.AllConstantsAttribute.CURRENT_EXAM_ATTRIBUTE;
import static util.AllConstantsAttribute.CATEGORY_ATTRIBUTE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsParam.CATEGORY_PATH;
import static util.AllConstantsParam.TEST_PATH;
import static util.AllConstantsParam.QUESTION_NUMBER;
import static util.AllMessage.EXAM_EMPTY;

/**
 * Created by Tatyana on 29.04.2016.
 */
public class StartExamServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        String categoryPath = request.getParameter(CATEGORY_PATH);
        String testPath = request.getParameter(TEST_PATH);
        Category category = getCategory(categoryPath);
        List<QuestionEntry> questionEntries =
                getQuestionEntries(person, category, request);
        String url = MESSAGE_PAGE;
        if (questionEntries.isEmpty()) {
            url = url + "?" + MESSAGE_ATTRIBUTE + "=" + EXAM_EMPTY;
        } else {
            setExam(session, person, questionEntries, category);
            request.getSession().setAttribute(CATEGORY_ATTRIBUTE, category);
            String urlPattern = "/%s?%s=%s&%s=%s&%s=%s";
            url = String.format(urlPattern, SHOW_EXAM_QUESTION,
                    CATEGORY_PATH, categoryPath,
                    TEST_PATH, testPath,
                    QUESTION_NUMBER, 0);
        }
        response.sendRedirect(request.getContextPath() + url);
    }

    private List<QuestionEntry> getQuestionEntries(Person person,
                                                   Category category,
                                                   HttpServletRequest request) {
        String questionType = request.getParameter("questionType");
        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        return questionEntryHandler.getQuestions(category,
                person, questionType);
    }

    private Exam setExam(HttpSession session, Person person,
                         List<QuestionEntry> questionEntries,
                         Category category) {
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
