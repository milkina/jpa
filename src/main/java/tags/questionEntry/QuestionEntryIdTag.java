package tags.questionEntry;

import model.AbstractQuestionEntry;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 05.06.2016.
 */
public class QuestionEntryIdTag extends TagSupport {

    public int doStartTag() {
        try {
            QuestionEntryTag parent =
                    (QuestionEntryTag) findAncestorWithClass(this, QuestionEntryTag.class);
            AbstractQuestionEntry questionEntry = parent.getQuestionEntry();
            JspWriter out = pageContext.getOut();
            out.print(questionEntry.getId());
        } catch (IOException ioe) {
            System.out.println("Error in QuestionEntryIdTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
