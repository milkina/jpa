package main.java.util;

import main.java.model.QuestionEntry;

import static main.java.util.AllConstantsParam.*;

/**
 * Created by Tatyana on 29.12.2015.
 */
public class ShowQuestionUtility {
    public static String createPath(QuestionEntry questionEntry, String contextPath) {
        String categoryPathName = questionEntry.getCategory().getPathName();
        return String.format("%s/servlet/EditQuestionEntryServlet?%s=%d&%s=%s&%s="
                , contextPath
                , QUESTION_ENTRY_ID_PARAM, questionEntry.getId()
                , CATEGORY_PATH, categoryPathName
                , EDIT_MODE_PARAM);
    }
}
