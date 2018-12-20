package controller.comment;

import controller.EditMode;
import data.comment.CommentHandler;
import model.comment.Comment;
import model.comment.CommentType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.AllConstants.MESSAGE_PAGE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsParam.EDIT_MODE_PARAM;
import static util.AllConstantsParam.COMMENT_ID;
import static util.AllConstantsParam.COMMENT_BODY;
import static util.AllConstantsParam.COMMENT_TYPE;
import static util.AllConstantsParam.REFERENCE_ID;
import static util.AllMessage.COMMENT_CHANGED;
import static util.GeneralUtility.getIntegerValue;

/**
 * Created by Tatyana on 11.05.2016.
 */
public class ModifyCommentServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        String editMode = request.getParameter(EDIT_MODE_PARAM);
        Integer commentId = getIntegerValue(request, COMMENT_ID);
        CommentHandler commentHandler = new CommentHandler();
        if (EditMode.EDIT.toString().equals(editMode)) {
            String commentBody = request.getParameter(COMMENT_BODY);
            String commentType = request.getParameter(COMMENT_TYPE);
            Integer referenceId = getIntegerValue(request, REFERENCE_ID);

            Comment comment = commentHandler.getComment(commentId);

            comment.setComment(commentBody);
            comment.setType(CommentType.valueOf(commentType));
            comment.setReferenceId(referenceId);

            commentHandler.updateComment(comment);

            request.setAttribute(MESSAGE_ATTRIBUTE, COMMENT_CHANGED);
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


