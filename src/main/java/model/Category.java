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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 29.09.2012
 * Time: 20:21:59
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "Category")
public class Category implements Serializable, Comparable<Category> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    // @Column(unique = true)
    private String pathName;

    private String videoPath;

    @ManyToMany
    @JoinTable(name = "TEST_CATEGORY",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "TEST_ID"))
    @OrderBy("orderId,id")
    private List<Test> tests;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Article article;

    @OneToMany(mappedBy = "category")
    private List<AbstractQuestionEntry> questionEntries;

    @ManyToOne
    @JoinColumn(name = "parent_category")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.EAGER)
    @OrderBy("orderId,id")
    private List<Category> subCategories;

    private boolean hidden = false;
    private int orderId;

    @Column(name = "qcount")
    private int questionsCount;

    @Column(name = "tcount")
    private int testsCount;

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

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public List<AbstractQuestionEntry> getQuestionEntries() {
        return questionEntries;
    }

    public void setQuestionEntries(List<AbstractQuestionEntry> questionEntries) {
        this.questionEntries = questionEntries;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public int getQuestionsCount() {
        return questionsCount;
    }

    public void setQuestionsCount(int questionsCount) {
        this.questionsCount = questionsCount;
    }

    public int getTestsCount() {
        return testsCount;
    }

    public void setTestsCount(int testsCount) {
        this.testsCount = testsCount;
    }

    public void addTest(Test test) {
        if (tests == null) {
            tests = new ArrayList<>();
        }
        tests.add(test);
    }

    public void removeTest(Test test) {
        if (test != null) {
            tests.remove(test);
        }
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pathName='" + pathName + '\'' +
                ", hidden='" + hidden + '\'' +
                ", orderId='" + orderId + '\'' +
                ", questionsCount='" + questionsCount + '\'' +
                '}';
    }

    public int compareTo(Category c) {
        if (this.orderId != c.orderId) {
            return this.getOrderId() - c.getOrderId();
        }
        return this.getId() - c.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        if (category.getSubCategories() == null && subCategories != null) {
            return false;
        }
        if (category.getSubCategories() != null && category.getSubCategories().isEmpty()
                && this.getSubCategories() != null && !subCategories.isEmpty()) {
            return false;
        }
        return hidden == category.hidden &&
                orderId == category.orderId &&
                Objects.equals(name, category.name) &&
                Objects.equals(id, category.id) &&
                Objects.equals(pathName, category.pathName) &&
                Objects.equals(parentCategory, category.parentCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pathName, parentCategory, hidden, orderId, questionsCount, testsCount);
    }
}
