package data.exam;

import model.AbstractExam;
import model.TestExam;
import model.person.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ExamBean implements ExamBeanI {
    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;

    public AbstractExam createExam(AbstractExam exam) {
        entityManager.persist(exam);
        return exam;
    }

    public List<TestExam> getExams(Person person) {
        Query query = entityManager.createNamedQuery(
                "TestExam.getPersonTestExams");
        query.setParameter("param", person);
        return query.getResultList();
    }
}
