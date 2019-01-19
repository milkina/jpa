package spring.question;

import controller.EditMode;
import data.category.CategoryHandler;
import data.questionEntry.QuestionEntryHandler;
import data.test.TestHandler;
import model.AbstractQuestionEntry;
import model.Category;
import model.Test;
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
import java.util.Locale;

import static util.AllConstants.ADD_QUESTION_PAGE;
import static util.AllConstants.EDIT_QUESTION_ENTRY_PAGE;
import static util.AllConstants.MESSAGE_PAGE;
import static util.AllConstants.MOVE_QUESTIONS_PAGE;
import static util.AllConstants.SPRING_MESSAGE_PAGE;
import static util.AllConstantsAttribute.LOCALE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsAttribute.QUESTION_ENTRY_ATTRIBUTE;
import static util.AllConstantsParam.ANSWER_NUMBER;
import static util.AllConstantsParam.CATEGORY_PATH;
import static util.AllConstantsParam.FROM_NUMBER;
import static util.AllConstantsParam.OLD_CATEGORY_PATH;
import static util.AllConstantsParam.OLD_TEST_PATH;
import static util.AllConstantsParam.QUESTION_ENTRY_ID_PARAM;
import static util.AllConstantsParam.QUESTION_TEXT_PARAM;
import static util.AllConstantsParam.TEST_PATH;
import static util.AllConstantsParam.TO_NUMBER;
import static util.AllMessage.QUESTIONS_MOVED;
import static util.GeneralUtility.getResourceValue;
import static util.question.QuestionEntryUtility.addQuestionEntry;
import static util.question.QuestionEntryUtility.changeQuestionType;
import static util.question.QuestionEntryUtility.createEditQuestionPageUrl;
import static util.question.QuestionEntryUtility.createShowQuestionPageUrl;
import static util.question.QuestionEntryUtility.findQuestionEntry;
import static util.question.QuestionEntryUtility.fixTinyMCEIssue;
import static util.question.QuestionEntryUtility.setAnswers;
import static util.question.QuestionEntryUtility.updateCategory;

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

        String message = getResourceValue(locale, "question.added", "messages");
        model.addAttribute(MESSAGE_ATTRIBUTE, message);

        TestUtility.loadTestsToServletContext(request.getServletContext());
        return new ModelAndView(SPRING_MESSAGE_PAGE);
    }

    @RequestMapping(value = "/show-questions")
    public String showQuestions(Locale locale) {
        HttpServletRequest request = GeneralUtility.getRequest();
        request.setAttribute(LOCALE, locale);
        return "/question/show-questions";
    }

    @RequestMapping(value = "/show-edit-question")
    public ModelAndView showEditQuestion(@RequestParam(QUESTION_ENTRY_ID_PARAM) String questionEntryId,
                                         @RequestParam(TEST_PATH) String testPath) {
        AbstractQuestionEntry questionEntry = findQuestionEntry(questionEntryId);

        fixTinyMCEIssue(questionEntry);
        ModelAndView modelAndView = new ModelAndView(EDIT_QUESTION_ENTRY_PAGE);
        modelAndView.addObject(QUESTION_ENTRY_ATTRIBUTE, questionEntry);
        if (GeneralUtility.isEmpty(testPath)) {
            Test test = new TestHandler().getTestByQuestion(questionEntry);
            modelAndView.addObject(TEST_PATH, test.getPathName());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/edit-question", method = RequestMethod.POST)
    public ModelAndView editQuestion(@RequestParam(QUESTION_TEXT_PARAM) String newQuestionText,
                                     @RequestParam(CATEGORY_PATH) String categoryPath,
                                     @RequestParam(OLD_CATEGORY_PATH) String oldCategoryPath,
                                     @RequestParam(QUESTION_ENTRY_ID_PARAM) String questionEntryId,
                                     Locale locale) {
        newQuestionText = GeneralUtility.decodeRussianCharacters(newQuestionText.trim());
        AbstractQuestionEntry questionEntry = findQuestionEntry(questionEntryId);
        HttpServletRequest request = GeneralUtility.getRequest();
        Person person = TestUtility.getPersonFromSession(request.getSession());
        questionEntry.setApproved(person.isSysadmin());
        Category category =
                CategoryUtility.getCategoryFromServletContext(request);
        Category oldCategory = null;
        CategoryHandler categoryHandler = new CategoryHandler();
        if (!oldCategoryPath.equals(categoryPath)) {
            //moved to different category
            questionEntry.setCategory(category);
            oldCategory = categoryHandler.getCategory(oldCategoryPath);
        }

        questionEntry.getQuestion().setText(newQuestionText);
        int answerNumber = GeneralUtility.getIntegerValue(request, ANSWER_NUMBER);

        int oldAnswersSize = questionEntry.getAnswers().size();
        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        questionEntryHandler.removeAnswers(questionEntry);

        setAnswers(request, answerNumber, questionEntry);

        questionEntryHandler.updateQuestionEntry(questionEntry);
        changeQuestionType(questionEntry, oldAnswersSize, questionEntryHandler);

        categoryHandler.updateCategoryCounts(category);
        if (oldCategory != null) {
            categoryHandler.updateCategoryCounts(oldCategory);
        }
        TestUtility.loadTestsToServletContext(request.getServletContext());
        ModelAndView model = new ModelAndView(SPRING_MESSAGE_PAGE);
        String message = getResourceValue(locale, "question.updated", "messages");
        model.addObject(MESSAGE_ATTRIBUTE, message);
        return model;
    }

    @RequestMapping(value = "/up-question")
    public String up(@RequestParam("EDIT_MODE_PARAM") String modeParam) {
        EditMode mode = EditMode.valueOf(modeParam);
        HttpServletRequest request = GeneralUtility.getRequest();
        Integer questionId = GeneralUtility.getIntegerValue(
                request, QUESTION_ENTRY_ID_PARAM);

        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        questionEntryHandler.moveQuestionEntryUp(questionId);
        TestUtility.loadTestsToServletContext(request.getServletContext());
        return mode == EditMode.UP ? createShowQuestionPageUrl(request)
                : createEditQuestionPageUrl(request, questionId);
    }

    @RequestMapping(value = "/delete-question")
    public ModelAndView delete(Locale locale) {
        HttpServletRequest request = GeneralUtility.getRequest();
        int questionEntryId = GeneralUtility.getIntegerValue(request,
                QUESTION_ENTRY_ID_PARAM);

        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        updateCategory(questionEntryId, questionEntryHandler);
        questionEntryHandler.deleteQuestionEntry(questionEntryId);
        TestUtility.loadTestsToServletContext(request.getServletContext());
        ModelAndView model = new ModelAndView(SPRING_MESSAGE_PAGE);
        model.addObject(MESSAGE_ATTRIBUTE, getResourceValue(locale, "question.removed", "messages"));
        return model;
    }

    @RequestMapping(value = "/approve-question")
    public ModelAndView approve(Locale locale) {
        HttpServletRequest request = GeneralUtility.getRequest();
        int questionEntryId = GeneralUtility.getIntegerValue(request,
                QUESTION_ENTRY_ID_PARAM);

        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        AbstractQuestionEntry questionEntry = questionEntryHandler.getQuestionEntry(questionEntryId);
        questionEntry.setApproved(true);
        questionEntryHandler.updateQuestionEntry(questionEntry);
        new CategoryHandler().updateCategory(questionEntry.getCategory());
        TestUtility.loadTestsToServletContext(request.getServletContext());
        ModelAndView modelAndView = new ModelAndView(SPRING_MESSAGE_PAGE);
        modelAndView.addObject(MESSAGE_ATTRIBUTE, getResourceValue(locale, "question.approved", "messages"));
        return modelAndView;
    }

    @RequestMapping(value = "/show-move-batch")
    public String showMoveBatch() {
        return "/question/move-questions";
    }

    @RequestMapping(value = "/move-batch", method = RequestMethod.POST)
    public ModelAndView moveBatch(Locale locale) {
        CategoryHandler categoryHandler = new CategoryHandler();
        HttpServletRequest request = GeneralUtility.getRequest();
        Category category = CategoryUtility.getCategoryByPath(request);
        String oldTestPath = request.getParameter(OLD_TEST_PATH).trim();
        String oldCategoryPath = request.getParameter(OLD_CATEGORY_PATH);
        Category oldCategory = categoryHandler.getCategory(oldCategoryPath);
        QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
        long oldCategoryQuestionsNumber =
                questionEntryHandler.getAllQuestions(oldCategory).size();

        Integer from = GeneralUtility.getIntegerValue(request, FROM_NUMBER);
        Integer to = GeneralUtility.getIntegerValue(request, TO_NUMBER);
        String page = SPRING_MESSAGE_PAGE;
        int amount = to - from + 1;
        String message = String.format(QUESTIONS_MOVED, amount);
        if (oldCategory.getId() == category.getId()) {
            message = getResourceValue(locale, "select.different.category", "messages");
            page = String.format(MOVE_QUESTIONS_PAGE,
                    oldTestPath, oldCategoryPath, message);
        } else if (QuestionEntryUtility.isValidNumbers(
                from, to, oldCategoryQuestionsNumber)) {
            questionEntryHandler.moveBatch(oldCategory, category, from, to);
            categoryHandler.updateCategoryCounts(category);
            categoryHandler.updateCategoryCounts(oldCategory);
        } else {
            message = getResourceValue(locale, "invalid.numbers", "messages");
            page = String.format(MOVE_QUESTIONS_PAGE, oldTestPath,
                    oldCategoryPath, message);
        }
        TestUtility.loadTestsToServletContext(request.getServletContext());
        ModelAndView modelAndView = new ModelAndView(page);
        modelAndView.addObject(MESSAGE_ATTRIBUTE, message);
        return modelAndView;
    }
}