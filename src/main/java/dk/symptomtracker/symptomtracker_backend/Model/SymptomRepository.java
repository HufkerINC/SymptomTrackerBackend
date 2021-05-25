package dk.symptomtracker.symptomtracker_backend.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface SymptomRepository extends JpaRepository<Symptom, Integer> {


    @Query("select e from Symptom e where e.userId = ?1")
    public List<Symptom> getAllSymptomsForUser(int userId);

/*    @Query("select e from Symptom e where e.name = ?1")
    public List<User> findByName(String name);*/

}
