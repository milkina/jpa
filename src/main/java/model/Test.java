package model;


import model.article.Article;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 28.02.2013
 * Time: 21:11:13
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TESTS")
@NamedQueries({
        @NamedQuery(name = "Test.findAllTests",
                query = "SELECT t,sum(c.questionsCount),sum(c.testsCount) FROM Test t "
                        + "left join t.categories c "
                        + "GROUP BY t ORDER BY t.orderId "),
        @NamedQuery(name = "Test.findPathName",
                query = "select t.id,t.pathName from Test t"),
        @NamedQuery(name = "Test.getTestByQuestion",
                query = "SELECT t FROM Test t INNER JOIN t.categories c INNER JOIN c.questionEntries q" +
                        " WHERE q=:param"),
        @NamedQuery(name = "Test.findAllWithNotEmptyTests",
                query = "SELECT distinct t FROM Test t "
                        + "left join t.categories c WHERE "
                        + "c.testsCount>0 "
                        + "ORDER BY t.orderId"),
        @NamedQuery(name = "Course.findAllWithNotEmptyQuestions",
                query = "SELECT distinct t FROM Test t "
                        + "left join t.categories c WHERE "
                        + "c.questionsCount>0 "
                        + "ORDER BY t.orderId"),
        @NamedQuery(name = "Test.getPreviousTests",
                query = "SELECT t FROM Test t WHERE t.orderId<= "
                        + " (SELECT tt.orderId FROM Test tt WHERE tt.pathName=:param) ORDER BY t.orderId"),
        @NamedQuery(name = "Test.getNextTests",
                query = "SELECT t FROM Test t WHERE t.orderId>= "
                        + " (SELECT tt.orderId FROM Test tt WHERE tt.pathName=:param) ORDER BY t.orderId")
})
public class Test implements Serializable, Comparable<Test> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "TEST_NAME")
    private String name;

    @Transient
    private Long questionsNumber = 0L;

    @Transient
    private Long testsNumber = 0L;

    private Date updatedDate;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tests")
    @OrderBy("orderId,id")
    @MapKey(name = "pathName")
    private Map<String, Category> categories;

    private String pathName;

    private String tags;

    private int orderId;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Article article;

    private String iconText;

    @ManyToOne
    @JoinColumn(name = "language")
    private Language language;

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Category> getCategories() {
        return categories;
    }

    public void setCategories(Map<String, Category> categories) {
        this.categories = categories;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public Long getQuestionsNumber() {
        return questionsNumber;
    }

    public void setQuestionsNumber(Long questionsNumber) {
        this.questionsNumber = questionsNumber;
    }

    public Long getTestsNumber() {
        return testsNumber;
    }

    public void setTestsNumber(Long testNumber) {
        this.testsNumber = testNumber;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getIconText() {
        return iconText;
    }

    public void setIconText(String iconText) {
        this.iconText = iconText;
    }

    public int compareTo(Test t) {
        return getId() - t.getId();
    }

    public void removeCategory(Category category) {
        if (categories != null && categories.containsKey(category.getPathName())) {
            categories.remove(category.getPathName());
        }
    }

    public void addCategory(Category category) {
        if (categories == null) {
            categories = new HashMap<String, Category>();
        }
        categories.put(category.getPathName(), category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (getId() != test.getId()) return false;
        if (getName() != null ? !getName().equals(test.getName()) : test.getName() != null) return false;

        return !(getPathName() != null ? !getPathName().equals(test.getPathName()) : test.getPathName() != null);

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getQuestionsNumber() != null ? getQuestionsNumber().hashCode() : 0);
        result = 31 * result + (getCategories() != null ? getCategories().hashCode() : 0);
        result = 31 * result + (getPathName() != null ? getPathName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", questionsNumber=" + questionsNumber +
                ", updatedDate=" + updatedDate +
                ", categories=" + categories +
                ", pathName='" + pathName + '\'' +
                ", tags='" + tags + '\'' +
                ", orderId=" + orderId +
                ", article=" + article +
                ", iconText='" + iconText + '\'' +
                ", language=" + language +
                '}';
    }
}
