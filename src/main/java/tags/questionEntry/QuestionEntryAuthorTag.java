package tags.questionEntry;

import util.GeneralUtility;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Date;

public class QuestionEntryAuthorTag extends TagSupport {

    public int doStartTag() {
        try {
            QuestionEntryTag parent =
                    (QuestionEntryTag) findAncestorWithClass(this, QuestionEntryTag.class);
            JspWriter out = pageContext.getOut();
            String login = parent.getQuestionEntry().getPerson().getLogin();
            out.print(login);
        } catch (IOException ioe) {
            System.out.println("Error in QuestionEntryAuthorTag: " + ioe);
        }
        return (SKIP_BODY);
    }
}