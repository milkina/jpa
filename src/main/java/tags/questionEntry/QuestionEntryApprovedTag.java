package tags.questionEntry;

import util.GeneralUtility;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;

import static util.AllConstantsAttribute.LOCALE;

public class QuestionEntryApprovedTag extends TagSupport {

    public int doStartTag() {
        try {

            QuestionEntryTag parent =
                    (QuestionEntryTag) findAncestorWithClass(this, QuestionEntryTag.class);
            JspWriter out = pageContext.getOut();
            String text = parent.getQuestionEntry().getApproved() ? "approved" : "not.approved";
            Locale locale = (Locale) pageContext.getRequest().getAttribute(LOCALE);
            String value = GeneralUtility.getResourceValue(locale, text, "label");
            out.print(value);
        } catch (IOException ioe) {
            System.out.println("Error in QuestionEntryApprovedTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
