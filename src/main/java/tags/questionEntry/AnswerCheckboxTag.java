package tags.questionEntry;

import model.Answer;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class AnswerCheckboxTag extends TagSupport {

    public int doStartTag() {
        try {
            AnswerTag parent =
                    (AnswerTag) findAncestorWithClass(this, AnswerTag.class);
            Answer answer = parent.getAnswer();
            JspWriter out = pageContext.getOut();
            if (answer.getCorrect()) {
                out.print("checked");
            }
        } catch (IOException ioe) {
            System.out.println("Error in AnswerCheckboxTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
