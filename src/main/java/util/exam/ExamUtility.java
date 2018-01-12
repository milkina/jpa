package util.exam;

import data.person.PersonHandler;
import model.Exam;
import model.QuestionEntry;
import model.person.Person;

import java.util.List;

/**
 * Created by Tatyana on 30.04.2016.
 */
public class ExamUtility {
    private static PersonHandler personHandler = new PersonHandler();

    public static PersonHandler getPersonHandler() {
        return personHandler;
    }

    public static void setPersonHandler(PersonHandler personHandler) {
        ExamUtility.personHandler = personHandler;
    }

    public static boolean isQuestionChecked(int questionNumber, Exam exam,
                                            List<QuestionEntry> ansQuestions) {
        QuestionEntry questionEntry =
                exam.getQuestionEntries().get(questionNumber);
        Person person = exam.getPerson();
        if (person == null) {
            return false;
        }
        return ansQuestions != null
                && ansQuestions.contains(questionEntry);
    }

    public static boolean isCurrentQuestionChecked(
            Exam exam, List<QuestionEntry> answeredQuestions) {
        return exam != null && exam.getCurrentNumber() != null
                && isQuestionChecked(exam.getCurrentNumber(),
                exam, answeredQuestions);
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
