package tags.questionEntry;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class QuestionEntryApprovedTag  extends TagSupport {

    public int doStartTag() {
        try {
            QuestionEntryTag parent =
                    (QuestionEntryTag) findAncestorWithClass(this, QuestionEntryTag.class);
            JspWriter out = pageContext.getOut();
            String text = parent.getQuestionEntry().getApproved() ? "Approved" : "Not Approved";
            out.print(text);
        } catch (IOException ioe) {
            System.out.println("Error in QuestionEntryApprovedTag: " + ioe);
        }
        return (SKIP_BODY);
    }
}
