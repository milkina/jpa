package spring.person;

import data.exam.ExamHandler;
import data.person.PersonHandler;
import data.questionEntry.QuestionEntryHandler;
import model.AbstractQuestionEntry;
import model.QuestionType;
import model.TestExam;
import model.person.Person;
import model.person.PersonInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import util.AllConstants;
import util.GeneralUtility;
import util.PersonUtility;
import util.ServletUtilities;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

import static util.AllConstants.MY_PROFILE_PAGE;
import static util.AllConstants.SHOW_PERSON_HISTORY_PAGE;
import static util.AllConstants.SHOW_QUESTIONS_PAGE;
import static util.AllConstants.SPRING_MESSAGE_PAGE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsAttribute.PERSON_ANSWERED_QUESTIONS;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsAttribute.SOME_USER;
import static util.AllConstantsAttribute.USER_TEST_EXAMS;
import static util.AllConstantsParam.CATEGORY_PATH;
import static util.AllConstantsParam.TEST_PATH;
import static util.AllConstantsParam.TYPE;
import static util.AllConstantsParam.USER_ID;
import static util.GeneralUtility.getResourceValue;

@Controller
public class PersonController {
    @RequestMapping(value = "/change-user-settings", method = RequestMethod.POST)
    public ModelAndView changeUserSettings(@ModelAttribute("SpringWeb") Person changedPerson,
                                           ModelMap model, Locale locale) {
        HttpSession session = GeneralUtility.getSession(true);

        PersonUtility.decodeRussianCharacters(changedPerson);

        PersonHandler personHandler = new PersonHandler();
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        Person receivedPerson =
                personHandler.getPersonByLoginViaCriteria(changedPerson.getLogin());
        if (receivedPerson != null
                && !changedPerson.getLogin().equalsIgnoreCase(person.getLogin())) {
            String message = getResourceValue(locale, "login.exist", "messages");
            model.addAttribute(MESSAGE_ATTRIBUTE, message);
        } else {
            person.setLogin(changedPerson.getLogin());
            PersonInfo personInfo = new PersonInfo();
            personInfo.setFirstName(changedPerson.getPersonInfo().getFirstName());
            personInfo.setLastName(changedPerson.getPersonInfo().getLastName());
            personInfo.setEmail(changedPerson.getPersonInfo().getEmail());
            person.setPersonInfo(personInfo);

            personHandler.updatePerson(person);
        }
        return new ModelAndView(MY_PROFILE_PAGE, "command", person);
    }

    @RequestMapping(value = "/change-user-password", method = RequestMethod.GET)
    public ModelAndView changeUserSettings() {
        HttpSession session = GeneralUtility.getSession(true);
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        return new ModelAndView("/person/change-password", "command", person);
    }

    @RequestMapping(value = "/save-user-password", method = RequestMethod.POST)
    public ModelAndView savePassword(@RequestParam("password") String password,
                                     @RequestParam("confirmPassword") String confPassword,
                                     ModelMap model, Locale locale) {
        HttpSession session = GeneralUtility.getSession(true);
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        String url = AllConstants.MY_PROFILE_PAGE;
        if (!password.equals(confPassword)) {
            String message = getResourceValue(locale, "password.and.conf.password.different", "messages");
            model.addAttribute(MESSAGE_ATTRIBUTE, message);
            url = "/person/change-password";
        } else {
            password = GeneralUtility.decodeRussianCharacters(password.trim());
            person.setPassword(ServletUtilities.getMD5(password));
            PersonHandler personHandler = new PersonHandler();
            personHandler.updatePerson(person);
        }

        return new ModelAndView(url, "command", person);
    }

    @RequestMapping(value = "/delete-person")
    public ModelAndView deletePerson(@RequestParam(USER_ID) Integer userId, Locale locale) {
        String result = getResourceValue(locale, "person.not.removed", "messages");
        ;
        if (userId != null) {
            QuestionEntryHandler questionEntryHandler = new QuestionEntryHandler();
            List<AbstractQuestionEntry> questionEntries = questionEntryHandler.getPersonQuestions(userId);
            if (questionEntries.size() == 0) {
                PersonHandler personHandler = new PersonHandler();
                personHandler.removePerson(userId);
                result = getResourceValue(locale, "person.removed", "messages");
            } else {
                result = getResourceValue(locale, "person.has.questions", "messages");
            }
        }
        ModelAndView modelAndView = new ModelAndView(SPRING_MESSAGE_PAGE);
        modelAndView.addObject(MESSAGE_ATTRIBUTE, result);
        return modelAndView;
    }

    @RequestMapping(value = "/clear-history")
    public String clearHistory(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        String categoryPath = request.getParameter(CATEGORY_PATH);
        String testPath = request.getParameter(TEST_PATH);

        PersonHandler personHandler = new PersonHandler();
        personHandler.removeAnsweredQuestions(person);

        session.setAttribute(PERSON_ANSWERED_QUESTIONS, null);
        return String.format("redirect:%s?%s=%s&%s=%s&%s=%s",
                SHOW_QUESTIONS_PAGE, CATEGORY_PATH, categoryPath, TEST_PATH,
                testPath, TYPE, QuestionType.QUESTION);
    }

    @RequestMapping(value = "/show-person-history")
    public String showPersonHistory(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int personId = GeneralUtility.getIntegerValue(request, USER_ID);
        Person person = new PersonHandler().getPersonById(personId);
        ExamHandler examHandler = new ExamHandler();
        List<TestExam> exams = examHandler.getExams(person);
        session.setAttribute(USER_TEST_EXAMS, exams);
        session.setAttribute(SOME_USER, person);
        return "redirect:" + SHOW_PERSON_HISTORY_PAGE;
    }
}
