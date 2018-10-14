package model;

import model.person.Person;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "AbstractQuestionEntry.getAllQuestions",
                query = "SELECT DISTINCT qe FROM AbstractQuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answers "
                        + "JOIN FETCH qe.category WHERE qe.category=:param ORDER BY qe.orderColumn, qe.id"),
        @NamedQuery(name = "QuestionEntry.getPreviousQuestionEntry",
                query = "select qe1 from AbstractQuestionEntry qe1 "
                        + "where qe1.orderColumn = (select max(qe2.orderColumn) "
                        + "from AbstractQuestionEntry qe2 where qe2.orderColumn<:param and qe2.category.id="
                        + "(select qe3.category.id from AbstractQuestionEntry qe3 where qe3.orderColumn=:param))")

})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
@Table(name = "QUESTIONS")
public abstract class AbstractQuestionEntry {
    @Id
    @Column(name = "entry_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    protected Category category;

    @OneToOne(cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    protected Question question;

    @OneToMany(mappedBy = "questionEntry",fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REMOVE})
    protected List<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    protected Person person;

    @Column(name = "CREATED_DATE")
    protected Date createdDate;
    protected int orderColumn;

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

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
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
        if (o == null || getClass() != o.getClass()) return false;

        AbstractQuestionEntry that = (AbstractQuestionEntry) o;

        if (getId() != that.getId()) return false;
        if (getOrderColumn() != that.getOrderColumn()) return false;
        if (getCategory() != null ? !getCategory().equals(that.getCategory()) : that.getCategory() != null)
            return false;
        if (getQuestion() != null ? !getQuestion().equals(that.getQuestion()) : that.getQuestion() != null)
            return false;
        if (getAnswers() != null ? !getAnswers().equals(that.getAnswers()) : that.getAnswers() != null) return false;
        if (getPerson() != null ? !getPerson().equals(that.getPerson()) : that.getPerson() != null) return false;
        return getCreatedDate() != null ? getCreatedDate().equals(that.getCreatedDate()) : that.getCreatedDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        result = 31 * result + (getQuestion() != null ? getQuestion().hashCode() : 0);
        result = 31 * result + (getAnswers() != null ? getAnswers().hashCode() : 0);
        result = 31 * result + (getPerson() != null ? getPerson().hashCode() : 0);
        result = 31 * result + (getCreatedDate() != null ? getCreatedDate().hashCode() : 0);
        result = 31 * result + getOrderColumn();
        return result;
    }

    @Override
    public String toString() {
        return "AbstractQuestionEntry{" +
                "id=" + id +
                ", category=" + category +
                ", question=" + question +
                ", answers=" + answers +
                ", person=" + person +
                ", createdDate=" + createdDate +
                ", orderColumn=" + orderColumn +
                '}';
    }
}
