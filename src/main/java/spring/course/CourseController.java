package spring.course;

import data.test.TestHandler;
import model.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import util.GeneralUtility;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static util.AllConstants.TESTS_PAGE;
import static util.AllConstantsAttribute.TESTS_WITH_TESTS;

@Controller
public class CourseController {
    private static TestHandler testHandler = new TestHandler();

    @RequestMapping(value = "/tests")
    public String selectCoursesWithTests() {
        List<Test> testMap = testHandler.getAllTestsWithNotEmptyTests();
        HttpServletRequest request = GeneralUtility.getRequest();
        request.setAttribute(TESTS_WITH_TESTS, testMap);
        return TESTS_PAGE;
    }
}
