package controller.test;

import data.test.TestHandler;
import model.Test;
import util.TestUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.AllConstants.MESSAGE_PAGE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllMessage.TEST_DELETED_MESSAGE;
import static util.AllMessage.TEST_NOT_DELETED_MESSAGE;

/**
 * Created by Tatyana on 20.01.2017.
 */
public class DeleteTestServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Test test = TestUtility.getTestFromServletContext(request);
        TestHandler testHandler = new TestHandler();
        if (testHandler.deleteTest(test)) {
            request.setAttribute(MESSAGE_ATTRIBUTE, TEST_DELETED_MESSAGE);
        } else {
            request.setAttribute(MESSAGE_ATTRIBUTE, TEST_NOT_DELETED_MESSAGE);
        }

        TestUtility.loadTestsToServletContext(request.getServletContext());

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(MESSAGE_PAGE);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

