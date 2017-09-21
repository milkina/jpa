package main.java.controller;

import main.java.data.comment.CommentHandler;
import main.java.model.comment.Comment;
import main.java.model.comment.CommentType;
import main.java.model.person.Person;
import main.java.util.GeneralUtility;
import main.java.util.TestUtility;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Date;

import static main.java.util.AllConstantsParam.*;
import static main.java.util.AllConstantsAttribute.*;


/**
 * Created by IntelliJ IDEA.
 * User: TanyaM
 * Date: 16.02.2010
 * Time: 12:50:31
 * To change this template use File | Settings | File Templates.
 */
public class SaveComment extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Person person = (Person) request.getSession().getAttribute(PERSON_ATTRIBUTE);
        if (person != null) {
            Comment commentEntity = createComment(request);

            CommentHandler commentHandler = new CommentHandler();
            commentHandler.addComment(commentEntity);
        }
        TestUtility.loadTestsToServletContext(request.getServletContext());
        String referrerUrl = request.getHeader("Referer");
        response.sendRedirect(referrerUrl);
    }

    private Comment createComment(HttpServletRequest request) {
        Comment commentEntity = new Comment();
        setCommentText(request, commentEntity);
        setUser(request, commentEntity);
        setType(request, commentEntity);
        setReferenceId(request, commentEntity);
        commentEntity.setDate(new Date());
        return commentEntity;
    }

    private void setCommentText(HttpServletRequest request, Comment commentEntity) {
        String comment = GeneralUtility.decodeRussianCharacters(request.getParameter(COMMENT_TEXT_PARAM));
        commentEntity.setComment(comment);
    }

    private void setReferenceId(HttpServletRequest request, Comment commentEntity) {
        int referenceId = GeneralUtility.getIntegerValue(request, REFERENCE_ID);
        commentEntity.setReferenceId(referenceId);
    }

    private void setType(HttpServletRequest request, Comment commentEntity) {
        String type = request.getParameter(COMMENT_TYPE);
        commentEntity.setType(CommentType.valueOf(type));
    }

    private void setUser(HttpServletRequest request, Comment commentEntity) {
        Person person = (Person) request.getSession().getAttribute(PERSON_ATTRIBUTE);
        commentEntity.setUser(person);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
