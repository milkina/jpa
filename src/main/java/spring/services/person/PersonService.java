package spring.services.person;

import model.AbstractQuestionEntry;
import model.person.Person;

import java.util.List;

public interface PersonService {
    Person findByLoginAndPassword(String login, String password);

    Person getPerson(int id);

    Person save(Person person);

    void removePerson(int personId);

    List<Person> findAllOrderByCreatedDate();

    long count();

    Person addPerson(Person person);

    Person findByLogin(String login);

    List<AbstractQuestionEntry> findAnsweredQuestions(int personId);

    void removeAnsweredQuestions(Person person);
}
