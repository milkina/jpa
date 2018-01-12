package data.comment;

import model.comment.Comment;
import model.comment.CommentType;
import model.person.Person;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 09.07.2011
 * Time: 21:30:06
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface CommentBeanI {
    Comment getComment(int id);

    Comment addComment(Comment comment);

    /*List<CommentTag> getAllCommentsForTest(Test exam);

    List<CommentTag> getAllCommentsForTestViaCriteria(Test exam);*/

    List<Comment> getAllNonAssignedComments();

    List<Comment> getAllGeneralComments();

    List<Comment> getAllCommentsForPerson(Person person);

    List<Comment> getComments(CommentType type, Integer referenceId);

    List<Comment> getLastComments(int number);

    Comment updateComment(Comment comment);

    void deleteComment(Comment comment);
}
