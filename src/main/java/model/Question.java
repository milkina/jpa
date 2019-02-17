package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 02.10.2012
 * Time: 9:26:04
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "QUESTION_TEXT")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question")
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) &&
                Objects.equals(text, question.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }
}
