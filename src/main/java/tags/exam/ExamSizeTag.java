package tags.exam;

import model.AbstractExam;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

import static util.AllConstantsAttribute.CURRENT_EXAM_ATTRIBUTE;

/**
 * Created by Tatyana on 10.05.2016.
 */
public class ExamSizeTag extends TagSupport {

    public int doStartTag() {
        try {
            JspWriter out = pageContext.getOut();
            int size = getExam().getQuestionEntries().size();
            out.print(size);
        } catch (IOException ioe) {
            System.out.println("Error in ExamSizeTag: " + ioe);
        }
        return SKIP_BODY;
    }

    private AbstractExam getExam() {
        return (AbstractExam) pageContext.getSession().getAttribute(CURRENT_EXAM_ATTRIBUTE);
    }
}


