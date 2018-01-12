package tags.comment;

import data.comment.CommentHandler;
import model.article.Article;
import model.comment.Comment;
import model.comment.CommentType;

import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.List;

import static util.AllConstantsAttribute.ARTICLE_ATTRIBUTE;

/**
 * Created by Tatyana on 07.05.2016.
 */
public class CommentListTag extends BodyTagSupport {
    private CommentType type;
    private Integer referenceId;
    private Integer amount;
    private List<Comment> commentList;

    public CommentType getType() {
        return type;
    }

    public void setType(CommentType type) {
        this.type = type;
    }

    public Integer getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Integer referenceId) {
        this.referenceId = referenceId;
    }

    public int doStartTag() {
        Article article = (Article) pageContext.getRequest().getAttribute(ARTICLE_ATTRIBUTE);
        CommentHandler commentHandler = new CommentHandler();
        if (amount != null) {
            commentList = commentHandler.getLastComments(amount);
        } else if (article != null) {
            commentList = article.getComments();
        } else {
            commentList = commentHandler.getComments(type, referenceId);
        }
        return EVAL_BODY_INCLUDE;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}


