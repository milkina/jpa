package main.java.tags.exam;

import main.java.model.Exam;
import main.java.model.QuestionEntry;
import main.java.util.PersonUtility;
import main.java.util.exam.ExamUtility;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

import static main.java.util.AllConstants.MARK_QUESTION_HINT;
import static main.java.util.AllConstantsAttribute.CURRENT_EXAM_ATTRIBUTE;

/**
 * Created by Tatyana on 30.04.2016.
 */
public class CheckboxTag extends TagSupport {

    public int doStartTag() {
        try {
            JspWriter out = pageContext.getOut();
            Exam exam = getExam();
            if (exam != null) {
                int questionEntryId = getQuestionEntryId(exam);
                String checkboxId = "isAnswered" + questionEntryId;
                List<QuestionEntry> answeredQuestions = PersonUtility.getAnsweredQuestions(pageContext.getSession());
                String isCheckedQuestion = ExamUtility.isCurrentQuestionChecked(exam, answeredQuestions) ? "checked" : "";
                String contextPath = pageContext.getServletContext().getContextPath();
                String str = String.format("<input type='checkbox' id='%s' name='isAnswered'" +
                        "title='%s' %s onchange=\"markAnswered('%s',%d);\">"
                        , checkboxId
                        , MARK_QUESTION_HINT
                        , isCheckedQuestion
                        , contextPath
                        , questionEntryId);
                out.print(str);
            }
        } catch (IOException ioe) {
            System.out.println("Error in CheckboxTag: " + ioe);
        }
        return (SKIP_BODY);
    }

    private int getQuestionEntryId(Exam exam) {
        QuestionEntry questionEntry = ExamUtility.getCurrentQuestionEntry(exam);
        return questionEntry.getId();
    }

    private Exam getExam() {
        return (Exam) pageContext.getSession().getAttribute(CURRENT_EXAM_ATTRIBUTE);
    }
}