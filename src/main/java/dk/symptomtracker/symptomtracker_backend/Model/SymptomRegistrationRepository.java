package dk.symptomtracker.symptomtracker_backend.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SymptomRegistrationRepository extends JpaRepository<SymptomRegistration, Integer> {

    @Query("select e from SymptomRegistration e where e.symptomId = ?1 and e.date =?2")
    public List<SymptomRegistration> findSymptomRegistrationsBySymptomIdAndDate(int symptomId, LocalDate date);

    @Query("select e from SymptomRegistration e where e.symptomId = ?1 and e.date =?2 and e.regNum =?3")
    public Optional<SymptomRegistration> findSymptomRegistrationsBySymptomIdDateRegNum(int symptomId,
                                                                                    LocalDate date,
                                                                                    int regNum);

    @Query("select e from SymptomRegistration e where e.symptomId = ?1 and e.date >= ?2 and e.date <= ?3 order by e.date, e.regNum")
    public List<SymptomRegistration> getAllRegistrationsBySymptomIdOrderedByDateAndRegNum(int symptomId, LocalDate date1, LocalDate date2);

}



