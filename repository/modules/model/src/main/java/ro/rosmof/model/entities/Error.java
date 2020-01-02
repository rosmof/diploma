package ro.rosmof.model.entities;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "Error")
public class Error {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Calendar date;

    @Lob
    @Column(name = "errorMessage", length = 512)
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
