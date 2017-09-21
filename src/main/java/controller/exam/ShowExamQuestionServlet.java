package main.java.controller.exam;

import main.java.model.Exam;
import main.java.model.QuestionEntry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static main.java.util.AllConstants.SHOW_EXAM_QUESTION_PAGE;
import static main.java.util.AllConstantsAttribute.CURRENT_EXAM_ATTRIBUTE;

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

        updateCurrentQuestionEntry(i, session);
        RequestDispatcher dispatcher = request.getRequestDispatcher(SHOW_EXAM_QUESTION_PAGE);
        dispatcher.forward(request, response);
    }

    private void updateCurrentQuestionEntry(int i, HttpSession session) {
        Exam exam = (Exam) session.getAttribute(CURRENT_EXAM_ATTRIBUTE);
        Integer currentNumber = exam.getCurrentNumber() + i;
        exam.setCurrentNumber(currentNumber);

        QuestionEntry currentQuestionEntry = exam.getQuestionEntries().get(currentNumber);
        exam.setCurrentQuestionEntry(currentQuestionEntry);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
