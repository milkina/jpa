package tags.category;

import model.Category;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 15.05.2016.
 */
public class CategoryNameTag extends TagSupport {
    public int doStartTag() {
        try {
            String name = "";
            CategoryTag parent =
                    (CategoryTag) findAncestorWithClass(this, CategoryTag.class);
            if (parent != null) {
                Category category = parent.getCategory();
                if (category != null) {
                    name = category.getName();
                }
            }
            JspWriter out = pageContext.getOut();
            out.print(name);
        } catch (IOException ioe) {
            System.out.println("Error in CategoryNameTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
