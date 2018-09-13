package controller.comment;

import data.comment.CommentHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.AllConstants.MESSAGE_PAGE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsParam.DELETE_COMMENT;
import static util.AllMessage.COMMENT_REMOVED;

public class DeleteCommentServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        CommentHandler commentHandler = new CommentHandler();
        String[] values = request.getParameterValues(DELETE_COMMENT);
        if (values != null) {
            for (String param : values) {
                commentHandler.deleteComment(Integer.parseInt(param));
            }
        }
        request.setAttribute(MESSAGE_ATTRIBUTE, COMMENT_REMOVED);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(MESSAGE_PAGE);
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
