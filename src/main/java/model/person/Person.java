package main.java.model.person;


import main.java.model.QuestionEntry;

import javax.persistence.*;
import java.util.*;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "findPersonByLoginAndPassword",
                query = "SELECT c FROM Person c WHERE c.login=:loginName AND c.password=:passwordName"),
        @NamedQuery(
                name = "findPersonByLogin",
                query = "SELECT c FROM Person c WHERE c.login=:loginName"),
        @NamedQuery(
                name = "Person.findAnsweredQuestions",
                query = "SELECT aq from Person p join p.answeredQuestions aq WHERE p.ID=:personId")
})
@Table(name = "Users")
public class Person implements Comparable<Person> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int ID;

    @Column(name = "login")
    private String login = "";

    @Column(name = "password")
    String password = "";

    @Column(name = "sysadmin")
    private Boolean sysadmin = false;

    private Date createdDate;

    @Embedded
    private PersonInfo personInfo;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "answered_questions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<QuestionEntry> answeredQuestions;


    public void setLogin(String l) {
        login = l;
    }

    public String getLogin() {
        return login;
    }

    public void setID(int l) {
        ID = l;
    }

    public int getID() {
        return ID;
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

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<QuestionEntry> getAnsweredQuestions() {
        if (answeredQuestions == null) {
            answeredQuestions = new ArrayList<QuestionEntry>();
        }
        return answeredQuestions;
    }

    public void setAnsweredQuestions(List<QuestionEntry> answeredQuestions) {
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
        if (o == null) return false;

        Person person = (Person) o;

        if (ID != person.ID) return false;
        if (login != null ? !login.equals(person.login) : person.login != null) return false;
        if (password != null ? !password.equals(person.password) : person.password != null) return false;
       /* if(personInfo!=null && person.getPersonInfo()==null) return false;
        if(personInfo==null && person.getPersonInfo()!=null) return false;
        if(personInfo!=null && person.getPersonInfo()!=null && !personInfo.equals(person.getPersonInfo())) return false;*/
        return true;
    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return login;
    }
}

