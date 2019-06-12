package utils;

import model.comment.Comment;
import model.person.Person;
import util.AllConstants;
import util.PersonUtility;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by Tatyana on 03.05.2016.
 */
public class PersonUtilityTest {
    private Comment comment1, comment2, comment3, comment4, comment7;

    @BeforeTest
    public void before() {
        comment1 = new Comment();
        comment1.setUser(new Person());

        comment2 = new Comment();
        Person person = new Person();
        comment2.setUser(person);


        comment3 = new Comment();
        comment3.setUser(person);

        comment4 = setData(null);
        comment7 = setData("login");
    }

    private Comment setData(String login) {
        Person person = new Person();

        person.setLogin(login);

        Comment comment = new Comment();
        comment.setUser(person);
        return comment;
    }


    @DataProvider
    public Object[][] data() {
        return new Object[][]
                {{null, AllConstants.UNKNOWN_USER},
                        {new Comment(), AllConstants.UNKNOWN_USER},
                        {comment1, ""},
                        {comment4, AllConstants.UNKNOWN_USER},
                        {comment7, "login"},
                };
    }

    @Test(dataProvider = "data")
    public void testGetCommentAuthor(Comment comment, String author) {
        String result = PersonUtility.getCommentAuthor(comment);
        Assert.assertNotNull(result);
        Assert.assertEquals(author, result);
    }
}
