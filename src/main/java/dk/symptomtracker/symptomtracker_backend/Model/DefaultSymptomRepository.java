package dk.symptomtracker.symptomtracker_backend.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DefaultSymptomRepository extends JpaRepository<DefaultSymptom, Integer> {


    @Query("select e from DefaultSymptom e order by e.name")
    public List<DefaultSymptom> findAllInSortedList();

}
