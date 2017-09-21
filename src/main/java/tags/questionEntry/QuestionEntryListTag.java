package main.java.tags.questionEntry;

import main.java.data.category.CategoryHandler;
import main.java.data.questionEntry.QuestionEntryHandler;
import main.java.model.Category;
import main.java.model.QuestionEntry;
import main.java.model.person.Person;

import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.List;

import static main.java.util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static main.java.util.AllConstantsParam.CATEGORY_PATH;

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
        return (EVAL_BODY_INCLUDE);
    }

    private Category getCategory() {
        String categoryPathParameter = pageContext.getRequest().getParameter(CATEGORY_PATH);

        return categoryHandler.getCategory(categoryPathParameter);
    }
}



