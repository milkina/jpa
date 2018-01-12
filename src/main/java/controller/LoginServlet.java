package controller;

import data.person.PersonHandler;
import model.person.Person;
import util.ServletUtilities;

import static util.AllConstantsParam.LOGIN_PARAMETER;
import static util.AllConstantsParam.PASSWORD_PARAMETER;
import static util.AllConstantsParam.REMEMBER_PARAMETER;
import static util.AllConstants.COOKIE_VALUE;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsAttribute.WRONG_LOGIN_MESSAGE_ATTRIBUTE;
import static util.AllMessage.WRONG_LOGIN_MESSAGE;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by IntelliJ IDEA.
 * User: TanyaM
 * Date: 27.05.2009
 * Time: 18:34:56
 * To change this template use File | Settings | File Templates.
 */
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {   //If Register
        String address;

        // If Enter in the system
        PersonHandler personHandler = new PersonHandler();
        Person person = personHandler.getPersonByLoginAndPassword(
                request.getParameter(LOGIN_PARAMETER),
                ServletUtilities.getMD5(
                        request.getParameter(PASSWORD_PARAMETER)));

        if (person != null) {
            Cookie cookie;
            if (request.getParameter(REMEMBER_PARAMETER) != null) {
                cookie = new Cookie(COOKIE_VALUE,
                        Integer.toString(person.getID()));
            } else {
                cookie = new Cookie(COOKIE_VALUE, "");
            }
            cookie.setMaxAge(60 * 60 * 24 * 365);
            cookie.setPath("/");
            response.addCookie(cookie);
            HttpSession session = request.getSession();
            session.setAttribute(PERSON_ATTRIBUTE, person);
            address = "/";
            response.sendRedirect(request.getContextPath() + address);
        } else {
            request.setAttribute(WRONG_LOGIN_MESSAGE_ATTRIBUTE,
                    WRONG_LOGIN_MESSAGE);
            address = "/person/login.jsp?param="
                    + WRONG_LOGIN_MESSAGE_ATTRIBUTE;
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

