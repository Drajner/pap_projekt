package edu.iipw.pap;

import java.time.LocalDateTime;

/**
 * A class representing an appointment.
 */
public class Appointment {
    /**
     * The appointment's id.
     */
    private int id;
    /**
     * The appointment's doctor.
     */
    private Doctor doctor;
    /**
     * The appointment's patient.
     */
    private Patient patient;
    /**
     * The appointment's date and time.
     */
    private LocalDateTime timeOfAppointment;
    /**
     * The appointment's office_id.
     */
    private int officeId;

    /**
     * Constructor for the Appointment class.
     *
     * @param id                The appointment's id.
     * @param doctor            The appointment's doctor.
     * @param patient           The appointment's patient.
     * @param timeOfAppointment The appointment's date and time.
     * @param officeId          The appointment's office_id.
     */
    public Appointment(int id, Doctor doctor, Patient patient, LocalDateTime timeOfAppointment, int officeId) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.timeOfAppointment = timeOfAppointment;
        this.officeId = officeId;
    }

    /**
     * Returns the appointment's id.
     *
     * @return The appointment's id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the appointment's id.
     *
     * @param id The appointment's id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the appointment's doctor.
     *
     * @return The appointment's doctor.
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Sets the appointment's doctor.
     *
     * @param doctor The appointment's doctor.
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Returns the appointment's patient.
     *
     * @return The appointment's patient.
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets the appointment's patient.
     *
     * @param patient The appointment's patient.
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Returns the appointment's date and time.
     *
     * @return The appointment's date and time.
     */
    public LocalDateTime getTimeOfAppointment() {
        return timeOfAppointment;
    }

    /**
     * Sets the appointment's date and time.
     *
     * @param timeOfAppointment The appointment's date and time.
     */
    public void setTimeOfAppointment(LocalDateTime timeOfAppointment) {
        this.timeOfAppointment = timeOfAppointment;
    }

    /**
     * Returns the appointment's officeId.
     *
     * @return The appointment's officeId.
     */
    public int getOfficeId() {
        return officeId;
    }

    /**
     * Sets the appointment's officeId.
     *
     * @param officeId The appointment's address.
     */
    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    /**
     * Returns a string representation of the appointment.
     *
     * @return A string representation of the appointment.
     */
    @Override
    public String toString() {
        return id + " [Doctor: " + doctor + "][Patient: " + patient +
                "][When: " + timeOfAppointment + "][Where: " + officeId + ']';
    }

}
