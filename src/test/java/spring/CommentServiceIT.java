package spring;

import model.comment.Comment;
import model.comment.CommentType;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestUtils;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 10.03.2013
 * Time: 20:53:09
 * To change this template use File | Settings | File Templates.
 */
@Test
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class CommentServiceIT extends BaseIT {

    @Test
    public void testGetComments() {
        List<Comment> result = commentService.getComments(CommentType.ARTICLE, articles[0].getId());
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() >= 1);
    }

    @Test
    public void testAddComment() {
        Comment comment = TestUtils.createComment(3, persons[0], CommentType.ARTICLE, articles[0].getId());
        comment = commentService.save(comment);
        Comment receivedComment = commentService.getComment(comment.getId());
        Assert.assertNotNull(receivedComment);
        Assert.assertEquals(receivedComment, comment);
    }

    @Test
    public void testGetLastComments() {
        List<Comment> result = commentService.getLastComments(3);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() >= 3);
        Assert.assertNotNull(result.get(0));
        Assert.assertNotNull(result.get(1));
        Assert.assertNotNull(result.get(2));
        Assert.assertNotNull(result.get(0).getCreatedDate());
        Assert.assertNotNull(result.get(1).getCreatedDate());
        Assert.assertNotNull(result.get(2).getCreatedDate());
        Assert.assertFalse(result.get(0).getCreatedDate().before(result.get(1).getCreatedDate()));
        Assert.assertFalse(result.get(1).getCreatedDate().before(result.get(2).getCreatedDate()));
    }

    @Test
    public void testAddCommentWithNullPerson() {
        Comment comment = TestUtils.createComment(4, null, CommentType.ARTICLE, articles[0].getId());
        Comment receivedComment = commentService.save(comment);
        Assert.assertNotNull(receivedComment);
        Assert.assertEquals(receivedComment, comment);
        Assert.assertNull(receivedComment.getUser());
    }

    @Test
    public void testGetComment() {
        Comment receivedComment = commentService.getComment(comments[0].getId());
        Assert.assertNotNull(receivedComment);
        Assert.assertEquals(receivedComment, comments[0]);
    }

    @Test
    public void testUpdateComment() {
        String newCommentValue = "newCommentValue";
        Comment comment = TestUtils.createComment(5, null, CommentType.ARTICLE, articles[1].getId());
        comment = commentService.save(comment);

        comment.setComment(newCommentValue);
        comment.setCommentType(CommentType.TEST);
        comment.setReferenceId(tests[1].getId());

        Comment receivedComment = commentService.save(comment);
        Assert.assertNotNull(receivedComment);
        Assert.assertEquals(comment, receivedComment);
        Assert.assertEquals(receivedComment.getComment(), newCommentValue);
        Assert.assertEquals(receivedComment.getCommentType(), CommentType.TEST);
        Assert.assertEquals(Integer.valueOf(receivedComment.getReferenceId()), tests[1].getId());
    }

    @Test
    public void testDeleteComment() {
        Comment comment = TestUtils.createComment(6, null, CommentType.ARTICLE, articles[0].getId());
        comment = commentService.save(comment);

        commentService.deleteComment(comment.getId());
        comment = commentService.getComment(comment.getId());
        Assert.assertNull(comment);
    }
}
