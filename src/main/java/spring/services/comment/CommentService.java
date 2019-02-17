package spring.services.comment;

import model.comment.Comment;
import model.comment.CommentType;
import model.person.Person;

import java.util.List;

public interface CommentService {

    Comment getComment(int id);

    void deleteComment(int id);

    Comment save(Comment comment);

    void removePersonFromComment(Person person);

    List<Comment> getComments(CommentType type, Integer referenceId);

    List<Comment> getLastComments(int number);

    List<Comment> getAllGeneralComments();
}
