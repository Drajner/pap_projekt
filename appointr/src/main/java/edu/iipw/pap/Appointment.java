package edu.iipw.pap;

import java.time.LocalDateTime;

/** A class representing an appointment. */
public class Appointment {
    /** The appointment's doctor. */
    private Doctor doctor;
    /** The appointment's patient. */
    private Patient patient;
    /** The appointment's date and time. */
    private LocalDateTime timeOfAppointment;
    /** The appointment's address. */
    private String address;

    /**
     * Constructor for the Appointment class.
     * @param doctor The appointment's doctor.
     * @param patient The appointment's patient.
     * @param timeOfAppointment The appointment's date and time.
     * @param address The appointment's address.
     */
    public Appointment(Doctor doctor, Patient patient, LocalDateTime timeOfAppointment, String address) {
        this.doctor = doctor;
        this.patient = patient;
        this.timeOfAppointment = timeOfAppointment;
        this.address = address;
    }

    /**
     * Returns the appointment's doctor.
     * @return The appointment's doctor.
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Returns the appointment's patient.
     * @return The appointment's patient.
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Returns the appointment's date and time.
     * @return The appointment's date and time.
     */
    public LocalDateTime getTimeOfAppointment() {
        return timeOfAppointment;
    }

    /**
     * Returns the appointment's address.
     * @return The appointment's address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the appointment's doctor.
     * @param doctor The appointment's doctor.
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Sets the appointment's patient.
     * @param patient The appointment's patient.
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Sets the appointment's date and time.
     * @param timeOfAppointment The appointment's date and time.
     */
    public void setTimeOfAppointment(LocalDateTime timeOfAppointment) {
        this.timeOfAppointment = timeOfAppointment;
    }

    /**
     * Sets the appointment's address.
     * @param address The appointment's address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns a string representation of the appointment.
     * @return A string representation of the appointment.
     */
    @Override
    public String toString() {
        return "Appointment{" +
                "doctor=" + doctor +
                ", patient=" + patient +
                ", timeOfAppointment=" + timeOfAppointment +
                ", address='" + address + '\'' +
                '}';
    }

}
