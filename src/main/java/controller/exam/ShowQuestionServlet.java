package controller.exam;

import data.questionEntry.QuestionEntryHandler;
import model.AbstractQuestionEntry;
import model.Test;
import util.AllConstantsParam;
import util.GeneralUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.AllConstants.SHOW_QUESTION_PAGE;
import static util.AllConstants.SHOW_QUESTION_PICTURE_PAGE;
import static util.AllConstantsAttribute.QUESTION_ENTRY_ATTRIBUTE;
import static util.AllConstantsParam.TEST_PATH;
import static util.GeneralUtility.getIntegerValue;

/**
 * Created by Tatyana on 30.04.2016.
 */
public class ShowQuestionServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer questionEntryId = getIntegerValue(request,
                AllConstantsParam.QUESTION_ENTRY_ID_PARAM);
        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        AbstractQuestionEntry questionEntry =
                questionEntryHandler.getQuestionEntry(questionEntryId);
        request.setAttribute(QUESTION_ENTRY_ATTRIBUTE, questionEntry);
        String mode = request.getParameter(AllConstantsParam.MODE);
        String testPathName = request.getParameter(TEST_PATH);
        if (GeneralUtility.isEmpty(testPathName)) {
            Test test = questionEntryHandler.getFirstQuestionEntryTest(
                    questionEntry.getId());
            testPathName = test.getPathName();
        }
        String url = mode == null ? SHOW_QUESTION_PAGE
                : SHOW_QUESTION_PICTURE_PAGE;
        url = url + "?" + TEST_PATH + "=" + testPathName;
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
