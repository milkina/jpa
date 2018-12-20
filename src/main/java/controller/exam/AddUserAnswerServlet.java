package controller.exam;

import model.Answer;
import model.TestExam;
import model.TestQuestionEntry;
import util.GeneralUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static util.AllConstants.SHOW_EXAM_QUESTION;
import static util.AllConstantsAttribute.CURRENT_EXAM_ATTRIBUTE;
import static util.AllConstantsParam.ANSWER_NUMBER;
import static util.AllConstantsParam.CATEGORY_PATH;
import static util.AllConstantsParam.TEST_PATH;

public class AddUserAnswerServlet
        extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryPath = request.getParameter(CATEGORY_PATH);
        String testPath = request.getParameter(TEST_PATH);
        HttpSession session = request.getSession();
        TestExam exam = (TestExam) session.getAttribute(CURRENT_EXAM_ATTRIBUTE);
        TestQuestionEntry currentQuestionEntry = (TestQuestionEntry) exam.getCurrentQuestionEntry();

        setUserAnswer(request, currentQuestionEntry);
        String url = String.format("/%s?%s=%s&%s=%s&", SHOW_EXAM_QUESTION,
                CATEGORY_PATH, categoryPath,
                TEST_PATH, testPath);
        if (exam.getCurrentNumber() != exam.getQuestionEntries().size() - 1) {
            url = url + "&NEXT=NEXT";
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

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
