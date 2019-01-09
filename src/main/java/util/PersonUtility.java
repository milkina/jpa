package util;

import data.person.PersonHandler;
import model.AbstractQuestionEntry;
import model.comment.Comment;
import model.person.Person;
import model.person.PersonInfo;

import javax.servlet.http.HttpSession;
import java.util.List;

import static util.AllConstantsAttribute.PERSON_ANSWERED_QUESTIONS;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;

/**
 * Created by Tatyana on 29.12.2015.
 */
public class PersonUtility {
    public static boolean isSysadmin(Person person) {
        return person != null && person.isSysadmin();
    }

    public static String getCommentAuthor(Comment comment) {
        if (comment == null || comment.getUser() == null
                || comment.getUser().getPersonInfo() == null) {
            return AllConstants.UNKNOWN_USER;
        }
        return getPersonName(comment.getUser());
    }

    public static String getPersonName(Person person) {
        String personName = getPersonName(person.getPersonInfo());
        if (personName.isEmpty()) {
            return person.getLogin();
        }
        return personName;
    }

    private static String getPersonName(PersonInfo personInfo) {
        String firstName = personInfo.getFirstName();
        String lastName = personInfo.getLastName();
        firstName = firstName == null ? "" : firstName;
        lastName = lastName == null ? "" : lastName;
        if (firstName.isEmpty() && lastName.isEmpty()) {
            return "";
        }
        if (lastName.isEmpty()) {
            return firstName;
        }
        if (firstName.isEmpty()) {
            return lastName;
        }
        return firstName + " " + lastName;
    }

    public static List<AbstractQuestionEntry> getAnsweredQuestions(
            HttpSession session) {
        List<AbstractQuestionEntry> answeredQuestions = (List<AbstractQuestionEntry>)
                session.getAttribute(PERSON_ANSWERED_QUESTIONS);
        if (answeredQuestions == null || answeredQuestions.isEmpty()) {
            answeredQuestions = getAnsweredQuestionsFromDB(session);
            session.setAttribute(PERSON_ANSWERED_QUESTIONS,
                    answeredQuestions);
        }
        return answeredQuestions;
    }

    private static List<AbstractQuestionEntry> getAnsweredQuestionsFromDB(
            HttpSession session) {
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        if (person == null) {
            return null;
        }
        PersonHandler personHandler = new PersonHandler();
        return personHandler.findAnsweredQuestions(person.getID());
    }

    public static void decodeRussianCharacters(Person person) {
        person.setLogin(GeneralUtility.decodeRussianCharacters(person.getLogin().trim()));
        person.setPassword(GeneralUtility.decodeRussianCharacters(person.getPassword().trim()));
        person.getPersonInfo().setFirstName(GeneralUtility.decodeRussianCharacters(person.getPersonInfo().getFirstName().trim()));
        person.getPersonInfo().setLastName(GeneralUtility.decodeRussianCharacters(person.getPersonInfo().getLastName().trim()));
    }
}
