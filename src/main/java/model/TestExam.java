package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "TestExam.getPersonTestExams",
                query = "SELECT e from TestExam e WHERE e.person=:param ORDER BY e.date")
})
@DiscriminatorValue("TEST")
public class TestExam extends AbstractExam {
    public double getRightQuestionsCount() {
        int result = 0;
        List<TestQuestionEntry> questionEntries = getQuestionEntries();
        for (TestQuestionEntry questionEntry : questionEntries) {
            if (questionEntry.isCorrectAnswered()) {
                result++;
            }
        }
        return result;
    }
}
