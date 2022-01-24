package edu.iipw.pap;

import java.time.LocalDate;

public class DoctorTableRow {
    private String pesel;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private String specialization;

    public DoctorTableRow(Doctor doctor) {
        this.pesel = doctor.getPesel();
        this.name = doctor.getName() + " " + doctor.getSurname();
        this.gender = doctor.getGender().toString();
        this.birthDate = doctor.getDateOfBirth();
        this.specialization = doctor.getSpecialization();
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

}
