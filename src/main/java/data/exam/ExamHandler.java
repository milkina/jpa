package data.exam;

import model.AbstractExam;
import model.TestExam;
import model.person.Person;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.List;

import static util.AllBeanNameConstants.EXAM_BEAN_NAME;

public class ExamHandler {
    private ExamBeanI examBeanI;
    private Context ct;

    public ExamHandler() {
        try {
            ct = new InitialContext();
            examBeanI = (ExamBeanI) ct.lookup(EXAM_BEAN_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public ExamHandler(ExamBeanI examBeanI) {
        this.examBeanI = examBeanI;
    }

    public AbstractExam createExam(AbstractExam exam) {
        return examBeanI.createExam(exam);
    }

    public List<TestExam> getExams(Person person) {
        return examBeanI.getExams(person);
    }
}
