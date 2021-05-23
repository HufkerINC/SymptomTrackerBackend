package dk.symptomtracker.symptomtracker_backend.Model;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    String title;

    String note;

    @Column(name="datetime_start")
    LocalDateTime dateTimeStart;

    @Column(name="datetime_end")
    LocalDateTime dateTimeEnd;

    int strain;

    @Column(name="user_id")
    int userId;


    // Getters and setters
    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() { return note; }
    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getDateTimeStart() {
        return dateTimeStart;
    }
    public void setDateTimeStart(LocalDateTime dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    public LocalDateTime getDateTimeEnd() {
        return dateTimeEnd;
    }
    public void setDateTimeEnd(LocalDateTime dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }

    public int getStrain() {
        return strain;
    }
    public void setStrain(int strain) {
        this.strain = strain;
    }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

}
