package util.question;

import data.questionEntry.QuestionEntryHandler;
import model.AbstractQuestionEntry;
import model.Answer;
import model.QuestionExam;
import util.AllConstants;
import util.AllConstantsAttribute;
import util.AllConstantsParam;
import util.GeneralUtility;
import util.exam.ExamUtility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NavigableSet;

import static util.AllConstantsParam.ANSWER_TEXT_PARAM;

/**
 * Created by Tatyana on 27.03.2016.
 */
public class QuestionEntryUtility {
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
            return getQuestionEntry(questionEntryId);
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

    public static AbstractQuestionEntry getQuestionEntry(Integer questionEntryId) {
        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        return questionEntryHandler.getQuestionEntry(questionEntryId);
    }

    public static String getQuestionUrl(int questionId) {
        return String.format("%s?%s=%d",
                AllConstants.SHOW_QUESTION_SERVLET_PAGE,
                AllConstantsParam.QUESTION_ENTRY_ID_PARAM,
                questionId);
    }
}
