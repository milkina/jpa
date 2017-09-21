package main.java.tags.questionEntry;

import main.java.controller.EditMode;
import main.java.model.QuestionEntry;
import main.java.util.ShowQuestionUtility;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 05.06.2016.
 */
public class ShowHrefTag  extends TagSupport {

    public int doStartTag() {
        try {
            QuestionEntryTag parent =
                    (QuestionEntryTag) findAncestorWithClass(this, QuestionEntryTag.class);
            QuestionEntry questionEntry = parent.getQuestionEntry();
            String path = ShowQuestionUtility.createPath(questionEntry, pageContext.getServletContext().getContextPath());
            JspWriter out = pageContext.getOut();
            out.print(path + EditMode.SHOW);
        } catch (IOException ioe) {
            System.out.println("Error in ShowHrefTag: " + ioe);
        }
        return (SKIP_BODY);
    }
}
