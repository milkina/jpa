package main.java.tags.comment;

import main.java.model.comment.Comment;
import main.java.model.comment.CommentType;

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
            CommentType type = comment.getType();
            JspWriter out = pageContext.getOut();
            out.print(type);
        } catch (IOException ioe) {
            System.out.println("Error in CommentTypeTag: " + ioe);
        }
        return (SKIP_BODY);
    }


}
