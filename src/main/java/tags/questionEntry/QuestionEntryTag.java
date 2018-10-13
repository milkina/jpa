package tags.questionEntry;

import model.AbstractQuestionEntry;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Tatyana on 05.06.2016.
 */
public class QuestionEntryTag extends BodyTagSupport {
    private int number;
    private AbstractQuestionEntry questionEntry;
    private Iterator<AbstractQuestionEntry> iterator;

    public AbstractQuestionEntry getQuestionEntry() {
        return questionEntry;
    }

    public int getNumber() {
        return number;
    }

    public int doStartTag() {
        setIterator();
        if (iterator.hasNext()) {
            getQuestionEntryFromList();
        }
        if (questionEntry == null) {
            return SKIP_BODY;
        }
        return EVAL_BODY_TAG;
    }

    private void getQuestionEntryFromList() {
        questionEntry = iterator.next();
        number++;
    }

    private void setIterator() {
        QuestionEntryListTag parent = (QuestionEntryListTag)
                findAncestorWithClass(this, QuestionEntryListTag.class);
        List<AbstractQuestionEntry> questionEntries = parent.getQuestionEntries();
        iterator = questionEntries.iterator();
    }

    public int doAfterBody() {
        BodyContent body = getBodyContent();
        try {
            JspWriter out = body.getEnclosingWriter();
            out.println(body.getString());
            body.clearBody(); // Clear for next evaluation
        } catch (IOException ioe) {
            System.out.println("Error in QuestionEntryTag: " + ioe);
        }
        if (iterator != null && iterator.hasNext()) {
            getQuestionEntryFromList();
            return EVAL_BODY_TAG;
        } else {
            iterator = null;
            number = 0;
            questionEntry = null;
            return SKIP_BODY;
        }
    }
}


