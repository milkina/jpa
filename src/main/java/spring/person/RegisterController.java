package spring.person;

import data.person.PersonHandler;
import model.person.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import util.GeneralUtility;
import util.PersonUtility;

import javax.servlet.http.HttpSession;
import java.util.Locale;

import static util.AllConstants.REGISTER_PAGE;
import static util.AllConstants.WELCOME_REGISTER_PAGE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsAttribute.USER_NAME;

@Controller
public class RegisterController {
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(Model model) {
        return new ModelAndView(REGISTER_PAGE, "command", new Person());
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public ModelAndView addPerson(@ModelAttribute("SpringWeb") Person person,
                                  @RequestParam("confPassword") String confPassword,
                                  ModelMap model, Locale locale) {
        String url = REGISTER_PAGE;
        decodeRussianCharacters(person);
        confPassword = GeneralUtility.decodeRussianCharacters(confPassword.trim());
        if (isValidData(model, person, confPassword, locale)) {
            PersonHandler personHandler = new PersonHandler();
            person = personHandler.addPerson(person);

            HttpSession session = GeneralUtility.getSession(true);
            session.setAttribute(PERSON_ATTRIBUTE, person);
            model.addAttribute(USER_NAME,
                    PersonUtility.getPersonName(person));
            url = WELCOME_REGISTER_PAGE;
        } else {
            model.addAttribute("login", person.getLogin());
            model.addAttribute("firstName", person.getPersonInfo().getFirstName());
            model.addAttribute("lastName", person.getPersonInfo().getLastName());
            model.addAttribute("email", person.getPersonInfo().getEmail());
        }
        return new ModelAndView(url, "command", person);
    }

    private void decodeRussianCharacters(Person person) {
        person.setLogin(GeneralUtility.decodeRussianCharacters(person.getLogin().trim()));
        person.setPassword(GeneralUtility.decodeRussianCharacters(person.getPassword().trim()));
        person.getPersonInfo().setFirstName(GeneralUtility.decodeRussianCharacters(person.getPersonInfo().getFirstName().trim()));
        person.getPersonInfo().setLastName(GeneralUtility.decodeRussianCharacters(person.getPersonInfo().getLastName().trim()));
    }

    private boolean isValidData(ModelMap modelMap, Person person,
                                String confPassword, Locale locale) {
        if (isLoginExists(person.getLogin())) {
            modelMap.addAttribute(MESSAGE_ATTRIBUTE,
                    GeneralUtility.getResourceValue(locale, "login.exist", "messages"));
            return false;
        } else if (!person.getPassword().equals(confPassword)) {
            modelMap.addAttribute(MESSAGE_ATTRIBUTE,
                    GeneralUtility.getResourceValue(locale, "password.and.conf.password.different", "messages"));
            return false;
        }
        return true;
    }

    private boolean isLoginExists(String login) {
        PersonHandler personHandler = new PersonHandler();
        Person existedPerson = personHandler.getPersonByLogin(login);
        return !login.isEmpty() && existedPerson != null;
    }
}