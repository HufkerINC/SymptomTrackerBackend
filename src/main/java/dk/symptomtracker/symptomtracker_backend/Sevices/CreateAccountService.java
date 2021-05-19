package dk.symptomtracker.symptomtracker_backend.Sevices;

import dk.symptomtracker.symptomtracker_backend.Model.User;
import dk.symptomtracker.symptomtracker_backend.Model.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

public class CreateAccountService {

    public boolean addAccountToDB(String email, String password, PasswordEncoder passwordEncoder, UserRepository userRepository) {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return false;
        }

        else {
            // Create new instance of User
            User user = new User();

            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));

            // Save User object to DB
            userRepository.save(user);

        }

        return true;
    }
}
