package util;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import spring.services.article.ArticleService;
import spring.services.category.CategoryService;
import spring.services.course.CourseService;
import spring.services.exam.ExamService;
import spring.services.language.LanguageService;
import spring.services.person.PersonService;
import spring.services.question.QuestionService;

import javax.servlet.ServletContext;

public class SpringUtility {
    private static ArticleService articleService;
    private static CourseService courseService;
    private static CategoryService categoryService;
    private static PersonService personService;
    private static LanguageService languageService;
    private static ExamService examService;
    private static QuestionService questionService;

    public static <T> T getService(ServletContext servletContext, Class service) {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        return (T) webApplicationContext.getBean(service);
    }

    protected static CourseService getCourseService(ServletContext servletContext) {
        if (courseService == null) {
            courseService = SpringUtility.getService(servletContext, CourseService.class);
        }
        return courseService;
    }

    protected static CategoryService getCategoryService(ServletContext servletContext) {
        if (categoryService == null) {
            categoryService = SpringUtility.getService(servletContext, CategoryService.class);
        }
        return categoryService;
    }

    protected static ArticleService getArticleService(ServletContext servletContext) {
        if (articleService == null) {
            articleService = SpringUtility.getService(servletContext, ArticleService.class);
        }
        return articleService;
    }

    protected static PersonService getPersonService(ServletContext servletContext) {
        if (personService == null) {
            personService = SpringUtility.getService(servletContext, PersonService.class);
        }
        return personService;
    }

    protected static LanguageService getLanguageService(ServletContext servletContext) {
        if (languageService == null) {
            languageService = SpringUtility.getService(servletContext, LanguageService.class);
        }
        return languageService;
    }

    protected static ExamService getExamService(ServletContext servletContext) {
        if (examService == null) {
            examService = SpringUtility.getService(servletContext, ExamService.class);
        }
        return examService;
    }

    protected static QuestionService getQuestionService(ServletContext servletContext) {
        if (questionService == null) {
            questionService = SpringUtility.getService(servletContext, QuestionService.class);
        }
        return questionService;
    }
}
