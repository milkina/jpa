package main.java.controller;

import main.java.data.category.CategoryHandler;
import main.java.data.questionEntry.QuestionEntryHandler;
import main.java.model.Answer;
import main.java.model.Category;
import main.java.model.Question;
import main.java.model.QuestionEntry;
import main.java.util.CategoryUtility;
import main.java.util.GeneralUtility;
import main.java.util.ServletUtilities;
import main.java.util.TestUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static main.java.util.AllConstants.*;
import static main.java.util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static main.java.util.AllConstantsAttribute.QUESTION_ENTRY_ATTRIBUTE;
import static main.java.util.AllConstantsParam.*;
import static main.java.util.AllMessage.*;
import static main.java.util.question.QuestionEntryUtility.isValidNumbers;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 21.10.2012
 * Time: 22:17:26
 * To change this template use File | Settings | File Templates.
 */

public class EditQuestionEntryServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String modeParam = request.getParameter(EDIT_MODE_PARAM);
        EditMode mode = EditMode.valueOf(modeParam);
        switch (mode) {
            case SHOW:
                showQuestionEntry(request, response);
                break;
            case SAVE:
                saveQuestionEntry(request, response);
                break;
            case UP:
            case UP_FROM_EDIT:
                moveQuestionEntryUp(request, response, mode);
                break;
            case DELETE:
                deleteQuestionEntry(request, response);
                break;
            case MOVE_BATCH:
                moveBatch(request, response);
                break;
        }
        TestUtility.loadTestsToServletContext(request.getServletContext());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    private void showQuestionEntry(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        QuestionEntry questionEntry = findQuestionEntry(request);

        fixTinyMCEIssue(questionEntry);

        RequestDispatcher dispatcher = request.getRequestDispatcher(EDIT_QUESTION_ENTRY_PAGE);
        request.setAttribute(QUESTION_ENTRY_ATTRIBUTE, questionEntry);
        dispatcher.forward(request, response);
    }

    private void fixTinyMCEIssue(QuestionEntry questionEntry) {
        Question question = questionEntry.getQuestion();
        question.setText(ServletUtilities.fixTinyMceIssue(question.getText()));

        Answer answer = questionEntry.getAnswer();
        answer.setText(ServletUtilities.fixTinyMceIssue(answer.getText()));
    }

    private void saveQuestionEntry(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String newQuestionText = GeneralUtility.decodeRussianCharacters(request.getParameter(QUESTION_TEXT_PARAM).trim());
        String newAnswerText = GeneralUtility.decodeRussianCharacters(request.getParameter(ANSWER_TEXT_PARAM).trim());
        String categoryPath = request.getParameter(CATEGORY_PATH);
        String oldCategoryPath = request.getParameter(OLD_CATEGORY_PATH);
        QuestionEntry questionEntry = null;
        Category category = CategoryUtility.getCategoryFromServletContext(request);
        if (oldCategoryPath.equals(categoryPath)) {
            questionEntry = findQuestionEntry(request);
        } else {
            //moved to different category
            questionEntry = findQuestionEntry(request);
            questionEntry.setCategory(category);
        }

        questionEntry.getQuestion().setText(newQuestionText);
        questionEntry.getAnswer().setText(newAnswerText);

        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        questionEntryHandler.updateQuestionEntry(questionEntry);

        RequestDispatcher dispatcher = request.getRequestDispatcher(MESSAGE_PAGE);
        request.setAttribute(MESSAGE_ATTRIBUTE, QUESTION_CHANGED_MESSAGE);
        dispatcher.forward(request, response);
    }

    private QuestionEntry findQuestionEntry(HttpServletRequest request) {
        String questionEntryId = request.getParameter(QUESTION_ENTRY_ID_PARAM);
        return new QuestionEntryHandler().getQuestionEntry(Integer.valueOf(questionEntryId));
    }

    private void moveQuestionEntryUp(HttpServletRequest request, HttpServletResponse response, EditMode mode) throws IOException, ServletException {
        Integer questionId = GeneralUtility.getIntegerValue(request, QUESTION_ENTRY_ID_PARAM);

        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        questionEntryHandler.moveQuestionEntryUp(questionId);

        String url = mode == EditMode.UP ? createShowQuestionPageUrl(request)
                : createEditQuestionPageUrl(request, questionId);
        response.sendRedirect(url);
    }

    private String createShowQuestionPageUrl(HttpServletRequest request) {
        return String.format("%s%s?%s=%s&%s=%s",
                request.getContextPath(),
                SHOW_QUESTIONS_PAGE,
                CATEGORY_PATH, request.getParameter(CATEGORY_PATH),
                TEST_PATH, request.getParameter(TEST_PATH));
    }

    private String createEditQuestionPageUrl(HttpServletRequest request, Integer questionId) {
        return String.format("%s%s?%s=%s&%s=%s&%s=%d&%s=%s",
                request.getContextPath(),
                EDIT_QUESTION_ENTRY_SERVLET,
                CATEGORY_PATH, request.getParameter(CATEGORY_PATH),
                TEST_PATH, request.getParameter(TEST_PATH),
                QUESTION_ENTRY_ID_PARAM, questionId,
                EDIT_MODE_PARAM, EditMode.SHOW);
    }

    private void deleteQuestionEntry(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int questionEntryId = GeneralUtility.getIntegerValue(request, QUESTION_ENTRY_ID_PARAM);

        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        questionEntryHandler.deleteQuestionEntry(questionEntryId);
        Category category = CategoryUtility.getCategoryFromServletContext(request);
        Map<Integer, QuestionEntry> allQuestionsOfCategory = questionEntryHandler.getAllQuestions(category);
        allQuestionsOfCategory.remove(questionEntryId);
        RequestDispatcher dispatcher = request.getRequestDispatcher(MESSAGE_PAGE);
        request.setAttribute(MESSAGE_ATTRIBUTE, QUESTION_REMOVE_MESSAGE);
        dispatcher.forward(request, response);
    }

    public void moveBatch(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Category category = CategoryUtility.getCategoryByPath(request);
        String oldTestPath = request.getParameter(OLD_TEST_PATH).trim();
        CategoryHandler categoryHandler = new CategoryHandler();
        String oldCategoryPath = request.getParameter(OLD_CATEGORY_PATH);
        Category oldCategory = categoryHandler.getCategory(oldCategoryPath);
        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        long oldCategoryQuestionsNumber = questionEntryHandler.getAllQuestions(oldCategory).size();


        Integer from = GeneralUtility.getIntegerValue(request, FROM_NUMBER);
        Integer to = GeneralUtility.getIntegerValue(request, TO_NUMBER);
        String page = MESSAGE_PAGE;
        String message = String.format(QUESTIONS_MOVED, to - from + 1);
        if (oldCategory.getId() == category.getId()) {
            page = String.format(MOVE_QUESTIONS_PAGE, oldTestPath, oldCategoryPath);
            message = SELECT_DIFFERENT_CATEGORY;
        } else if (isValidNumbers(from, to, oldCategoryQuestionsNumber)) {
            questionEntryHandler.moveBatch(oldCategory, category, from, to);
        } else {
            page = String.format(MOVE_QUESTIONS_PAGE, oldTestPath, oldCategoryPath);
            message = INVALID_NUMBERS_MESSAGE;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        request.setAttribute(MESSAGE_ATTRIBUTE, message);
        dispatcher.forward(request, response);
    }

}