package tags.comment;

import model.comment.Comment;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DateFormat;

/**
 * Created by Tatyana on 03.05.2016.
 */
public class CommentDateTag extends TagSupport {

    public int doStartTag() {
        try {
            CommentTag parent =
                    (CommentTag) findAncestorWithClass(this, CommentTag.class);
            Comment comment = parent.getComment();
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
            JspWriter out = pageContext.getOut();
            out.print(dateFormat.format(comment.getCreatedDate()));
        } catch (IOException ioe) {
            System.out.println("Error in CommentDateTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
