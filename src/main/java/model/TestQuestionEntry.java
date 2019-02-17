package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
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

    public void changeCategoryCount(int i) {
        int count = getCategory().getTestsCount();
        getCategory().setTestsCount(count + i);
    }
}
