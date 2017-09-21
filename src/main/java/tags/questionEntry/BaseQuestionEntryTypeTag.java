package main.java.tags.questionEntry;

import main.java.data.person.PersonHandler;
import main.java.model.QuestionEntry;
import main.java.model.person.Person;
import main.java.util.PersonUtility;

import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;

import static main.java.util.AllConstantsAttribute.PERSON_ATTRIBUTE;

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
