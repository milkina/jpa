package main.java.tags.questionEntry;

import main.java.model.QuestionEntry;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Tatyana on 05.06.2016.
 */
public class QuestionEntryTypeTag extends BaseQuestionEntryTypeTag {

    public int doStartTag() {
        try {
            QuestionEntryTag parent =
                    (QuestionEntryTag) findAncestorWithClass(this, QuestionEntryTag.class);
            QuestionEntry questionEntry = parent.getQuestionEntry();
            JspWriter out = pageContext.getOut();

            List<QuestionEntry> answeredQuestion = getAnsweredQuestions();

            String liClass = isAnswered(questionEntry, answeredQuestion) ? "answeredQuestion" : "notAnsweredQuestion";
            out.print(liClass);
        } catch (IOException ioe) {
            System.out.println("Error in QuestionEntryTypeTag: " + ioe);
        }
        return (SKIP_BODY);
    }
}