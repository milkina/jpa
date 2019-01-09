package spring.person;

import data.exam.ExamHandler;
import model.TestExam;
import model.person.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import util.GeneralUtility;

import javax.servlet.http.HttpSession;
import java.util.List;

import static util.AllConstants.MY_PROFILE_PAGE;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsAttribute.USER_TEST_EXAMS;

@Controller
public class MyProfileController {
    @RequestMapping(value = "/show-user-profile", method = RequestMethod.GET)
    public ModelAndView showUserProfile(Model model) {
        HttpSession session = GeneralUtility.getSession(true);
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        ExamHandler examHandler = new ExamHandler();
        List<TestExam> exams = examHandler.getExams(person);
        session.setAttribute(USER_TEST_EXAMS, exams);
        return new ModelAndView(MY_PROFILE_PAGE, "command", person);
    }
}
