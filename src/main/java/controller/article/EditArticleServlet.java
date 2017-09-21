package main.java.controller.article;

import main.java.model.person.Person;
import main.java.util.GeneralUtility;
import main.java.util.article.ArticleUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static main.java.util.AllConstants.MESSAGE_PAGE;
import static main.java.util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static main.java.util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static main.java.util.AllConstantsParam.ARTICLE_ID;
import static main.java.util.AllMessage.ARTICLE_UPDATED_MESSAGE;

/**
 * Created by Tatyana on 10.05.2016.
 */
public class EditArticleServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //request.setCharacterEncoding("utf-8");
        Integer id = GeneralUtility.getIntegerValue(request, ARTICLE_ID);
        if (id == null) {
            Person person = (Person) request.getSession().getAttribute(PERSON_ATTRIBUTE);
            ArticleUtility.createArticle(request, person);
        } else {
            ArticleUtility.updateArticle(id, request);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(MESSAGE_PAGE);
        request.setAttribute(MESSAGE_ATTRIBUTE, ARTICLE_UPDATED_MESSAGE);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

