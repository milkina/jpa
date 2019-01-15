package util;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 09.10.2012
 * Time: 22:58:05
 * To change this template use File | Settings | File Templates.
 */
public interface AllMessage {

    String CATEGORY_CREATED_MESSAGE = "The category is created.";
    String CATEGORY_ADDED_MESSAGE = "The category is added.";
    String CATEGORY_NOT_ADDED_MESSAGE = "The test already contains selected category.";
    String CATEGORY_UPDATED_MESSAGE = "The category is changed.";
    String CATEGORY_REMOVED_MESSAGE = "The category is removed.";
    String CATEGORY_REMOVED_FROM_TEST_MESSAGE =
            "The category is removed from the selected test.";
    String ARTICLE_REMOVED_MESSAGE = "The article is removed.";
    String ARTICLE_UPDATED_MESSAGE = "The article is added/updated.";
    String CATEGORY_NOT_REMOVED_MESSAGE =
            "The category cannot be removed - it contains questions.";
    String TEST_DELETED_MESSAGE = "The test is deleted.";
    String TEST_NOT_DELETED_MESSAGE =
            "The test cannot be deleted. Make sure it doesn't contain categories.";
    String QUESTION_CHANGED_MESSAGE = "The question is updated.";
    String QUESTION_REMOVE_MESSAGE = "The question is removed.";
    String INVALID_NUMBERS_MESSAGE = "'From' cannot be greater than 'To' number. " +
            "'From' and 'To' should be less than total number of questions in the current category.";
    String QUESTIONS_MOVED = "%s questions moved.";
    String SELECT_DIFFERENT_CATEGORY = "Please select different category.";
    String COMMENT_CHANGED = "Comment is updated.";
    String COMMENT_REMOVED = "Comments are removed.";
    String USER_REMOVED = "User is removed.";
    String USER_HAS_ADDED_QUESTIONS = "User cannot be removed, because he has added questions.";
    String USER_NOT_REMOVED = "User cannot be removed.";
    String QUESTION_APPROVED = "The question is approved.";
}
