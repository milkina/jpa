package util.exam;

import data.category.CategoryHandler;
import data.exam.ExamHandler;
import data.person.PersonHandler;
import data.questionEntry.QuestionEntryHandler;
import model.AbstractExam;
import model.AbstractQuestionEntry;
import model.Category;
import model.QuestionExam;
import model.TestExam;
import model.TestQuestionEntry;
import model.person.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import static util.AllConstants.SHOW_QUIZ_QUESTION_PAGE;
import static util.AllConstants.SHOW_TEST_QUESTION_PAGE;
import static util.AllConstantsAttribute.CURRENT_EXAM_ATTRIBUTE;
import static util.AllConstantsParam.NUMBER_OF_QUESTIONS;

/**
 * Created by Tatyana on 30.04.2016.
 */
public class ExamUtility {
    private static PersonHandler personHandler = new PersonHandler();
    private static CategoryHandler categoryHandler = new CategoryHandler();

    public static PersonHandler getPersonHandler() {
        return personHandler;
    }

    public static void setPersonHandler(PersonHandler personHandler) {
        ExamUtility.personHandler = personHandler;
    }

    public static boolean isQuestionChecked(int questionNumber, QuestionExam exam,
                                            List<AbstractQuestionEntry> ansQuestions) {
        AbstractQuestionEntry questionEntry =
                (AbstractQuestionEntry) exam.getQuestionEntries().get(questionNumber);
        Person person = exam.getPerson();
        if (person == null) {
            return false;
        }
        return ansQuestions != null
                && ansQuestions.contains(questionEntry);
    }

    public static boolean isCurrentQuestionChecked(
            QuestionExam exam, List<AbstractQuestionEntry> answeredQuestions) {
        return exam != null && exam.getCurrentNumber() != null
                && isQuestionChecked(exam.getCurrentNumber(),
                exam, answeredQuestions);
    }

    public static AbstractQuestionEntry getCurrentQuestionEntry(QuestionExam exam) {
        if (exam == null || exam.getQuestionEntries() == null
                || exam.getQuestionEntries().isEmpty()
                || exam.getCurrentNumber() == null) {
            return null;
        }
        return (AbstractQuestionEntry) exam.getQuestionEntries().get(exam.getCurrentNumber());
    }


    public static boolean isLastQuestion(AbstractExam exam) {
        return exam.getCurrentNumber() == exam.getQuestionEntries().size() - 1;
    }

    public static void createExam(TestExam exam) {
        exam.setPercent(exam.getRightQuestionsCount() / exam.getQuestionEntries().size() * 100.0);
        exam.setDate(new Date());
        new ExamHandler().createExam(exam);
    }

    public static List<Category> getCategories(String[] categoryPaths) {
        List<Category> categories = new ArrayList<>();
        for (String categoryPath : categoryPaths) {
            categories.add(categoryHandler.getCategory(categoryPath));
        }
        return categories;
    }

    public static int getCount(String[] categoryPaths, HttpServletRequest request, Function<Category, Integer> function) {
        int count = 0;
        String countString = request.getParameter(NUMBER_OF_QUESTIONS);
        if (countString != null && !countString.isEmpty()) {
            count = Integer.parseInt(countString);
        } else if (categoryPaths.length == 1) {
            Category category = getCategory(categoryPaths[0]);
            count = function.apply(category) != 0 ? function.apply(category) : 20;
        }
        return count;
    }

    public static TestExam setTestExam(HttpSession session, Person person,
                                       List<AbstractQuestionEntry> questionEntries, String[] categoryPaths, int count) {
        List<Category> categories = getCategories(categoryPaths);
        TestExam exam = new TestExam();
        exam.setPerson(person);
        exam.setQuestionEntries(questionEntries);
        exam.setCurrentNumber(0);
        exam.setCategories(categories);
        exam.setCurrentQuestionEntry(questionEntries.get(0));
        exam.setAmount(count);
        session.setAttribute(CURRENT_EXAM_ATTRIBUTE, exam);
        return exam;
    }

    public static Category getCategory(String categoryPath) {
        CategoryHandler categoryHandler = new CategoryHandler();
        return categoryHandler.getCategory(categoryPath);
    }

    public static String updateCurrentQuestionEntry(int currentNumber, AbstractExam exam) {
        String url = null;
        exam.setCurrentNumber(currentNumber);
        AbstractQuestionEntry currentQuestionEntry =
                (AbstractQuestionEntry) exam.getQuestionEntries().get(currentNumber);
        if (exam instanceof QuestionExam) {
            url = SHOW_QUIZ_QUESTION_PAGE;
        } else {
            url = SHOW_TEST_QUESTION_PAGE;
        }
        exam.setCurrentQuestionEntry(currentQuestionEntry);
        return url;
    }

    public static QuestionExam setQuestionExam(HttpSession session, Person person,
                                               List<AbstractQuestionEntry> questionEntries,
                                               String[] categoryPaths, int count) {
        List<Category> categories = getCategories(categoryPaths);
        QuestionExam exam = new QuestionExam();
        exam.setPerson(person);
        exam.setQuestionEntries(questionEntries);
        exam.setCurrentNumber(0);
        exam.setCategories(categories);
        exam.setCurrentQuestionEntry(questionEntries.get(0));
        exam.setAmount(count);
        session.setAttribute(CURRENT_EXAM_ATTRIBUTE, exam);
        return exam;
    }
}
