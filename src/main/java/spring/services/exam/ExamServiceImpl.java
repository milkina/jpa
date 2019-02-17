package spring.services.exam;

import model.AbstractExam;
import model.TestExam;
import model.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.repositories.ExamRepository;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamRepository examRepository;

    @Override
    public AbstractExam createExam(AbstractExam exam) {
        return examRepository.save(exam);
    }

    @Override
    public List<TestExam> getExams(Person person) {
        return examRepository.getPersonTestExams(person);
    }
}
