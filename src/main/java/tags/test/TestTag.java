package tags.test;

import model.Test;
import spring.services.course.CourseService;
import util.SpringUtility;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Tatyana on 15.05.2016.
 */
public class TestTag extends TagSupport {
    private CourseService courseService;
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
            String output = getOutputString(test,
                    pageContext.getServletContext().getContextPath());
            JspWriter out = pageContext.getOut();
            out.print(output);
        } catch (IOException ioe) {
            System.out.println("Error in TestTag: " + ioe);
        }
        return SKIP_BODY;
    }

    public Map<Integer, Test> getTestMap() {
        if (testMap == null) {
            testMap = getCourseService(pageContext.getServletContext()).getAllCourses();
        }
        return testMap;
    }

    public Map<String, Test> getTestMapPath() {
        if (testMapPath == null) {
            testMapPath = getCourseService(pageContext.getServletContext())
                    .getAllCoursesWithPath();
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
            default:
                return "";
        }
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

    private CourseService getCourseService(ServletContext servletContext) {
        if (courseService == null) {
            courseService = SpringUtility.getService(servletContext, CourseService.class);
        }
        return courseService;
    }
}
