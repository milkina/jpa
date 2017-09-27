package main.java.model;

import main.java.model.person.Person;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 02.10.2012
 * Time: 9:24:09
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "QUESTIONS")
@NamedQueries({
        @NamedQuery(name = "GET_ALL_QUESTION_ENTRIES", query = "SELECT qe from QuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answer " +
                "JOIN FETCH qe.category where qe.category=:param ORDER BY qe.orderColumn, qe.id"),
        @NamedQuery(name = "QuestionEntry.GET_ANSWERED_QUESTION_ENTRIES", query = "SELECT qe from QuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answer " +
                "JOIN FETCH qe.category where qe.category=:category AND qe.id IN " +
                "(select aq.id from Person p JOIN p.answeredQuestions aq where p=:person) ORDER BY qe.orderColumn,qe.id"),
        @NamedQuery(name = "QuestionEntry.GET_NOT_ANSWERED_QUESTION_ENTRIES", query = "SELECT qe from QuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answer " +
                "JOIN FETCH qe.category where qe.category=:category and qe.id NOT IN " +
                "(select aq.id from Person p JOIN p.answeredQuestions aq where p=:person) ORDER BY qe.orderColumn,qe.id"),
        @NamedQuery(name = "QuestionEntry.getPreviousQuestionEntry",
                query = "select qe1 from QuestionEntry qe1 " +
                        "where qe1.orderColumn = (select max(qe2.orderColumn) " +
                        "from QuestionEntry qe2 where qe2.orderColumn<:param and qe2.category.id=" +
                        "(select qe3.category.id from QuestionEntry qe3 where qe3.orderColumn=:param))")

})
public class QuestionEntry implements Serializable {
    @Id
    @Column(name = "entry_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "answer_id", referencedColumnName = "id")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    private int orderColumn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(int orderColumn) {
        this.orderColumn = orderColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        QuestionEntry that = (QuestionEntry) o;

        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (person != null ? !person.equals(that.person) : that.person != null) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (person != null ? person.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
