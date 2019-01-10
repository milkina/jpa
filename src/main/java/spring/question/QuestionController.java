package spring.question;

import data.questionEntry.QuestionEntryHandler;
import model.AbstractQuestionEntry;
import model.Category;
import model.Question;
import model.QuestionEntry;
import model.TestQuestionEntry;
import model.person.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import util.CategoryUtility;
import util.GeneralUtility;
import util.TestUtility;
import util.question.QuestionEntryUtility;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Locale;

import static util.AllConstants.ADD_QUESTION_PAGE;
import static util.AllConstants.SPRING_MESSAGE_PAGE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsParam.QUESTION_TEXT_PARAM;

@Controller
public class QuestionController {

    @RequestMapping(value = "/add-question", method = RequestMethod.GET)
    public ModelAndView addQuestion() {
        return new ModelAndView(ADD_QUESTION_PAGE);
    }

    @RequestMapping(value = "/save-question", method = RequestMethod.POST)
    public ModelAndView saveQuestion(@RequestParam("answerNumber") int answerNumber, ModelMap model, Locale locale) {
        HttpServletRequest request = GeneralUtility.getRequest();
        addQuestionEntry(answerNumber, request);

        String message = GeneralUtility.getResourceValue(locale, "question.added", "messages");
        model.addAttribute(MESSAGE_ATTRIBUTE, message);

        TestUtility.loadTestsToServletContext(request.getServletContext());
        return new ModelAndView(SPRING_MESSAGE_PAGE);
    }

    private void addQuestionEntry(int answerNumber, HttpServletRequest request) {
        Category category =
                CategoryUtility.getCategoryFromServletContext(request);

        AbstractQuestionEntry newQuestionEntry = answerNumber > 1 ? new TestQuestionEntry() : new QuestionEntry();
        newQuestionEntry.setCategory(category);
        QuestionEntryUtility.setAnswers(request, answerNumber, newQuestionEntry);
        setQuestionText(request, newQuestionEntry);
        newQuestionEntry.setCreatedDate(new Date());
        setPerson(request, newQuestionEntry);

        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        questionEntryHandler.addQuestionEntry(newQuestionEntry);
    }

    private void setPerson(HttpServletRequest request, AbstractQuestionEntry newQuestionEntry) {
        if (request.getSession().getAttribute(PERSON_ATTRIBUTE) != null) {
            Person person = (Person)
                    request.getSession().getAttribute(PERSON_ATTRIBUTE);
            newQuestionEntry.setPerson(person);
            newQuestionEntry.setApproved(person.isSysadmin());
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
