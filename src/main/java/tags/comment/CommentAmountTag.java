package tags.comment;

import model.comment.Comment;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created by Tatyana on 07.05.2016.
 */
public class CommentAmountTag extends TagSupport {

    public int doStartTag() {
        try {
            CommentListTag parent =
                    (CommentListTag) findAncestorWithClass(this, CommentListTag.class);
            List<Comment> commentList = parent.getCommentList();
            int commentAmount = commentList != null ? commentList.size() : 0;
            JspWriter out = pageContext.getOut();
            out.print(commentAmount);
        } catch (IOException ioe) {
            System.out.println("Error in CommentAmountTag: " + ioe);
        }
        return SKIP_BODY;
    }
}
