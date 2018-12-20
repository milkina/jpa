package controller.person;

import data.exam.ExamHandler;
import data.person.PersonHandler;
import model.TestExam;
import model.person.Person;
import util.AllConstants;
import util.GeneralUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static util.AllConstantsAttribute.SOME_USER;
import static util.AllConstantsAttribute.USER_TEST_EXAMS;
import static util.AllConstantsParam.USER_ID;

public class ShowPersonHistoryServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int personId = GeneralUtility.getIntegerValue(request, USER_ID);
        Person person = new PersonHandler().getPersonById(personId);
        ExamHandler examHandler = new ExamHandler();
        List<TestExam> exams = examHandler.getExams(person);
        session.setAttribute(USER_TEST_EXAMS, exams);
        session.setAttribute(SOME_USER, person);
        response.sendRedirect(request.getServletContext().getContextPath()
                + AllConstants.SHOW_PERSON_HISTORY_PAGE);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
