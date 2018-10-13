package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 02.10.2012
 * Time: 9:26:17
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "ANSWER_TEXT")
public class Answer implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "answer")
    private String text;

    @Column(name = "is_correct")
    private Boolean correct;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "entry_id", referencedColumnName = "entry_id")
    private AbstractQuestionEntry questionEntry;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public AbstractQuestionEntry getQuestionEntry() {
        return questionEntry;
    }

    public void setQuestionEntry(AbstractQuestionEntry questionEntry) {
        this.questionEntry = questionEntry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id &&
                Objects.equals(text, answer.text) &&
                Objects.equals(correct, answer.correct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, correct);
    }

    @Override
    public Object clone()  {
        try {
            return super.clone();
        }catch (CloneNotSupportedException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
