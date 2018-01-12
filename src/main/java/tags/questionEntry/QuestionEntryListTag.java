package tags.questionEntry;

import data.category.CategoryHandler;
import data.questionEntry.QuestionEntryHandler;
import model.Category;
import model.QuestionEntry;
import model.person.Person;

import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.List;

import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsParam.CATEGORY_PATH;

/**
 * Created by Tatyana on 05.06.2016.
 */
public class QuestionEntryListTag extends BodyTagSupport {
    QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
    CategoryHandler categoryHandler = new CategoryHandler();
    private List<QuestionEntry> questionEntries;

    public List<QuestionEntry> getQuestionEntries() {
        return questionEntries;
    }

    public int doStartTag() {
        Category category = getCategory();
        Person person = (Person) pageContext.getSession().getAttribute(PERSON_ATTRIBUTE);
        questionEntries = questionEntryHandler.getQuestions(category, person, "ALL");
        return EVAL_BODY_INCLUDE;
    }

    private Category getCategory() {
        String categoryPathParameter = pageContext.getRequest().getParameter(CATEGORY_PATH);
        return categoryHandler.getCategory(categoryPathParameter);
    }
}



