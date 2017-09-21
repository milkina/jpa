package ejb.comment;

import main.java.model.comment.Comment;
import main.java.model.comment.CommentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseIT;
import utils.TestUtils;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 10.03.2013
 * Time: 20:53:09
 * To change this template use File | Settings | File Templates.
 */
public class CommentHandlerIT extends BaseIT {

    @Test
    public void testGetComments() {
        List<Comment> result = commentHandler.getComments(CommentType.ARTICLE, 1);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() >= 1);
    }

    @Test
    public void testAddComment() {
        Comment comment = TestUtils.createComment(3, persons[0], CommentType.ARTICLE);
        comment = commentHandler.addComment(comment);
        Comment receivedComment = commentHandler.getComment(comment.getId());
        Assert.assertNotNull(receivedComment);
        Assert.assertEquals(receivedComment, comment);
    }

    @Test
    public void testGetLastComments() {
        List<Comment> result = commentHandler.getLastComments(3);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() >= 3);
        Assert.assertNotNull(result.get(0));
        Assert.assertNotNull(result.get(1));
        Assert.assertNotNull(result.get(2));
        Assert.assertNotNull(result.get(0).getDate());
        Assert.assertNotNull(result.get(1).getDate());
        Assert.assertNotNull(result.get(2).getDate());
        Assert.assertFalse(result.get(0).getDate().before(result.get(1).getDate()));
        Assert.assertFalse(result.get(1).getDate().before(result.get(2).getDate()));
    }

    @Test
    public void testAddCommentWithNullPerson() {
        Comment comment = TestUtils.createComment(4, null, CommentType.ARTICLE);
        Comment receivedComment = commentHandler.addComment(comment);
        Assert.assertNotNull(receivedComment);
        Assert.assertEquals(receivedComment, comment);
        Assert.assertNull(receivedComment.getUser());
    }

    @Test
    public void testGetComment() {
        Comment receivedComment = commentHandler.getComment(0);
        Assert.assertNotNull(receivedComment);
        Assert.assertEquals(receivedComment, comments[0]);
    }

    @Test
    public void testUpdateComment() {
        String newCommentValue = "newCommentValue";
        int newReferenceId = 1;
        Comment comment = TestUtils.createComment(5, null, CommentType.ARTICLE);
        comment = commentHandler.addComment(comment);

        comment.setComment(newCommentValue);
        comment.setType(CommentType.TEST);
        comment.setReferenceId(newReferenceId);

        Comment receivedComment = commentHandler.updateComment(comment);
        Assert.assertNotNull(receivedComment);
        Assert.assertEquals(comment, receivedComment);
        Assert.assertEquals(receivedComment.getComment(), newCommentValue);
        Assert.assertEquals(receivedComment.getType(), CommentType.TEST);
        Assert.assertEquals(receivedComment.getReferenceId(), newReferenceId);
    }

    @Test
    public void testDeleteComment() {
        Comment comment = TestUtils.createComment(6, null, CommentType.ARTICLE);
        comment = commentHandler.addComment(comment);

        commentHandler.deleteComment(comment);
        comment = commentHandler.getComment(comment.getId());
        Assert.assertNull(comment);
    }
/*
    @Test(enabled = false)
    public void getAllGeneralComments() {
        System.out.println("Run CommentHandlerTest.getAllGeneralComments");
        int foundComments = 0;
        List<Comment> receivedComments = commentHandler.getAllGeneralComments();
        Assert.assertNotNull(receivedComments);
        Assert.assertTrue(receivedComments.size() >= 2);
        for (Comment comment : receivedComments) {
            Assert.assertNotNull(comment);
            Assert.assertEquals(comment.getType(), CommentType.ALL);
            if (comment.equals(comment1) || comment.equals(comment2)) {
                foundComments++;
            }
        }
        Assert.assertTrue(foundComments == 2);
    }

    @Test(enabled = false)
    public void getAllCommentsForPerson() {
        System.out.println("Run CommentHandlerTest.getAllCommentsForPerson");
        List<Comment> receivedComments = commentHandler.getAllCommentsForPerson(person);
        Assert.assertNotNull(receivedComments);
        Assert.assertTrue(receivedComments.size() >= 1);
        for (Comment comment : receivedComments) {
            Assert.assertNotNull(comment);
            Assert.assertEquals(comment.getUser(), person);
        }
    }*/
}
