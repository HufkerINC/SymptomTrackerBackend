package dk.symptomtracker.symptomtracker_backend.RestControllers;

import dk.symptomtracker.symptomtracker_backend.Model.*;
import dk.symptomtracker.symptomtracker_backend.Presenters.SymptomComponentPresenter;
import dk.symptomtracker.symptomtracker_backend.Sevices.SymptomRegistrationService;
import dk.symptomtracker.symptomtracker_backend.VTOs.SymptomVTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

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
    public SymptomVTO[] getSymptomComponent(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate requestDate, Principal principal){

        SymptomComponentPresenter symptomComponentPresenter = new SymptomComponentPresenter();

        //Get data for SymptomComponent
        SymptomVTO[] symptomVTOArray = symptomComponentPresenter.getDataForSymptomComponent(
                                        symptomRepository,
                                        symptomRegistrationRepository,
                                        requestDate,
                                        principal,
                                        userRepository);
        return symptomVTOArray;
    }



    @PostMapping("/symptomRegistration")
    public void postSymptomComponent(@RequestBody SymptomRegistration symptomRegistrationFromPost, Principal principal){

        SymptomRegistrationService symptomRegistrationService = new SymptomRegistrationService();

        // Varify that the symptomId matches a symptom on logged in user.
        boolean symptomVerified = symptomRegistrationService.verifyUserSymptom(
                                                                        symptomRegistrationFromPost.getSymptomId(),
                                                                        principal,
                                                                        userRepository,
                                                                        symptomRepository);
        if(!symptomVerified){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                                "verification failed due mismatch between symptomId and userId"); // status code: 403
        }

        symptomRegistrationService.saveRegistration(symptomRegistrationFromPost, symptomRegistrationRepository);
    }

}
