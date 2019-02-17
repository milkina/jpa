package tags.comment;

import model.comment.Comment;
import model.comment.CommentType;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 08.05.2016.
 */
public class CommentUrlTag extends TagSupport {

    public int doStartTag() {
        try {
            CommentTag parent =
                    (CommentTag) findAncestorWithClass(this, CommentTag.class);
            Comment comment = parent.getComment();
            CommentType type = comment.getCommentType();
            String url = type.getUrl(comment.getReferenceId(), pageContext.getServletContext());
            String contextPath = pageContext.getServletContext().getContextPath();
            JspWriter out = pageContext.getOut();
            out.print(contextPath + "/" + url);
        } catch (IOException ioe) {
            System.out.println("Error in CommentUrlTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
