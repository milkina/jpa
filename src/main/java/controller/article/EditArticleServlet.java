package controller.article;

import model.person.Person;
import util.GeneralUtility;
import util.article.ArticleUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.AllConstants.MESSAGE_PAGE;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsParam.ARTICLE_ID;
import static util.AllMessage.ARTICLE_UPDATED_MESSAGE;

/**
 * Created by Tatyana on 10.05.2016.
 */
public class EditArticleServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //request.setCharacterEncoding("utf-8");
        Integer id = GeneralUtility.getIntegerValue(request, ARTICLE_ID);
        if (id == null) {
            Person person = (Person)
                    request.getSession().getAttribute(PERSON_ATTRIBUTE);
            ArticleUtility.createArticle(request, person);
        } else {
            ArticleUtility.updateArticle(id, request);
        }

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(MESSAGE_PAGE);
        request.setAttribute(MESSAGE_ATTRIBUTE, ARTICLE_UPDATED_MESSAGE);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

