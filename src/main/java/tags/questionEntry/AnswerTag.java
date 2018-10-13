package tags.questionEntry;

import model.Answer;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Iterator;

public class AnswerTag extends BodyTagSupport {
    private int number;
    private Answer answer;
    private Iterator<Answer> iterator;

    public Answer getAnswer() {
        return answer;
    }

    public int getNumber() {
        return number;
    }

    public int doStartTag() {
        setIterator();
        if (iterator.hasNext()) {
            getNextAnswer();
            return EVAL_BODY_TAG;
        } else {
            return SKIP_BODY;
        }
    }

    private void getNextAnswer() {
        answer = iterator.next();
        number++;
    }

    private void setIterator() {
        AnswersTag parent = (AnswersTag)
                findAncestorWithClass(this, AnswersTag.class);
        iterator = parent.getIterator();
    }

    public int doAfterBody() {
        BodyContent body = getBodyContent();
        try {
            JspWriter out = body.getEnclosingWriter();
            out.println(body.getString());
            body.clearBody(); // Clear for next evaluation
        } catch (IOException ioe) {
            System.out.println("Error in AnswerTag: " + ioe);
        }
        if (iterator != null && iterator.hasNext()) {
            getNextAnswer();
            return EVAL_BODY_TAG;
        } else {
            iterator = null;
            number = 0;
          //  questionEntry = null;
            return SKIP_BODY;
        }
    }
}



