package spring.services.comment;

import model.comment.Comment;
import model.comment.CommentType;
import model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spring.repositories.CommentRepository;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository repository;

    @Override
    public Comment getComment(int id) {
        return repository.findOne(id);
    }

    @Override
    public void deleteComment(int id) {
        repository.delete(id);
    }

    @Override
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public void removePersonFromComment(Person person) {
        for (Comment comment : repository.findByUser(person)) {
            comment.setUser(null);
            repository.save(comment);
        }
    }

    @Override
    public List<Comment> getComments(CommentType type, Integer referenceId) {
        return repository.findByCommentTypeAndReferenceIdOrderByCreatedDateAsc(type, referenceId);
    }

    @Override
    public List<Comment> getLastComments(int number) {
        Pageable top = new PageRequest(0, number);
        return repository.getLastComments(top);
    }

    @Override
    public List<Comment> getAllGeneralComments() {
        return repository.findByCommentTypeOrderByIdAsc(CommentType.ALL);
    }
}
