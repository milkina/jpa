package util;

import model.AbstractQuestionEntry;

import static util.AllConstantsParam.*;

/**
 * Created by Tatyana on 29.12.2015.
 */
public class ShowQuestionUtility {
    public static final String STRING =
            "%s/servlet/EditQuestionEntryServlet?%s=%d&%s=%s&%s=";

    public static String createPath(AbstractQuestionEntry questionEntry,
                                    String contextPath) {
        String categoryPathName = questionEntry.getCategory().getPathName();
        return String.format(STRING,
                contextPath,
                QUESTION_ENTRY_ID_PARAM, questionEntry.getId(),
                CATEGORY_PATH, categoryPathName,
                EDIT_MODE_PARAM);
    }
}
