package model;

import model.person.Person;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private Integer id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    private double percent;

    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPercent() {
        return Math.round(percent);
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    @Transient
    private List<T> questionEntries;

    @Transient
    private Integer currentNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "exam_category",
            joinColumns = {@JoinColumn(name = "exam_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private List<Category> categories;

    @Transient
    private T currentQuestionEntry;

    public List<T> getQuestionEntries() {
        return questionEntries;
    }

    public void setQuestionEntries(List<T> questionEntries) {
        this.questionEntries = questionEntries;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setCategory(Category category) {
        this.categories = new ArrayList<>();
        this.categories.add(category);
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
        AbstractExam<?> that = (AbstractExam<?>) o;
        return Objects.equals(id, that.id) &&
                Double.compare(that.percent, percent) == 0 &&
                Objects.equals(date, that.date) &&
                Objects.equals(person, that.person) &&
                Objects.equals(questionEntries, that.questionEntries) &&
                Objects.equals(currentNumber, that.currentNumber) &&
                Objects.equals(categories, that.categories) &&
                Objects.equals(currentQuestionEntry, that.currentQuestionEntry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, person, percent, questionEntries, currentNumber, categories, currentQuestionEntry);
    }
}

