package model;

import model.person.Person;

import java.util.List;

/**
 * Created by Tatyana on 29.04.2016.
 */
public class Exam {
    private Person person;
    private List<QuestionEntry> questionEntries;
    private Integer currentNumber;
    private Category category;
    private QuestionEntry currentQuestionEntry;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<QuestionEntry> getQuestionEntries() {
        return questionEntries;
    }

    public void setQuestionEntries(List<QuestionEntry> questionEntries) {
        this.questionEntries = questionEntries;
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

    public QuestionEntry getCurrentQuestionEntry() {
        return currentQuestionEntry;
    }

    public void setCurrentQuestionEntry(QuestionEntry currentQuestionEntry) {
        this.currentQuestionEntry = currentQuestionEntry;
    }
}
