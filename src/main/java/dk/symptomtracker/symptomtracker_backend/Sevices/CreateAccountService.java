package dk.symptomtracker.symptomtracker_backend.Sevices;

import dk.symptomtracker.symptomtracker_backend.Model.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;

public class CreateAccountService {

    public boolean addAccountToDB(String email,
                                  String password,
                                  PasswordEncoder passwordEncoder,
                                  UserRepository userRepository,
                                  DefaultSymptomRepository defaultSymptomRepository,
                                  SymptomRepository symptomRepository) {

        // Always save email to db in lowercase letters.
        String lowerCaseEmail = email.toLowerCase();

        // Find out whether email is available.
        Optional<User> userOptional = userRepository.findByEmail(lowerCaseEmail);

        // if not
        if (userOptional.isPresent()) {
            return false;
        }

        // Create new instance of User
        User user = new User();

        user.setEmail(lowerCaseEmail);
        user.setPassword(passwordEncoder.encode(password));
        user.setRedStrainVisibility(false);
        user.setYellowStrainVisibility(false);
        user.setGreenStrainVisibility(false);

        // Save User object to DB
        userRepository.save(user);

        // Create default list of symptoms for new user
        NewUserSymptomListService newUserSymptomListService = new NewUserSymptomListService();

        newUserSymptomListService.createSymptomListForUser(defaultSymptomRepository,
                                                    userRepository,
                                                    symptomRepository,
                                                    lowerCaseEmail);

        return true;

    }
}
