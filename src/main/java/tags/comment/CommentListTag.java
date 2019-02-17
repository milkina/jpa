package tags.comment;

import model.article.Article;
import model.comment.Comment;
import model.comment.CommentType;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import spring.services.comment.CommentService;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
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

    private static CommentService commentService;

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
        ServletRequest request = pageContext.getRequest();
        Article article = (Article) request.getAttribute(ARTICLE_ATTRIBUTE);
        if (amount != null) {
            commentList = getCommentService(request).getLastComments(amount);
        } else if (article != null) {
            commentList = article.getComments();
        } else {
            commentList = getCommentService(request).getComments(type, referenceId);
        }
        return EVAL_BODY_INCLUDE;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    private CommentService getCommentService(ServletRequest request) {
        if (commentService == null) {
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            commentService = webApplicationContext.getBean(CommentService.class);
        }
        return commentService;
    }
}


