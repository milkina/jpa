package spring.controllers.person;

import model.AbstractQuestionEntry;
import model.QuestionType;
import model.TestExam;
import model.article.Article;
import model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.services.article.ArticleService;
import spring.services.exam.ExamService;
import spring.services.person.PersonService;
import spring.services.question.QuestionService;
import util.AllConstants;
import util.GeneralUtility;
import util.PersonUtility;
import util.ServletUtilities;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

import static util.AllConstants.ARTICLES;
import static util.AllConstants.CHANGE_PASSWORD_PAGE;
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
import static util.AllConstantsParam.CHECKED_PARAM;
import static util.AllConstantsParam.TEST_PATH;
import static util.AllConstantsParam.TYPE;
import static util.AllConstantsParam.UNCHECKED_PARAM;
import static util.AllConstantsParam.USER_ID;
import static util.GeneralUtility.getResourceValue;

@Controller
public class PersonController {
    @Autowired
    private PersonService personService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ExamService examService;

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/change-user-settings", method = RequestMethod.POST)
    public ModelAndView changeUserSettings(@ModelAttribute("SpringWeb") Person changedPerson,
                                           ModelMap model, Locale locale) {
        HttpSession session = GeneralUtility.getSession(true);

        PersonUtility.decodeRussianCharacters(changedPerson);

        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        Person receivedPerson =
                personService.findByLogin(changedPerson.getLogin());
        if (receivedPerson != null
                && !changedPerson.getLogin().equalsIgnoreCase(person.getLogin())) {
            String message = getResourceValue(locale, "login.exist", "messages");
            model.addAttribute(MESSAGE_ATTRIBUTE, message);
        } else {
            person.setLogin(changedPerson.getLogin());
            person.setEmail(changedPerson.getEmail());

            personService.save(person);
        }
        return new ModelAndView(MY_PROFILE_PAGE, "command", person);
    }

    @RequestMapping(value = "/change-user-password", method = RequestMethod.GET)
    public ModelAndView changeUserSettings() {
        HttpSession session = GeneralUtility.getSession(true);
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        return new ModelAndView(CHANGE_PASSWORD_PAGE, "command", person);
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
            url = CHANGE_PASSWORD_PAGE;
        } else {
            password = GeneralUtility.decodeRussianCharacters(password.trim());
            person.setPassword(ServletUtilities.getMD5(password));
            personService.save(person);
        }

        return new ModelAndView(url, "command", person);
    }

    @RequestMapping(value = "/delete-person")
    public ModelAndView deletePerson(@RequestParam(USER_ID) Integer userId, Locale locale) {
        String result = getResourceValue(locale, "person.not.removed", "messages");
        ;
        if (userId != null) {
            List<AbstractQuestionEntry> questionEntries = questionService.getPersonQuestions(userId);
            if (questionEntries.size() == 0) {
                personService.removePerson(userId);
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

        personService.removeAnsweredQuestions(person);

        session.setAttribute(PERSON_ANSWERED_QUESTIONS, null);
        return String.format("forward:%s?%s=%s&%s=%s&%s=%s",
                SHOW_QUESTIONS_PAGE, CATEGORY_PATH, categoryPath, TEST_PATH,
                testPath, TYPE, QuestionType.QUESTION);
    }

    @RequestMapping(value = "/show-person-history")
    public String showPersonHistory(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int personId = GeneralUtility.getIntegerValue(request, USER_ID);
        Person person = personService.getPerson(personId);
        List<TestExam> exams = examService.getExams(person);
        session.setAttribute(USER_TEST_EXAMS, exams);
        session.setAttribute(SOME_USER, person);
        return SHOW_PERSON_HISTORY_PAGE;
    }

    @RequestMapping(value = "/change-answered-question")
    public void changeAnsweredQuestion(HttpServletRequest request) {
        boolean checked = request.getParameter(CHECKED_PARAM) != null;
        String idString = checked ? request.getParameter(CHECKED_PARAM)
                : request.getParameter(UNCHECKED_PARAM);
        HttpSession session = request.getSession();
        int id = Integer.parseInt(idString);
        AbstractQuestionEntry questionEntry =
                questionService.getQuestionEntry(id);

        List<AbstractQuestionEntry> answeredQuestions =
                PersonUtility.getAnsweredQuestions(request);
        if (checked && !answeredQuestions.contains(questionEntry)) {
            answeredQuestions.add(questionEntry);
        } else if (!checked) {
            answeredQuestions.remove(questionEntry);
        }

        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        person.setAnsweredQuestions(answeredQuestions);
        personService.save(person);
    }

    @RequestMapping(value = "/show-user-profile", method = RequestMethod.GET)
    public ModelAndView showUserProfile(Model model) {
        HttpSession session = GeneralUtility.getSession(true);
        Person person = (Person) session.getAttribute(PERSON_ATTRIBUTE);
        List<TestExam> exams = examService.getExams(person);
        session.setAttribute(USER_TEST_EXAMS, exams);
        List<Article> articles = articleService.getArticles(person);
        model.addAttribute(ARTICLES, articles);
        return new ModelAndView(MY_PROFILE_PAGE, "command", person);
    }
}
