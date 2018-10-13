package tags.exam;

import model.AbstractExam;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

import static util.AllConstantsAttribute.CURRENT_EXAM_ATTRIBUTE;

/**
 * Created by Tatyana on 10.05.2016.
 */
public class NumberTag extends TagSupport {

    public int doStartTag() {
        try {
            JspWriter out = pageContext.getOut();
            int number = getExam().getCurrentNumber() + 1;
            out.print(number);
        } catch (IOException ioe) {
            System.out.println("Error in NumberTag: " + ioe);
        }
        return SKIP_BODY;
    }

    private AbstractExam getExam() {
        return (AbstractExam) pageContext.getSession().getAttribute(CURRENT_EXAM_ATTRIBUTE);
    }
}
