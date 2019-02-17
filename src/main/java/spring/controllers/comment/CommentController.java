package spring.controllers.comment;

import model.comment.Comment;
import model.comment.CommentType;
import model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spring.services.comment.CommentService;
import util.CommentUtility;
import util.EditMode;
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
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/delete-comment")
    public ModelAndView deleteComment(HttpServletRequest request, Locale locale) {
        String[] values = request.getParameterValues(DELETE_COMMENT);
        if (values != null) {
            for (String param : values) {
                commentService.deleteComment(Integer.parseInt(param));
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
            commentService.save(commentEntity);
        }
        TestUtility.loadTestsToServletContext(request.getServletContext());
        String referrerUrl = request.getHeader("Referer");
        return "redirect:" + referrerUrl;
    }

    @RequestMapping(value = "/modify-comment")
    public ModelAndView modifyComment(HttpServletRequest request, Locale locale) {
        String editMode = request.getParameter(EDIT_MODE_PARAM);
        Integer commentId = getIntegerValue(request, COMMENT_ID);
        ModelAndView modelAndView = new ModelAndView(SPRING_MESSAGE_PAGE);
        if (EditMode.EDIT.toString().equals(editMode)) {
            String commentBody = request.getParameter(COMMENT_BODY);
            String commentType = request.getParameter(COMMENT_TYPE);
            Integer referenceId = getIntegerValue(request, REFERENCE_ID);

            Comment comment = commentService.getComment(commentId);

            comment.setComment(commentBody);
            comment.setCommentType(CommentType.valueOf(commentType));
            comment.setReferenceId(referenceId);

            commentService.save(comment);

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
