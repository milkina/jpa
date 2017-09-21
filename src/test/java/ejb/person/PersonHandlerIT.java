package ejb.person;

import main.java.model.QuestionEntry;
import main.java.model.comment.Comment;
import main.java.model.comment.CommentType;
import main.java.model.person.Person;
import main.java.model.person.PersonInfo;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseIT;
import utils.TestUtils;
import utils.TestValues;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static utils.TestValues.*;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 02.03.2013
 * Time: 19:12:56
 * To change this template use File | Settings | File Templates.
 */
public class PersonHandlerIT extends BaseIT {
    @Test
    public void testAddNullPerson() {
        Person receivedPerson = personHandler.addPerson(null);
        Assert.assertNull(receivedPerson);
    }

    @Test
    public void testUpdatePerson() {
        Person person = TestUtils.createPerson(3);
        person = personHandler.addPerson(person);

        person.setLogin(LOGINS[4]);
        person.setPassword(PASSWORDS[4]);
        person.setSysadmin(false);

        person.getPersonInfo().setLastName(LAST_NAMES[4]);
        person.getPersonInfo().setFirstName(FIRST_NAMES[4]);
        person.getPersonInfo().setEmail(EMAILS[4]);
        personHandler.updatePerson(person);
        Person receivedPerson = personHandler.getPersonById(person.getID());
        Assert.assertNotNull(receivedPerson);
        Assert.assertEquals(receivedPerson.getPassword(), PASSWORDS[4]);
        Assert.assertEquals(receivedPerson.getLogin(), LOGINS[4]);
        Assert.assertNotNull(receivedPerson.getPersonInfo());
        Assert.assertEquals(receivedPerson.getPersonInfo().getLastName(), LAST_NAMES[4]);
        Assert.assertEquals(receivedPerson.getPersonInfo().getFirstName(), FIRST_NAMES[4]);
        Assert.assertEquals(receivedPerson.getPersonInfo().getEmail(), EMAILS[4]);
    }

    @Test
    public void testGetAllPersonsMap() {
        Map<Integer, Person> receivedMap = personHandler.getAllPersonsMap();
        Assert.assertNotNull(receivedMap);
        Assert.assertTrue(receivedMap.size() >= persons.length);
        for (Person person : persons) {
            Person receivedPerson = receivedMap.get(person.getID());
            Assert.assertNotNull(receivedPerson);
            Assert.assertEquals(receivedPerson, person);
        }
    }

    @Test
    public void testGetAllPersonsList() {
        List<Person> receivedList = personHandler.getAllPersonsList();
        Assert.assertNotNull(receivedList);
        Assert.assertTrue(receivedList.size() >= persons.length);
        for (Person person : receivedList) {
            Assert.assertNotNull(person);
        }
    }

    @Test
    public void testGetPersonById() {
        Person person = personHandler.getPersonById(persons[0].getID());
        Assert.assertNotNull(person);
        Assert.assertEquals(person.getID(), persons[0].getID());
    }

    @Test
    public void testGetPersonByValidLoginAndPassword() {
        Person person = personHandler.getPersonByLoginAndPassword(persons[0].getLogin(), persons[0].getPassword());
        Assert.assertNotNull(person);
        Assert.assertEquals(person.getLogin(), persons[0].getLogin());
        Assert.assertEquals(person.getPassword(), persons[0].getPassword());
    }

    @Test
    public void testGetPersonByInValidLoginAndPassword() {
        Person person = personHandler.getPersonByLoginAndPassword(LOGINS[0], PASSWORDS[1]);
        Assert.assertNull(person);
    }

    @Test
    public void testGetPersonByLogin() {
        Person person = personHandler.getPersonByLogin(LOGINS[1]);
        System.out.println("Persons[1]" + persons[1]);
        Assert.assertNotNull(person);
        Assert.assertEquals(person, persons[1]);

    }

    @Test
    public void testGetPersonByInvalidLogin() {
        Person person = personHandler.getPersonByLogin(PASSWORDS[0]);
        Assert.assertNull(person);
    }

    @Test
    public void testRemovePerson() {
        Person person = TestUtils.createPerson(9);
        person = personHandler.addPerson(person);

        Comment comment = TestUtils.createComment(7, person, CommentType.ARTICLE);
        comment = commentHandler.addComment(comment);

        personHandler.removePerson(person);
        Assert.assertNull(personHandler.getPersonById(person.getID()));

        Comment foundComment = commentHandler.getComment(comment.getId());
        Assert.assertNotNull(foundComment);
        Assert.assertNull(foundComment.getUser());
    }

    @Test
    public void testRemoveAnsweredQuestions() {
        PersonInfo personInfo = TestUtils.createPersonInfo(10);
        List<QuestionEntry> answeredQuestions = new ArrayList<QuestionEntry>();
        answeredQuestions.add(questionEntries[0]);
        answeredQuestions.add(questionEntries[1]);
        answeredQuestions.add(questionEntries[2]);
        Person person = TestUtils.createPerson(TestValues.LOGINS[10], TestValues.PASSWORDS[10], false,
                personInfo, answeredQuestions);
        personHandler.addPerson(person);
        personHandler.removeAnsweredQuestions(person);

        answeredQuestions = personHandler.findAnsweredQuestions(person.getID());
        Assert.assertNotNull(answeredQuestions);
        Assert.assertEquals(answeredQuestions.size(), 0);
    }
}
