package spring.controllers.person;

import model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import spring.services.person.PersonService;
import util.GeneralUtility;
import util.ServletUtilities;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

import static util.AllConstants.COOKIE_VALUE;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsAttribute.WRONG_LOGIN_MESSAGE_ATTRIBUTE;
import static util.AllConstantsParam.LOGIN_PARAMETER;
import static util.AllConstantsParam.PASSWORD_PARAMETER;
import static util.AllConstantsParam.REMEMBER_PARAMETER;
import static util.GeneralUtility.getResourceValue;

@Controller
public class LoginController {
    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/show-login-page")
    public String showLoginPage() {
        return "person/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(LOGIN_PARAMETER) String login,
                        @RequestParam(PASSWORD_PARAMETER) String password,
                        @RequestParam(value = REMEMBER_PARAMETER, required = false) String remember,
                        Model model, Locale locale) {
        login = GeneralUtility.decodeRussianCharacters(login);
        password = ServletUtilities.getMD5(
                GeneralUtility.decodeRussianCharacters(password));

        Person person = personService.findByLoginAndPassword(
                login, password);
        if (person != null) {
            Cookie cookie;
            if (remember != null) {
                cookie = new Cookie(COOKIE_VALUE,
                        Integer.toString(person.getId()));
            } else {
                cookie = new Cookie(COOKIE_VALUE, "");
            }
            cookie.setMaxAge(60 * 60 * 24 * 365);
            cookie.setPath("/");
            HttpServletResponse response = GeneralUtility.getResponse();
            response.addCookie(cookie);
            HttpSession session = GeneralUtility.getSession(true);

            session.setAttribute(PERSON_ATTRIBUTE, person);
            return "redirect:" + getResourceValue(locale, "menu.home", "label");
        } else {
            model.addAttribute(WRONG_LOGIN_MESSAGE_ATTRIBUTE,
                    GeneralUtility.getResourceValue(locale, "wrong.login.password", "messages"));
            return "person/login";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(Locale locale) {
        HttpSession session = GeneralUtility.getSession(true);
        session.invalidate();
        Cookie cookie = new Cookie(COOKIE_VALUE, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        HttpServletResponse response = GeneralUtility.getResponse();
        response.addCookie(cookie);
        String href = getResourceValue(locale, "menu.home", "label");
        return "redirect:" + href;
    }
}
