package spring.services.exam;

import model.AbstractExam;
import model.TestExam;
import model.person.Person;

import java.util.List;

public interface ExamService {
    AbstractExam createExam(AbstractExam exam);

    List<TestExam> getExams(Person person);
}
