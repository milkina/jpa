package main.java.tags.exam;

import main.java.model.Exam;
import main.java.util.exam.ExamUtility;

import javax.servlet.jsp.tagext.TagSupport;

import static main.java.util.AllConstantsAttribute.CURRENT_EXAM_ATTRIBUTE;

/**
 * Created by Tatyana on 30.04.2016.
 */
public class NextButtonTag extends TagSupport {

    public int doStartTag() {
        Exam exam = getExam();
        if (exam != null && !ExamUtility.isLastQuestion(exam)) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }

    private Exam getExam() {
        return (Exam) pageContext.getSession().getAttribute(CURRENT_EXAM_ATTRIBUTE);
    }
}
