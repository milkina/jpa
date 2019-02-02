package spring.controllers.comment;

import util.EditMode;
import data.comment.CommentHandler;
import model.comment.Comment;
import model.comment.CommentType;
import model.person.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import util.CommentUtility;
import util.GeneralUtility;
import util.TestUtility;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

import static util.AllConstants.SPRING_MESSAGE_PAGE;
import static util.AllConstantsAttribute.MESSAGE_ATTRIBUTE;
import static util.AllConstantsAttribute.PERSON_ATTRIBUTE;
import static util.AllConstantsParam.COMMENT_BODY;
import static util.AllConstantsParam.COMMENT_ID;
import static util.AllConstantsParam.COMMENT_TYPE;
import static util.AllConstantsParam.DELETE_COMMENT;
import static util.AllConstantsParam.EDIT_MODE_PARAM;
import static util.AllConstantsParam.REFERENCE_ID;
import static util.GeneralUtility.getIntegerValue;

@Controller
public class CommentController {
    @RequestMapping(value = "/delete-comment")
    public ModelAndView deleteComment(HttpServletRequest request, Locale locale) {
        CommentHandler commentHandler = new CommentHandler();
        String[] values = request.getParameterValues(DELETE_COMMENT);
        if (values != null) {
            for (String param : values) {
                commentHandler.deleteComment(Integer.parseInt(param));
            }
        }
        ModelAndView modelAndView = new ModelAndView(SPRING_MESSAGE_PAGE);
        modelAndView.addObject(MESSAGE_ATTRIBUTE,
                GeneralUtility.getResourceValue(locale, "comment.removed", "messages"));
        return modelAndView;
    }

    @RequestMapping(value = "/save-comment")
    public String saveComment(HttpServletRequest request) {
        Person person = (Person)
                request.getSession().getAttribute(PERSON_ATTRIBUTE);
        if (person != null) {
            Comment commentEntity = CommentUtility.createComment(request);

            CommentHandler commentHandler = new CommentHandler();
            commentHandler.addComment(commentEntity);
        }
        TestUtility.loadTestsToServletContext(request.getServletContext());
        String referrerUrl = request.getHeader("Referer");
        return "redirect:" + referrerUrl;
    }

    @RequestMapping(value = "/modify-comment")
    public ModelAndView modifyComment(HttpServletRequest request, Locale locale) {
        String editMode = request.getParameter(EDIT_MODE_PARAM);
        Integer commentId = getIntegerValue(request, COMMENT_ID);
        CommentHandler commentHandler = new CommentHandler();
        ModelAndView modelAndView = new ModelAndView(SPRING_MESSAGE_PAGE);
        if (EditMode.EDIT.toString().equals(editMode)) {
            String commentBody = request.getParameter(COMMENT_BODY);
            String commentType = request.getParameter(COMMENT_TYPE);
            Integer referenceId = getIntegerValue(request, REFERENCE_ID);

            Comment comment = commentHandler.getComment(commentId);

            comment.setComment(commentBody);
            comment.setType(CommentType.valueOf(commentType));
            comment.setReferenceId(referenceId);

            commentHandler.updateComment(comment);

            modelAndView.addObject(MESSAGE_ATTRIBUTE,
                    GeneralUtility.getResourceValue(locale, "comment.changed", "messages"));
        }
        return modelAndView;
    }

    @RequestMapping(value = "/show-edit-comment")
    public String showEditComment() {
        return "comment/edit-comment";
    }
}
