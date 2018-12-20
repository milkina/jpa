package model;

import model.person.Person;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "AbstractQuestionEntry.getAllQuestions",
                query = "SELECT DISTINCT qe FROM AbstractQuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answers "
                        + "JOIN FETCH qe.category WHERE qe.category=:param AND qe.approved=true ORDER BY qe.orderColumn, qe.id"),
        @NamedQuery(name = "AbstractQuestionEntry.getNotApprovedQuestions",
                query = "SELECT DISTINCT qe FROM AbstractQuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answers "
                        + "JOIN FETCH qe.category WHERE qe.approved=false ORDER BY qe.orderColumn, qe.id"),
        @NamedQuery(name = "AbstractQuestionEntry.getPersonQuestions",
                query = "SELECT DISTINCT qe FROM AbstractQuestionEntry qe JOIN FETCH qe.question JOIN FETCH qe.answers "
                        + "JOIN FETCH qe.category WHERE qe.person.ID=:param ORDER BY qe.orderColumn, qe.id"),
        @NamedQuery(name = "QuestionEntry.getPreviousQuestionEntry",
                query = "SELECT qe1 FROM AbstractQuestionEntry qe1 "
                        + "WHERE qe1.orderColumn = (SELECT max(qe2.orderColumn) "
                        + "FROM AbstractQuestionEntry qe2 WHERE qe2.approved=true AND qe2.orderColumn<:param AND qe2.category.id="
                        + "(SELECT qe3.category.id FROM AbstractQuestionEntry qe3 WHERE qe3.orderColumn=:param)"
                        + " AND qe2.type=(SELECT qe4.type FROM AbstractQuestionEntry qe4 WHERE qe4.orderColumn=:param))")

})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "QTYPE")
@Table(name = "QUESTIONS")
public abstract class AbstractQuestionEntry {
    @Id
    @Column(name = "entry_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToOne(cascade = {CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    @OneToMany(mappedBy = "questionEntry", fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    private int orderColumn;

    @Column(name = "QTYPE", insertable = false, updatable = false)
    private String type;

    private Boolean approved = false;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        if (!this.approved && approved) {
            changeCategoryCount(1);
        } else if (this.approved && !approved) {
            changeCategoryCount(-1);
        }
        this.approved = approved;
    }

    public abstract void changeCategoryCount(int i);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        //    if (o == null || getClass() != o.getClass()) return false;

        AbstractQuestionEntry that = (AbstractQuestionEntry) o;

        if (getId() != that.getId()) return false;
        if (getOrderColumn() != that.getOrderColumn()) return false;
        if (getCategory() != null ? !getCategory().equals(that.getCategory()) : that.getCategory() != null)
            return false;
        if (getQuestion() != null ? !getQuestion().equals(that.getQuestion()) : that.getQuestion() != null)
            return false;
        if (getAnswers() == null && that.getAnswers() != null) {
            return false;
        }
        if (getAnswers() != null && that.getAnswers() == null) {
            return false;
        }
        if (getAnswers().size() != that.getAnswers().size()) {
            return false;
        }
        for (int i = 0; i < getAnswers().size(); i++) {
            if (!getAnswers().get(i).equals(that.getAnswers().get(i))) {
                return false;
            }
        }
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
