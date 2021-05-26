package dk.symptomtracker.symptomtracker_backend.Sevices;

import dk.symptomtracker.symptomtracker_backend.Model.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;

public class SymptomRegistrationService {

    public boolean varifyUserSymptom(int symptomId,
                                     Principal principal,
                                     UserRepository userRepository,
                                     SymptomRepository symptomRepository){

        // Get logged i user
        User loggedInUser = userRepository.findByEmail(principal.getName()).get();

        // Get Optional containing data from search in DB - Does the given symptom belong to logged in  user?
        Optional optionalSymptom = symptomRepository.findByUserIdAndSymptomId(symptomId, loggedInUser.getId());

        if(optionalSymptom.isPresent()){
            return true;
        }
        else {
            return false;
        }
    }


    public void saveSymptomRegistrationInDB(SymptomRegistration symptomRegistration,
                                       SymptomRegistrationRepository symptomRegistrationRepository,
                                       int symptomId,
                                       int intensity,
                                       int regNum,
                                       LocalDate date){

        // set attributes on symptomregistration obj.
        symptomRegistration.setIntensity(intensity);
        symptomRegistration.setRegNum(regNum);
        symptomRegistration.setDate(date);
        symptomRegistration.setSymptomId(symptomId);

        symptomRegistrationRepository.save(symptomRegistration);

    }

}
