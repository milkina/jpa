package util;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 29.09.2012
 * Time: 20:42:26
 * To change this template use File | Settings | File Templates.
 */
public interface AllConstants {
    String LOCAL_NAME = "java:module/";

    String TEST_NAME_PARAM = "testName";
    String MODE_PARAM = "mode";
    String SERIALIZE_PARAM = "serialize";
    String DESERIALIZE_PARAM = "deserialize";
    String ALL_CATEGORIES = "ALL_CATEGORIES";
    String ALL_TESTS = "ALL_TESTS";
    String SHOW_QUESTIONS_PAGE = "/show-questions";
    String EDIT_QUESTION_ENTRY_PAGE = "/question/edit-question";
    String EDIT_QUESTION_ENTRY_SERVLET = "/servlet/EditQuestionEntryServlet";
    String INDEX_PAGE = "/main.jsp";
    String MESSAGE_PAGE = "/messagePage.jsp";
    String SPRING_MESSAGE_PAGE = "/message-page";
    String MOVE_QUESTIONS_PAGE = "redirect:show-move-batch?TEST_PATH=%s&CATEGORY_PATH=%s&message=%s";
    String MY_PROFILE_PAGE = "/person/my-profile";
    String SHOW_PERSON_HISTORY_PAGE = "/person/show-person-history.jsp";
    String EDIT_ARTICLE = "article/edit-article";
    String SHOW_ARTICLE_PAGE = "article/show-article";

    String ALL_QUESTIONS = "ALL_QUESTIONS";
    String GET_ALL_QUESTION_ENTRIES = "GET_ALL_QUESTION_ENTRIES";
    String COOKIE_VALUE = "ID";
    String UNKNOWN_USER = "unknown";
    String MARK_QUESTION_HINT = "Mark question as answered and filter questions";
    String ADD_QUESTION_PAGE = "/question/add-question";
    String ADMINISTRATION_WELCOME_PAGE = "/administration/welcome.jsp";
    String SHOW_QUESTION_PAGE = "/WEB-INF/showQuestions/show-question.jsp";
    String SHOW_QUESTION_PICTURE_PAGE = "/WEB-INF/showQuestions/show-question-picture.jsp";
    String SHOW_QUIZ_QUESTION_PAGE = "/exam/show-quiz-question";
    String SHOW_TEST_QUESTION_PAGE = "/exam/show-test-question";
    String SHOW_QUESTION_SERVLET_PAGE = "show-question";
    String SHOW_EXAM_QUESTION = "show-exam-question";
    String GROUP_NAME = "java";
    String REGISTER_PAGE = "person/register";
    String WELCOME_REGISTER_PAGE = "/person/welcome";
    String SITE_NAME = "http://www.examclouds.com/";
    String TESTS_PAGE = "/exam/tests";
}
