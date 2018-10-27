package tags.category;

import model.Category;
import util.CategoryUtility;
import util.GeneralUtility;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Tatyana on 15.05.2016.
 */
public class CategoryTag extends BodyTagSupport {
    private Category category;
    private String categoryPath;

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    public Category getCategory() {
        return category;
    }

    public int doStartTag() {
        if (!GeneralUtility.isEmpty(categoryPath)) {
            getCategoryFromServletContext(pageContext.getRequest());
        }

        return EVAL_BODY_TAG;
    }

    private void getCategoryFromServletContext(ServletRequest request) {
        Map<String, Category> categoryMap = CategoryUtility.getCategoriesFromServletContext(request);
        category = categoryMap.get(categoryPath);
    }

    public int doAfterBody() {
        BodyContent body = getBodyContent();
        try {
            JspWriter out = body.getEnclosingWriter();
            out.println(body.getString());
            body.clearBody();
        } catch (IOException ioe) {
            System.out.println("Error in CategoryTag: " + ioe);
        }
        category = null;
        return SKIP_BODY;
    }
}



