package data.exam;

import model.AbstractExam;
import model.TestExam;
import model.person.Person;

import java.util.List;

public interface ExamBeanI {
    AbstractExam createExam(AbstractExam exam);

    List<TestExam> getExams(Person person);
}
