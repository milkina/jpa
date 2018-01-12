package tags.comment;

import model.comment.Comment;
import util.PersonUtility;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Tatyana on 03.05.2016.
 */
public class CommentAuthorTag extends TagSupport {
    public int doStartTag() {
        try {
            CommentTag parent =
                    (CommentTag) findAncestorWithClass(this, CommentTag.class);
            Comment comment = parent.getComment();
            String author = PersonUtility.getCommentAuthor(comment);
            JspWriter out = pageContext.getOut();
            out.print(author);
        } catch (IOException ioe) {
            System.out.println("Error in CommentAuthorTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
