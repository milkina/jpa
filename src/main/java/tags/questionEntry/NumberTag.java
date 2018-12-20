package tags.questionEntry;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 05.06.2016.
 */
public class NumberTag extends TagSupport {

    public int doStartTag() {
        try {
            QuestionEntryTag parent =
                    (QuestionEntryTag) findAncestorWithClass(this, QuestionEntryTag.class);
            JspWriter out = pageContext.getOut();
            out.print(parent.getNumber());
        } catch (IOException ioe) {
            System.out.println("Error in NumberTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
