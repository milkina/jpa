package main.java.tags.category;

import main.java.model.Category;
import main.java.util.CategoryUtility;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Tatyana on 15.05.2016.
 */
public class CategoryTag extends BodyTagSupport {
    private Category category;
    private Iterator<Category> iterator;
    private String categoryPath;

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    public Category getCategory() {
        return category;
    }

    public int doStartTag() {
        if (categoryPath != null) {
            getCategoryFromServletContext(pageContext.getRequest());
        }

        if (category == null) {
            return (SKIP_BODY);
        }
        return (EVAL_BODY_TAG);
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
            body.clearBody(); // Clear for next evaluation
        } catch (IOException ioe) {
            System.out.println("Error in CategoryTag: " + ioe);
        }
            category = null;
            return (SKIP_BODY);
    }
}



