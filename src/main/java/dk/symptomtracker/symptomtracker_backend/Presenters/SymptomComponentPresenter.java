package dk.symptomtracker.symptomtracker_backend.Presenters;

import dk.symptomtracker.symptomtracker_backend.Model.*;
import dk.symptomtracker.symptomtracker_backend.VTOs.SymptomVTO;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

public class SymptomComponentPresenter {

    public SymptomVTO[] getDataForSymptomComponent(SymptomRepository symptomRepository,
                                                   SymptomRegistrationRepository symptomRegistrationRepository,
                                                   LocalDate date,
                                                   Principal principal, UserRepository userRepository){

        // Get logged i user
        User loggedInUser = userRepository.findByEmail(principal.getName()).get();

        // get list of all symptoms for logged in user
        List<Symptom> symptomList = symptomRepository.getAllSymptomsForUser(loggedInUser.getId());

        // Create Array of SymptomVTO's - this is the return value.
        SymptomVTO[] symptomVTOArray = new SymptomVTO[symptomList.size()];

        //  Make one SymptomVTO for every symptom for logged in user. Put each SymptomVTO in SymptomVTOArray
        for(int i = 0; i < symptomList.size(); i++){

            // Create SymptomVTO object.
            SymptomVTO symptomVTO = new SymptomVTO();

            // Set the Symptom attribute on VTO object to the symptom represented in this iteration of the loop
            symptomVTO.setSymptom(symptomList.get(i));

            // Make an array with size equal to number of daily registrations expected on symptom.
            // NB. every index in the array will by default contain an int set to 0.
            int[] intensityArray = new int[symptomList.get(i).getNumDailyRegistration()];

            // Get all registrations on symptom
            List<SymptomRegistration> symptomRegistrationList =
                    symptomRegistrationRepository.findSymptomRegistrationsBySymptomIdAndDate
                            (symptomList.get(i).getId(), date);


            // Sort symptomRegistration objs in symptomRegistrationList by their regNum value.
            // Set each index in the intensityArray to the symptomRegistration with the right regNum value.
            // As follows:
            // symptomRegistation object with regNum = 1 should be stores in index 0 of intensityArray,
            // regNum = 2 should be in index 1, regNum = 3 should be in index 2.
            for (int j = 0; j < symptomRegistrationList.size(); j++) {

                int index = symptomRegistrationList.get(j).getRegNum() - 1;
                int intensity = symptomRegistrationList.get(j).getIntensity();

                intensityArray[index] = intensity;
            }

            symptomVTO.setIntensityArray(intensityArray);
            symptomVTOArray[i] = symptomVTO;
        }

        return symptomVTOArray;
    }
}





