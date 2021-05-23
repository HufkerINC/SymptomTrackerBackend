package dk.symptomtracker.symptomtracker_backend.RestControllers;

import dk.symptomtracker.symptomtracker_backend.Model.SymptomRepository;
import dk.symptomtracker.symptomtracker_backend.Presenters.SymptomComponentPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class MainPageController {

    @Autowired
    SymptomRepository symptomRepository;


    @GetMapping("/createAccount")
    public <Principle> String getHomePageData(@RequestParam LocalDate dateRequested, Principle principle){

        SymptomComponentPresenter symptomComponentPresenter = new SymptomComponentPresenter();

        //Get data for SymptomComponent

        return "helloWorld";
    }



}
