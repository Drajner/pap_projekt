package edu.iipw.pap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/** A class representing a doctor. */
public class Doctor extends Person{
    /** The doctor's specialty. */
    private String specialization;
    /** The doctor's account's login. */
    private String login;
    /** The doctor's account's password. */
    private String password;
    /** The doctor's appointments. */
    private ArrayList<Appointment> appointments;

    /**
     * Constructor for the Doctor class.
     * @param name The doctor's name.
     * @param surname The doctor's surname.
     * @param dateOfBirth The doctor's date of birth.
     * @param specialization The doctor's specialization.
     * @param login The doctor's account's login.
     * @param password The doctor's account's password.
     * @param appointments The doctor's appointments.
     */
    public Doctor(String name, String surname, LocalDate dateOfBirth, String specialization,
                  String login, String password, ArrayList<Appointment> appointments) {
        super(name, surname, dateOfBirth);
        this.specialization = specialization;
        this.login = login;
        this.password = password;
        this.appointments = appointments;

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
     * Returns the doctor's appointments.
     * @return The doctor's appointments.
     */
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }


    /**
     * Sets the doctor's appointments.
     * @param appointments The doctor's appointments.
     */
    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     * Adds an appointment to doctor's appointments.
     * @param appointment The appointment to be added.
     * @return True if the appointment was added, false otherwise.
     */
    public boolean addAppointment(Appointment appointment) {
        if (appointments.contains(appointment)) {
            System.out.println("The appointment is already in the doctor's appointments.");
            return false;
        } else {
            for (Appointment a : appointments) {
                if (a.getTimeOfAppointment().isEqual(appointment.getTimeOfAppointment())) {
                    System.out.println("The doctor already has an appointment at that time.");
                    return false;
                }
            }
            return appointments.add(appointment);
        }
    }

    /**
     * Returns the doctor's appointments for a given date.
     * @param date The date of the appointments.
     * @return The doctor's appointments for a given date.
     */
    public ArrayList<Appointment> getAppointmentsGivenDate(LocalDateTime date) {
        ArrayList<Appointment> appointments = new ArrayList<>();
        for (Appointment appointment : this.appointments) {
            if (appointment.getTimeOfAppointment().getYear() == date.getYear()) {
                if (appointment.getTimeOfAppointment().getDayOfYear() == date.getDayOfYear()) {
                    appointments.add(appointment);
                }
            }
        }
        return appointments;
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
        return getName() + ' ' + getSurname() + "; " + specialization;
    }

}
