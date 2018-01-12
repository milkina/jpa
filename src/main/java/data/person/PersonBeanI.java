package data.person;

import model.QuestionEntry;
import model.person.Person;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 09.07.2011
 * Time: 17:27:13
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface PersonBeanI {
    Person getPersonById(int id);

    Person addPerson(Person person);

    List<Person> getAllPersons();

    Person getPersonByLoginAndPassword(String login, String password);

    Person updatePerson(Person person);

    Person getPersonByLogin(String login);

    void removePerson(Person person);

    List<QuestionEntry> findAnsweredQuestions(int personId);

    void removeAnsweredQuestions(Person person);
}
