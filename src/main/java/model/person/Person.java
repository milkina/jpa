package model.person;

import model.AbstractQuestionEntry;
import util.GeneralUtility;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Users")
public class Person implements Comparable<Person> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "login")
    private String login = "";

    @Column(name = "password")
    private String password = "";

    @Column(name = "sysadmin")
    private Boolean sysadmin = false;

    private Date createdDate;

    @Column(name = "email")
    private String email = "";

    public void setEmail(String e) {
        email = e;
    }

    public String getEmail() {
        return email;
    }

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "answered_questions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<AbstractQuestionEntry> answeredQuestions;


    public void setLogin(String l) {
        login = l;
    }

    public String getLogin() {
        return login;
    }

    public void setId(Integer l) {
        id = l;
    }

    public Integer getId() {
        return id;
    }

    public void setPassword(String p) {
        password = p;
    }

    public String getPassword() {
        return password;
    }

    public void setSysadmin(Boolean l) {
        sysadmin = l;
    }

    public Boolean getSysadmin() {
        return isSysadmin();
    }

    public Boolean isSysadmin() {
        if (sysadmin == null) {
            return false;
        }
        return sysadmin;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getFormattedCreatedDate() {
        return getCreatedDate() != null ? GeneralUtility.formatDate(getCreatedDate()) : "";
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<AbstractQuestionEntry> getAnsweredQuestions() {
        if (answeredQuestions == null) {
            answeredQuestions = new ArrayList<>();
        }
        return answeredQuestions;
    }

    public void setAnsweredQuestions(List<AbstractQuestionEntry> answeredQuestions) {
        this.answeredQuestions = answeredQuestions;
    }

    public int compareTo(Person a) {
        if (this.getLogin().compareTo(a.getLogin()) > 1) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(login, person.login) &&
                Objects.equals(password, person.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }

    @Override
    public String toString() {
        return login;
    }
}

