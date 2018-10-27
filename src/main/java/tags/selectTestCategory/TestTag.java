package tags.selectTestCategory;

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
import static util.AllConstantsParam.TEST_PATH;

/**
 * Created by Tatyana on 20.05.2016.
 */
public class TestTag extends BodyTagSupport {
    private LinkedHashMap<String, Test> tests;
    private Iterator<Test> iterator;
    private Test test;
    private Integer selectedTestID = null;

    public Test getTest() {
        return test;
    }

    public Integer getSelectedTestID() {
        return selectedTestID;
    }

    public int doStartTag() {
        setTests();
        setSelectedTestID();
        setIterator();
        getTestFromList();
        if (test == null) {
            return SKIP_BODY;
        }
        return EVAL_BODY_TAG;
    }

    private void setSelectedTestID() {
        String selectedTestPath =
                pageContext.getRequest().getParameter(TEST_PATH);
        if (GeneralUtility.isEmpty(selectedTestPath)) {
            selectedTestPath = (String) pageContext.getRequest().getAttribute(TEST_PATH);
        }
        if (selectedTestPath != null) {
            Test selectedTest = tests.get(selectedTestPath);
            selectedTestID = selectedTest.getId();
        } else {
            Iterator<Map.Entry<String, Test>> itr1 =
                    tests.entrySet().iterator();
            if (itr1.hasNext()) {
                selectedTestID = itr1.next().getValue().getId();
            }
        }
    }

    private void setTests() {
        tests = (LinkedHashMap)
                pageContext.getServletContext().getAttribute(TESTS);
    }

    private void getTestFromList() {
        if (iterator.hasNext()) {
            test = iterator.next();
        }
    }


    private void setIterator() {
        iterator = tests.values().iterator();
    }

    public int doAfterBody() {
        BodyContent body = getBodyContent();
        try {
            JspWriter out = body.getEnclosingWriter();
            out.println(body.getString());
            body.clearBody(); // Clear for next evaluation
        } catch (IOException ioe) {
            System.out.println("Error in TestTag: " + ioe);
        }
        if (iterator != null && iterator.hasNext()) {
            test = iterator.next();
            return EVAL_BODY_TAG;
        } else {
            iterator = null;
            test = null;
            return SKIP_BODY;
        }
    }
}

