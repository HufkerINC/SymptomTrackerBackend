package dk.symptomtracker.symptomtracker_backend.Model;

import javax.persistence.*;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String name;
    private String password;

    @Column(name="red_strain_visibility")
    private boolean redStrainVisibility;

    @Column(name="yellow_strain_visibility")
    private boolean yellowStrainVisibility;

    @Column(name="green_strain_visibility")
    private boolean greenStrainVisibility;


    // Getters and setters
    public Integer getId() { return id; }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isRedStrainVisibility() { return redStrainVisibility; }
    public void setRedStrainVisibility(boolean redStrainVisibility) { this.redStrainVisibility = redStrainVisibility; }

    public boolean isYellowStrainVisibility() { return yellowStrainVisibility; }
    public void setYellowStrainVisibility(boolean yellowStrainVisibility) { this.yellowStrainVisibility = yellowStrainVisibility; }

    public boolean isGreenStrainVisibility() { return greenStrainVisibility; }
    public void setGreenStrainVisibility(boolean greenStrainVisibility) { this.greenStrainVisibility = greenStrainVisibility; }
}
