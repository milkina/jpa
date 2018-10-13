package data.person;

import model.AbstractQuestionEntry;
import model.person.Person;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.AllBeanNameConstants.PERSON_BEAN_NAME;

/**
 * Created by IntelliJ IDEA.
 * User: TanyaM
 * Date: Jul 18, 2011
 * Time: 3:32:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class PersonHandler {

    private PersonBeanI personBean;
    private Context ct;

    public PersonHandler() {
        try {
            ct = new InitialContext();
            personBean = (PersonBeanI) ct.lookup(PERSON_BEAN_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public PersonHandler(PersonBeanI personBean) {
        this.personBean = personBean;
    }

    public Map<Integer, Person> getAllPersonsMap() {
        List<Person> list = getAllPersonsList();
        Map<Integer, Person> map = new HashMap<Integer, Person>();
        for (Person person : list) {
            map.put(person.getID(), person);
        }
        return map;
    }

    public List<Person> getAllPersonsList() {
        return personBean.getAllPersons();
    }

    public Person getPersonById(int id) {
        return personBean.getPersonById(id);
    }

    public Person getPersonByLoginAndPassword(String login, String password) {
        return personBean.getPersonByLoginAndPassword(login, password);
    }

    public Person addPerson(Person person) {
        if (person == null) {
            return null;
        }
        person.setCreatedDate(new Date());
        return personBean.addPerson(person);
    }

    public Person updatePerson(Person person) {
        return personBean.updatePerson(person);
    }

    public Person getPersonByLogin(String login) {
        return personBean.getPersonByLogin(login);
    }

    public Person getPersonByLoginViaCriteria(String login) {
        return personBean.getPersonByLogin(login);
    }

    public void removePerson(int personId) {
        Person person = personBean.getPersonById(personId);
        personBean.removePerson(person);
    }

    public void removePerson(Person person) {
        personBean.removePerson(person);
    }

    public List<AbstractQuestionEntry> findAnsweredQuestions(int personId) {
        return personBean.findAnsweredQuestions(personId);
    }

    public void removeAnsweredQuestions(Person person) {
        personBean.removeAnsweredQuestions(person);
    }
}
