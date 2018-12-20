package tags.questionEntry;

import data.category.CategoryHandler;
import data.questionEntry.QuestionEntryHandler;
import model.AbstractQuestionEntry;
import model.Category;
import model.QuestionType;
import model.person.Person;
import util.GeneralUtility;

import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.List;

import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsParam.CATEGORY_PATH;
import static util.AllConstantsParam.TYPE;

/**
 * Created by Tatyana on 05.06.2016.
 */
public class QuestionEntryListTag extends BodyTagSupport {
    private QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
    private CategoryHandler categoryHandler = new CategoryHandler();
    private List<AbstractQuestionEntry> questionEntries;

    public List<AbstractQuestionEntry> getQuestionEntries() {
        return questionEntries;
    }

    public int doStartTag() {
        Category category = getCategory();
        String type = pageContext.getRequest().getParameter(TYPE);
        if (QuestionType.QUESTION.toString().equals(type)) {
            questionEntries = questionEntryHandler.getAllQuestions(category);
        } else if (QuestionType.TEST.toString().equals(type)) {
            questionEntries = questionEntryHandler.getAllTestQuestions(category);
        } else if (QuestionType.NOT_APPROVED.toString().equals(type)) {
            questionEntries = questionEntryHandler.getNotApprovedQuestions();
        } else if (QuestionType.MY_QUESTIONS.toString().equals(type)) {
            Person person = (Person) pageContext.getSession().getAttribute(PERSON_ATTRIBUTE);
            questionEntries = questionEntryHandler.getPersonQuestions(person.getID());
        }
        return EVAL_BODY_INCLUDE;
    }

    private Category getCategory() {
        String categoryPathParameter = pageContext.getRequest().getParameter(CATEGORY_PATH);
        if (!GeneralUtility.isEmpty(categoryPathParameter)) {
            return categoryHandler.getCategory(categoryPathParameter);
        } else {
            return null;
        }
    }
}
