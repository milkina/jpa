package controller.person;

import data.person.PersonHandler;
import data.questionEntry.QuestionEntryHandler;
import model.AbstractQuestionEntry;
import util.GeneralUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static util.AllConstants.MESSAGE_PAGE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsParam.USER_ID;
import static util.AllMessage.USER_HAS_ADDED_QUESTIONS;
import static util.AllMessage.USER_NOT_REMOVED;
import static util.AllMessage.USER_REMOVED;

/**
 * Created by Tatyana on 25.05.2016.
 */
public class DeleteUser extends HttpServlet {
    private static QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
    private static PersonHandler personHandler = new PersonHandler();

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer userId = GeneralUtility.getIntegerValue(request, USER_ID);
        String result = USER_NOT_REMOVED;
        if (userId != null) {
            List<AbstractQuestionEntry> questionEntries = questionEntryHandler.getPersonQuestions(userId);
            if (questionEntries.size() == 0) {
                personHandler.removePerson(userId);
                result = USER_REMOVED;
            } else {
                result = USER_HAS_ADDED_QUESTIONS;
            }
        }
        request.setAttribute(MESSAGE_ATTRIBUTE, result);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(MESSAGE_PAGE);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

