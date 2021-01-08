package tags.questionEntry;

import model.person.Person;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class QuestionEntryAuthorTag extends TagSupport {

    public int doStartTag() {
        try {
            QuestionEntryTag parent =
                    (QuestionEntryTag) findAncestorWithClass(this, QuestionEntryTag.class);
            JspWriter out = pageContext.getOut();

            String login = "";
            Person person = parent.getQuestionEntry().getPerson();
            if (person != null) {
                login = person.getLogin();
            }
            out.print(login);
        } catch (IOException ioe) {
            System.out.println("Error in QuestionEntryAuthorTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
