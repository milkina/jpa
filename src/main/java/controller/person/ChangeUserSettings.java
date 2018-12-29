package controller.person;

import data.person.PersonHandler;
import model.person.Person;
import model.person.PersonInfo;
import util.GeneralUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static util.AllConstants.MY_PROFILE_PAGE;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsParam.EMAIL_PARAM;
import static util.AllConstantsParam.LOGIN_PARAMETER;
import static util.AllConstantsParam.LAST_NAME_PARAM;
import static util.AllConstantsParam.FIRST_NAME_PARAM;
import static util.AllMessage.ENTER_REQ_FIELDS_MESSAGE;
import static util.AllMessage.LOGIN_EXIST_MESSAGE;

/**
 * Created by IntelliJ IDEA.
 * User: sad
 * Date: 27.04.2010
 * Time: 14:02:44
 * To change this template use File | Settings | File Templates.
 */
public class ChangeUserSettings extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String login = GeneralUtility.decodeRussianCharacters(request.getParameter(LOGIN_PARAMETER).trim());
        String firstName = GeneralUtility.decodeRussianCharacters(request.getParameter(FIRST_NAME_PARAM).trim());
        String lastName = GeneralUtility.decodeRussianCharacters(request.getParameter(LAST_NAME_PARAM).trim());
        String email = request.getParameter(EMAIL_PARAM).trim();

        PersonHandler personHandler = new PersonHandler();
        Person receivedPerson =
                personHandler.getPersonByLoginViaCriteria(login);
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);

        if (login.trim().isEmpty() || email.trim().isEmpty()) {
            request.setAttribute(MESSAGE_ATTRIBUTE, ENTER_REQ_FIELDS_MESSAGE);
        } else if (receivedPerson != null
                && !login.equalsIgnoreCase(person.getLogin())) {
            request.setAttribute(MESSAGE_ATTRIBUTE, LOGIN_EXIST_MESSAGE);
        } else {
            person.setLogin(login);
            PersonInfo personInfo = new PersonInfo();
            personInfo.setFirstName(firstName);
            personInfo.setLastName(lastName);
            personInfo.setEmail(email);
            person.setPersonInfo(personInfo);

            personHandler.updatePerson(person);
        }
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(MY_PROFILE_PAGE);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
