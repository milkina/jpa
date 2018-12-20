package tags.selectTag;

/**
 * Created by Tatyana on 11.05.2016.
 */
public class SelectTagUtility {
    public static final String OPTION_TAG = "<option value='%s' %s>%s</option>";
    public static final String SELECT_TAG = "<select name='%s'>%s</select>";

    public static String createSelectTag(String name,
                                         Object[] options, Object selected) {
        return String.format(SELECT_TAG, name,
                createOptionTags(options, selected));
    }

    public static String createOptionTags(Object[] options, Object selected) {
        StringBuilder result = new StringBuilder();
        for (Object option : options) {
            result.append(createOptionTag(option, selected));
        }
        return result.toString();
    }

    public static String createOptionTag(Object option, Object selected) {
        String selectedStr = getSelectedMark(option, selected);
        return String.format(OPTION_TAG, option, selectedStr, option);
    }

    public static String getSelectedMark(Object option, Object selected) {
        return selected.toString().equals(option.toString()) ? "selected" : "";
    }
}
