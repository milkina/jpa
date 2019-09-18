package tags.canonical;

import model.AbstractQuestionEntry;
import model.Category;
import model.Test;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Map;

import static util.AllConstantsAttribute.DUPLICATE_CATEGORIES;
import static util.AllConstantsAttribute.QUESTION_ENTRY_ATTRIBUTE;
import static util.AllConstantsAttribute.TESTS;
import static util.AllConstantsParam.TEST_PATH;

public class CanonicalTag extends TagSupport {

    public int doStartTag() {
        try {
            ServletContext servletContext = pageContext.getServletContext();
            Map<String, Category> duplicateCategories = (Map<String, Category>)
                    servletContext.getAttribute(DUPLICATE_CATEGORIES);
            Category category = getCategory();

            String categoryPathName = category.getPathName();
            String testPath = pageContext.getRequest().getParameter(TEST_PATH);
            Category duplicateCategory = duplicateCategories.get(categoryPathName);
            String result = servletContext.getContextPath() + "/java/" + testPath + "/" + categoryPathName;
            if (duplicateCategory != null
                    && !duplicateCategory.getTests().get(0).getPathName().equals(testPath)) {
                category = duplicateCategory;
                testPath = duplicateCategory.getTests().get(0).getPathName();
                result = servletContext.getContextPath() + "/java/" + testPath + "/" + categoryPathName;
            }
            if (testPath == null) {
                result = servletContext.getContextPath();
            } else if (category.getHidden() || !category.getArticle().isIndexStatus()) {
                Map<String, Test> testMap = (Map<String, Test>) servletContext.getAttribute(TESTS);
                Test test = testMap.get(testPath);
                result = servletContext.getContextPath() + "/" + test.getFullPathName();
            }
            JspWriter out = pageContext.getOut();
            out.print(result);
        } catch (IOException exception) {
            System.out.println("Error in CanonicalTag: " + exception);
        }
        return SKIP_BODY;
    }

    public Category getCategory() {
        AbstractQuestionEntry questionEntry = (AbstractQuestionEntry)
                pageContext.getRequest().getAttribute(QUESTION_ENTRY_ATTRIBUTE);
        return questionEntry.getCategory();
    }
}

