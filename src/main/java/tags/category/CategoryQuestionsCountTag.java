package tags.category;

import model.Category;
import model.QuestionType;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;

import static util.AllConstantsAttribute.LOCALE;
import static util.AllConstantsParam.TYPE;
import static util.GeneralUtility.getResourceValue;

public class CategoryQuestionsCountTag extends TagSupport {
    public int doStartTag() {
        try {
            String type = pageContext.getRequest().getParameter(TYPE);
            CategoryTag parent =
                    (CategoryTag) findAncestorWithClass(this, CategoryTag.class);
            Category category = parent.getCategory();
            JspWriter out = pageContext.getOut();
            if (category != null) {
                int count = type.equals(QuestionType.QUESTION.toString()) ?
                        category.getQuestionsCount() : category.getTestsCount();
                Locale locale = (Locale) pageContext.getRequest().getAttribute(LOCALE);
                String total = getResourceValue(locale, "total", "label");
                String questions = getResourceValue(locale, "total.questions", "label");
                out.print(String.format("%s: %d %s.", total, count, questions));
            }
        } catch (IOException ioe) {
            System.out.println("Error in CategoryQuestionsCountTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
