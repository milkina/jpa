package utils;


/**
 * Created by IntelliJ IDEA.
 * User: TanyaM
 * Date: Jul 14, 2011
 * Time: 9:59:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestValues {
    private static int PERSON_LENGTH = 11;
    private static int TEST_LENGTH = 26;
    private static int CATEGORY_LENGTH = 24;

    public static String[] LOGINS = createArray("login", PERSON_LENGTH);
    public static String[] PASSWORDS = createArray("password", PERSON_LENGTH);
    public static String[] EMAILS = createArray("Email1@gmail.com", PERSON_LENGTH);

    public static String[] TEST_NAMES = createArray("Test", TEST_LENGTH);
    public static String[] TEST_PATHS = createArray("testpath", TEST_LENGTH);
    public static String[] TEST_TAGS = createArray("#testTag", TEST_LENGTH);
    public static long[] TEST_QUESTION_NUMBERS = new long[TEST_LENGTH];

    static {
        TEST_QUESTION_NUMBERS[0] = 3;
        TEST_QUESTION_NUMBERS[7] = 1;
        TEST_QUESTION_NUMBERS[8] = 1;
    }

    public static String[] CATEGORY_NAMES = createArray("Category", CATEGORY_LENGTH);
    public static String[] CATEGORY_PATHNAMES = createArray("CategoryPathName", CATEGORY_LENGTH);

    public static String[] QUESTION_TEXTS = createArray("Question", 16);
    public static String[] ANSWERS = createArray("PossibleAnswer", 16);

    public static String COMMENTS[] = createArray("Comment", 8);

    public static String ARTICLE_URLS[] = createArray("URL", CATEGORY_LENGTH);
    public static String ARTICLE_TEXT[] = createArray("text", CATEGORY_LENGTH);
    public static String ARTICLE_IMAGE[] = createArray("img", CATEGORY_LENGTH);
    public static String ARTICLE_TITLE[] = createArray("title", CATEGORY_LENGTH);
    public static String ARTICLE_DESC[] = createArray("desc", CATEGORY_LENGTH);
    public static String ARTICLE_KEYWORDS[] = createArray("keywords", CATEGORY_LENGTH);

    public static String LANGUAGE_CODE[] = createArray("code",2);
    public static String LANGUAGE_DESCRIPTION[] = createArray("description",2);

    public static String[] createArray(String value, int length) {
        String[] result = new String[length];
        for (int i = 0; i < length; i++) {
            result[i] = value + i;
        }
        return result;
    }
}
