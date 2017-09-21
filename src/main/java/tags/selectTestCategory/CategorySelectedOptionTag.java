package main.java.tags.selectTestCategory;

import main.java.model.Category;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 21.05.2016.
 */
public class CategorySelectedOptionTag extends TagSupport {

    public int doStartTag() {
        try {
            CategoryTag parent =
                    (CategoryTag) findAncestorWithClass(this, CategoryTag.class);
            Category category = parent.getCategory();
            JspWriter out = pageContext.getOut();
            String selected = parent.getSelectedCategoryId() != null && category.getId() == parent.getSelectedCategoryId()
                    ? "selected" : "";
            out.print(selected);
        } catch (IOException ioe) {
            System.out.println("Error in CategorySelectedOptionTag: " + ioe);
        }
        return (SKIP_BODY);
    }
}
