package tags.questionEntry;

import model.QuestionEntry;
import util.PersonUtility;

import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;

/**
 * Created by Tatyana on 05.06.2016.
 */
public class BaseQuestionEntryTypeTag extends TagSupport {
    protected boolean isAnswered(QuestionEntry questionEntry, List<QuestionEntry> answeredQuestion) {
        return answeredQuestion != null && answeredQuestion.contains(questionEntry);
    }

    protected List<QuestionEntry> getAnsweredQuestions() {
        return PersonUtility.getAnsweredQuestions(pageContext.getSession());
    }
}
