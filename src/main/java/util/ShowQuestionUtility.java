package util;

import model.AbstractQuestionEntry;

import static util.AllConstantsParam.CATEGORY_PATH;
import static util.AllConstantsParam.EDIT_MODE_PARAM;
import static util.AllConstantsParam.QUESTION_ENTRY_ID_PARAM;

/**
 * Created by Tatyana on 29.12.2015.
 */
public class ShowQuestionUtility {
    public static final String STRING =
            "%s/up-question?%s=%d&%s=%s&%s=";

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
