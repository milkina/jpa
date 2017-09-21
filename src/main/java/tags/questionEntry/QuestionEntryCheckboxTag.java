package main.java.tags.questionEntry;

import main.java.model.QuestionEntry;

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
            QuestionEntry questionEntry = parent.getQuestionEntry();
            JspWriter out = pageContext.getOut();

            List<QuestionEntry> answeredQuestion = getAnsweredQuestions();
            String isCheckedQuestion = isAnswered(questionEntry, answeredQuestion) ? "checked" : "";
            out.print(isCheckedQuestion);
        } catch (IOException ioe) {
            System.out.println("Error in QuestionEntryCheckboxTag: " + ioe);
        }
        return (SKIP_BODY);
    }
}