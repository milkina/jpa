package main.java.tags.questionEntry;

import main.java.model.QuestionEntry;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 05.06.2016.
 */
public class AnswerTextTag extends TagSupport {

    public int doStartTag() {
        try {
            QuestionEntryTag parent =
                    (QuestionEntryTag) findAncestorWithClass(this, QuestionEntryTag.class);
            QuestionEntry questionEntry = parent.getQuestionEntry();
            JspWriter out = pageContext.getOut();
            out.print(questionEntry.getAnswer().getText());
        } catch (IOException ioe) {
            System.out.println("Error in AnswerTextTag: " + ioe);
        }
        return (SKIP_BODY);
    }
}