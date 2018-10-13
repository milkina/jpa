package controller.exam;

import data.exam.ExamHandler;
import model.Answer;
import model.Category;
import model.TestExam;
import model.TestQuestionEntry;
import util.CategoryUtility;
import util.GeneralUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static util.AllConstants.SHOW_EXAM_QUESTION;
import static util.AllConstantsAttribute.CURRENT_EXAM_ATTRIBUTE;
import static util.AllConstantsParam.*;

public class AddUserAnswerServlet
        extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryPath = request.getParameter(CATEGORY_PATH);
        String testPath = request.getParameter(TEST_PATH);
        HttpSession session = request.getSession();
        TestExam exam = (TestExam) session.getAttribute(CURRENT_EXAM_ATTRIBUTE);
        TestQuestionEntry currentQuestionEntry = (TestQuestionEntry) exam.getCurrentQuestionEntry();

        Category category = CategoryUtility.getCategoryByPath(request);

        setUserAnswer(request, currentQuestionEntry);
        String url = null;
        if (exam.getCurrentNumber() != exam.getQuestionEntries().size() - 1) {
            url = String.format("/%s?%s=%s&%s=%s&%s=%s&%s=%s", SHOW_EXAM_QUESTION,
                    CATEGORY_PATH, categoryPath,
                    TEST_PATH, testPath,
                    QUESTION_NUMBER, exam.getCurrentNumber(),
                    "NEXT", "NEXT");
        } else {
            url = "/show-test-result.jsp";
            createExam(exam, category);
        }
        response.sendRedirect(request.getContextPath() + url);
    }

    private void setUserAnswer(HttpServletRequest request, TestQuestionEntry currentQuestionEntry) {
        int answerNumber = GeneralUtility.getIntegerValue(request, ANSWER_NUMBER);
        List<Answer> userAnswers = currentQuestionEntry.getUserAnswers();
        for (int i = 0; i < answerNumber; i++) {
            String checkbox = request.getParameter("checkbox" + i);
            Answer answer = userAnswers.get(i);
            answer.setCorrect(checkbox != null);
        }
        currentQuestionEntry.setAnswered(true);
    }

    private void createExam(TestExam exam, Category category) {
        exam.setPercent(exam.getRightQuestionsCount() / exam.getQuestionEntries().size() * 100.0);
        exam.setDate(new Date());
        exam.setCategory(category);
        new ExamHandler().createExam(exam);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
