package dk.symptomtracker.symptomtracker_backend.RestControllers;

import dk.symptomtracker.symptomtracker_backend.Model.*;
import dk.symptomtracker.symptomtracker_backend.Presenters.SymptomVisualizationPresenter;
import dk.symptomtracker.symptomtracker_backend.VTOs.SymptomVisualizationVTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

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
    public SymptomVisualizationVTO[] getSymptomVisualizationComponent(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate requestDateFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate requestDateTo,
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

    @PostMapping("/symptomVisualizationControl")
    public void postsymptomVisualizationControllRequest(@RequestBody Symptom symptom, Principal principal){

        // Find symptom in DB and set att. visibilityOnStatistics. Update this symptom in DB.
        // This is done to insure that users are not able to get manipulated date into database.
        Symptom controlledSymptom = symptomRepository.findById(symptom.getId()).get();

        controlledSymptom.setVisibilityOnStatistics(symptom.getVisibilityOnStatistics());

        symptomRepository.save(controlledSymptom);

    }

}

