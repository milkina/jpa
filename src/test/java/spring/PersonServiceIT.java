package spring;

import model.AbstractQuestionEntry;
import model.comment.Comment;
import model.comment.CommentType;
import model.person.Person;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestUtils;
import utils.TestValues;

import java.util.ArrayList;
import java.util.List;

import static utils.TestValues.EMAILS;
import static utils.TestValues.LOGINS;
import static utils.TestValues.PASSWORDS;

@Test
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class PersonServiceIT extends BaseIT {
    @Test
    public void testGetPersonByValidLoginAndPassword() {
        Person person = personService.findByLoginAndPassword(persons[0].getLogin(), persons[0].getPassword());
        Assert.assertNotNull(person);
        Assert.assertEquals(person.getLogin(), persons[0].getLogin());
        Assert.assertEquals(person.getPassword(), persons[0].getPassword());
    }

    @Test
    public void testGetPersonByInValidLoginAndPassword() {
        Person person = personService.findByLoginAndPassword(LOGINS[0], PASSWORDS[1]);
        Assert.assertNull(person);
    }

    @Test
    public void testGetAllPersonsList() {
        Iterable<Person> receivedList = personService.findAllOrderByCreatedDate();
        Assert.assertNotNull(receivedList);
        for (Person person : receivedList) {
            Assert.assertNotNull(person);
        }
    }

    @Test
    public void testGetPersonByLogin() {
        Person person = personService.findByLogin(LOGINS[1]);
        System.out.println("Persons[1]" + persons[1]);
        Assert.assertNotNull(person);
        Assert.assertEquals(person, persons[1]);
    }

    @Test
    public void testGetPersonByInvalidLogin() {
        Person person = personService.findByLogin(PASSWORDS[0]);
        Assert.assertNull(person);
    }

    @Test
    public void testRemoveAnsweredQuestions() {
        List<AbstractQuestionEntry> answeredQuestions = new ArrayList<>();
        answeredQuestions.add(questionEntries[0]);
        answeredQuestions.add(questionEntries[1]);
        answeredQuestions.add(questionEntries[2]);
        Person person = TestUtils.createPerson(TestValues.LOGINS[10], TestValues.PASSWORDS[10], false,
                EMAILS[10], answeredQuestions);
        personService.addPerson(person);
        personService.removeAnsweredQuestions(person);

        answeredQuestions = personService.findAnsweredQuestions(person.getId());
        Assert.assertNotNull(answeredQuestions);
        Assert.assertEquals(answeredQuestions.size(), 0);
    }

    @Test
    public void testRemovePerson() {
        Person person = TestUtils.createPerson(9);
        person = personService.addPerson(person);

        Comment comment = TestUtils.createComment(7, person, CommentType.ARTICLE, articles[0].getId());
        comment = commentService.save(comment);

        personService.removePerson(person.getId());
        Assert.assertNull(personService.getPerson(person.getId()));

        Comment foundComment = commentService.getComment(comment.getId());
        Assert.assertNotNull(foundComment);
        Assert.assertNull(foundComment.getUser());
    }

    @Test
    public void testAddNullPerson() {
        Person receivedPerson = personService.addPerson(null);
        Assert.assertNull(receivedPerson);
    }

    @Test
    public void testUpdatePerson() {
        Person person = TestUtils.createPerson(3);
        person = personService.addPerson(person);

        person.setLogin(LOGINS[4]);
        person.setPassword(PASSWORDS[4]);
        person.setSysadmin(false);

        person.setEmail(EMAILS[4]);
        personService.save(person);
        Person receivedPerson = personService.getPerson(person.getId());
        Assert.assertNotNull(receivedPerson);
        Assert.assertEquals(receivedPerson.getPassword(), PASSWORDS[4]);
        Assert.assertEquals(receivedPerson.getLogin(), LOGINS[4]);
        Assert.assertEquals(receivedPerson.getEmail(), EMAILS[4]);
    }

    @Test
    public void testGetPersonById() {
        Person person = personService.getPerson(persons[0].getId());
        Assert.assertNotNull(person);
        Assert.assertEquals(person.getId(), persons[0].getId());
    }
}
