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
    /** The appointment's office_id. */
    private String officeId;

    /**
     * Constructor for the Appointment class.
     * @param doctor The appointment's doctor.
     * @param patient The appointment's patient.
     * @param timeOfAppointment The appointment's date and time.
     * @param officeId The appointment's office_id.
     */
    public Appointment(Doctor doctor, Patient patient, LocalDateTime timeOfAppointment, String officeId) {
        this.doctor = doctor;
        this.patient = patient;
        this.timeOfAppointment = timeOfAppointment;
        this.officeId = officeId;
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
     * Returns the appointment's officeId.
     * @return The appointment's officeId.
     */
    public String getOfficeId() {
        return officeId;
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
     * Sets the appointment's officeId.
     * @param officeId The appointment's address.
     */
    public void setAddress(String officeId) {
        this.officeId = officeId;
    }

    /**
     * Returns a string representation of the appointment.
     * @return A string representation of the appointment.
     */
    @Override
    public String toString() {
        return "[Doctor: " + doctor + "][Patient: " + patient +
                "][When: " + timeOfAppointment + "][Where: " + officeId + ']';
    }

}
