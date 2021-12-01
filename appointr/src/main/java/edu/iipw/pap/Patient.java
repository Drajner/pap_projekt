package edu.iipw.pap;

import java.time.LocalDate;

public class Patient extends Person{
    private String description;

    public Patient(String name, String surname, LocalDate dateOfBirth, String description) {
        super(name, surname, dateOfBirth);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", dateOfBirth=" + getDateOfBirth() +
                ", description='" + description + '\'' +
                '}';
    }

}
