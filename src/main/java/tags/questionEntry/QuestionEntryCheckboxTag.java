package tags.questionEntry;

import model.AbstractQuestionEntry;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Tatyana on 05.06.2016.
 */
public class QuestionEntryCheckboxTag extends BaseQuestionEntryTypeTag {

    public int doStartTag() {
        try {
            QuestionEntryTag parent =
                    (QuestionEntryTag) findAncestorWithClass(this, QuestionEntryTag.class);
            AbstractQuestionEntry questionEntry = parent.getQuestionEntry();
            JspWriter out = pageContext.getOut();

            List<AbstractQuestionEntry> answeredQuestion = getAnsweredQuestions();
            String isCheckedQuestion = isAnswered(questionEntry, answeredQuestion) ? "checked" : "";
            out.print(isCheckedQuestion);
        } catch (IOException ioe) {
            System.out.println("Error in QuestionEntryCheckboxTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
