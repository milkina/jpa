package spring.controllers.administration;

import model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.services.person.PersonService;

import java.util.List;

import static util.AllConstantsAttribute.PERSON_LIST_ATTRIBUTE;
import static util.AllConstantsAttribute.SIZE;

@Controller
public class AdministrationController {
    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/show-administration")
    public String showAdministration(Model model) {
        List<Person> personList = personService.findAllOrderByCreatedDate();
        model.addAttribute(PERSON_LIST_ATTRIBUTE, personList);
        long size = personService.count();
        model.addAttribute(SIZE, size);
        return "administration/welcome";
    }

    @RequestMapping(value = "/")
    public String showMain() {
        return "main";
    }
}
