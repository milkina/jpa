package util.question;

import model.AbstractQuestionEntry;
import model.Answer;
import model.Category;
import model.Question;
import model.QuestionEntry;
import model.QuestionExam;
import model.TestQuestionEntry;
import model.person.Person;
import util.AllConstants;
import util.AllConstantsAttribute;
import util.AllConstantsParam;
import util.CategoryUtility;
import util.EditMode;
import util.GeneralUtility;
import util.ServletUtilities;
import util.SpringUtility;
import util.exam.ExamUtility;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NavigableSet;

import static util.AllConstants.SHOW_QUESTIONS_PAGE;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsParam.ANSWER_TEXT_PARAM;
import static util.AllConstantsParam.CATEGORY_PATH;
import static util.AllConstantsParam.EDIT_MODE_PARAM;
import static util.AllConstantsParam.QUESTION_ENTRY_ID_PARAM;
import static util.AllConstantsParam.QUESTION_TEXT_PARAM;
import static util.AllConstantsParam.TEST_PATH;
import static util.AllConstantsParam.TYPE;

/**
 * Created by Tatyana on 27.03.2016.
 */
public class QuestionEntryUtility extends SpringUtility {
    public static Integer[] getQuestionsId(NavigableSet<Integer> set,
                                           Integer from, Integer to) {
        if (set == null || set.isEmpty()) {
            return null;
        }
        Integer[] questionsId = set.toArray(new Integer[set.size()]);
        return Arrays.copyOfRange(questionsId, from - 1, to);
    }

    public static boolean isValidNumbers(int from, int to,
                                         long questionsNumber) {
        return from <= to && from <= questionsNumber && to <= questionsNumber;
    }

    public static AbstractQuestionEntry getQuestionEntry(HttpServletRequest request) {
        Integer questionEntryId = GeneralUtility.getIntegerValue(request,
                AllConstantsParam.QUESTION_ENTRY_ID_PARAM);
        if (questionEntryId == null) {
            return getQuestionEntryFromExam(request.getSession());
        } else {
            return getQuestionService(request.getServletContext()).getQuestionEntry(questionEntryId);
        }
    }

    public static void setAnswers(HttpServletRequest request, int answerNumber, AbstractQuestionEntry newQuestionEntry) {
        List<Answer> answers = new ArrayList<>();
        for (int i = 1; i < answerNumber + 1; i++) {
            String newAnswerText = request.getParameter(ANSWER_TEXT_PARAM + i);
            if (newAnswerText != null) {
                newAnswerText = GeneralUtility.decodeRussianCharacters(newAnswerText.trim());
                Answer answer = new Answer();
                answer.setText(newAnswerText);
                answer.setQuestionEntry(newQuestionEntry);
                String checkbox = request.getParameter("checkbox" + i);
                answer.setCorrect(checkbox != null);
                answers.add(answer);
            }
        }
        newQuestionEntry.setAnswers(answers);
    }

    public static AbstractQuestionEntry getQuestionEntryFromExam(HttpSession session) {
        QuestionExam exam = (QuestionExam) session.getAttribute(
                AllConstantsAttribute.CURRENT_EXAM_ATTRIBUTE);
        return ExamUtility.getCurrentQuestionEntry(exam);
    }


    public static String getQuestionUrl(int questionId) {
        return String.format("%s?%s=%d",
                AllConstants.SHOW_QUESTION_SERVLET_PAGE,
                AllConstantsParam.QUESTION_ENTRY_ID_PARAM,
                questionId);
    }


    public static void fixTinyMCEIssue(AbstractQuestionEntry questionEntry) {
        Question question = questionEntry.getQuestion();
        question.setText(ServletUtilities.fixTinyMceIssue(question.getText()));
        for (Answer answer : questionEntry.getAnswers()) {
            answer.setText(ServletUtilities.fixTinyMceIssue(answer.getText()));
        }
    }

    public static void addQuestionEntry(int answerNumber, HttpServletRequest request) {
        Category category =
                CategoryUtility.getCategoryFromServletContext(request);

        AbstractQuestionEntry newQuestionEntry = answerNumber > 1 ? new TestQuestionEntry() : new QuestionEntry();
        newQuestionEntry.setCategory(category);
        QuestionEntryUtility.setAnswers(request, answerNumber, newQuestionEntry);
        setQuestionText(request, newQuestionEntry);
        setPerson(request, newQuestionEntry);

        getQuestionService(request.getServletContext()).addQuestionEntry(newQuestionEntry);
    }

    public static void setPerson(HttpServletRequest request, AbstractQuestionEntry newQuestionEntry) {
        if (request.getSession().getAttribute(PERSON_ATTRIBUTE) != null) {
            Person person = (Person)
                    request.getSession().getAttribute(PERSON_ATTRIBUTE);
            newQuestionEntry.setPerson(person);
            newQuestionEntry.setApproved(person.isSysadmin());
        }
    }

    public static void setQuestionText(HttpServletRequest request, AbstractQuestionEntry newQuestionEntry) {
        String newQuestionText = GeneralUtility.decodeRussianCharacters(
                request.getParameter(QUESTION_TEXT_PARAM).trim());
        Question question = new Question();
        question.setText(newQuestionText);
        newQuestionEntry.setQuestion(question);
    }


    public static void changeQuestionType(AbstractQuestionEntry questionEntry, int oldAnswersSize,
                                          ServletContext servletContext) {
        int id = questionEntry.getId();
        int size = questionEntry.getAnswers().size();
        if (oldAnswersSize == 1 && size > 1) {
            getQuestionService(servletContext).changeQuestionToTestQuestion(id);
        } else if (oldAnswersSize > 1 && size == 1) {
            getQuestionService(servletContext).changeTestQuestionToQuestion(id);
        }
    }

    public static String createShowQuestionPageUrl(HttpServletRequest request) {
        return String.format("redirect:%s?%s=%s&%s=%s&%s=%s",
                SHOW_QUESTIONS_PAGE,
                CATEGORY_PATH, request.getParameter(CATEGORY_PATH),
                TEST_PATH, request.getParameter(TEST_PATH),
                TYPE, request.getParameter(TYPE));
    }

    public static String createEditQuestionPageUrl(HttpServletRequest request,
                                                   Integer questionId) {
        return String.format("redirect:/show-edit-question?%s=%s&%s=%s&%s=%d&%s=%s",
                CATEGORY_PATH, request.getParameter(CATEGORY_PATH),
                TEST_PATH, request.getParameter(TEST_PATH),
                QUESTION_ENTRY_ID_PARAM, questionId,
                EDIT_MODE_PARAM, EditMode.SHOW);
    }

    public static Category updateCategory(int questionEntryId,
                                          ServletContext servletContext) {
        AbstractQuestionEntry questionEntry = getQuestionService(servletContext).getQuestionEntry(questionEntryId);
        Category category = null;
        if (questionEntry.getApproved()) {
            category = questionEntry.getCategory();
            questionEntry.changeCategoryCount(-1);
            getCategoryService(servletContext).update(category);
        }
        return category;
    }
}
