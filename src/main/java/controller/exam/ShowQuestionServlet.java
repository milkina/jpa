package main.java.controller.exam;

import main.java.data.questionEntry.QuestionEntryHandler;
import main.java.model.QuestionEntry;
import main.java.model.Test;
import main.java.util.AllConstantsParam;
import main.java.util.GeneralUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static main.java.util.AllConstants.SHOW_QUESTION_PAGE;
import static main.java.util.AllConstants.SHOW_QUESTION_PICTURE_PAGE;
import static main.java.util.AllConstantsAttribute.QUESTION_ENTRY_ATTRIBUTE;
import static main.java.util.AllConstantsParam.TEST_PATH;

/**
 * Created by Tatyana on 30.04.2016.
 */
public class ShowQuestionServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer questionEntryId = GeneralUtility.getIntegerValue(request, AllConstantsParam.QUESTION_ENTRY_ID_PARAM);
        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        QuestionEntry questionEntry = questionEntryHandler.getQuestionEntry(questionEntryId);
        request.setAttribute(QUESTION_ENTRY_ATTRIBUTE, questionEntry);
        String mode = request.getParameter(AllConstantsParam.MODE);
        String showQuestionPageUrl = SHOW_QUESTION_PAGE;
        if (request.getParameter(TEST_PATH) == null) {
            Test test = questionEntryHandler.getFirstQuestionEntryTest(questionEntry.getId());
            showQuestionPageUrl = showQuestionPageUrl + "?" + TEST_PATH + "=" + test.getPathName();
        }
        String url = mode == null ? showQuestionPageUrl : SHOW_QUESTION_PICTURE_PAGE;
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
