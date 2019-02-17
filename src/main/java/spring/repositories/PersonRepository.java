package spring.repositories;

import model.AbstractQuestionEntry;
import model.person.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
    @Query("SELECT p FROM Person p ORDER BY p.createdDate DESC")
    List<Person> findAllOrderByCreatedDate();

    Person findByLoginAndPassword(String login, String password);

    Person findByLogin(String login);

    @Query("SELECT aq from Person p JOIN p.answeredQuestions aq WHERE p.id=:personId")
    List<AbstractQuestionEntry> findAnsweredQuestions(@Param("personId") int personId);
}
