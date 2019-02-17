package model.comment;

import model.person.Person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: TanyaM
 * Date: 16.02.2010
 * Time: 13:28:08
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "user_comments")
public class Comment implements Comparable {
    @Column(name = "comment")
    private String comment;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "entered_date")
    private Date createdDate;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Person user;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CommentType commentType;

    @Column(name = "reference_id")
    private int referenceId;

    public int getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(int referenceId) {
        this.referenceId = referenceId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String c) {
        comment = c;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date d) {
        createdDate = d;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person u) {
        user = u;
    }

    public CommentType getCommentType() {
        return commentType;
    }

    public void setCommentType(CommentType type) {
        this.commentType = type;
    }

    public int compareTo(Object c) {
        Comment b = (Comment) c;
        if (this.getCreatedDate().before(b.getCreatedDate()))
            return 1;
        else if (this.getCreatedDate().after(b.getCreatedDate()))
            return -1;
        else
            return b.getId() - this.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return referenceId == comment1.referenceId &&
                Objects.equals(comment, comment1.comment) &&
                Objects.equals(id, comment1.id) &&
                Objects.equals(user, comment1.user) &&
                commentType == comment1.commentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment, id, user, commentType, referenceId);
    }
}
