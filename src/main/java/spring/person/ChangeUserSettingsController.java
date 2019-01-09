package spring.person;

import data.person.PersonHandler;
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

import javax.servlet.http.HttpSession;
import java.util.Locale;

import static util.AllConstants.MY_PROFILE_PAGE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsParam.PASSWORD_PARAMETER;

@Controller
public class ChangeUserSettingsController {
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
            String message = GeneralUtility.getResourceValue(locale, "login.exist", "messages");
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
            String message = GeneralUtility.getResourceValue(locale, "password.and.conf.password.different", "messages");
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
}
