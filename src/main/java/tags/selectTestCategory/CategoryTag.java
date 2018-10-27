package tags.selectTestCategory;

import model.Category;
import model.Test;
import util.GeneralUtility;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static util.AllConstantsAttribute.TESTS;
import static util.AllConstantsParam.CATEGORY_PATH;
import static util.AllConstantsParam.TEST_PATH;

/**
 * Created by Tatyana on 20.05.2016.
 */
public class CategoryTag extends BodyTagSupport {
    private Map<String, Category> categories;
    private Iterator<Category> iterator;
    private Category category;
    private Integer selectedCategoryId = null;

    public Integer getSelectedCategoryId() {
        return selectedCategoryId;
    }

    public Category getCategory() {
        return category;
    }

    public int doStartTag() {
        setCategories();
        setSelectedTestID();
        setIterator();
        getCategoryFromList();
        if (category == null) {
            return SKIP_BODY;
        }
        return EVAL_BODY_TAG;
    }

    private void setSelectedTestID() {
        String selectedCategoryPath = pageContext.getRequest().getParameter(CATEGORY_PATH);
        if (selectedCategoryPath != null) {
            Category selectedCategory = categories.get(selectedCategoryPath);
            selectedCategoryId = selectedCategory.getId();
        }
    }

    private void setCategories() {
        String testPath = pageContext.getRequest().getParameter(TEST_PATH);
        if (GeneralUtility.isEmpty(testPath)) {
            testPath = (String) pageContext.getRequest().getAttribute(TEST_PATH);
        }
        LinkedHashMap<String, Test> tests = (LinkedHashMap)
                pageContext.getServletContext().getAttribute(TESTS);
        Test test = tests.get(testPath);
        if (testPath == null) {
            Iterator<Map.Entry<String, Test>> itr1 =
                    tests.entrySet().iterator();
            if (itr1.hasNext()) {
                test = itr1.next().getValue();
            }
        }
        categories = test.getCategories();
    }

    private void getCategoryFromList() {
        if (iterator.hasNext()) {
            category = iterator.next();
        }
    }

    private void setIterator() {
        iterator = categories.values().iterator();
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
        if (iterator != null && iterator.hasNext()) {
            category = iterator.next();
            return EVAL_BODY_TAG;
        } else {
            iterator = null;
            category = null;
            return SKIP_BODY;
        }
    }
}



