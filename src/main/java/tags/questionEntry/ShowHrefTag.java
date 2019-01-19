package tags.questionEntry;

import model.AbstractQuestionEntry;
import util.ShowQuestionUtility;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

import static util.AllConstantsParam.CATEGORY_PATH;
import static util.AllConstantsParam.QUESTION_ENTRY_ID_PARAM;

/**
 * Created by Tatyana on 05.06.2016.
 */
public class ShowHrefTag extends TagSupport {
    public static final String STRING =
            "%s/show-edit-question?%s=%d&%s=%s";
    public int doStartTag() {
        try {
            QuestionEntryTag parent = (QuestionEntryTag)
                    findAncestorWithClass(this, QuestionEntryTag.class);
            AbstractQuestionEntry questionEntry = parent.getQuestionEntry();
            String path = createPath(questionEntry,
                    pageContext.getServletContext().getContextPath());
            JspWriter out = pageContext.getOut();
            out.print(path);
        } catch (IOException ioe) {
            System.out.println("Error in ShowHrefTag: " + ioe);
        }
        return SKIP_BODY;
    }


    public static String createPath(AbstractQuestionEntry questionEntry,
                                    String contextPath) {
        String categoryPathName = questionEntry.getCategory().getPathName();
        return String.format(STRING,
                contextPath,
                QUESTION_ENTRY_ID_PARAM, questionEntry.getId(),
                CATEGORY_PATH, categoryPathName);
    }
}
