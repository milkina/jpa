package utils;

import data.language.LanguageBeanI;
import data.article.ArticleBeanI;
import data.category.CategoryBeanI;
import data.comment.CommentBeanI;
import data.exam.ExamBeanI;
import data.person.PersonBeanI;
import data.questionEntry.QuestionEntryBeanI;
import data.test.TestBeanI;
import org.testng.Assert;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: TanyaM
 * Date: Jul 20, 2011
 * Time: 5:59:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class InitialContextHandler {
    protected static String CATEGORY_BEAN_NAME = "CategoryBeanLocal";
    protected static String TEST_BEAN_NAME = "TestBeanLocal";
    protected static String PERSON_BEAN_NAME = "PersonBeanLocal";
    protected static String QUESTION_ENTRY_BEAN_NAME = "QuestionEntryBeanLocal";
    protected static String COMMENT_BEAN_NAME = "CommentBeanLocal";
    protected static String ARTICLE_BEAN_NAME = "ArticleBeanLocal";
    protected static String EXAM_BEAN_NAME = "ExamBeanLocal";
    protected static String LANGUAGE_BEAN_NAME = "LanguageBeanLocal";

    public static InitialContext initialContext = createInitialContext();

    protected final static String JTA_DATA_SOURCE = "movieDatabase";
    protected final static String NON_JTA_DATA_SOURCE = "movieDatabaseUnmanaged";
    protected final static String PERSISTENCE_UNIT_NAME = "primary";

    public static InitialContext createInitialContext() {
        System.out.println("-------------------------initialContext=" + initialContext);
        if (initialContext == null) {
            try {
                Properties properties = new Properties();
                properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
                properties.put("openejb.configuration", "openejb.conf");
                properties.put(JTA_DATA_SOURCE, "new://Resource?type=DataSource");
                properties.put(JTA_DATA_SOURCE + ".JdbcDriver", "org.hsqldb.jdbcDriver");
                properties.put(JTA_DATA_SOURCE + ".JdbcUrl", "jdbc:hsqldb:mem:moviedb");

                properties.put(NON_JTA_DATA_SOURCE, "new://Resource?type=DataSource");
                properties.put(NON_JTA_DATA_SOURCE + ".JdbcDriver", "org.hsqldb.jdbcDriver");
                properties.put(NON_JTA_DATA_SOURCE + ".JdbcUrl", "jdbc:hsqldb:mem:moviedb");
                properties.put(NON_JTA_DATA_SOURCE + ".JtaManaged", "false");

                properties.put(PERSISTENCE_UNIT_NAME + ".openjpa.jdbc.SynchronizeMappings", "buildSchema(ForeignKeys=true)");

                initialContext = new InitialContext(properties);
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return initialContext;
    }

    public static CategoryBeanI getCategoryBean() {
        Object object = getBean(CATEGORY_BEAN_NAME);
        Assert.assertTrue(object instanceof CategoryBeanI);
        return (CategoryBeanI) object;
    }

    public static TestBeanI getTestBean() {
        Object object = getBean(TEST_BEAN_NAME);
        Assert.assertTrue(object instanceof TestBeanI);
        return (TestBeanI) object;
    }

    protected static Object getBean(String beanName) {
        Object object = null;
        try {
            object = initialContext.lookup(beanName);
        } catch (NamingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Assert.assertNotNull(object);
        return object;
    }

    public static PersonBeanI getPersonBean() {
        Object object = getBean(PERSON_BEAN_NAME);

        Assert.assertTrue(object instanceof PersonBeanI);
        return (PersonBeanI) object;
    }

    public static QuestionEntryBeanI getQuestionEntryBean() {
        Object object = getBean(QUESTION_ENTRY_BEAN_NAME);
        Assert.assertTrue(object instanceof QuestionEntryBeanI);
        return (QuestionEntryBeanI) object;
    }

    public static CommentBeanI getCommentBean() {
        Object object = getBean(COMMENT_BEAN_NAME);
        Assert.assertTrue(object instanceof CommentBeanI);
        return (CommentBeanI) object;
    }

    public static ArticleBeanI getArticleBean() {
        Object object = getBean(ARTICLE_BEAN_NAME);
        Assert.assertTrue(object instanceof ArticleBeanI);
        return (ArticleBeanI) object;
    }

    public static ExamBeanI getExamBean() {
        Object object = getBean(EXAM_BEAN_NAME);
        Assert.assertTrue(object instanceof ExamBeanI);
        return (ExamBeanI) object;
    }

    public static LanguageBeanI getLanguageBean() {
        Object object = getBean(LANGUAGE_BEAN_NAME);
        Assert.assertTrue(object instanceof LanguageBeanI);
        return (LanguageBeanI) object;
    }
}
