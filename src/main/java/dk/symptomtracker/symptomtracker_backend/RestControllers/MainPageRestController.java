package dk.symptomtracker.symptomtracker_backend.RestControllers;

import dk.symptomtracker.symptomtracker_backend.Model.*;
import dk.symptomtracker.symptomtracker_backend.Presenters.SymptomComponentPresenter;
import dk.symptomtracker.symptomtracker_backend.Sevices.SymptomRegistrationService;
import dk.symptomtracker.symptomtracker_backend.VTOs.SymptomVTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
public class MainPageRestController {

    @Autowired
    SymptomRepository symptomRepository;

    @Autowired
    SymptomRegistrationRepository symptomRegistrationRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/symptomRegistration")
    public SymptomVTO[] getSymptomComponent(@RequestParam LocalDate dateRequested, Principal principal){

        SymptomComponentPresenter symptomComponentPresenter = new SymptomComponentPresenter();

        //Get data for SymptomComponent
        SymptomVTO[] symptomVTOArray = symptomComponentPresenter.getDataForSymptomComponent(
                                        symptomRepository,
                                        symptomRegistrationRepository,
                                        dateRequested,
                                        principal,
                                        userRepository);
        return symptomVTOArray;
    }

    @PostMapping("/symptomRegistration")
    public void postSymptomComponent(WebRequest dataFromFormSymptomRegistration, Principal principal){

        SymptomRegistrationService symptomRegistrationService = new SymptomRegistrationService();

        // Get data from weRequest and parse data to appropriate datatype for storage in db.
        int symptomId = Integer.parseInt(dataFromFormSymptomRegistration.getParameter("symptomId"));
        int intensity = Integer.parseInt(dataFromFormSymptomRegistration.getParameter("intensity"));
        int regNum = Integer.parseInt(dataFromFormSymptomRegistration.getParameter("regNum"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        LocalDate date = LocalDate.parse(dataFromFormSymptomRegistration.getParameter("date"), formatter);

        // Varify that the symptomId matches a symptom on logged in user.
        boolean symptomVarified = symptomRegistrationService.varifyUserSymptom(symptomId,
                                                                        principal,
                                                                        userRepository,
                                                                        symptomRepository);


        // Save symptom registration to db.
        Optional<SymptomRegistration> systemRegistrationOptional = symptomRegistrationRepository
                .findSymptomRegistrationsBySymptomIdDateRegNum(symptomId, date, regNum);

        // If symptomregistration should be updated
        if(systemRegistrationOptional.isPresent()){

            SymptomRegistration symptomRegistration = systemRegistrationOptional.get();

            symptomRegistrationService.saveSymptomRegistrationInDB(symptomRegistration,
                    symptomRegistrationRepository,
                    symptomId, intensity, regNum, date);
        }

        // If symptom registration should be created and saved
        else{
            SymptomRegistration newSymptomRegistration = new SymptomRegistration();

            symptomRegistrationService.saveSymptomRegistrationInDB(newSymptomRegistration,
                    symptomRegistrationRepository,
                    symptomId, intensity, regNum, date);
        }

    }

}
