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
import java.util.Objects;

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
                        + "GROUP BY t ORDER BY t.orderId ")

})
public class Test implements Serializable, Comparable<Test> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getFullPathName() {
        if (getPathName().equals("jpa") || getPathName().equals("web-services")) {
            return pathName;
        }
        return "exam/" + pathName;
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
            categories = new HashMap<>();
        }
        categories.put(category.getPathName(), category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(id, test.id) &&
                Objects.equals(name, test.name) &&
                Objects.equals(pathName, test.pathName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pathName);
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
