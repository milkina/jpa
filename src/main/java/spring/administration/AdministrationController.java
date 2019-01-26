package spring.administration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdministrationController {
    @RequestMapping(value = "/show-administration")
    public String showAdministration() {
        return "administration/welcome";
    }

    @RequestMapping(value = "/")
    public String showMain() {
        return "main";
    }
}
