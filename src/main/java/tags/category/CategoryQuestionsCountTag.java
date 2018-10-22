package tags.category;

import model.Category;
import model.QuestionType;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

import static util.AllConstantsParam.TYPE;

public class CategoryQuestionsCountTag extends TagSupport {
    public int doStartTag() {
        try {
            String type = pageContext.getRequest().getParameter(TYPE);
            CategoryTag parent =
                    (CategoryTag) findAncestorWithClass(this, CategoryTag.class);
            Category category = parent.getCategory();
            JspWriter out = pageContext.getOut();
            int count = type.equals(QuestionType.QUESTION.toString()) ?
                    category.getQuestionsCount() : category.getTestsCount();
            out.print(count);
        } catch (IOException ioe) {
            System.out.println("Error in CategoryQuestionsCountTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
