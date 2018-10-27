package tags.category;

import model.Category;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 19.05.2016.
 */
public class CategoryPathNameTag extends TagSupport {
    public int doStartTag() {
        try {
            CategoryTag parent =
                    (CategoryTag) findAncestorWithClass(this, CategoryTag.class);
            Category category = parent.getCategory();
            JspWriter out = pageContext.getOut();
            String pathName = category != null ? category.getPathName() : "";
            out.print(pathName);
        } catch (IOException ioe) {
            System.out.println("Error in CategoryPathNameTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
