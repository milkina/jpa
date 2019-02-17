package spring.repositories;

import model.AbstractExam;
import model.TestExam;
import model.person.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends CrudRepository<AbstractExam, Integer> {
    @Query("SELECT e from TestExam e WHERE e.person=:param ORDER BY e.date")
    List<TestExam> getPersonTestExams(@Param("param") Person person);

}
