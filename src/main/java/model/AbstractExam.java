package model;

import model.person.Person;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "exam")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
public class AbstractExam<T extends AbstractQuestionEntry> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    private double percent;

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    @Transient
    private List<T> questionEntries;

    @Transient
    private Integer currentNumber;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    @Transient
    private T currentQuestionEntry;

    public List<T> getQuestionEntries() {
        return questionEntries;
    }

    public void setQuestionEntries(List<T> questionEntries) {
        this.questionEntries = questionEntries;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public T getCurrentQuestionEntry() {
        return currentQuestionEntry;
    }

    public void setCurrentQuestionEntry(T currentQuestionEntry) {
        this.currentQuestionEntry = currentQuestionEntry;
    }

    public String getFormattedDate() {
        if (date == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        return simpleDateFormat.format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractExam that = (AbstractExam) o;
        return id == that.id &&
                Objects.equals(date, that.date) &&
                Objects.equals(person, that.person) &&
                Objects.equals(currentNumber, that.currentNumber) &&
                Objects.equals(category, that.category) &&
                Objects.equals(currentQuestionEntry, that.currentQuestionEntry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, person, currentNumber, category, currentQuestionEntry);
    }
}

