package util;

import model.AbstractQuestionEntry;
import model.comment.Comment;
import model.person.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static util.AllConstantsAttribute.PERSON_ANSWERED_QUESTIONS;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;

/**
 * Created by Tatyana on 29.12.2015.
 */

public class PersonUtility extends SpringUtility {

    public static boolean isSysadmin(Person person) {
        return person != null && person.isSysadmin();
    }

    public static String getCommentAuthor(Comment comment) {
        if (comment == null || comment.getUser() == null
                || comment.getUser().getLogin() == null) {
            return AllConstants.UNKNOWN_USER;
        }
        return getPersonName(comment.getUser());
    }

    public static String getPersonName(Person person) {
        return person.getLogin();
    }

    public static List<AbstractQuestionEntry> getAnsweredQuestions(
            HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<AbstractQuestionEntry> answeredQuestions = (List<AbstractQuestionEntry>)
                session.getAttribute(PERSON_ANSWERED_QUESTIONS);
        if (answeredQuestions == null || answeredQuestions.isEmpty()) {
            answeredQuestions = getAnsweredQuestionsFromDB(request);
            session.setAttribute(PERSON_ANSWERED_QUESTIONS,
                    answeredQuestions);
        }
        return answeredQuestions;
    }

    private static List<AbstractQuestionEntry> getAnsweredQuestionsFromDB(
            HttpServletRequest request) {
        Person person = (Person) request.getSession().getAttribute(PERSON_ATTRIBUTE);
        if (person == null) {
            return null;
        }
        return getPersonService(request.getServletContext()).findAnsweredQuestions(person.getId());
    }

    public static void decodeRussianCharacters(Person person) {
        person.setLogin(GeneralUtility.decodeRussianCharacters(person.getLogin().trim()));
        person.setPassword(GeneralUtility.decodeRussianCharacters(person.getPassword().trim()));
    }
}
