package dk.symptomtracker.symptomtracker_backend.VTOs;

import dk.symptomtracker.symptomtracker_backend.Model.Symptom;
import dk.symptomtracker.symptomtracker_backend.Model.SymptomRegistration;


public class SymptomRegistrationVTO {

    private Symptom symptom;
    private int[] intensityArray;


    public Symptom getSymptom() { return symptom; }
    public void setSymptom(Symptom symptom) { this.symptom = symptom; }

    public int[] getIntensityArray() { return intensityArray; }
    public void setIntensityArray(int[] intensityArray) { this.intensityArray = intensityArray; }
}
