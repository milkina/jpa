package data.comment;

import model.comment.Comment;
import model.comment.CommentType;
import model.person.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 09.07.2011
 * Time: 21:29:37
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class CommentBean implements CommentBeanI {
    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;

    public Comment getComment(int id) {
        return entityManager.find(Comment.class, id);
    }

    public Comment addComment(Comment comment) {
        entityManager.persist(comment);
        return comment;
    }

   /* public List<CommentTag> getAllCommentsForTest(Test exam) {
        javax.persistence.Query query = entityManager.createQuery(
        "SELECT i FROM CommentTag i WHERE i.exam=:param");
        query.setParameter("param", exam);
        List<CommentTag> list = query.getResultList();
        return list;
    }*/

/*    public List<CommentTag> getAllCommentsForTestViaCriteria(Test exam) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CommentTag> criteriaQuery =
        criteriaBuilder.createQuery(CommentTag.class);
        Root<CommentTag> comment = criteriaQuery.from(CommentTag.class);
        criteriaQuery.select(comment).where(
        criteriaBuilder.equal(comment.get("exam"), exam));
        TypedQuery<CommentTag> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }*/

    public List<Comment> getAllNonAssignedComments() {
        Query query = entityManager.createQuery(
                "SELECT i FROM Comment i WHERE i.exam is null");
        List<Comment> list = query.getResultList();
        return list;
    }

    public List<Comment> getAllCommentsForPerson(Person person) {
        Query query = entityManager.createQuery(
                "SELECT i FROM Comment i WHERE i.user=:param");
        query.setParameter("param", person);
        List<Comment> list = query.getResultList();
        return list;
    }

    public List<Comment> getAllGeneralComments() {
        Query query = entityManager.createQuery(
                "SELECT c FROM Comment c WHERE c.type=:param order by c.id");
        query.setParameter("param", CommentType.ALL);
        List<Comment> list = query.getResultList();
        return list;
    }

    public List<Comment> getComments(CommentType type, Integer referenceId) {
        Query query = entityManager.createNamedQuery("Comment.getComments");
        query.setParameter("typeParam", type);
        query.setParameter("referenceParam", referenceId);
        List<Comment> list = query.getResultList();
        return list;
    }

    public List<Comment> getLastComments(int number) {
        Query query = entityManager.createNamedQuery("Comment.getLastComments");
        query.setMaxResults(number);
        List<Comment> list = query.getResultList();
        return list;
    }

    public Comment updateComment(Comment comment) {
        entityManager.merge(comment);
        return comment;
    }

    public void deleteComment(Comment comment) {
        entityManager.remove(entityManager.merge(comment));
    }

    public void deleteCommentAuthor() {

    }
}
