package main.java.controller.person;

import main.java.data.person.PersonHandler;
import main.java.model.person.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static main.java.util.AllConstants.SHOW_QUESTIONS_PAGE;
import static main.java.util.AllConstantsAttribute.PERSON_ANSWERED_QUESTIONS;
import static main.java.util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static main.java.util.AllConstantsParam.CATEGORY_PATH;
import static main.java.util.AllConstantsParam.TEST_PATH;

/**
 * Created by Tatyana on 07.06.2016.
 */
public class ClearHistoryServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        String categoryPath = request.getParameter(CATEGORY_PATH);
        String testPath = request.getParameter(TEST_PATH);

        PersonHandler personHandler = new PersonHandler();
        personHandler.removeAnsweredQuestions(person);

        session.setAttribute(PERSON_ANSWERED_QUESTIONS, null);

        response.sendRedirect(request.getContextPath() + SHOW_QUESTIONS_PAGE + "?" +
                CATEGORY_PATH + "=" + categoryPath + "&" +
                TEST_PATH + "=" + testPath);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

