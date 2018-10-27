package tags.category;

import model.Category;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 17.05.2016.
 */
public class CategoryDescriptionTag extends TagSupport {
    public int doStartTag() {
        try {
            String description = "";
            CategoryTag parent =
                    (CategoryTag) findAncestorWithClass(this, CategoryTag.class);
            if (parent != null) {
                Category category = parent.getCategory();
                if (category != null) {
                    description = category.getArticle().getDescription();
                }
            }
            JspWriter out = pageContext.getOut();
            out.print(description);
        } catch (IOException ioe) {
            System.out.println("Error in CategoryDescriptionTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
