package model;


import model.article.Article;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tatyana
 * Date: 29.09.2012
 * Time: 20:21:59
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "Category")
@NamedQueries({
        @NamedQuery(name = "Category.findPathName",
                query = "select c.id,c.pathName from Category c"),
        @NamedQuery(name = "Category.findByPathName",
                query = "select c from Category c  " +
                        "where c.pathName=:p"),
        @NamedQuery(name = "Category.findCategories",
                query = "select c from Test t join t.categories c where t.id=:p"),
        @NamedQuery(name = "Category.findCategoriesByTestPath",
                query = "select c from Test t join t.categories c where t.pathName=:p"),
        @NamedQuery(name = "Category.findDuplicateCategories",
                query = "select c from Category c join fetch c.tests where c.id in " +
                        "(select cc.id from Category cc join cc.tests t group by cc having count(t)>1)")
})
public class Category implements Serializable, Comparable<Category> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    // @Column(unique = true)
    private String pathName;

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
    public Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.EAGER)
    @OrderBy("orderId,id")
    public List<Category> subCategories;

    private boolean hidden = false;
    private int orderId;

    @Column(name = "qcount")
    private int questionsCount;

    @Column(name = "tcount")
    private int testsCount;

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

    public void increaseQuestionsCount() {
        this.questionsCount++;
    }

    public void decreaseQuestionsCount() {
        this.questionsCount--;
    }

    public void increaseTestsCount() {
        this.testsCount++;
    }

    public void decreaseTestsCount() {
        this.testsCount--;
    }

    public int getTestsCount() {
        return testsCount;
    }

    public void setTestsCount(int testsCount) {
        this.testsCount = testsCount;
    }

    public void addTest(Test test) {
        if (tests == null) {
            tests = new ArrayList<Test>();
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

        if (getId() != category.getId()) return false;
        if (!getName().equals(category.getName())) return false;
        return getPathName().equals(category.getPathName());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        return result;
    }
}