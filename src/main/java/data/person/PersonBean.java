package main.java.data.person;

import main.java.model.QuestionEntry;
import main.java.model.person.Person;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 09.07.2011
 * Time: 17:26:57
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class PersonBean implements PersonBeanI {
    @PersistenceContext(unitName = "primary")
    EntityManager entityManager;

    public Person getPersonById(int id) {
        return entityManager.find(Person.class, id);
    }

    public Person addPerson(Person person) {
        entityManager.persist(person);
        return person;
    }

    public List<Person> getAllPersons() {
        Query query = entityManager.createQuery("SELECT i FROM Person i order by i.createdDate desc, i.ID desc");
        List<Person> list = query.getResultList();
        return list;
    }

    public Person getPersonByLoginAndPassword(String login, String password) {
        Person person = null;
        try {
            Query query = entityManager.createNamedQuery("findPersonByLoginAndPassword");
            query.setParameter("loginName", login);
            query.setParameter("passwordName", password);
            person = (Person) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } catch (NonUniqueResultException ex) {
            return null;
        }
        return person;
    }

    public Person updatePerson(Person person) {
        entityManager.merge(person);
        entityManager.flush();
        return person;

    }

    public Person getPersonByLogin(String login) {
        Person person = null;
        try {
            Query query = entityManager.createNamedQuery("findPersonByLogin");
            query.setParameter("loginName", login);
            person = (Person) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } catch (NonUniqueResultException ex) {
            return null;
        }
        return person;
    }


    public Person getPersonByLoginViaCriteria(String login) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = criteriaBuilder.createQuery(Person.class);
        Root<Person> personRoot = query.from(Person.class);
        query.select(personRoot).where(criteriaBuilder.equal(personRoot.get("login"), login));
        TypedQuery<Person> typedQuery = entityManager.createQuery(query);
        Person person = null;
        try {
            person = typedQuery.getSingleResult();
        } catch (Exception e) {

        }
        return person;
    }

    public void removePerson(Person person) {
        removeCommentAuthor(person);
        entityManager.remove(entityManager.merge(person));
    }

    private void removeCommentAuthor(Person person) {
        Query query = entityManager.createNamedQuery("Comment.updateAuthor");
        query.setParameter("id", person.getID());
        query.executeUpdate();
    }

    public List<QuestionEntry> findAnsweredQuestions(int personId) {
        Query query = entityManager.createNamedQuery("Person.findAnsweredQuestions");
        query.setParameter("personId", personId);
        return query.getResultList();
    }

    public void removeAnsweredQuestions(Person person) {
        List<QuestionEntry> answeredQuestions = findAnsweredQuestions(person.getID());
        person = entityManager.merge(person);
        person.getAnsweredQuestions().removeAll(answeredQuestions);
    }
}
