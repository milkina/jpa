package tags.questionEntry;

import model.Answer;

import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.Iterator;
import java.util.List;

public class AnswersTag extends BodyTagSupport {
    private List<Answer> answers;
    private Iterator<Answer> iterator;

    public int doStartTag() {
        QuestionEntryTag parent = (QuestionEntryTag)
                findAncestorWithClass(this, QuestionEntryTag.class);

        answers = parent.getQuestionEntry().getAnswers();
        iterator = answers.iterator();
        return EVAL_BODY_INCLUDE;
    }

    public Iterator<Answer> getIterator() {
        return iterator;
    }
}
