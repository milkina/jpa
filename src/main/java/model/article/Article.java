package model.article;

import model.Category;
import model.comment.Comment;
import model.person.Person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * Created by Tatyana on 05.05.2016.
 */
@Entity
@Table(name = "article")
@NamedQueries({
        @NamedQuery(name = "Article.getAllArticles", query = "SELECT a from Article a where a.hidden=false and " +
                "a.url is not null and a.title is not null order by a.createdDate desc"),
        @NamedQuery(name = "Article.getPersonArticles", query = "SELECT a from Article a where a.hidden=false and " +
                "a.url is not null and a.title is not null AND a.author=:param order by a.createdDate desc"),
        @NamedQuery(name = "Article.getArticleByUrl", query = "SELECT a from Article a where a.url=:param"),
        @NamedQuery(name = "Article.getCategory", query = "SELECT c from Category c where c.article.id=:article_id")})
public class Article {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String url;

    @Lob
    private String text;

    private Date createdDate;

    private String image;

    private String keywords;

    private String description;

    private String title;

    private boolean hidden = false;
    private boolean indexStatus = true;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "reference_id")
    private List<Comment> comments;

    @OneToOne
    private Person author;

    @OneToOne(mappedBy = "article")
    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isIndexStatus() {
        return indexStatus;
    }

    public void setIndexStatus(boolean indexStatus) {
        this.indexStatus = indexStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (getUrl() != null ? !getUrl().equals(article.getUrl()) : article.getUrl() != null) return false;
        if (getText() != null ? !getText().equals(article.getText()) : article.getText() != null) return false;
        if (getCreatedDate() != null ? !getCreatedDate().equals(article.getCreatedDate()) : article.getCreatedDate() != null)
            return false;
        if (getImage() != null ? !getImage().equals(article.getImage()) : article.getImage() != null) return false;
        if (getKeywords() != null ? !getKeywords().equals(article.getKeywords()) : article.getKeywords() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(article.getDescription()) : article.getDescription() != null)
            return false;
        if (getTitle() != null ? !getTitle().equals(article.getTitle()) : article.getTitle() != null) return false;
        if (getAuthor() != null ? !getAuthor().equals(article.getAuthor()) : article.getAuthor() != null) return false;
        return !(getCategory() != null ? !getCategory().equals(article.getCategory()) : article.getCategory() != null);
    }

    @Override
    public int hashCode() {
        int result = getUrl() != null ? getUrl().hashCode() : 0;
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        result = 31 * result + (getCreatedDate() != null ? getCreatedDate().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        result = 31 * result + (getKeywords() != null ? getKeywords().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        return result;
    }
}
