package dk.symptomtracker.symptomtracker_backend.Model;

import javax.persistence.*;

@Entity
@Table(name = "default_symptom")
public class defaultSymptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;


    public Integer getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
