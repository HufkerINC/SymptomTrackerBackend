package dk.symptomtracker.symptomtracker_backend.RestControllers;

import dk.symptomtracker.symptomtracker_backend.Model.SymptomRegistration;
import dk.symptomtracker.symptomtracker_backend.Model.SymptomRegistrationRepository;
import dk.symptomtracker.symptomtracker_backend.Model.SymptomRepository;
import dk.symptomtracker.symptomtracker_backend.Model.UserRepository;
import dk.symptomtracker.symptomtracker_backend.Presenters.SymptomComponentPresenter;
import dk.symptomtracker.symptomtracker_backend.Sevices.SaveSymptomRegistrationService;
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
    public void postSymptomComponent(WebRequest dataFromFormSymptomRegistration){

        // Get data from weRequest and parse data to appropriate datatype for storage in db.
        int symptomId = Integer.parseInt(dataFromFormSymptomRegistration.getParameter("symptomId"));
        int intensity = Integer.parseInt(dataFromFormSymptomRegistration.getParameter("intensity"));
        int regNum = Integer.parseInt(dataFromFormSymptomRegistration.getParameter("regNum"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        LocalDate date = LocalDate.parse(dataFromFormSymptomRegistration.getParameter("date"), formatter);

        SaveSymptomRegistrationService saveSymptomRegistrationService = new SaveSymptomRegistrationService();

        Optional<SymptomRegistration> systemRegistrationOptional = symptomRegistrationRepository
                .findSymptomRegistrationsBySymptomIdDateRegNum(symptomId, date, regNum);

        if(systemRegistrationOptional.isPresent()){

            SymptomRegistration symptomRegistration = systemRegistrationOptional.get();

            saveSymptomRegistrationService.saveSymptomRegistrationInDB(symptomRegistration,
                    symptomRegistrationRepository,
                    symptomId, intensity, regNum, date);
        }

        else{
            SymptomRegistration newSymptomRegistration = new SymptomRegistration();

            saveSymptomRegistrationService.saveSymptomRegistrationInDB(newSymptomRegistration,
                    symptomRegistrationRepository,
                    symptomId, intensity, regNum, date);
        }

    }

}
