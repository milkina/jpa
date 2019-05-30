package controller.test;

import util.CategoryUtility;
import util.LanguageUtility;
import util.TestUtility;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Tatyana on 17.05.2016.
 */
public class LoadTestServlet implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        TestUtility.loadTestsToServletContext(servletContext);
        CategoryUtility.setDuplicateCategories(servletContext);
        LanguageUtility.loadLanguages(servletContext);
        TestUtility.loadCoursesForTests(servletContext);
        TestUtility.loadCoursesForQuestions(servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }
}
