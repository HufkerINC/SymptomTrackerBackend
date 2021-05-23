package dk.symptomtracker.symptomtracker_backend.VTOs;

import dk.symptomtracker.symptomtracker_backend.Model.Symptom;


public class SymptomRegistrationVTO {

    private Symptom symptom;

    private int symptomReg1;

    private int symptomReg2;

    private int symptomReg3;



    public Symptom getSymptom() { return symptom; }
    public void setSymptom(Symptom symptom) { this.symptom = symptom; }

    public int getSymptomReg1() { return symptomReg1; }
    public void setSymptomReg1(int symptomReg1) { this.symptomReg1 = symptomReg1; }

    public int getSymptomReg2() { return symptomReg2; }
    public void setSymptomReg2(int symptomReg2) { this.symptomReg2 = symptomReg2; }

    public int getSymptomReg3() { return symptomReg3; }
    public void setSymptomReg3(int symptomReg3) { this.symptomReg3 = symptomReg3; }
}
