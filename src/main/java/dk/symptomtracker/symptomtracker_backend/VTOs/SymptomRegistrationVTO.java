package dk.symptomtracker.symptomtracker_backend.VTOs;

import java.time.LocalDateTime;


public class SymptomRegistrationVTO {

    LocalDateTime dateTime;
    int intensity;


    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public int getIntensity() { return intensity; }
    public void setIntensity(int intensity) { this.intensity = intensity; }
}
