package main.java.controller.person;

import main.java.data.person.PersonHandler;
import main.java.util.GeneralUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static main.java.util.AllConstants.MESSAGE_PAGE;
import static main.java.util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static main.java.util.AllConstantsParam.USER_ID;
import static main.java.util.AllMessage.USER_REMOVED;
import static main.java.util.AllMessage.USER_NOT_REMOVED;

/**
 * Created by Tatyana on 25.05.2016.
 */
public class DeleteUser extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer userId = GeneralUtility.getIntegerValue(request, USER_ID);
        if (userId != null) {
            PersonHandler personHandler = new PersonHandler();
            personHandler.removePerson(userId);
            request.setAttribute(MESSAGE_ATTRIBUTE, USER_REMOVED);
        }else{
            request.setAttribute(MESSAGE_ATTRIBUTE, USER_NOT_REMOVED);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(MESSAGE_PAGE);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

