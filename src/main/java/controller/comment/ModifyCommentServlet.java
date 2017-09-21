package main.java.controller.comment;

import main.java.controller.EditMode;
import main.java.data.comment.CommentHandler;
import main.java.model.comment.Comment;
import main.java.model.comment.CommentType;
import main.java.util.GeneralUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static main.java.util.AllConstants.MESSAGE_PAGE;
import static main.java.util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static main.java.util.AllConstantsParam.COMMENT_BODY;
import static main.java.util.AllConstantsParam.COMMENT_TYPE;
import static main.java.util.AllConstantsParam.COMMENT_ID;
import static main.java.util.AllConstantsParam.EDIT_MODE_PARAM;
import static main.java.util.AllConstantsParam.REFERENCE_ID;
import static main.java.util.AllMessage.COMMENT_CHANGED;
import static main.java.util.AllMessage.COMMENT_REMOVED;

/**
 * Created by Tatyana on 11.05.2016.
 */
public class ModifyCommentServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String editMode = request.getParameter(EDIT_MODE_PARAM);
        Integer commentId = GeneralUtility.getIntegerValue(request, COMMENT_ID);
        CommentHandler commentHandler = new CommentHandler();
        if (EditMode.EDIT.toString().equals(editMode)) {
            String commentBody = request.getParameter(COMMENT_BODY);
            String commentType = request.getParameter(COMMENT_TYPE);
            Integer referenceId = GeneralUtility.getIntegerValue(request,REFERENCE_ID);

            Comment comment = commentHandler.getComment(commentId);

            comment.setComment(commentBody);
            comment.setType(CommentType.valueOf(commentType));
            comment.setReferenceId(referenceId);

            commentHandler.updateComment(comment);

            request.setAttribute(MESSAGE_ATTRIBUTE, COMMENT_CHANGED);
        } else if (EditMode.DELETE.toString().equals(editMode)) {
            commentHandler.deleteComment(commentId);
            request.setAttribute(MESSAGE_ATTRIBUTE, COMMENT_REMOVED);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(MESSAGE_PAGE);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}


