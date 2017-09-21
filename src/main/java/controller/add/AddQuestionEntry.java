package main.java.controller.add;

import main.java.data.questionEntry.QuestionEntryHandler;
import main.java.model.Answer;
import main.java.model.Category;
import main.java.model.Question;
import main.java.model.QuestionEntry;
import main.java.model.person.Person;
import main.java.util.CategoryUtility;
import main.java.util.GeneralUtility;
import main.java.util.TestUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static main.java.util.AllConstants.MESSAGE_PAGE;
import static main.java.util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static main.java.util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static main.java.util.AllConstantsParam.ANSWER_TEXT_PARAM;
import static main.java.util.AllConstantsParam.QUESTION_TEXT_PARAM;
import static main.java.util.AllMessage.QUESTION_ADDED_MESSAGE;


/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 21.10.2012
 * Time: 22:17:26
 * To change this template use File | Settings | File Templates.
 */

public class AddQuestionEntry extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        addQuestionEntry(request, response);
        TestUtility.loadTestsToServletContext(request.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    private void addQuestionEntry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newQuestionText = GeneralUtility.decodeRussianCharacters(request.getParameter(QUESTION_TEXT_PARAM).trim());
        String newAnswerText = GeneralUtility.decodeRussianCharacters(request.getParameter(ANSWER_TEXT_PARAM).trim());
        Category category = CategoryUtility.getCategoryFromServletContext(request);

        QuestionEntry newQuestionEntry = new QuestionEntry();
        newQuestionEntry.setCategory(category);

        Question question = new Question();
        question.setText(newQuestionText);
        newQuestionEntry.setQuestion(question);

        Answer answer = new Answer();
        answer.setText(newAnswerText);
        newQuestionEntry.setAnswer(answer);

        newQuestionEntry.setCreatedDate(new Date());

        if (request.getSession().getAttribute(PERSON_ATTRIBUTE) != null) {
            newQuestionEntry.setPerson((Person) request.getSession().getAttribute(PERSON_ATTRIBUTE));
        }

        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        questionEntryHandler.addQuestionEntry(newQuestionEntry);

        RequestDispatcher dispatcher = request.getRequestDispatcher(MESSAGE_PAGE);
        request.setAttribute(MESSAGE_ATTRIBUTE, QUESTION_ADDED_MESSAGE);
        dispatcher.forward(request, response);
    }
}