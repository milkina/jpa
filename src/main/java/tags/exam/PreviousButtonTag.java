package tags.exam;

import model.Exam;

import javax.servlet.jsp.tagext.TagSupport;

import static util.AllConstantsAttribute.CURRENT_EXAM_ATTRIBUTE;

/**
 * Created by Tatyana on 30.04.2016.
 */
public class PreviousButtonTag extends TagSupport {

    public int doStartTag() {
        Exam exam = getExam();
        if (exam != null && exam.getCurrentNumber() != 0) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }

    private Exam getExam() {
        return (Exam) pageContext.getSession().getAttribute(CURRENT_EXAM_ATTRIBUTE);
    }
}
