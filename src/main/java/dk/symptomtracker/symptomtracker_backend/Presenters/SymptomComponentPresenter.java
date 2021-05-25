package dk.symptomtracker.symptomtracker_backend.Presenters;

import dk.symptomtracker.symptomtracker_backend.Model.Symptom;
import dk.symptomtracker.symptomtracker_backend.Model.SymptomRegistration;
import dk.symptomtracker.symptomtracker_backend.Model.SymptomRegistrationRepository;
import dk.symptomtracker.symptomtracker_backend.Model.SymptomRepository;
import dk.symptomtracker.symptomtracker_backend.VTOs.SymptomRegistrationVTO;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class SymptomComponentPresenter {

    public SymptomRegistrationVTO[] getDataForSymptomComponent(SymptomRepository symptomRepository,
                                           SymptomRegistrationRepository symptomRegistrationRepository,
                                           LocalDate date,
                                           Principal principal){


        List<Symptom> allSymptomsList= symptomRepository.findAll();

        SymptomRegistrationVTO[] symptomRegistrationVTOArray = new SymptomRegistrationVTO[allSymptomsList.size()];


        for(int i = 0; i < allSymptomsList.size(); i++){

            // Create SymptomRegistrationVTO object.
            SymptomRegistrationVTO symptomRegistrationVTO = new SymptomRegistrationVTO();

            // Set the Symptom attribute on VTO object to the symptom represented by i in this iteration of the loop
            symptomRegistrationVTO.setSymptom(allSymptomsList.get(i));

            // make an array with size equal to number of daily registrations on symptom.
            int[] intensityArray = new int[allSymptomsList.get(i).getNumDailyRegistration()];

            // Get all registrations on symptom
            List<SymptomRegistration> symptomRegistrationList =
                    symptomRegistrationRepository.findSymptomRegistrationsBySymptomIdAndDate
                            (allSymptomsList.get(i).getId(), date);

            if(intensityArray.length == symptomRegistrationList.size()) {

                // Sort symptomRegistration obj in symptomRegistrationArray. Sort symptomRegistration obj by their regNum number.
                // iterate through list of registrations on symptom. Set each symptomRegistration, in the list to be stored in
                // the index of symptomRegistrationArray as follows:
                // regnum = 1 should be in index 0, regNum = 2 should be in index 1, regNum = 3 should be in index 2, of symptomRegistrationArray
                for (int j = 0; j < symptomRegistrationList.size(); j++) {

                    int index = symptomRegistrationList.get(j).getRegNum() - 1;
                    int intensity = symptomRegistrationList.get(j).getIntensity();

                    intensityArray[index] = intensity;
                }
            }

            else{

                int[] regNumArray = new int[intensityArray.length];

                for(int j = 0; j < symptomRegistrationList.size(); j++) {

                    int regNum = symptomRegistrationList.get(j).getRegNum();
                    regNumArray[regNum - 1] = regNum;
                }

                switch(symptomRegistrationList.size()) {

                    case 0: // in case there are no registrations on symptom

                        if(intensityArray.length == 3){ // in case where 3 registrations are expected on symptom
                            intensityArray[0] = 0;
                            intensityArray[1] = 0;
                            intensityArray[2] = 0;
                        }

                        if(intensityArray.length == 2){ // in case where 2 registrations are expected on symptom
                            intensityArray[0] = 0;
                            intensityArray[1] = 0;
                        }

                        if(intensityArray.length == 1){ // in case where 1 registrations are expected on symptom
                            intensityArray[0] = 0;
                        }
                    break;

                    case 1: // in case there are 1 registrations on symptom

                        if (intensityArray.length == 3) { // in case where 3 registrations are expected on symptom

                            if(symptomRegistrationList.get(0).getRegNum() == 1){
                                intensityArray[0] = symptomRegistrationList.get(0).getIntensity();
                                intensityArray[1] = 0;
                                intensityArray[2] = 0;

                            }

                            if(symptomRegistrationList.get(0).getRegNum() == 2) {
                                intensityArray[0] = 0;
                                intensityArray[1] = symptomRegistrationList.get(0).getIntensity();
                                intensityArray[2] = 0;
                            }

                            if(symptomRegistrationList.get(0).getRegNum() == 3) {
                                intensityArray[0] = 0;
                                intensityArray[1] = 0;
                                intensityArray[2] = symptomRegistrationList.get(0).getIntensity();
                            }

                        }

                        if (intensityArray.length == 2){ // in case where 2 registrations are expected on symptom

                            if(symptomRegistrationList.get(0).getRegNum() == 1) {
                                intensityArray[0] = symptomRegistrationList.get(0).getIntensity();
                                intensityArray[1] = 0;
                            }

                            if(symptomRegistrationList.get(0).getRegNum() == 2) {
                                intensityArray[0] = 0;
                                intensityArray[1] = symptomRegistrationList.get(0).getIntensity();
                            }
                        }
                    break;

                    case 2: // in case there are 2 registrations on symptom,
                            // and 3 registrations where expected.


                        if(symptomRegistrationList.get(0).getRegNum() == 2 && symptomRegistrationList.get(1).getRegNum() == 1){
                            intensityArray[0] = symptomRegistrationList.get(1).getIntensity(); // Set intencity for regNum = 1
                            intensityArray[1] = symptomRegistrationList.get(0).getIntensity(); // Set intencity for regNum = 2
                            intensityArray[2] = 0; // set intencity for regNum = 3
                        }

                        if(symptomRegistrationList.get(0).getRegNum() == 2 && symptomRegistrationList.get(1).getRegNum() == 3){
                            intensityArray[0] = 0; // set intencity for regNum = 1
                            intensityArray[1] = symptomRegistrationList.get(0).getIntensity(); // set intencity for regNum = 2
                            intensityArray[2] = symptomRegistrationList.get(1).getIntensity(); // set intencity for regNum = 3
                        }

                        if(symptomRegistrationList.get(0).getRegNum() == 3 && symptomRegistrationList.get(1).getRegNum() == 1){
                            intensityArray[0] = symptomRegistrationList.get(1).getIntensity(); // set intencity for regNum = 1
                            intensityArray[1] = 0; // set intencity for regNum = 2
                            intensityArray[2] = symptomRegistrationList.get(0).getIntensity(); // set intencity for regNum = 3
                        }

                    break;

                    default:

                    break;

                }


            }

            symptomRegistrationVTO.setIntensityArray(intensityArray);
            symptomRegistrationVTOArray[i] = symptomRegistrationVTO;
        }

        return symptomRegistrationVTOArray;
    }

}





