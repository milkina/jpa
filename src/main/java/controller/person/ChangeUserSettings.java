package main.java.controller.person;

import main.java.data.person.PersonHandler;
import main.java.model.person.Person;
import main.java.model.person.PersonInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static main.java.util.AllConstants.MY_PROFILE_PAGE;
import static main.java.util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static main.java.util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static main.java.util.AllConstantsParam.*;
import static main.java.util.AllMessage.ENTER_REQ_FIELDS_MESSAGE;
import static main.java.util.AllMessage.LOGIN_EXIST_MESSAGE;


/**
 * Created by IntelliJ IDEA.
 * User: sad
 * Date: 27.04.2010
 * Time: 14:02:44
 * To change this template use File | Settings | File Templates.
 */
public class ChangeUserSettings extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //Get parameters
        String login = request.getParameter(LOGIN_PARAMETER).trim();
        String firstName = request.getParameter(FIRST_NAME_PARAM).trim();
        String lastName = request.getParameter(LAST_NAME_PARAM).trim();
        String email = request.getParameter(EMAIL_PARAM).trim();
        //find such login in the DB

        PersonHandler personHandler = new PersonHandler();
        Person receivedPerson = personHandler.getPersonByLoginViaCriteria(login);
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);

        //   if login exists
        if (login.trim().isEmpty() || (email.trim().isEmpty())) {
            request.setAttribute(MESSAGE_ATTRIBUTE, ENTER_REQ_FIELDS_MESSAGE);
        } else if (receivedPerson != null && !login.equalsIgnoreCase(person.getLogin())) {
            request.setAttribute(MESSAGE_ATTRIBUTE, LOGIN_EXIST_MESSAGE);

        } else {
            //set Bean info
            person.setLogin(login);
            PersonInfo personInfo = new PersonInfo();
            personInfo.setFirstName(firstName);
            personInfo.setLastName(lastName);
            personInfo.setEmail(email);
            person.setPersonInfo(personInfo);

            personHandler.updatePerson(person);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(MY_PROFILE_PAGE);
        dispatcher.forward(request, response);

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
