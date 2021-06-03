package dk.symptomtracker.symptomtracker_backend.Presenters;


import dk.symptomtracker.symptomtracker_backend.Model.*;
import dk.symptomtracker.symptomtracker_backend.Sevices.DateToDateTimeService;
import dk.symptomtracker.symptomtracker_backend.VTOs.SymptomRegistrationVTO;
import dk.symptomtracker.symptomtracker_backend.VTOs.SymptomVisualizationVTO;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SymptomVisualizationPresenter {


    public SymptomVisualizationVTO[] getSymptomVisualizationVTOList(
                                                LocalDate dateFrom,
                                                LocalDate dateTo,
                                                SymptomRegistrationRepository symptomRegistrationRepository,
                                                SymptomRepository symptomRepository,
                                                UserRepository userRepository,
                                                Principal principal){


        // Get logged i user
        User loggedInUser = userRepository.findByEmail(principal.getName()).get();

        // Get all symptoms for logged in user
        List<Symptom> userSymptomList = symptomRepository.getAllSymptomsForUser(loggedInUser.getId());

        // Get List of SymptomVisualizationVTO obj. One for each symptom.
        List<SymptomVisualizationVTO> symptomVisualizationVTOList = new ArrayList<SymptomVisualizationVTO>();

        for(Symptom symptom: userSymptomList){

            List<SymptomRegistration> orderedRegistrationList =
                    symptomRegistrationRepository.getAllRegistrationsBySymptomIdOrderedByDateAndRegNum(symptom.getId(),dateFrom, dateTo);

            SymptomVisualizationVTO symptomVisualizationVTO = new SymptomVisualizationVTO();
            symptomVisualizationVTO.setSymptom(symptom);

            List<SymptomRegistrationVTO> symptomRegistrationVTOList = new ArrayList<SymptomRegistrationVTO>();

            for(SymptomRegistration registration: orderedRegistrationList){

                SymptomRegistrationVTO symptomRegistrationVTO = new SymptomRegistrationVTO();

                symptomRegistrationVTO.setIntensity(registration.getIntensity());

                DateToDateTimeService dateToDateTimeService = new DateToDateTimeService();

                String[][] timeOptions2DimensionalArray = {
                                        new String[]{"12:00"},
                                        new String[]{"06:00", "18:00"},
                                        new String[]{"04:00", "12:00", "20:00"}};

                String stringTime = timeOptions2DimensionalArray
                                            [symptom.getNumDailyRegistration()-1]
                                            [registration.getRegNum()-1];

                LocalDateTime dateTime = dateToDateTimeService.getDateTime(registration.getDate(), stringTime);

                symptomRegistrationVTO.setDateTime(dateTime);

                symptomRegistrationVTOList.add(symptomRegistrationVTO);

            }

            // Convert from List to array
            SymptomRegistrationVTO[] symptomRegistrationVTOArray = symptomRegistrationVTOList.toArray(new SymptomRegistrationVTO[symptomRegistrationVTOList.size()]);

            // set SymptomRegistrationVTOArray attribute on symptomVisualizationVTO
            symptomVisualizationVTO.setSymptomRegistrationVTOArray(symptomRegistrationVTOArray);

            // add symptomVisualizationVTO to symptomVisualizationVTOList
            symptomVisualizationVTOList.add(symptomVisualizationVTO);
        }

        // Convert symptomVisualizationVTOList to an Array before returning.
        SymptomVisualizationVTO[] symptomVisualizationVTOArray = symptomVisualizationVTOList.toArray(new SymptomVisualizationVTO[symptomVisualizationVTOList.size()]);

        return symptomVisualizationVTOArray;
    }

}
