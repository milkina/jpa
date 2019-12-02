package model.article;

import model.Category;
import model.Language;
import model.comment.Comment;
import model.person.Person;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Tatyana on 05.05.2016.
 */
@Entity
@Table(name = "article")
public class Article {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "reference_id")
    private List<Comment> comments;

    @OneToOne
    private Person author;

    @OneToOne(mappedBy = "article")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "language")
    private Language language;

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    public String getFormattedDate() {
        if (createdDate == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        return simpleDateFormat.format(createdDate);
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
        return hidden == article.hidden &&
                indexStatus == article.indexStatus &&
                Objects.equals(id, article.id) &&
                Objects.equals(url, article.url) &&
                Objects.equals(text, article.text) &&
                Objects.equals(image, article.image) &&
                Objects.equals(keywords, article.keywords) &&
                Objects.equals(description, article.description) &&
                Objects.equals(title, article.title) &&
                Objects.equals(author, article.author) &&
                Objects.equals(category, article.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, text, createdDate, image, keywords, description, title, hidden, indexStatus, comments, author, category);
    }
}
