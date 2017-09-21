package main.java.controller;

import main.java.data.person.PersonHandler;
import main.java.model.person.Person;
import main.java.model.person.PersonInfo;
import main.java.util.PersonUtility;
import main.java.util.ServletUtilities;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static main.java.util.AllConstants.REGISTER_PAGE;
import static main.java.util.AllConstants.WELCOME_REGISTER_PAGE;
import static main.java.util.AllConstantsAttribute.*;
import static main.java.util.AllConstantsParam.*;
import static main.java.util.AllMessage.LOGIN_EXIST_MESSAGE;
import static main.java.util.AllMessage.PASS_AND_CONF_PASS_DIFFERENT_MESSAGE;


public class CreatePerson extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String confPassword = ServletUtilities.getMD5(request.getParameter(CONF_PASSWORD_PARAMETER).trim());
        Person person = createPerson(request);
        String url = REGISTER_PAGE;

        if (isValidData(request, person, confPassword)) {
            PersonHandler personHandler = new PersonHandler();
            person = personHandler.addPerson(person);

            HttpSession session = request.getSession();
            session.setAttribute(PERSON_ATTRIBUTE, person);
            request.setAttribute(USER_NAME, PersonUtility.getPersonName(person));
            url = WELCOME_REGISTER_PAGE;
        } else {
            request.setAttribute(NEW_PERSON_ATTRIBUTE, person);

        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    private Person createPerson(HttpServletRequest request) {
        String login = request.getParameter(NEW_LOGIN).trim();
        String password = request.getParameter(NEW_PASSWORD).trim();
        String firstName = request.getParameter(FIRST_NAME_PARAM).trim();
        String lastName = request.getParameter(LAST_NAME_PARAM).trim();
        String email = request.getParameter(EMAIL_PARAM).trim();
        //set Bean info
        Person person = new Person();
        person.setLogin(login);
        person.setPassword(ServletUtilities.getMD5(password));
        PersonInfo personInfo = new PersonInfo();
        personInfo.setFirstName(firstName);
        personInfo.setLastName(lastName);
        personInfo.setEmail(email);
        person.setPersonInfo(personInfo);

        return person;
    }

    private boolean isValidData(HttpServletRequest request, Person person, String confPassword) {
        if (isLoginExists(person.getLogin())) {
            request.setAttribute(MESSAGE_ATTRIBUTE, LOGIN_EXIST_MESSAGE);
            return false;
        } else if (!person.getPassword().equals(confPassword)) {
            request.setAttribute(MESSAGE_ATTRIBUTE, PASS_AND_CONF_PASS_DIFFERENT_MESSAGE);
            return false;
        }
        return true;
    }


    private boolean isLoginExists(String login) {
        PersonHandler personHandler = new PersonHandler();
        Person existedPerson = personHandler.getPersonByLogin(login);
        return !login.isEmpty() && existedPerson != null;
    }
}
