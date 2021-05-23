package dk.symptomtracker.symptomtracker_backend.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;

public interface SymptomRegistrationRepository extends JpaRepository<SymptomRegistration, Integer> {
/*
    @Query("select e from Event e where e.startDateTime = ?1 and e.activityId =?2")
    public List<SymptomRegistration> findEventsAfter(LocalDateTime dateTime, int activityId);*/
}
