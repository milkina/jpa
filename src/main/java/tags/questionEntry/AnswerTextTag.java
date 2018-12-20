package tags.questionEntry;

import model.Answer;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 05.06.2016.
 */
public class AnswerTextTag extends TagSupport {

    public int doStartTag() {
        try {
            AnswerTag parent =
                    (AnswerTag) findAncestorWithClass(this, AnswerTag.class);
            Answer answer = parent.getAnswer();
            JspWriter out = pageContext.getOut();
            out.print(answer.getText());
        } catch (IOException ioe) {
            System.out.println("Error in AnswerTextTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
