package data.comment;

import model.comment.Comment;
import model.comment.CommentType;
import model.person.Person;

import static util.AllBeanNameConstants.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: TanyaM
 * Date: Jul 20, 2011
 * Time: 5:05:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class CommentHandler {

    private CommentBeanI commentBean;
    private Context ct;

    public CommentHandler() {
        try {
            ct = new InitialContext();
            commentBean = (CommentBeanI) ct.lookup(COMMENT_BEAN_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public CommentHandler(CommentBeanI commentBean) {
        this.commentBean = commentBean;
    }

    public Comment getComment(int id) {
        return commentBean.getComment(id);
    }

    public Comment addComment(Comment comment) {
        return commentBean.addComment(comment);
    }

  /*  public List<CommentTag> getAllCommentsForTest(Test exam) {
        if (exam == null) {
            return commentBean.getAllNonAssignedComments();
        }
        {
            return commentBean.getAllCommentsForTest(exam);
        }
    }

    public List<CommentTag> getAllCommentsForTestViaCriteria(Test exam) {
        if (exam == null) {
            return commentBean.getAllNonAssignedComments();
        } else {
            return commentBean.getAllCommentsForTestViaCriteria(exam);
        }
    }*/


    public List<Comment> getAllCommentsForPerson(Person person) {
        return commentBean.getAllCommentsForPerson(person);
    }

    public List<Comment> getAllGeneralComments() {
        return commentBean.getAllGeneralComments();
    }

    public List<Comment> getComments(CommentType type, Integer referenceId) {
        return commentBean.getComments(type, referenceId);
    }

    public List<Comment> getLastComments(int number) {
        return commentBean.getLastComments(number);
    }

    public Comment updateComment(Comment comment) {
        return commentBean.updateComment(comment);
    }

    public void deleteComment(Comment comment) {
        commentBean.deleteComment(comment);
    }

    public void deleteComment(int commentId) {
        Comment comment = commentBean.getComment(commentId);
        deleteComment(comment);
    }
}
