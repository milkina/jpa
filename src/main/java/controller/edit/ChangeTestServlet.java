package main.java.controller.edit;

import com.google.gson.Gson;
import main.java.model.Category;
import main.java.model.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import static main.java.util.AllConstantsAttribute.TESTS;
import static main.java.util.AllConstantsParam.TEST_PATH;

/**
 * Created by Tatyana on 28.03.2016.
 */
public class ChangeTestServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String testPath = request.getParameter(TEST_PATH);
        Map<String, Test> tests = (Map) request.getServletContext().getAttribute(TESTS);
        Test test = tests.get(testPath);

        Map<String, String> categoryMap = new TreeMap<String, String>();
        for (Category category : test.getCategories().values()) {
            categoryMap.put(category.getPathName(), category.getName());
        }
        String json = new Gson().toJson(categoryMap);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
