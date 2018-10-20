package controller.exam;

import data.exam.ExamHandler;
import model.Category;
import model.TestExam;
import util.CategoryUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

import static util.AllConstantsAttribute.CURRENT_EXAM_ATTRIBUTE;

public class FinishExamServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        TestExam exam = (TestExam) session.getAttribute(CURRENT_EXAM_ATTRIBUTE);
        Category category = CategoryUtility.getCategoryByPath(request);

        String url = "/show-test-result.jsp";
        createExam(exam, category);
        response.sendRedirect(request.getContextPath() + url);
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