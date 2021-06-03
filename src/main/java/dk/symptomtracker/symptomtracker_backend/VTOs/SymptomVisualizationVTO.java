package dk.symptomtracker.symptomtracker_backend.VTOs;

import dk.symptomtracker.symptomtracker_backend.Model.Symptom;


public class SymptomVisualizationVTO {

    private Symptom symptom;
    private SymptomRegistrationVTO[] symptomRegistrationVTOArray;


    public Symptom getSymptom() { return symptom; }
    public void setSymptom(Symptom symptom) { this.symptom = symptom; }

    public SymptomRegistrationVTO[] getSymptomRegistrationVTOArray() { return symptomRegistrationVTOArray; }
    public void setSymptomRegistrationVTOArray(SymptomRegistrationVTO[] symptomRegistrationVTOArray) {
        this.symptomRegistrationVTOArray = symptomRegistrationVTOArray;
    }

}
