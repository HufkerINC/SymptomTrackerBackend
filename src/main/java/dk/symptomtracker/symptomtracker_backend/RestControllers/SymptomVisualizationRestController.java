package dk.symptomtracker.symptomtracker_backend.RestControllers;

import dk.symptomtracker.symptomtracker_backend.Model.SymptomRegistrationRepository;
import dk.symptomtracker.symptomtracker_backend.Model.SymptomRepository;
import dk.symptomtracker.symptomtracker_backend.Model.UserRepository;
import dk.symptomtracker.symptomtracker_backend.Presenters.SymptomVisualizationPresenter;
import dk.symptomtracker.symptomtracker_backend.VTOs.SymptomVisualizationVTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDate;


@RestController
public class SymptomVisualizationRestController {

    @Autowired
    SymptomRegistrationRepository symptomRegistrationRepository;

    @Autowired
    SymptomRepository symptomRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/symptomVisualization")
    public SymptomVisualizationVTO[] getSymptomVisualizationComponent(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                         LocalDate requestDateFrom,
                                                         LocalDate requestDateTo,
                                                         Principal principal) {


        SymptomVisualizationPresenter symptomVisualizationPresenter = new SymptomVisualizationPresenter();

        SymptomVisualizationVTO[] symptomVisualizationVTOArray = symptomVisualizationPresenter.getSymptomVisualizationVTOList(
                requestDateFrom,
                requestDateTo,
                symptomRegistrationRepository,
                symptomRepository,
                userRepository,
                principal);


        return symptomVisualizationVTOArray;
    }
}
