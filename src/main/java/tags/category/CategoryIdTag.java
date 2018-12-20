package tags.category;

import model.Category;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 19.05.2016.
 */
public class CategoryIdTag extends TagSupport {
    public int doStartTag() {
        try {
            CategoryTag parent =
                    (CategoryTag) findAncestorWithClass(this, CategoryTag.class);
            Category category = parent.getCategory();
            JspWriter out = pageContext.getOut();
            out.print(category.getId());
        } catch (IOException ioe) {
            System.out.println("Error in CategoryIdTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
