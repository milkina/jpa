package controller.category;

import data.category.CategoryHandler;
import model.Category;
import util.CategoryUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static util.AllConstants.MESSAGE_PAGE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsParam.CATEGORY_PATH;
import static util.AllMessage.CATEGORY_NOT_REMOVED_MESSAGE;
import static util.AllMessage.CATEGORY_REMOVED_MESSAGE;

/**
 * Created by Tatyana on 18.06.2016.
 */
public class DeleteCategoryServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryPath = request.getParameter(CATEGORY_PATH);
        CategoryHandler categoryHandler = new CategoryHandler();
        Category category = categoryHandler.getCategory(categoryPath);
        if (CategoryUtility.containQuestionEntries(category)) {
            request.setAttribute(MESSAGE_ATTRIBUTE,
                    CATEGORY_NOT_REMOVED_MESSAGE);
        } else {
            categoryHandler.removeCategory(category);
            Collection<Category> categoryList =
                    CategoryUtility.getCategoriesFromServletContext(request)
                            .values();
            categoryList.remove(category);
            request.setAttribute(MESSAGE_ATTRIBUTE, CATEGORY_REMOVED_MESSAGE);
        }

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(MESSAGE_PAGE);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

