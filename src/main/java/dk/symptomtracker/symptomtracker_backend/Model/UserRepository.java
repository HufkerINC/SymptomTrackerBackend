package dk.symptomtracker.symptomtracker_backend.Model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<Activity, Integer> {

    public Optional<User> findByEmail(String email);

}
