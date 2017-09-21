package main.java.tags.test;

import main.java.data.test.TestHandler;
import main.java.model.Test;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Tatyana on 15.05.2016.
 */
public class TestTag extends TagSupport {
    private TestHandler testHandler = new TestHandler();
    private Map<Integer, Test> testMap;
    private Map<String, Test> testMapPath;
    private Integer testId;
    private String pathName;
    private String attribute;

    public int doStartTag() {
        try {
            Test test = null;
            if (testId != null) {
                test = getTestMap().get(testId);
            } else if (pathName != null) {
                test = getTestMapPath().get(pathName);
            }
            String output = getOutputString(test, pageContext.getServletContext().getContextPath());
            JspWriter out = pageContext.getOut();
            out.print(output);
        } catch (IOException ioe) {
            System.out.println("Error in TestTag: " + ioe);
        }
        return (SKIP_BODY);
    }

    public Map<Integer, Test> getTestMap() {
        if (testMap == null) {
            testMap = testHandler.getAllTests();
        }
        return testMap;
    }

    public Map<String, Test> getTestMapPath() {
        if (testMapPath == null) {
            testMapPath = testHandler.getAllTestsWithPath();
        }
        return testMapPath;
    }

    private String getOutputString(Test test, String contextPath) {
        switch (TestAttribute.valueOf(attribute)) {
            case QUESTIONS_NUMBER:
                return test.getQuestionsNumber().toString();
            case PATH_NAME:
                return contextPath + "/" + test.getPathName();
            case NAME:
                return test.getName();
        }
        return "";
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }
}