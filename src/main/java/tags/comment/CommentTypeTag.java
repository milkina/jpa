package tags.comment;

import model.comment.Comment;
import model.comment.CommentType;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 11.05.2016.
 */
public class CommentTypeTag extends TagSupport {

    public int doStartTag() {
        try {
            CommentTag parent =
                    (CommentTag) findAncestorWithClass(this, CommentTag.class);
            Comment comment = parent.getComment();
            CommentType type = comment.getCommentType();
            JspWriter out = pageContext.getOut();
            out.print(type);
        } catch (IOException ioe) {
            System.out.println("Error in CommentTypeTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
