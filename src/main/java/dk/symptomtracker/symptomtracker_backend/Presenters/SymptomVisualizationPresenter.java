package dk.symptomtracker.symptomtracker_backend.Presenters;


import dk.symptomtracker.symptomtracker_backend.Model.*;
import dk.symptomtracker.symptomtracker_backend.Sevices.DateToDateTimeService;
import dk.symptomtracker.symptomtracker_backend.VTOs.SymptomRegistrationVTO;
import dk.symptomtracker.symptomtracker_backend.VTOs.SymptomVisualizationVTO;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

        // Get array of SymptomVisualizationVTO obj. One for each symptom.
        SymptomVisualizationVTO[] symptomVisualizationVTOArray = new SymptomVisualizationVTO[userSymptomList.size()];

        for(int i = 0; i < userSymptomList.size(); i++){

            List<SymptomRegistration> orderedRegistrationList =
                    symptomRegistrationRepository.getAllRegistrationsBySymptomIdOrderedByDateAndRegNum(userSymptomList.get(i).getId(),dateFrom, dateTo);

            SymptomVisualizationVTO symptomVisualizationVTO = new SymptomVisualizationVTO();
            symptomVisualizationVTO.setSymptom(userSymptomList.get(i));

            SymptomRegistrationVTO[] symptomRegistrationVTOArray = new SymptomRegistrationVTO[orderedRegistrationList.size()];

            for(int j = 0; j < orderedRegistrationList.size(); j++){

                SymptomRegistrationVTO symptomRegistrationVTO = new SymptomRegistrationVTO();

                symptomRegistrationVTO.setIntensity(orderedRegistrationList.get(j).getIntensity());

                DateToDateTimeService dateToDateTimeService = new DateToDateTimeService();

                String[][] timeOptions2DimensionalArray = {
                                        new String[]{"12:00"},
                                        new String[]{"06:00", "18:00"},
                                        new String[]{"04:00", "12:00", "20:00"}};

                String stringTime = timeOptions2DimensionalArray
                                            [userSymptomList.get(i).getNumDailyRegistration()]
                                            [orderedRegistrationList.get(j).getRegNum()];

                LocalDateTime dateTime = dateToDateTimeService.getDateTime(orderedRegistrationList.get(j).getDate(), stringTime);

                symptomRegistrationVTO.setDateTime(dateTime);

                symptomRegistrationVTOArray[j] = symptomRegistrationVTO;


            }

            symptomVisualizationVTOArray[i].setSymptomRegistrationVTOArray(symptomRegistrationVTOArray);
        }

        return symptomVisualizationVTOArray;
    }

}
