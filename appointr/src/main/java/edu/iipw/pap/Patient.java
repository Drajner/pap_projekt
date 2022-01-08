package edu.iipw.pap;

import java.time.LocalDate;

/** A class representing a patient. */
public class Patient extends Person{
    /** The patient's reason for an appointment. */
    private String description;

    /**
     * Constructor for the Patient class.
     * @param name The patient's first name.
     * @param surname The patient's surname.
     * @param birthDate The patient's birth date.
     * @param description The patient's reason for an appointment.
     */
    public Patient(String name, String surname, LocalDate dateOfBirth, String description) {
        super(name, surname, dateOfBirth);
        this.description = description;
        System.out.print(name);
    }

    /**
     * Returns the patient's reason for an appointment.
     * @return The doctor's reason for an appointment.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the patient's reason for an appointment.
     * @param description The patient's reason for an appointment.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns a string representation of the patient.
     * @return A string representation of the patient.
     */
    @Override
    public String toString() {
        return getName() + ' ' + getSurname() + "; " + description;
    }

}
