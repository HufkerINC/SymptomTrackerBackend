package dk.symptomtracker.symptomtracker_backend.Sevices;

import dk.symptomtracker.symptomtracker_backend.Model.*;
import java.util.List;

public class NewUserSymptomListService {

    private final static String[] COLORS = {"#9E0059", "#AE5E26", "#FF8000", "#FF4000", "#85AD14", "#F3DE2C",
            "#6C0079", "#218380", "#7CB518", "#FFBD00", "#FF4000", "#40DDC8", "#CC33FF", "#9E0059", "#00A0E3",
            "#FC2F00", "#03045E", "#6B0504", "#FFBD00", "#D81159", "#8688E4", "#825217", "#1AA122", "#FF8900",
            "#80D072", "#220802", "#FF0000", "#D704B2"};


    // Create symptomlist for new user

    public void createSymptomListForUser(DefaultSymptomRepository defaultSymptomRepository,
                                         UserRepository userRepository,
                                         SymptomRepository symptomRepository,
                                         String email){

        List<DefaultSymptom> defaultSymptomList = defaultSymptomRepository.findAllInSortedList();

        for(DefaultSymptom desymp : defaultSymptomList){System.out.println(desymp.getName());}

        int colorIndex = 0;

        for(DefaultSymptom defaultSymptom : defaultSymptomList){

            Symptom symptom = new Symptom();

            symptom.setName(defaultSymptom.getName());
            symptom.setDescription(" ");
            symptom.setDefaultSymptomId(defaultSymptom.getId());
            symptom.setNumDailyRegistration(3);
            symptom.setUserId(userRepository.findByEmail(email).get().getId());
            symptom.setVisibilityOnStatistics(false);
            symptom.setActive(true);

            String color = COLORS[colorIndex];
            colorIndex++;
            colorIndex = colorIndex % COLORS.length;

            symptom.setColor(color);

            symptomRepository.save(symptom);
        }
    }
}
