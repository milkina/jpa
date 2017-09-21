package main.java.model.comment;


import main.java.model.person.Person;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: TanyaM
 * Date: 16.02.2010
 * Time: 13:28:08
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "user_comments")
@NamedQueries({
        @NamedQuery(name = "Comment.getComments",
                query = "SELECT c FROM Comment c WHERE c.type=:typeParam AND c.referenceId=:referenceParam order by c.date"),
        @NamedQuery(name = "Comment.getLastComments",
                query = "SELECT c FROM Comment c order by c.date desc"),
        @NamedQuery(name="Comment.updateAuthor",
                query = "UPDATE Comment c SET c.user=NULL WHERE c.user.ID =:id")
}
)
public class Comment implements Comparable {
    @Column(name = "comment")
    private String comment;

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "entered_date")
    private Date date;


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "ID")
    private Person user;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CommentType type;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date d) {
        date = d;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person u) {
        user = u;
    }

    public CommentType getType() {
        return type;
    }

    public void setType(CommentType type) {
        this.type = type;
    }

    public int compareTo(Object c) {
        Comment b = (Comment) c;
        if (this.getDate().before(b.getDate()))
            return 1;
        else if (this.getDate().after(b.getDate()))
            return -1;
        else
            return b.getId() - this.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        Comment comment1 = (Comment) o;

        if (comment != null ? !comment.equals(comment1.comment) : comment1.comment != null) return false;
        if (date != null ? !date.equals(comment1.date) : comment1.date != null) return false;
        if (user != null ? !user.equals(comment1.user) : comment1.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = comment != null ? comment.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
