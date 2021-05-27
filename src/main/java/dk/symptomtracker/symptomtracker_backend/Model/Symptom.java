package dk.symptomtracker.symptomtracker_backend.Model;

import javax.persistence.*;

@Entity
@Table(name = "symptom")
public class Symptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    @Column(name="default_symptom_id")
    private Integer defaultSymptomId;

    private String color;

    private boolean active;

    @Column(name="visibility_on_statistics")
    private boolean visibilityOnStatistics;

    @Column(name="user_id")
    private int userId;

    @Column(name="num_daily_registration")
    private int numDailyRegistration;



    public Integer getId() { return id; }

    public String getName() { return name;}
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getDefaultSymptomId() { return defaultSymptomId; }
    public void setDefaultSymptomId(Integer defaultSymptomId) { this.defaultSymptomId = defaultSymptomId; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public boolean isVisibilityOnStatistics() { return visibilityOnStatistics; }
    public void setVisibilityOnStatistics(boolean visibilityOnStatistics) {
        this.visibilityOnStatistics = visibilityOnStatistics; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getNumDailyRegistration() { return numDailyRegistration; }
    public void setNumDailyRegistration(int numDailyRegistration)
    { this.numDailyRegistration = numDailyRegistration; }
}
