package controller.add;

import data.category.CategoryHandler;
import data.questionEntry.QuestionEntryHandler;
import model.*;
import model.person.Person;
import util.CategoryUtility;
import util.GeneralUtility;
import util.TestUtility;
import util.question.QuestionEntryUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static util.AllConstants.MESSAGE_PAGE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsParam.ANSWER_NUMBER;
import static util.AllConstantsParam.QUESTION_TEXT_PARAM;
import static util.AllMessage.QUESTION_ADDED_MESSAGE;

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

    private void addQuestionEntry(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {
        int answerNumber = GeneralUtility.getIntegerValue(request, ANSWER_NUMBER);
        Category category =
                CategoryUtility.getCategoryFromServletContext(request);

        AbstractQuestionEntry newQuestionEntry = null;
        if (answerNumber > 1) {
            newQuestionEntry = new TestQuestionEntry();
            category.setTestsCount(category.getTestsCount() + 1);
        } else {
            newQuestionEntry = new QuestionEntry();
            category.setQuestionsCount(category.getQuestionsCount() + 1);
        }
        newQuestionEntry.setCategory(category);
        QuestionEntryUtility.setAnswers(request, answerNumber, newQuestionEntry);
        setQuestionText(request, newQuestionEntry);
        newQuestionEntry.setCreatedDate(new Date());
        setPerson(request, newQuestionEntry);

        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        questionEntryHandler.addQuestionEntry(newQuestionEntry);
        CategoryHandler categoryHandler = new CategoryHandler();
        categoryHandler.updateCategory(category);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(MESSAGE_PAGE);
        request.setAttribute(MESSAGE_ATTRIBUTE, QUESTION_ADDED_MESSAGE);
        dispatcher.forward(request, response);
    }

    private void setPerson(HttpServletRequest request, AbstractQuestionEntry newQuestionEntry) {
        if (request.getSession().getAttribute(PERSON_ATTRIBUTE) != null) {
            Person person = (Person)
                    request.getSession().getAttribute(PERSON_ATTRIBUTE);
            newQuestionEntry.setPerson(person);
        }
    }

    private void setQuestionText(HttpServletRequest request, AbstractQuestionEntry newQuestionEntry) {
        String newQuestionText = GeneralUtility.decodeRussianCharacters(
                request.getParameter(QUESTION_TEXT_PARAM).trim());
        Question question = new Question();
        question.setText(newQuestionText);
        newQuestionEntry.setQuestion(question);
    }
}
