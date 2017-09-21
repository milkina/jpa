package main.java.controller.test;

import main.java.util.CategoryUtility;
import main.java.util.TestUtility;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Tatyana on 17.05.2016.
 */
public class LoadTestServlet implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        TestUtility.loadTestsToServletContext(event.getServletContext());
        CategoryUtility.setDuplicateCategories(event.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }
}
