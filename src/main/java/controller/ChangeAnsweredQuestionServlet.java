package main.java.controller;

import main.java.data.person.PersonHandler;
import main.java.data.questionEntry.QuestionEntryHandler;
import main.java.model.QuestionEntry;
import main.java.model.person.Person;
import main.java.util.PersonUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static main.java.util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static main.java.util.AllConstantsParam.CHECKED_PARAM;
import static main.java.util.AllConstantsParam.UNCHECKED_PARAM;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 15.10.2012
 * Time: 21:01:01
 * To change this template use File | Settings | File Templates.
 */
public class ChangeAnsweredQuestionServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean checked = request.getParameter(CHECKED_PARAM) != null;
        String idString = checked ? request.getParameter(CHECKED_PARAM) : request.getParameter(UNCHECKED_PARAM);
        HttpSession session = request.getSession();
        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        QuestionEntry questionEntry = questionEntryHandler.getQuestionEntry(Integer.valueOf(idString));

        List<QuestionEntry> answeredQuestions = PersonUtility.getAnsweredQuestions(session);
        if (checked && !answeredQuestions.contains(questionEntry)) {
            answeredQuestions.add(questionEntry);
        } else if (!checked) {
            answeredQuestions.remove(questionEntry);
        }

        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        person.setAnsweredQuestions(answeredQuestions);
        PersonHandler personHandler = new PersonHandler();
        personHandler.updatePerson(person);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
