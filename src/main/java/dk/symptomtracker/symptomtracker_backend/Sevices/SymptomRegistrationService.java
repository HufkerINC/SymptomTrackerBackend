package dk.symptomtracker.symptomtracker_backend.Sevices;

import dk.symptomtracker.symptomtracker_backend.Model.*;

import java.security.Principal;
import java.util.Optional;

public class SymptomRegistrationService {

    public boolean verifyUserSymptom(int symptomId,
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

    public boolean verifyRegNumInBounds(SymptomRegistration symptomRegistrationFromPost,
                                        SymptomRepository symptomRepository){

        Symptom symptom = symptomRepository.findById(symptomRegistrationFromPost.getSymptomId()).get();

        if(symptomRegistrationFromPost.getRegNum() <= symptom.getNumDailyRegistration()){

            return true;
        }

        return false;
    }

    public void saveRegistration(SymptomRegistration symptomRegistrationFromPost,
                                 SymptomRegistrationRepository symptomRegistrationRepository){

        // Find out whether symptomRegistrationFromPost should be an update of an existing symptom registration,
        // or whether it should be a new registration in DB.
        Optional<SymptomRegistration> systemRegistrationOptional = symptomRegistrationRepository
                .findSymptomRegistrationsBySymptomIdDateRegNum(symptomRegistrationFromPost.getSymptomId(),
                                                                symptomRegistrationFromPost.getDate(),
                                                                symptomRegistrationFromPost.getRegNum());
        // If symptom registration should be updated
        if(systemRegistrationOptional.isPresent()){

            SymptomRegistration existingSymptomRegistration = systemRegistrationOptional.get();

            existingSymptomRegistration.setSymptomId(symptomRegistrationFromPost.getSymptomId());
            existingSymptomRegistration.setRegNum(symptomRegistrationFromPost.getRegNum());
            existingSymptomRegistration.setDate(symptomRegistrationFromPost.getDate());
            existingSymptomRegistration.setIntensity(symptomRegistrationFromPost.getIntensity());

            symptomRegistrationRepository.save(existingSymptomRegistration);
        }

        // If symptom registration should be created and saved
        else {
            symptomRegistrationRepository.save(symptomRegistrationFromPost);
        }
    }
}
