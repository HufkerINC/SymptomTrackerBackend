package dk.symptomtracker.symptomtracker_backend.Model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "symptom_registration")
public class SymptomRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="reg_num")
    private int regNum;

    private LocalDate date;

    private Enum intensity;

    @Column(name="symptom_id")
    private int symptomId;


    public Integer getId() { return id; }

    public int getRegNum() { return regNum; }
    public void setRegNum(int regNum) { this.regNum = regNum; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Enum getIntensity() { return intensity; }
    public void setIntensity(Enum intensity) { this.intensity = intensity; }

    public int getSymptomId() { return symptomId; }
    public void setSymptomId(int symptomId) { this.symptomId = symptomId; }

}
