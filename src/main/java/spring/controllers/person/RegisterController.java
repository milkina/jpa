package spring.controllers.person;

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
import spring.services.person.PersonService;
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
    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(Model model) {
        return new ModelAndView(REGISTER_PAGE, "command", new Person());
    }

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    public ModelAndView addPerson(@ModelAttribute("SpringWeb") Person person,
                                  @RequestParam("confPassword") String confPassword,
                                  ModelMap model, Locale locale) {
        String url = REGISTER_PAGE;
        PersonUtility.decodeRussianCharacters(person);
        confPassword = GeneralUtility.decodeRussianCharacters(confPassword.trim());
        if (isValidData(model, person, confPassword, locale)) {
            person = personService.addPerson(person);

            HttpSession session = GeneralUtility.getSession(true);
            session.setAttribute(PERSON_ATTRIBUTE, person);
            model.addAttribute(USER_NAME,
                    PersonUtility.getPersonName(person));
            url = WELCOME_REGISTER_PAGE;
        } else {
            model.addAttribute("login", person.getLogin());
            model.addAttribute("email", person.getEmail());
        }
        return new ModelAndView(url, "command", person);
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
        Person existedPerson = personService.findByLogin(login);
        return !login.isEmpty() && existedPerson != null;
    }
}