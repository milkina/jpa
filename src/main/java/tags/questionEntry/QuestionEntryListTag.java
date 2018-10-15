package tags.questionEntry;

import data.category.CategoryHandler;
import data.questionEntry.QuestionEntryHandler;
import model.AbstractExam;
import model.AbstractQuestionEntry;
import model.Category;
import model.QuestionType;

import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.List;

import static util.AllConstantsParam.CATEGORY_PATH;
import static util.AllConstantsParam.TYPE;

/**
 * Created by Tatyana on 05.06.2016.
 */
public class QuestionEntryListTag extends BodyTagSupport {
    QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
    CategoryHandler categoryHandler = new CategoryHandler();
    private List<AbstractQuestionEntry> questionEntries;

    public List<AbstractQuestionEntry> getQuestionEntries() {
        return questionEntries;
    }

    public int doStartTag() {
        Category category = getCategory();
        String type = pageContext.getRequest().getParameter(TYPE);
        if(QuestionType.QUESTION.toString().equals(type)){
            questionEntries = questionEntryHandler.getAllQuestions(category);
        }else {
            questionEntries = questionEntryHandler.getAllTestQuestions(category);
        }

        return EVAL_BODY_INCLUDE;
    }

    private Category getCategory() {
        String categoryPathParameter = pageContext.getRequest().getParameter(CATEGORY_PATH);
        return categoryHandler.getCategory(categoryPathParameter);
    }
}



