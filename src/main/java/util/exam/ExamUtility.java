package main.java.util.exam;

import main.java.data.person.PersonHandler;
import main.java.model.Exam;
import main.java.model.QuestionEntry;
import main.java.model.person.Person;
import main.java.util.PersonUtility;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Tatyana on 30.04.2016.
 */
public class ExamUtility {
    public static PersonHandler personHandler = new PersonHandler();

    public static boolean isQuestionChecked(int questionNumber, Exam exam, List<QuestionEntry> answeredQuestions) {
        QuestionEntry questionEntry = exam.getQuestionEntries().get(questionNumber);
        Person person = exam.getPerson();
        if (person == null) {
            return false;
        }
        return (answeredQuestions != null && answeredQuestions.contains(questionEntry));
    }

    public static boolean isCurrentQuestionChecked(Exam exam, List<QuestionEntry> answeredQuestions) {
        return exam != null && exam.getCurrentNumber() != null && isQuestionChecked(exam.getCurrentNumber(), exam, answeredQuestions);
    }


    public static QuestionEntry getCurrentQuestionEntry(Exam exam) {
        if (exam == null || exam.getQuestionEntries() == null
                || exam.getQuestionEntries().isEmpty()
                || exam.getCurrentNumber() == null) {
            return null;
        }
        return exam.getQuestionEntries().get(exam.getCurrentNumber());
    }


    public static boolean isLastQuestion(Exam exam) {
        return exam.getCurrentNumber() == exam.getQuestionEntries().size() - 1;
    }
}
