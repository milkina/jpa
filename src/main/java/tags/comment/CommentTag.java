package tags.comment;

import model.comment.Comment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import spring.services.comment.CommentService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Tatyana on 05.05.2016.
 */
public class CommentTag extends BodyTagSupport {
    private CommentService commentService;
    private Comment comment;
    private Iterator<Comment> iterator;
    private Integer commentId;

    private CommentService getCommentService(HttpServletRequest request) {
        if (commentService == null) {
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            commentService = webApplicationContext.getBean(CommentService.class);
        }
        return commentService;
    }

    public int doStartTag() {
        if (commentId != null) {
            getCommentFromDB((HttpServletRequest) pageContext.getRequest());
        } else {
            setIterator();
            getCommentFromList();
        }
        if (comment == null) {
            return SKIP_BODY;
        }
        return EVAL_BODY_TAG;
    }

    private void getCommentFromList() {
        if (iterator.hasNext()) {
            comment = iterator.next();
        }
    }

    private void getCommentFromDB(HttpServletRequest request) {
        comment = getCommentService(request).getComment(commentId);
    }

    private void setIterator() {
        CommentListTag parent =
                (CommentListTag) findAncestorWithClass(this, CommentListTag.class);
        List<Comment> commentList = parent.getCommentList();
        iterator = commentList.iterator();
    }

    public int doAfterBody() {
        BodyContent body = getBodyContent();
        try {
            JspWriter out = body.getEnclosingWriter();
            out.println(body.getString());
            body.clearBody(); // Clear for next evaluation
        } catch (IOException ioe) {
            System.out.println("Error in CommentTag: " + ioe);
        }
        if (iterator != null && iterator.hasNext()) {
            comment = iterator.next();
            return EVAL_BODY_TAG;
        } else {
            iterator = null;
            comment = null;
            return SKIP_BODY;
        }
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Comment getComment() {
        return comment;
    }
}


