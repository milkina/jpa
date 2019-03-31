package tags.exam;

import model.AbstractQuestionEntry;
import model.QuestionExam;
import util.PersonUtility;
import util.exam.ExamUtility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

import static util.AllConstants.MARK_QUESTION_HINT;
import static util.AllConstantsAttribute.CURRENT_EXAM_ATTRIBUTE;

/**
 * Created by Tatyana on 30.04.2016.
 */
public class CheckboxTag extends TagSupport {

    public int doStartTag() {
        try {
            JspWriter out = pageContext.getOut();
            QuestionExam exam = getExam();
            if (exam != null) {
                int questionEntryId = getQuestionEntryId(exam);
                String checkboxId = "isAnswered" + questionEntryId;
                List<AbstractQuestionEntry> answeredQuestions = PersonUtility.getAnsweredQuestions((HttpServletRequest) pageContext.getRequest());
                String isCheckedQuestion = ExamUtility.isCurrentQuestionChecked(exam, answeredQuestions) ? "checked" : "";
                String contextPath = pageContext.getServletContext().getContextPath();
                String str = String.format("<input type='checkbox' id='%s' name='isAnswered' " +
                                "title='%s' %s onchange=\"markAnswered('%s',%d);\">",
                        checkboxId,
                        MARK_QUESTION_HINT,
                        isCheckedQuestion,
                        contextPath,
                        questionEntryId);
                out.print(str);
            }
        } catch (IOException ioe) {
            System.out.println("Error in CheckboxTag: " + ioe);
        }
        return SKIP_BODY;
    }

    private int getQuestionEntryId(QuestionExam exam) {
        AbstractQuestionEntry questionEntry = ExamUtility.getCurrentQuestionEntry(exam);
        return questionEntry.getId();
    }

    private QuestionExam getExam() {
        return (QuestionExam) pageContext.getSession().getAttribute(CURRENT_EXAM_ATTRIBUTE);
    }
}
