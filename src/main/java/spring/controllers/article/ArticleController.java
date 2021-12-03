package spring.controllers.article;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import model.article.Article;
import model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.services.article.ArticleService;
import util.GeneralUtility;
import util.article.ArticleUtility;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

import static util.AllConstants.ARTICLES;
import static util.AllConstants.EDIT_ARTICLE;
import static util.AllConstants.SHOW_ARTICLE_PAGE;
import static util.AllConstants.SPRING_MESSAGE_PAGE;
import static util.AllConstantsAttribute.ARTICLE_ATTRIBUTE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsParam.ARTICLE_ID;
import static util.AllConstantsParam.URL_PARAM;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    private HtmlCompressor compressor = new HtmlCompressor();

    @RequestMapping(value = "/delete-article")
    public ModelAndView deleteArticle(Locale locale,
                                      @RequestParam(ARTICLE_ID) int id,
                                      HttpServletRequest request) {
        Article article = articleService.getArticle(id);
        ArticleUtility.removeArticleFromCategory(article, request.getServletContext());
        articleService.deleteArticle(article.getId());
        ModelAndView modelAndView = new ModelAndView(SPRING_MESSAGE_PAGE);
        modelAndView.addObject(MESSAGE_ATTRIBUTE,
                GeneralUtility.getResourceValue(locale, "article.removed", "messages"));

        return modelAndView;
    }

    @RequestMapping(value = {"/show-article", "{langid}/show-article"})
    public ModelAndView showArticle(@RequestParam(URL_PARAM) String articleUrl) {
        Article article =
                articleService.getArticleByUrl("publications/" + articleUrl);
        String text = article.getText();
        String compressedText = compressor.compress(text);
        article.setText(compressedText);
        ModelAndView modelAndView = new ModelAndView(SHOW_ARTICLE_PAGE);
        modelAndView.addObject(ARTICLE_ATTRIBUTE, article);
        return modelAndView;
    }

    @RequestMapping(value = {"{langid}/show-all-articles", "/show-all-articles"})
    public ModelAndView showAllArticles() {
        List<Article> articleList = articleService.getArticles();
        ModelAndView modelAndView = new ModelAndView("article/show-all-articles");
        modelAndView.addObject(ARTICLES, articleList);
        return modelAndView;
    }

    @RequestMapping(value = "/edit-article")
    public ModelAndView editArticle(@RequestParam(ARTICLE_ID) int id) {
        Article article = articleService.getArticle(id);
        ArticleUtility.fixTinyMceIssue(article);
        ModelAndView modelAndView = new ModelAndView(EDIT_ARTICLE);
        modelAndView.addObject(ARTICLE_ATTRIBUTE, article);
        return modelAndView;
    }

    @RequestMapping(value = "/save-article")
    public ModelAndView saveArticle(HttpServletRequest request, Locale locale) {
        Integer id = GeneralUtility.getIntegerValue(request, ARTICLE_ID);
        if (id == null) {
            Person person = (Person)
                    request.getSession().getAttribute(PERSON_ATTRIBUTE);
            ArticleUtility.createArticle(request, person);
        } else {
            ArticleUtility.updateArticle(id, request);
        }
        ModelAndView modelAndView = new ModelAndView(SPRING_MESSAGE_PAGE);
        modelAndView.addObject(MESSAGE_ATTRIBUTE,
                GeneralUtility.getResourceValue(locale, "article.updated", "messages"));
        return modelAndView;
    }

    @RequestMapping(value = "/add-article")
    public String addArticle() {
        return EDIT_ARTICLE;
    }

    @RequestMapping(value = "/ocjp-ocpjp")
    public String showOCJPPage() {
        return "ocjp-ocpjp";
    }
}
