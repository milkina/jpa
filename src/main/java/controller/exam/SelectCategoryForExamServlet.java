package controller.exam;

import data.category.CategoryHandler;
import model.Category;
import util.CategoryUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static util.AllConstantsAttribute.CATEGORIES;
import static util.AllConstantsParam.TEST_PATH;

public class SelectCategoryForExamServlet extends HttpServlet {
    private static CategoryHandler categoryHandler = new CategoryHandler();

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String testPath = request.getParameter(TEST_PATH);
        List<Category> categories = categoryHandler.getCategories(testPath);
        CategoryUtility.selectCategoriesWithTests(categories);

        request.setAttribute(CATEGORIES, categories);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("start-exam.jsp");
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

