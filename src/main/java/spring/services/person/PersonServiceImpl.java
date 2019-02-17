package spring.services.person;

import model.AbstractQuestionEntry;
import model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.repositories.PersonRepository;
import spring.services.comment.CommentService;
import util.ServletUtilities;

import java.util.Date;
import java.util.List;
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CommentService commentService;

    @Override
    public Person findByLoginAndPassword(String login, String password) {
        return personRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public Person findByLogin(String login) {
        return personRepository.findByLogin(login);
    }

    @Override
    public Person getPerson(int id) {
        return personRepository.findOne(id);
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void removePerson(int personId) {
        Person person = personRepository.findOne(personId);
        commentService.removePersonFromComment(person);
        personRepository.delete(person);
    }

    @Override
    public List<Person> findAllOrderByCreatedDate() {
        return personRepository.findAllOrderByCreatedDate();
    }

    @Override
    public long count() {
        return personRepository.count();
    }

    @Override
    public Person addPerson(Person person) {
        if (person == null) {
            return null;
        }
        person.setPassword(ServletUtilities.getMD5(person.getPassword()));
        person.setCreatedDate(new Date());
        return personRepository.save(person);
    }

    @Override
    public List<AbstractQuestionEntry> findAnsweredQuestions(int personId) {
        return personRepository.findAnsweredQuestions(personId);
    }

    @Override
    @Transactional
    public void removeAnsweredQuestions(Person person) {
        List<AbstractQuestionEntry> answeredQuestions =
                findAnsweredQuestions(person.getId());
        person = personRepository.save(person);
        person.getAnsweredQuestions().removeAll(answeredQuestions);
    }
}
