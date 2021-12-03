package spring.controllers.category;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import model.Category;
import model.Test;
import model.article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import spring.services.category.CategoryService;
import spring.services.course.CourseService;
import util.CategoryUtility;
import util.TestUtility;
import util.article.ArticleUtility;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static util.AllConstants.SPRING_MESSAGE_PAGE;
import static util.AllConstantsAttribute.ARTICLE_ATTRIBUTE;
import static util.AllConstantsAttribute.CATEGORY_ATTRIBUTE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsAttribute.NEXT_CATEGORY;
import static util.AllConstantsAttribute.PREVIOUS_CATEGORY;
import static util.AllConstantsAttribute.TESTS;
import static util.AllConstantsParam.CATEGORY_PATH;
import static util.AllConstantsParam.OLD_TEST_PATH;
import static util.AllConstantsParam.PREVIOUS_CATEGORY_PATH;
import static util.AllConstantsParam.TEST_PATH;
import static util.GeneralUtility.getResourceValue;

/**
 * Created by Tatyana on 17.06.2016.
 */
@Controller
public class CategoryController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private CategoryService categoryService;
    private HtmlCompressor compressor = new HtmlCompressor();

    @RequestMapping(value = {"/show-category", "{langid}/show-category"})
    public ModelAndView showCategory(@RequestParam(CATEGORY_PATH) String categoryPath,
                                     Model model, HttpServletRequest request, Locale locale) {
        Map<String, Category> categoryMap =
                CategoryUtility.getCategoriesFromServletContext(request);

        Category category = categoryMap.get(categoryPath);
        Article article = category.getArticle();

        String text = article.getText();
        String compressedText = compressor.compress(text);
        article.setText(compressedText);

        model.addAttribute(CATEGORY_ATTRIBUTE, category);
        model.addAttribute(ARTICLE_ATTRIBUTE, article);
        List<Category> list = new ArrayList<>(categoryMap.values());
        int index = list.indexOf(category);

        Category nextCategory = getNextCategory(index, list);
        Category previousCategory = getPreviousCategory(index, list);
        model.addAttribute(NEXT_CATEGORY, nextCategory);
        model.addAttribute(PREVIOUS_CATEGORY, previousCategory);
        if (category.getHidden()) {
            return new ModelAndView("forward:" + getResourceValue(locale, "menu.home", "label"));
        }
        return new ModelAndView("category/show-category");
    }

    private Category getNextCategory(int index, List<Category> list) {
        Category nextCategory;
        do {
            nextCategory = index < list.size() - 1 ? list.get(index + 1) : null;
            index++;
        } while (nextCategory != null && nextCategory.getHidden());
        return nextCategory;
    }

    private Category getPreviousCategory(int index, List<Category> list) {
        Category previousCategory;
        do {
            previousCategory = index > 0 ? list.get(index - 1) : null;
            index--;
        } while (previousCategory != null && previousCategory.getHidden());
        return previousCategory;
    }

    @RequestMapping(value = "/show-create-category")
    public String showCreateCategory() {
        return "category/create-category";
    }

    @RequestMapping(value = "/create-category")
    public ModelAndView createCategory(@RequestParam(TEST_PATH) String testPath, Locale locale,
                                       HttpServletRequest request) {
        //TODO Return error if category with such name or pathName already exists
        Map<String, Test> testMap =
                (Map<String, Test>) request.getServletContext()
                        .getAttribute(TESTS);
        Test test = testMap.get(testPath);

        Category category = new Category();
        CategoryUtility.setCategoryData(request, category);

        CategoryUtility.setCategoryArticle(request, category);
        category = categoryService.create(category);
        categoryService.addCategoryToCourse(test, category);
        if (category.getParentCategory() != null) {
            categoryService.moveCategoryUp(category, category.getParentCategory().getPathName(), testPath);
        }

        TestUtility.loadTestsToServletContext(request.getServletContext());
        ModelAndView modelAndView = new ModelAndView(SPRING_MESSAGE_PAGE);
        modelAndView.addObject(MESSAGE_ATTRIBUTE, getResourceValue(locale, "category.created", "messages"));
        return modelAndView;
    }

    @RequestMapping(value = "/show-edit-category")
    public ModelAndView showEditCategory(@RequestParam(CATEGORY_PATH) String categoryPath) {
        Category category = categoryService.getCategory(categoryPath);
        Article article = category.getArticle();
        ArticleUtility.fixTinyMceIssue(article);

        ModelAndView modelAndView = new ModelAndView("category/edit-category");
        modelAndView.addObject(CATEGORY_ATTRIBUTE, category);
        return modelAndView;
    }

    @RequestMapping(value = "/edit-category", method = RequestMethod.POST)
    public ModelAndView editCategory(@RequestParam(CATEGORY_PATH) String categoryPath,
                                     Locale locale, HttpServletRequest request) {
        //TODO Return error if category with such name or pathName
        // already exists
        Category category = categoryService.getCategory(categoryPath);
        CategoryUtility.updateCategory(request, category);

        ModelAndView modelAndView = new ModelAndView(SPRING_MESSAGE_PAGE);
        modelAndView.addObject(MESSAGE_ATTRIBUTE,
                getResourceValue(locale, "category.changed", "messages"));
        return modelAndView;
    }

    @RequestMapping(value = "/show-add-category")
    public String showAddCategory() {
        return "category/add-category";
    }

    @RequestMapping(value = "/add-category", method = RequestMethod.POST)
    public ModelAndView addCategory(@RequestParam(OLD_TEST_PATH) String oldTestPath,
                                    Locale locale, HttpServletRequest request) {
        Category category =
                CategoryUtility.getCategoryFromServletContext(request);

        Map<String, Test> testMap = (Map<String, Test>)
                request.getServletContext()
                        .getAttribute(TESTS);
        Test test = testMap.get(oldTestPath);
        String message = getResourceValue(locale, "category.added", "messages");
        if (test.getCategories().containsKey(category.getPathName())) {
            message = getResourceValue(locale, "category.not.added", "messages");
        } else {
            test.addCategory(category);
            categoryService.addCategoryToCourse(test, category);
        }
        CategoryUtility.setDuplicateCategories(request.getServletContext());

        ModelAndView modelAndView = new ModelAndView(SPRING_MESSAGE_PAGE);
        modelAndView.addObject(MESSAGE_ATTRIBUTE, message);
        return modelAndView;
    }

    @RequestMapping(value = "/delete-category")
    public ModelAndView deleteCategory(@RequestParam(CATEGORY_PATH) String categoryPath,
                                       Locale locale, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(SPRING_MESSAGE_PAGE);

        Category category = categoryService.getCategory(categoryPath);
        if (CategoryUtility.containQuestionEntries(category, request.getServletContext())) {
            modelAndView.addObject(MESSAGE_ATTRIBUTE,
                    getResourceValue(locale, "category.not.removed1", "messages"));
        } else if (!category.getSubCategories().isEmpty()) {
            modelAndView.addObject(MESSAGE_ATTRIBUTE,
                    getResourceValue(locale, "category.not.removed2", "messages"));
        } else {
            categoryService.removeCategory(category);
            Collection<Category> categoryList =
                    CategoryUtility.getCategoriesFromServletContext(request)
                            .values();
            categoryList.remove(category);
            modelAndView.addObject(MESSAGE_ATTRIBUTE,
                    getResourceValue(locale, "category.removed", "messages"));
        }

        return modelAndView;
    }

    @RequestMapping(value = "/delete-from-course")
    public ModelAndView deleteFromCourse(@RequestParam(CATEGORY_PATH) String categoryPath,
                                         Locale locale, HttpServletRequest request) {
        Test test = TestUtility.getTestFromServletContext(request);
        Map<String, Category> categories = test.getCategories();
        Category category = categories.get(categoryPath);
        ModelAndView modelAndView = new ModelAndView(SPRING_MESSAGE_PAGE);
        if (!category.getSubCategories().isEmpty()) {
            modelAndView.addObject(MESSAGE_ATTRIBUTE,
                    getResourceValue(locale, "category.not.removed2", "messages"));
        } else {
            courseService.removeCategoryFromCourse(test, category);

            TestUtility.loadTestsToServletContext(request.getServletContext());
            CategoryUtility.setDuplicateCategories(request.getServletContext());
            modelAndView.addObject(MESSAGE_ATTRIBUTE,
                    getResourceValue(locale, "category.removed.from.course", "messages"));
        }

        return modelAndView;
    }

    @RequestMapping(value = "/move-category")
    public void moveCategory(@RequestParam(PREVIOUS_CATEGORY_PATH) String previousCategoryPath,
                             @RequestParam(TEST_PATH) String testPath,
                             HttpServletRequest request) {
        Category category = CategoryUtility.getCategoryByPath(request);
        Category previousCategory = categoryService.getCategory(previousCategoryPath);
        synchronized (this) {
            if (category.getOrderId() > previousCategory.getOrderId()) {
                categoryService.moveCategoryUp(category, previousCategoryPath, testPath);
            } else {
                categoryService.moveCategoryDown(category, previousCategoryPath, testPath);
            }
            TestUtility.loadTestsToServletContext(request.getServletContext());
        }
    }
}
