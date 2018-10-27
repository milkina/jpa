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
@NamedQueries({
        @NamedQuery(name = "GET_ALL_QUESTION_ENTRIES",
                query = "SELECT DISTINCT qe FROM QuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answers "
                        + "JOIN FETCH qe.category WHERE qe.category=:param AND qe.approved=true ORDER BY qe.orderColumn, qe.id"),
        @NamedQuery(name = "QuestionEntry.GET_ANSWERED_QUESTION_ENTRIES",
                query = "SELECT qe FROM QuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answers "
                        + "JOIN FETCH qe.category WHERE qe.category=:category AND qe.id IN "
                        + "(select aq.id FROM Person p JOIN p.answeredQuestions aq where p=:person) ORDER BY qe.orderColumn,qe.id"),
        @NamedQuery(name = "QuestionEntry.GET_NOT_ANSWERED_QUESTION_ENTRIES",
                query = "SELECT qe FROM QuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answers "
                        + "JOIN FETCH qe.category WHERE qe.category=:category AND qe.approved=true AND qe.id NOT IN "
                        + "(SELECT aq.id FROM Person p JOIN p.answeredQuestions aq WHERE p=:person) ORDER BY qe.orderColumn,qe.id"),

})
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
