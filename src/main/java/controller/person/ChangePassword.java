package controller.person;

import data.person.PersonHandler;
import model.person.Person;
import util.AllConstants;
import util.GeneralUtility;
import util.ServletUtilities;

import static util.AllConstantsParam.PASSWORD_PARAMETER;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: sad
 * Date: 27.04.2010
 * Time: 14:21:48
 * To change this template use File | Settings | File Templates.
 */
public class ChangePassword extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String password = "";
        if (request.getParameter(PASSWORD_PARAMETER) != null) {
            password = GeneralUtility.decodeRussianCharacters(request.getParameter(PASSWORD_PARAMETER).trim());
        }
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        person.setPassword(ServletUtilities.getMD5(password));
        PersonHandler personHandler = new PersonHandler();
        personHandler.updatePerson(person);
        response.sendRedirect(request.getServletContext().getContextPath()
                + AllConstants.MY_PROFILE_PAGE);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
