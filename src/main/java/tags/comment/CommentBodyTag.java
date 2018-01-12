package tags.comment;

import model.comment.Comment;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 03.05.2016.
 */
public class CommentBodyTag extends TagSupport {
    public int doStartTag() {
        try {
            CommentTag parent =
                    (CommentTag) findAncestorWithClass(this, CommentTag.class);
            Comment comment = parent.getComment();
            JspWriter out = pageContext.getOut();
            out.print(comment.getComment());
        } catch (IOException ioe) {
            System.out.println("Error in CommentBodyTag: " + ioe);
        }
        return SKIP_BODY;
    }
}

