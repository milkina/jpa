package tags.selectTag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 11.05.2016.
 */
public class SelectTag extends TagSupport {

    private Object[] options;
    private Object selected;
    private String name;

    public int doStartTag() {
        try {
            JspWriter out = pageContext.getOut();
            out.print(SelectTagUtility.createSelectTag(name,
                    options, selected));
        } catch (IOException ioe) {
            System.out.println("Error in SelectTag: " + ioe);
        }
        return SKIP_BODY;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object[] getOptions() {
        return options;
    }

    public void setOptions(Object[] options) {
        this.options = options;
    }

    public Object getSelected() {
        return selected;
    }

    public void setSelected(Object selected) {
        this.selected = selected;
    }
}
