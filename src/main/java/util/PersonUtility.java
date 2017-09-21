package main.java.util;

import main.java.data.person.PersonHandler;
import main.java.model.QuestionEntry;
import main.java.model.comment.Comment;
import main.java.model.person.Person;
import main.java.model.person.PersonInfo;

import javax.servlet.http.HttpSession;
import java.util.List;

import static main.java.util.AllConstantsAttribute.PERSON_ANSWERED_QUESTIONS;
import static main.java.util.AllConstantsAttribute.PERSON_ATTRIBUTE;

/**
 * Created by Tatyana on 29.12.2015.
 */
public class PersonUtility {
    public static boolean isSysadmin(Person person) {
        return person != null && person.isSysadmin();
    }

    public static String getCommentAuthor(Comment comment) {
        if (comment == null || comment.getUser() == null || comment.getUser().getPersonInfo() == null) {
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

    public static List<QuestionEntry> getAnsweredQuestions(HttpSession session) {
        List<QuestionEntry> answeredQuestions = (List<QuestionEntry>) session.getAttribute(PERSON_ANSWERED_QUESTIONS);
        if (answeredQuestions == null || answeredQuestions.isEmpty()) {
            answeredQuestions = getAnsweredQuestionsFromDB(session);
            session.setAttribute(PERSON_ANSWERED_QUESTIONS, answeredQuestions);
               }
        return answeredQuestions;
    }

    private static List<QuestionEntry> getAnsweredQuestionsFromDB(HttpSession session) {
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        if (person == null) {
            return null;
        }
        PersonHandler personHandler = new PersonHandler();
        return personHandler.findAnsweredQuestions(person.getID());
    }
}
