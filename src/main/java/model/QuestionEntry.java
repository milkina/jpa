package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 02.10.2012
 * Time: 9:24:09
 * To change this template use File | Settings | File Templates.
 */
@Entity
@DiscriminatorValue("QUESTION")
public class QuestionEntry extends AbstractQuestionEntry implements Serializable {
    public Answer getAnswer() {
        Stream<Answer> stream = getAnswers().stream();
        return stream.findFirst().get();
    }

    public void setAnswer(Answer answer) {
        List<Answer> set = new ArrayList<>();
        set.add(answer);
        setAnswers(set);
    }

    public void changeCategoryCount(int i) {
        int count = getCategory().getQuestionsCount();
        getCategory().setQuestionsCount(count + i);
    }
}
