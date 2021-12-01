package edu.iipw.pap;

import java.time.LocalDate;
import java.time.LocalDateTime;

/** A class representing a doctor. */
public class Doctor extends Person{
    /** The doctor's specialty. */
    private String specialization;
    /** The doctor's account's login. */
    private String login;
    /** The doctor's account's password. */
    private String password;

    /**
     * Constructor for the Doctor class.
     * @param name The doctor's name.
     * @param surname The doctor's surname.
     * @param dateOfBirth The doctor's date of birth.
     * @param specialization The doctor's specialization.
     * @param login The doctor's account's login.
     * @param password The doctor's account's password.
     */
    public Doctor(String name, String surname, LocalDate dateOfBirth, String specialization, String login, String password) {
        super(name, surname, dateOfBirth);
        this.specialization = specialization;
        this.login = login;
        this.password = password;
    }

    /**
     * Returns the doctor's specialization.
     * @return The doctor's specialization.
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Sets the doctor's specialization.
     * @param specialization The doctor's specialization.
    */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * Returns the doctor's account's login.
     * @return The doctor's account's login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the doctor's account's login.
     * @param login The doctor's account's login.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Returns the doctor's account's password.
     * @return The doctor's account's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the doctor's account's password.
     * @param password The doctor's account's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Create new appointment for the doctor.
     * @param patient The patient for whom the appointment is created.
     * @param timeOfAppointment The time of the appointment.
     * @param address The address of the appointment.
     * @return The created appointment.
     */
    public Appointment createAppointment(Patient patient, LocalDateTime timeOfAppointment, String address) {
        return new Appointment(this, patient, timeOfAppointment, address);
    }

    /**
     * Returns a string representation of the doctor.
     * @return A string representation of the doctor.
     */
    @Override
    public String toString() {
        return "Doctor{" +
                "name=\"" + getName() + '\"' +
                ", surname=\"" + getSurname() + '\"' +
                ", dateOfBirth=" + getDateOfBirth() +
                ", specialization=\"" + specialization + '\"' +
                ", login=\"" + login + '\"' +
                ", password=\"" + password + '\"' +
                '}';
    }

}
