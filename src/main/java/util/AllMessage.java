package util;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 09.10.2012
 * Time: 22:58:05
 * To change this template use File | Settings | File Templates.
 */
public interface AllMessage {
    String WRONG_LOGIN_MESSAGE = "Wrong Login or Password";
    String PASS_AND_CONF_PASS_DIFFERENT_MESSAGE =
            "Password and Confirm Password should be the same. Please re-enter.";
    String LOGIN_EXIST_MESSAGE = "Such login already exists.";
    String ENTER_REQ_FIELDS_MESSAGE = "Please enter all required fields.";
    String QUESTION_ADDED_MESSAGE = "The question is added.";
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
    String TEST_ADDED_MESSAGE = "The test is added.";
    String TEST_UPDATED_MESSAGE = "The test is changed.";
    String TEST_DELETED_MESSAGE = "The test is deleted.";
    String TEST_NOT_DELETED_MESSAGE =
            "The test cannot be deleted. Make sure it doesn't contain categories.";
    String QUESTION_CHANGED_MESSAGE = "The question is updated.";
    String QUESTION_REMOVE_MESSAGE = "The question is removed.";
    String INVALID_NUMBERS_MESSAGE = "'From' cannot be greater than 'To' number. " +
            "'From' and 'To' should be less than total number of questions in the current category.";
    String QUESTIONS_MOVED = "%s questions moved.";
    String SELECT_DIFFERENT_CATEGORY = "Please select different category.";
    String EXAM_EMPTY = "There are no questions selected. " +
            "Either you have already answered all the questions or the selected category is empty.";
    String COMMENT_CHANGED = "Comment is updated.";
    String COMMENT_REMOVED = "Comments are removed.";
    String USER_REMOVED = "User is removed.";
    String USER_NOT_REMOVED = "User cannot be removed.";
}
