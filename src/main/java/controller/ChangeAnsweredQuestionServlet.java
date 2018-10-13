package controller;

import data.person.PersonHandler;
import data.questionEntry.QuestionEntryHandler;
import model.AbstractQuestionEntry;
import model.person.Person;
import util.PersonUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsParam.CHECKED_PARAM;
import static util.AllConstantsParam.UNCHECKED_PARAM;

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
        String idString = checked ? request.getParameter(CHECKED_PARAM)
                : request.getParameter(UNCHECKED_PARAM);
        HttpSession session = request.getSession();
        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        AbstractQuestionEntry questionEntry = questionEntryHandler.getQuestionEntry(
                Integer.valueOf(idString));

        List<AbstractQuestionEntry> answeredQuestions =
                PersonUtility.getAnsweredQuestions(session);
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
