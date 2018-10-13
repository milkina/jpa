package tags.questionEntry;

import model.AbstractQuestionEntry;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 05.06.2016.
 */
public class QuestionTextTag extends TagSupport {

    public int doStartTag() {
        try {
            QuestionEntryTag parent = (QuestionEntryTag)
                    findAncestorWithClass(this, QuestionEntryTag.class);
            AbstractQuestionEntry questionEntry = parent.getQuestionEntry();
            JspWriter out = pageContext.getOut();
            out.print(questionEntry.getQuestion().getText());
        } catch (IOException ioe) {
            System.out.println("Error in QuestionTextTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
