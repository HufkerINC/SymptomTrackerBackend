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
    private String activity_strain_visibility_shell;


    // Getters and setters
    public Integer getId() {
        return id;
    }

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

    public String getActivity_strain_visibility_shell() { return activity_strain_visibility_shell; }
    public void setActivity_strain_visibility_shell(String activity_strain_visibility_shell) {
        this.activity_strain_visibility_shell = activity_strain_visibility_shell;
    }
}
