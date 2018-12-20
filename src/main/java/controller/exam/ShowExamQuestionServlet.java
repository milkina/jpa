package controller.exam;

import model.AbstractExam;
import model.AbstractQuestionEntry;
import model.QuestionExam;
import util.GeneralUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static util.AllConstants.SHOW_EXAM_QUESTION_PAGE;
import static util.AllConstants.SHOW_EXAM_TEST_QUESTION;
import static util.AllConstantsAttribute.CURRENT_EXAM_ATTRIBUTE;
import static util.AllConstantsParam.QUESTION_NUMBER;

/**
 * Created by Tatyana on 18.05.2016.
 */
public class ShowExamQuestionServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int i = 0;
        if (request.getParameter("PREVIOUS") != null) {
            i = -1;
        } else if (request.getParameter("NEXT") != null) {
            i = 1;
        }
        HttpSession session = request.getSession();
        AbstractExam exam = (AbstractExam) session.getAttribute(CURRENT_EXAM_ATTRIBUTE);

        int currentNumber = exam.getCurrentNumber() + i;
        if (request.getParameter(QUESTION_NUMBER) != null) {
            currentNumber = GeneralUtility.getIntegerValue(request, QUESTION_NUMBER);
        }
        String url = updateCurrentQuestionEntry(currentNumber, exam);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    private String updateCurrentQuestionEntry(int currentNumber, AbstractExam exam) {
        String url = null;
        exam.setCurrentNumber(currentNumber);
        AbstractQuestionEntry currentQuestionEntry =
                (AbstractQuestionEntry) exam.getQuestionEntries().get(currentNumber);
        if (exam instanceof QuestionExam) {
            url = SHOW_EXAM_QUESTION_PAGE;
        } else {
            url = SHOW_EXAM_TEST_QUESTION;
        }
        exam.setCurrentQuestionEntry(currentQuestionEntry);
        return url;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
