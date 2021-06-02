package dk.symptomtracker.symptomtracker_backend.RestControllers;

import dk.symptomtracker.symptomtracker_backend.Model.*;
import dk.symptomtracker.symptomtracker_backend.Presenters.SymptomComponentPresenter;
import dk.symptomtracker.symptomtracker_backend.Sevices.SymptomRegistrationService;
import dk.symptomtracker.symptomtracker_backend.VTOs.SymptomVTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDate;

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

        //Verify that regNum on symptomRegistrationFromPost is les or equal to daylyRegistration on symptom.
        boolean regNumVerified = symptomRegistrationService.verifyRegNumInBounds(symptomRegistrationFromPost,
                                                                                    symptomRepository);


        if(!regNumVerified){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "verification failed due illegal number of registration on symptom"); // status code: 400

        }

        // Verify that the symptomId matches a symptom on logged in user.
        boolean symptomVerified = symptomRegistrationService.verifyUserSymptom(
                                                                        symptomRegistrationFromPost.getSymptomId(),
                                                                        principal,
                                                                        userRepository,
                                                                        symptomRepository);


        if(!symptomVerified){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "verification failed due mismatch between symptomId and userId"); // status code: 400
        }

        symptomRegistrationService.saveRegistration(symptomRegistrationFromPost, symptomRegistrationRepository);
    }

}
