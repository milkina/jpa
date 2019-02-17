package util;

import model.comment.Comment;
import model.comment.CommentType;
import model.person.Person;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsParam.COMMENT_TEXT_PARAM;
import static util.AllConstantsParam.COMMENT_TYPE;
import static util.AllConstantsParam.REFERENCE_ID;

public class CommentUtility {

    public static Comment createComment(HttpServletRequest request) {
        Comment commentEntity = new Comment();
        setCommentText(request, commentEntity);
        setUser(request, commentEntity);
        setType(request, commentEntity);
        setReferenceId(request, commentEntity);
        commentEntity.setCreatedDate(new Date());
        return commentEntity;
    }

    private static void setCommentText(HttpServletRequest request,
                                       Comment commentEntity) {
        String comment = GeneralUtility.decodeRussianCharacters(
                request.getParameter(COMMENT_TEXT_PARAM));
        commentEntity.setComment(comment);
    }

    private static void setReferenceId(HttpServletRequest request,
                                       Comment commentEntity) {
        int referenceId = GeneralUtility.getIntegerValue(request, REFERENCE_ID);
        commentEntity.setReferenceId(referenceId);
    }

    private static void setType(HttpServletRequest request, Comment commentEntity) {
        String type = request.getParameter(COMMENT_TYPE);
        commentEntity.setCommentType(CommentType.valueOf(type));
    }

    private static void setUser(HttpServletRequest request, Comment commentEntity) {
        Person person = (Person)
                request.getSession().getAttribute(PERSON_ATTRIBUTE);
        commentEntity.setUser(person);
    }
}
