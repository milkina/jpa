package tags.questionEntry;

import util.GeneralUtility;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Date;

public class QuestionEntryCreatedDateTag extends TagSupport {

    public int doStartTag() {
        try {
            QuestionEntryTag parent =
                    (QuestionEntryTag) findAncestorWithClass(this, QuestionEntryTag.class);
            JspWriter out = pageContext.getOut();
            Date date = parent.getQuestionEntry().getCreatedDate();
            out.print(GeneralUtility.formatDate(date));
        } catch (IOException ioe) {
            System.out.println("Error in QuestionEntryCreatedDateTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
