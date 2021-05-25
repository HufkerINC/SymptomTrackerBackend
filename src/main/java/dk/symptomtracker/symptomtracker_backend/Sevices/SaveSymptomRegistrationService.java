package dk.symptomtracker.symptomtracker_backend.Sevices;

import dk.symptomtracker.symptomtracker_backend.Model.SymptomRegistration;
import dk.symptomtracker.symptomtracker_backend.Model.SymptomRegistrationRepository;

import java.time.LocalDate;

public class SaveSymptomRegistrationService {

    public void saveSymptomRegistrationInDB(SymptomRegistration symptomRegistration,
                                       SymptomRegistrationRepository symptomRegistrationRepository,
                                       int symptomId,
                                       int intensity,
                                       int regNum,
                                       LocalDate date){


        symptomRegistration.setIntensity(intensity);
        symptomRegistration.setRegNum(regNum);
        symptomRegistration.setDate(date);
        symptomRegistration.setSymptomId(symptomId);

        symptomRegistrationRepository.save(symptomRegistration);

    }

}
