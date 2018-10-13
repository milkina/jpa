package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "TestQuestionEntry.getAllQuestions",
                query = "SELECT distinct qe from TestQuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answers "
                        + "JOIN FETCH qe.category where qe.category=:param ORDER BY qe.orderColumn, qe.id")
})
@DiscriminatorValue("TEST")
public class TestQuestionEntry extends AbstractQuestionEntry implements Serializable {
    @Transient
    private boolean answered;

    @Transient
    private List<Answer> userAnswers;

    @Transient
    private Boolean correctAnswered;

    public void setUserAnswers(List<Answer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public List<Answer> getUserAnswers() {
        if (userAnswers == null) {
            userAnswers = new ArrayList<>();
            for (int i = 0; i < this.getAnswers().size(); i++) {
                Answer answer = (Answer) this.getAnswers().get(i).clone();
                answer.setCorrect(false);
                userAnswers.add(answer);
            }
            this.setUserAnswers(userAnswers);
        }
        return this.userAnswers;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public void setAnswer(Answer answer) {
        List<Answer> set = getAnswers();
        if (getAnswers() == null) {
            set = new ArrayList<>();
        }
        set.add(answer);
        setAnswers(set);
    }

    public boolean isCorrectAnswered() {
        if (correctAnswered == null) {
            correctAnswered = true;
            for (int i = 0; i < this.getAnswers().size(); i++) {
                if (!this.getAnswers().get(i).equals(this.getUserAnswers().get(i))) {
                    correctAnswered = false;
                    break;
                }
            }
        }
        return correctAnswered;
    }
}
