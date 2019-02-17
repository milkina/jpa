package tags.questionEntry;

import model.AbstractQuestionEntry;
import util.PersonUtility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;

/**
 * Created by Tatyana on 05.06.2016.
 */
public class BaseQuestionEntryTypeTag extends TagSupport {
    protected boolean isAnswered(AbstractQuestionEntry questionEntry, List<AbstractQuestionEntry> answeredQuestion) {
        return answeredQuestion != null && answeredQuestion.contains(questionEntry);
    }

    protected List<AbstractQuestionEntry> getAnsweredQuestions() {
        return PersonUtility.getAnsweredQuestions((HttpServletRequest) pageContext.getRequest());
    }
}
