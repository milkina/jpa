package spring.repositories;

import model.comment.Comment;
import model.comment.CommentType;
import model.person.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    Iterable<Comment> findByUser(Person user);

    List<Comment> findByCommentTypeAndReferenceIdOrderByCreatedDateAsc(CommentType commentType, int referenceId);

    @Query("SELECT c FROM Comment c ORDER BY c.createdDate DESC")
    List<Comment> getLastComments(Pageable pageable);

    List<Comment> findByCommentTypeOrderByIdAsc(CommentType type);
}
