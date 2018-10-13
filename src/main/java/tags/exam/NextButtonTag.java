package tags.exam;

import model.AbstractExam;
import util.exam.ExamUtility;

import javax.servlet.jsp.tagext.TagSupport;

import static util.AllConstantsAttribute.CURRENT_EXAM_ATTRIBUTE;

/**
 * Created by Tatyana on 30.04.2016.
 */
public class NextButtonTag extends TagSupport {

    public int doStartTag() {
        AbstractExam exam = getExam();
        if (exam != null && !ExamUtility.isLastQuestion(exam)) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }

    private AbstractExam getExam() {
        return (AbstractExam) pageContext.getSession().getAttribute(CURRENT_EXAM_ATTRIBUTE);
    }
}
