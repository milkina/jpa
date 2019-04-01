package tags.category;

import model.Category;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 18.10.2016.
 */
public class CategoryParentTag extends TagSupport {
    public int doStartTag() {
        try {
            CategoryTag parent =
                    (CategoryTag) findAncestorWithClass(this, CategoryTag.class);
            Category category = parent.getCategory();
            JspWriter out = pageContext.getOut();
            Category parentCategory = null;
            if (category != null) {
                parentCategory = category.getParentCategory();
            }
            String result = parentCategory == null ? "" : parentCategory.getName();
            out.print(result);
        } catch (IOException ioe) {
            System.out.println("Error in CategoryParentTag: " + ioe);
        }
        return SKIP_BODY;
    }
}

