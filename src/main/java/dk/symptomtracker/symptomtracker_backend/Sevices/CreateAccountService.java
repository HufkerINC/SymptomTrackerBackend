package dk.symptomtracker.symptomtracker_backend.Sevices;

import dk.symptomtracker.symptomtracker_backend.Model.User;
import dk.symptomtracker.symptomtracker_backend.Model.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

public class CreateAccountService {

    public boolean addAccountToDB(String email, String password, PasswordEncoder passwordEncoder, UserRepository userRepository) {

        String lowerCaseEmail = email.toLowerCase();

        Optional<User> userOptional = userRepository.findByEmail(lowerCaseEmail);

        if (userOptional.isPresent()) {
            return false;
        }

        else {
            // Create new instance of User
            User user = new User();

            user.setEmail(lowerCaseEmail);
            user.setPassword(passwordEncoder.encode(password));
            user.setActivityStrainVisibilityShell(000);

            // Save User object to DB
            userRepository.save(user);

        }

        return true;
    }
}
