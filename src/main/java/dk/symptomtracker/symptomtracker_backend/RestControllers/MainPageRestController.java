package dk.symptomtracker.symptomtracker_backend.RestControllers;

import dk.symptomtracker.symptomtracker_backend.Model.SymptomRegistrationRepository;
import dk.symptomtracker.symptomtracker_backend.Model.SymptomRepository;
import dk.symptomtracker.symptomtracker_backend.Model.UserRepository;
import dk.symptomtracker.symptomtracker_backend.Presenters.SymptomComponentPresenter;
import dk.symptomtracker.symptomtracker_backend.VTOs.SymptomVTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/createAccount")
    public SymptomVTO[] getHomePageData(@RequestParam LocalDate dateRequested, Principal principal){

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

}
