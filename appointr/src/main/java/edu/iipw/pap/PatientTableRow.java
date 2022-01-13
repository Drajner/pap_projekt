package edu.iipw.pap;

import java.time.LocalDate;

public class PatientTableRow {
    private String pesel;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private String description;

    public PatientTableRow(Patient patient) {
        this.pesel = patient.getPesel();
        this.name = patient.getName() + " " + patient.getSurname();
        this.gender = "?";
        this.birthDate = patient.getDateOfBirth();
        this.description = patient.getDescription();
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
