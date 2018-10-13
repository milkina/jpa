package controller.person;

import data.exam.ExamHandler;
import model.TestExam;
import model.person.Person;
import util.AllConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsAttribute.USER_TEST_EXAMS;

public class ShowPersonProfile extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        ExamHandler examHandler = new ExamHandler();
        List<TestExam> exams = examHandler.getExams(person);
        session.setAttribute(USER_TEST_EXAMS, exams);
        response.sendRedirect(request.getServletContext().getContextPath()
                + AllConstants.MY_PROFILE_PAGE);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

