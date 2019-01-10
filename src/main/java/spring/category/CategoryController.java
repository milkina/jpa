package spring.category;

import model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import util.CategoryUtility;
import util.GeneralUtility;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static util.AllConstantsAttribute.ARTICLE_ATTRIBUTE;
import static util.AllConstantsAttribute.CATEGORY_ATTRIBUTE;
import static util.AllConstantsParam.CATEGORY_PATH;
import static util.AllConstantsParam.TEST_PATH;

/**
 * Created by Tatyana on 17.06.2016.
 */
@Controller
public class CategoryController {
    @RequestMapping(value = "/show-category")
    public ModelAndView showCategory(@RequestParam(CATEGORY_PATH) String categoryPath,
                                     Model model) {
        HttpServletRequest request = GeneralUtility.getRequest();
        Map<String, Category> categoryMap =
                CategoryUtility.getCategoriesFromServletContext(request);
        Category category = categoryMap.get(categoryPath);
        model.addAttribute(CATEGORY_ATTRIBUTE, category);
        model.addAttribute(ARTICLE_ATTRIBUTE, category.getArticle());
        return new ModelAndView("/category/show-category");
    }
}
