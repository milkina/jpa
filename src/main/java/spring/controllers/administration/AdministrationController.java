package spring.controllers.administration;

import model.article.Article;
import model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.services.article.ArticleService;
import spring.services.person.PersonService;

import java.util.List;

import static util.AllConstants.ARTICLES;
import static util.AllConstantsAttribute.PERSON_LIST_ATTRIBUTE;
import static util.AllConstantsAttribute.SIZE;

@Controller
public class AdministrationController {
    @Autowired
    private PersonService personService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/show-administration")
    public String showAdministration(Model model) {
        List<Person> personList = personService.findAllOrderByCreatedDate();
        model.addAttribute(PERSON_LIST_ATTRIBUTE, personList);

        List<Article> articleList = articleService.getArticles();
        model.addAttribute(ARTICLES, articleList);

        long size = personService.count();
        model.addAttribute(SIZE, size);
        return "administration/welcome";
    }

    @RequestMapping(value = "/")
    public String showMain() {
        return "main";
    }

    @RequestMapping(value = "/ru/")
    public String showMainRu() {
        return "main-ru";
    }
    @RequestMapping(value = "/privacy")
    public String showPrivacy() {
        return "privacy";
    }

    @RequestMapping(value = "/ru/privacy")
    public String showPrivacyRu() {
        return "privacy-ru";
    }
}
